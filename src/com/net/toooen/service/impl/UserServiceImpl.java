package com.net.toooen.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.user.DiseaseUserListRequest;
import com.net.toooen.api.request.user.ExpertDetailRequest;
import com.net.toooen.api.request.user.ExpertListRequest;
import com.net.toooen.api.request.user.HospUserListRequest;
import com.net.toooen.api.request.user.ModifyUserInfoRequest;
import com.net.toooen.api.request.user.MyRelationNumRequest;
import com.net.toooen.api.request.user.RelationExistsRequest;
import com.net.toooen.api.request.user.UserDetailRequest;
import com.net.toooen.api.request.user.UserListRequest;
import com.net.toooen.api.request.user.UserRelationListRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.service.UserService;
import com.net.toooen.util.Constants;
import com.net.toooen.util.JsonUtil;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Response modifyUserInfo(ModifyUserInfoRequest req) {
		Record record = new Record();
		for(String property : req.getParamMap().keySet()){
			record.set(property, req.getParamMap().get(property));
		}
		
		record.set("id", req.getUserId());
		Db.update("user", "id", record);
		//TODO 如果修改的是医生信息,专家信息也修改
		return Response.SUCCESS;
	}

	@Override
	public Response expertList(ExpertListRequest req) {
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"select id,name,txPic,workunit from expert_data ");
		if (null != req.getExpertBeilType()
				&& StringUtils.isNotEmpty(req.getKeyword())) {
				sb.append(" where expertBeilType in (?,3) and name like ? order by createDate desc  limit ?,?");
				records = Db.find(sb.toString(), req.getExpertBeilType(),
						"%"+req.getKeyword()+"%",req.getPageIndex(),Constants.PAGE_NUM);
		} else if (null != req.getExpertBeilType()) {
				sb.append(" where expertBeilType in (?,3) order by createDate desc  limit ?,? ");
				records = Db.find(sb.toString(), req.getExpertBeilType(),req.getPageIndex(),Constants.PAGE_NUM);
		} else if (StringUtils.isNotEmpty(req.getKeyword())) {
				sb.append(" where name like ? order by createDate desc  limit ?,? ");
				records = Db.find(sb.toString(), "%"+req.getKeyword()+"%",req.getPageIndex(),Constants.PAGE_NUM);
		} else {
				sb.append(" order by createDate desc limit ?,?");
				records = Db.find(sb.toString(), req.getPageIndex(),Constants.PAGE_NUM);
		}

		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("expertLists",
				JsonUtil.getJsonObjByjfinalList(records));
		return resp;
	}
	
	@Override
	public Response userList(UserListRequest req) {
		ResultResponse resp = new ResultResponse();
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"select id,name,sex,age,mobile,txPic,workunit,disease from user where checkState = 3 and isEnabled = true ");

		if(null != req.getIsRelation()){
			if(req.getIsRelation()){
				if(req.getUserType() == 0){
					sb.append(" and id in (select doctorId from patient_doctor where patientId  = ?) ");
				}else if(req.getUserType() == 1){
					sb.append(" and id in (select patientId from patient_doctor where doctorId  = ?) ");
				}
			}else if(!req.getIsRelation()){
				if(req.getUserType() == 0){
					sb.append(" and id not in (select doctorId from patient_doctor where patientId  = ?) ");
				}else if(req.getUserType() == 1){
					sb.append(" and id not in (select patientId from patient_doctor where doctorId  = ?) ");
				}
			}
		}
		
		if (StringUtils.isNotEmpty(req.getKeyword()) && null != req.getIsRelation()) {
			sb.append(" and name like ? order by createDate desc limit ?,? ");
			    records = Db.find(sb.toString(),req.getUserId(),
			            "%"+req.getKeyword()+"%",req.getPageIndex(),Constants.PAGE_NUM);
		}else if(null != req.getIsRelation()){
			sb.append(" order by createDate desc limit ?,? ");
			records = Db.find(sb.toString(),req.getUserId(),req.getPageIndex(),Constants.PAGE_NUM);
		}else if(StringUtils.isNotEmpty(req.getKeyword())){
			sb.append(" and userType = ? and name like ? order by createDate desc limit ?,? ");
		    records = Db.find(sb.toString(), req.getUserType(),
		            "%"+req.getKeyword()+"%",req.getPageIndex(),Constants.PAGE_NUM);
		} else {
			sb.append(" and userType = ? order by createDate desc  limit ?,? ");
            records = Db.find(sb.toString(), req.getUserType(),req.getPageIndex(),Constants.PAGE_NUM);
		}

		if (null == records) {
			return Response.SUCCESS;
		} else {
			resp.getResultMap().put("userLists",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

	@Override
	public Response userDetail(UserDetailRequest req) {
		Record record = null;
		StringBuilder sb = new StringBuilder(
				"select id,userName,name,nickName,idCard,mobile,age,txPic,sex,email,birth,address,hometown,ispush,brief,disease,deptphone");
		if (req.getUserType() == 0) {
			sb.append(",mediHistory,allergyDrug,firstContact,firstContactMobile from user where id = ?");
			record = Db.findFirst(sb.toString(), req.getUserId());
		} else if (req.getUserType() == 1) {
			sb.append(",title,workunit,curetime,diseaseDesc,expertBeilType,department,doctorCode,works  from user where id = ?");
			record = Db.findFirst(sb.toString(), req.getUserId());
		}

		if (null == record) {
			return Response.SUCCESS;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("userInfo",
					JsonUtil.getMapByJfinalRecord(record));
			return resp;
		}

	}

	@Override
	public Response relationExists(RelationExistsRequest req) {
		String sql = "select count(*) as num from patient_doctor where patientId = ? and doctorId = ?";
		Record record = Db.findFirst(sql, req.getPatientId(), req.getDoctorId());
		ResultResponse resp = new ResultResponse();
		if (record.getLong("num") > 0) {
			resp.getResultMap().put("result", true);
		} else {
			resp.getResultMap().put("result", false);
		}
		return resp;
	}

	@Override
	public Response myRelationNum(MyRelationNumRequest req) {
		Record  record = null;
		String sql = "select count(*) as num from patient_doctor";
		if(req.getUserType() == 0){
			sql += " where patientId = ?";
			record = Db.findFirst(sql,req.getUserId());
		}else if(req.getUserType() == 1){
			sql += " where doctorId = ?";
			record = Db.findFirst(sql,req.getUserId());
		}
		
		if(null == record){
			return Response.ERROR_SERVER;
		}
		
		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("num",record.getLong("num"));
		return resp;
	}

	@Override
	public Response expertDetail(ExpertDetailRequest req) {
		Record record = Db.findById("expert_data", "id",req.getExpertId());
		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("expert",
				JsonUtil.getMapByJfinalRecord(record));
		return resp;
	}

	@Override
	public Response relationUserList(UserRelationListRequest req) {
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"select id,name,sex,mobile,txPic,workunit,disease from user where checkState = 3 and isEnabled = true and id in ");

		if (0 == req.getUserType()) {
			sb.append(" (select doctorId from patient_doctor where patientId  = ?) ");
			if (StringUtils.isNotEmpty(req.getKeyword())) {
				sb.append(" and name like ? order by createDate desc limit ?,? ");
				records = Db.find(sb.toString(), req.getUserId(),
						"%"+req.getKeyword()+"%",req.getPageIndex(),Constants.PAGE_NUM);
			} else {
				sb.append(" order by createDate desc  limit ?,? ");
				records = Db.find(sb.toString(), req.getUserId(),req.getPageIndex(),Constants.PAGE_NUM);
			}
		} else if (1 == req.getUserType()) {
			sb.append(" (select patientId from patient_doctor where doctorId  = ?) ");
			if (StringUtils.isNotEmpty(req.getKeyword())) {
				sb.append(" and name like ? order by createDate desc limit ?,? ");
				records = Db.find(sb.toString(), req.getUserId(),
						"%"+req.getKeyword()+"%",req.getPageIndex(),Constants.PAGE_NUM);
			} else {
				sb.append(" order by createDate desc limit ?,? ");
				records = Db.find(sb.toString(), req.getUserId(),req.getPageIndex(),Constants.PAGE_NUM);
			}
		}

		if (null == records) {
			return Response.SUCCESS;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("userLists",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

    @Override
    public Response hospUserList(HospUserListRequest req) {
        List<Record> records = null;
        StringBuilder sb = new StringBuilder(
                "select id,name,sex,age,mobile,txPic,workunit,disease from user where  checkState = 3 and isEnabled = true and userType = ? and hospId = ? order by createDate limit ?,? ");

        records = Db.find(sb.toString(),req.getUserType(),req.getHospId(),req.getPageIndex(),Constants.PAGE_NUM);
        if (null == records) {
            return Response.SUCCESS;
        } else {
            ResultResponse resp = new ResultResponse();
            resp.getResultMap().put("userLists",
                    JsonUtil.getJsonObjByjfinalList(records));
            return resp;
        }
    }

    @Override
    public Response diseaseUserList(DiseaseUserListRequest req) {
        List<Record> records = null;
        StringBuilder sb = new StringBuilder(
                "select id,name,sex,age,mobile,txPic,workunit,disease from user where  checkState = 3 and isEnabled = true and userType = ? and disease like ? order by createDate limit ?,? ");

        records = Db.find(sb.toString(),req.getUserType(),"%"+req.getDiseaseName()+"%",req.getPageIndex(),Constants.PAGE_NUM);
        if (null == records) {
            return Response.SUCCESS;
        } else {
            ResultResponse resp = new ResultResponse();
            resp.getResultMap().put("userLists",
                    JsonUtil.getJsonObjByjfinalList(records));
            return resp;
        }
    }

	
}