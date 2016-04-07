package com.net.toooen.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.testProject.ApplyTestProjectListRequest;
import com.net.toooen.api.request.testProject.ApplyTestProjectRequest;
import com.net.toooen.api.request.testProject.DelTestProjectFolderRequest;
import com.net.toooen.api.request.testProject.JoinTestProjectFolderRequest;
import com.net.toooen.api.request.testProject.ReportSelectRequest;
import com.net.toooen.api.request.testProject.ReportUserListRequest;
import com.net.toooen.api.request.testProject.TestProjectDetailRequest;
import com.net.toooen.api.request.testProject.TestProjectFolderListRequest;
import com.net.toooen.api.request.testProject.TestProjectListRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.service.TestProjectService;
import com.net.toooen.util.Constants;
import com.net.toooen.util.JsonUtil;

@Service
public class TestProjectServiceImpl implements TestProjectService {

	@Override
	public Response testProjectList(TestProjectListRequest req) {
		ResultResponse resp = new ResultResponse();
	    String sqlTp = "select id,tpName from test_project where isEnabled = true ";
		if (StringUtils.isNotEmpty(req.getKeyword())) {
			List<Record> records = null;
			sqlTp += " and id in (select tpId from test_project_hm_relation where hospId = ?) and tpName like ? order by createDate desc  limit ?,? ";
			records = Db.find(sqlTp, req.getHospId(),
					"%" + req.getKeyword() + "%", req.getPageIndex(),
					Constants.PAGE_NUM);

			if (null == records) {
				return Response.SUCCESS;
			} else {
				resp.getResultMap().put("testProjects",
						JsonUtil.getJsonObjByjfinalList(records));
				return resp;
			}

		} else {
		    //父菜单ID不能为空
		    if(null == req.getParentMenuId()){
		        return Response.ERROR_PARAM;
		    }
		    
			List<Record> records= null;
			List<Record> recordTp = null;
			
			//查询子节点
			String sql = "select tphm.id as id,tpm.menuName as childMenu from test_project_hosp_menu tphm left join test_project_menu tpm on tphm.childMenuId = tpm.id where tphm.hospId = ? and tphm.parentMenuId = ? and tphm.childMenuId is not null ";
			
			records = Db.find(sql,req.getHospId(),req.getParentMenuId());
			if(null == records){
			    //查询检测项目
                sqlTp += " and id in (select tpId from test_project_hm_relation where hmId in (select id from test_project_hosp_menu where hospId = ? and parentMenuId =? and childMenuId is null and isEnabled = true )) limit ?,? ";
                records = Db.find(sqlTp,req.getHospId(),req.getParentMenuId(),req.getPageIndex(),
                        Constants.PAGE_NUM);
                resp.getResultMap().put("testProjects",
						JsonUtil.getJsonObjByjfinalList(records));
			}else{
			    //查询检测项目
	            sqlTp += " and id in (select tpId from test_project_hm_relation where hmId = ?) limit ?,? ";
	            
				for(Record child : records){
					recordTp = Db.find(sqlTp,child.getInt("id"),req.getPageIndex(),
	                        Constants.PAGE_NUM);
					child.set("testProjects", JsonUtil.getJsonObjByjfinalList(recordTp));
				}
				resp.getResultMap().put("testProjectList",
						JsonUtil.getJsonObjByjfinalList(records));
			}

			return resp;
		}
	}

	@Override
	public Response testProjectDetail(TestProjectDetailRequest req) {
		String sql = "select id,tpName,reportDate,ciinicalSign,price,readType,reposrRead,sampleTo from test_project where id = ?";
		Record record = Db.findFirst(sql, req.getId());
		if (null == record) {
			return Response.ERROR_SERVER;
		} else {
			if(StringUtils.isNotEmpty(record.getStr("reposrRead"))){
				record.set("reposrRead", new Gson().fromJson(record.getStr("reposrRead"), new TypeToken<List<String>>() {}.getType()));
			}else{
				record.set("reposrRead" ,new ArrayList<String>());
			}
			
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("testProject",
					JsonUtil.getMapByJfinalRecord(record));
			return resp;
		}
	}

	@Override
	public Response applyTestProject(final ApplyTestProjectRequest req) {
		boolean isSuccess = false;

		isSuccess = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				// 保存申请检测项目记录
				Record record = new Record().set("doctorId", req.getUserId())
						.set("hospId", req.getHospId())
						.set("patientName", req.getPatientName())
						.set("gsHospital", req.getGsHospital())
						.set("gsMobile", req.getGsMobile())
						.set("gsDoctor", req.getGsDoctor())
						.set("gsTitle", req.getGsTitle())
						.set("totalPrice", req.getTotalPrice())
						.set("progress", 1) //已提交
						.set("createDate", new Date());
				Db.save("apply_testproject", record);

				double totalPrice = 0;
				Record tpdRecord = null;
				// 保存详细记录
				for (Integer tpfId : req.getTpfIds()) {
					tpdRecord = new Record();
					tpdRecord.set("atpId", record.getLong("id"));
					tpdRecord.set("tpfId", tpfId);
					Db.save("test_project_detail", tpdRecord);
					
					tpdRecord = Db.findFirst("select price from test_project where id = (select tpId from test_project_folder where id = ?)",tpfId);
					totalPrice = totalPrice + tpdRecord.getDouble("price");
					
					//更新申请夹状态
					Db.update("update test_project_folder set status = 1,tpPrice = ? where id = ?",tpdRecord.getDouble("price"),tpfId);
				}
				
				if(req.getTotalPrice() != totalPrice){
					return false;
				}
				
				return true;
			}
		});

		if (isSuccess) {
			return Response.SUCCESS;
		} else {
			return new Response(Response.CODE_FAIL,"数据出错或许检测项目总价不正确.");
		}
	}

	@Override
	public Response applyTestProjectList(ApplyTestProjectListRequest req) {
		List<Record> records = null;
		List<Record> recordDetails = null;
		StringBuilder sb = new StringBuilder(
				"SELECT id,patientName,gsHospital,gsTitle,gsDoctor,gsMobile,totalPrice,createDate from apply_testproject where doctorId = ?");
		if (StringUtils.isNotEmpty(req.getKeyword())) {
			sb.append(" and patientName like ? order by createDate desc limit ?,? ");
			records = Db.find(sb.toString(), req.getUserId(),
					"%" + req.getKeyword() + "%", req.getPageIndex(),
					Constants.PAGE_NUM);
		} else {
			sb.append(" order by createDate desc  limit ?,?");
			records = Db.find(sb.toString(), req.getUserId(),
					req.getPageIndex(), Constants.PAGE_NUM);
		}

		ResultResponse resp = new ResultResponse();
		String sql = "select id,tpName,price,sampleTo from test_project tp where id in (select tpId from test_project_folder where id in (select tpfId from test_project_detail where atpId = ?))";
		if (null != records && records.size() > 0) {
			for (Record record : records) {
				// 获取检测项目详情列表
				recordDetails = Db.find(sql, record.getInt("id"));
				record.set("testprojects",
						JsonUtil.getJsonObjByjfinalList(recordDetails));
			}
		}
		resp.getResultMap().put("applyTestProjectList",
				JsonUtil.getJsonObjByjfinalList(records));
		return resp;
	}

	@Override
	public Response delTpFolder(DelTestProjectFolderRequest req) {
		Db.deleteById("test_project_folder", "id", req.getId());
		return Response.SUCCESS;
	}

	@Override
	public Response tpFolderlist(TestProjectFolderListRequest req) {
		String sql = "select tpf.id as id,tpf.tpId as tpId,tpName,tp.price,tp.sampleTo from test_project_folder tpf left join test_project tp on tpf.tpId = tp.id where tpf.status = 0 and tpf.userId = ? and tp.hospId = ?";
		List<Record> records = Db.find(sql, req.getUserId(), req.getHospId());
		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("tpFolderList",
				JsonUtil.getJsonObjByjfinalList(records));
		return resp;
	}

	@Override
	public Response joinTpFolder(final JoinTestProjectFolderRequest req) {
		
		boolean flag = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				Record record = null;

				for (Integer tp : req.getTpIds()) {
					record = new Record().set("tpId", tp)
							.set("userId", req.getUserId())
							.set("status", 0); //申请状态
					Db.save("test_project_folder", record);
				}
				return true;
			}
		});
		
		if(flag){
			return Response.SUCCESS;
		}else{
			return Response.ERROR_SERVER;
		}

	}

	@Override
	public Response reportSelect(ReportSelectRequest req) {
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"SELECT atp.id,atp.reportResult,tp.tpName,atp.reportFile,DATE_FORMAT(atp.createDate,'%Y-%m-%d %H-%i-%s') AS createDate from apply_testproject atp ");
		sb.append(" LEFT JOIN test_project_detail tpd ON atp.id = tpd.atpId ");	
		sb.append(" LEFT JOIN test_project_folder tpf ON tpd.tpfId = tpf.id ");	
		sb.append(" LEFT JOIN test_project tp ON tpf.tpId = tp.id ");	
		sb.append(" where doctorId = ? and patientName = ?  order by createDate desc limit ?,? ");	
		records = Db.find(sb.toString(), req.getUserId(),
					req.getPatientName(), req.getPageIndex(),
					Constants.PAGE_NUM);
		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("reportList",
				JsonUtil.getJsonObjByjfinalList(records));
		return resp;
	}
	
	@Override
	public Response testProjectHspital() {
		List<Record> records = null;
		String sql = "select id,priovinceName as name,fullName from area where countryId is null and cityId is null and type = 1  order by createDate ";
		String sqlMenu = "select id,menuName from test_project_menu where id in (select parentMenuId from test_project_hosp_menu where hospId = ?)";
		records = Db.find(sql);

		if (null == records) {
			return Response.SUCCESS;
		} else {
		    //获取检测省份医院SQL
			sql = "select id,hospitalName,hospitalLogo from hospital where type = 1 and fullAreaId like ?";
			List<Record> hospListRecord = null;
			List<Record> menuListRecord = null;
			for(Record areaRecord : records){
				hospListRecord = Db.find(sql,"%" + areaRecord.getInt("id") + "%");
				
				for(Record hospRecord : hospListRecord){
				    menuListRecord = Db.find(sqlMenu,hospRecord.getInt("id"));
				    hospRecord.set("menus", JsonUtil.getJsonObjByjfinalList(menuListRecord));
				}
				
				areaRecord.set("hospitalList", JsonUtil.getJsonObjByjfinalList(hospListRecord));
			}
			
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("areaLists",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

	@Override
	public Response reportUserLis(ReportUserListRequest req) {
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"SELECT DISTINCT patientName from apply_testproject where doctorId = ?");
		if (StringUtils.isNotEmpty(req.getKeyword())) {
			sb.append(" and patientName like ? order by createDate desc");
			records = Db.find(sb.toString(), req.getUserId(),
					"%" + req.getKeyword() + "%");
		} else {
			sb.append(" order by createDate desc");
			records = Db.find(sb.toString(), req.getUserId());
		}

		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("patientList",
				JsonUtil.getJsonObjByjfinalList(records));
		return resp;
	}


}