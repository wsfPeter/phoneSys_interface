package com.net.toooen.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.AreaListRequest;
import com.net.toooen.api.request.BackPasswordRequest;
import com.net.toooen.api.request.CaptchaRequest;
import com.net.toooen.api.request.HospitalListRequest;
import com.net.toooen.api.request.LoginRequest;
import com.net.toooen.api.request.RegisterRequest;
import com.net.toooen.api.request.ReplaceMobileRequest;
import com.net.toooen.api.request.SMSRequest;
import com.net.toooen.api.request.VersionRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.cache.CacheManage;
import com.net.toooen.service.AppInterfaceService;
import com.net.toooen.util.ALiDaYuMessageUtil;
import com.net.toooen.util.Constants;
import com.net.toooen.util.JsonUtil;
import com.net.toooen.util.SystemUtil;

@Service
public class AppInterfaceServiceImpl implements AppInterfaceService {

	private static final Logger logger = LoggerFactory
			.getLogger(AppInterfaceServiceImpl.class);

	@Override
	public Response register(RegisterRequest request) {
		Record record = null;
		// 用户名唯一性校验
		String sql = "select count(*) as num from user where nickName = ?";
		record = Db.findFirst(sql, request.getNickName());
		if (record.getLong("num") > 0) {
			return new Response(Response.CODE_FAIL, "昵称已存在.");
		}

		record = new Record();
		record.set("userType", request.getUserType())
				.set("nickName", request.getNickName())
				.set("userName", request.getNickName())
				.set("password", request.getPassword())
				.set("mobile", request.getMobile())
				.set("name", request.getName()).set("createDate", new Date())
				.set("isEnabled", true);

		// 医生注册
		if (request.getUserType() == 1) {
			record.set("workunit", request.getWorkunit())
					.set("deptPhone", request.getDeptPhone())
					.set("doctorCode", request.getDoctorCode());
			if (null != request.getHospId()) {
				record.set("hospId", request.getHospId());
			}
			// 设置用户注册需要审核.0未审核,1为审核失败,3为审核通过
			record.set("checkState", 0);
		} else if (request.getUserType() == 0) {
			// 患者直接通过审核
			record.set("checkState", 3);
		}

		Db.save("user", record);
		return Response.SUCCESS;
	}

	@Override
	public Response login(LoginRequest request) {
		Record record = null;
		StringBuilder sb = new StringBuilder(
				"select id,name,nickName,mobile,txPic,email,checkState,isEnabled ");

		// 登录查询时,要根据用户的类型和是否启用状态，同时昵称和手机号可以登录
		sb.append(" from user where (nickName = ? or mobile = ?) and password = ?  and userType = ?");
		record = Db.findFirst(sb.toString(), request.getLoginName(),
				request.getLoginName(), request.getPassword(),
				request.getLoginType());
		if (null == record) {
			return new Response(Response.CODE_FAIL, "用户名或密码错误.");
		} else {
			// 判断是否已通过审核
			if (record.getInt("checkState") != 3) {
				return new Response(Response.CODE_FAIL, "您的账号正在审核中,请联系客服.");
			} else if (!record.getBoolean("isEnabled")) {
				return new Response(Response.CODE_FAIL, "您的账号已禁用,请联系客服.");
			}

			Record updateRecord = new Record();
			updateRecord.set("id", record.getInt("id"));

			record.set("version", request.getVersion());
			updateRecord.set("systemType", request.getSystemType());
			updateRecord.set("version", request.getVersion());
			updateRecord.set("lastLoginDate", new Date());
			if (StringUtils.isNotEmpty(request.getPushId())) {
				record.set("pushId", request.getPushId());
				updateRecord.set("pushId", request.getPushId());
			}
			Db.update("user", updateRecord);

			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("userInfo",
					JsonUtil.getMapByJfinalRecord(record));

			return resp;
		}
	}

	@Override
	public Response sendSMS(SMSRequest request) {
		try {
			String mobile = request.getMobile();
			Integer type = request.getType();

			// 注册
			if (type == Constants.CAPTCHA_TYPE_REGISTER) {
				String sql = "select count(*) as num from user where mobile = ? and isEnabled = true";
				Record record = Db.findFirst(sql, mobile);
				if (record.getLong("num") == 0) {
					if (!CacheManage.getPhonegap(mobile)) {
						return Response.SUCCESS;
					} else if (!CacheManage.getPhonedayCache(mobile)) {
						return Response.SUCCESS;
					}
				} else {
					return new Response(Response.CODE_FAIL, "该手机号已被注册。");
				}

				if (!CacheManage.getPhonegap(mobile)) {
					return new Response(Response.CODE_FAIL, "每条验证码发送间隔为90秒。");
				} else if (!CacheManage.getPhonedayCache(mobile)) {
					return new Response(Response.CODE_FAIL, "今日已累计发送10次验证码。");
				}
			}

			// 生成验证码
			String captcha = SystemUtil.randString(4);

			boolean flag = ALiDaYuMessageUtil.sendVarifyMessage(false, mobile,
					captcha, type);
			if (flag) {
				CacheManage.saveOrUpdateVerifyCode(mobile, captcha);
				return Response.SUCCESS;
			} else {
				return new Response(Response.CODE_FAIL, "验证码发送失败。");
			}
		} catch (Exception e) {
			logger.error("APP003", e);
			return Response.ERROR_SERVER;
		}
	}

	@Override
	public Response checkCaptcha(CaptchaRequest request) {
		try {
			String captcha = CacheManage
					.getCacheVerifyCode(request.getMobile());

			if (StringUtils.isEmpty(captcha)) {
				return new Response(Response.CODE_FAIL, "验证码未获取或已失效。");
			}

			if (request.getCaptcha().equals(captcha)) {
				CacheManage.removeVerifyCode(request.getMobile());
				return Response.SUCCESS;
			} else {
				return new Response(Response.CODE_FAIL, "验证码输入错误。");
			}

		} catch (Exception e) {
			logger.error("APP004", e);
			return Response.ERROR_SERVER;
		}
	}

	@Override
	public Response backPassword(BackPasswordRequest request) {
		Record record = null;
		String sql = "select id from user where mobile = ?";
		record = Db.findFirst(sql, request.getMobile());

		if (null == record) {
			return new Response(Response.CODE_FAIL, "您所填写的手机号未在系统注册，请重新输入或注册。");
		} else {
			record.set("password", request.getPassword());
			Db.update("user", record);
			return Response.SUCCESS;
		}

	}

	@Override
	public Response versionUpgrade(VersionRequest request) {
		// 查询最新版本号
		String sql = "select versionName,versionType,downLoadAddr,length,isNeed,channel,content from version where versionType = ? and channel = ? and versionName > ? and versionGroup = ?";
		Record record = Db.findFirst(sql, request.getVersionType(),
				request.getChannel(),request.getVersionName(),request.getVersionGroup());
		if (null == record) {
		    return new Response(Response.CODE_FAIL,"没有最新版本.");
		} else {
			ResultResponse rep = new ResultResponse();
			rep.getResultMap().put("versionInfo",
					JsonUtil.getMapByJfinalRecord(record));
			return rep;
		}
	}


	@Override
	public Response replaceMobile(ReplaceMobileRequest req) {
		String sql = "select id from user where mobile = ? and password = ?";
		Record record = Db
				.findFirst(sql, req.getOldMobile(), req.getPassword());
		if (null == record) {
			return new Response(Response.CODE_FAIL, "旧手机号或密码不正确.");
		} else {
			sql = "update user set mobile = ? where id = ?";
			Db.update(sql, req.getNewMobile(), record.getInt("id"));
			return Response.SUCCESS;
		}
	}

	@Override
	public Response diseaseList() {
		String sql = "select id,diseaseName from disease where isEnabled = true order by createDate desc ";
		List<Record> records = Db.find(sql);
		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("diseaseLists",
				JsonUtil.getJsonObjByjfinalList(records));
		return resp;
	}

	@Override
	public Response areaList(AreaListRequest req) {
		List<Record> records = null;
		String sql = null;

		if (null != req.getPriovinceId()) {
			sql = "select cityId as id,cityName as name,fullName from area where priovinceId = ? and cityId is not null and countryId is null order by createDate ";
			records = Db.find(sql, req.getPriovinceId());
		} else if (null != req.getCityId()) {
			sql = "select countryId as id,countryName as name,fullName from area where cityId = ? and countryId is not null  order by createDate ";
			records = Db.find(sql, req.getCityId());
		} else {
			// 都为空,返回省份列表
			sql = "select priovinceId as id,priovinceName as name,fullName from area where cityId is null and countryId is null order by createDate";
			records = Db.find(sql);
		}

		if (null == records) {
			return Response.SUCCESS;
		} else {
			if(req.getGetHospital()){
				sql = "select id,hospitalName,hospitalLogo from hospital where areaId = ?";
				List<Record> hospListRecord = null;
				for(Record areaRecord : records){
					hospListRecord = Db.find(sql,areaRecord.getInt("id"));
					areaRecord.set("hospitalList", JsonUtil.getJsonObjByjfinalList(hospListRecord));
				}
			}
			
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("areaLists",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

	@Override
	public Response hospitalList(HospitalListRequest req) {
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"select hosp.id as id,hosp.areaId as areaId,ar.fullName,hospitalName,hospitalLogo from hospital hosp left join area ar on hosp.areaId = ar.id ");
		if (null != req.getAreaId() && StringUtils.isNotEmpty(req.getKeyword())) {
		    if(req.getIsPage()){
		        sb.append(" where hosp.areaId = ? and hosp.hospitalName like ? order by hosp.createDate limit ?,? ");
		        records = Db.find(sb.toString(), req.getAreaId(), "%"+req.getKeyword()+"%",req.getPageIndex(),Constants.PAGE_NUM);
		    }else{
    			sb.append(" where hosp.areaId = ? and hosp.hospitalName like ? order by hosp.createDate ");
    			records = Db.find(sb.toString(), req.getAreaId(), "%"+req.getKeyword()+"%");
		    }
		} else if (null != req.getAreaId()) {
		    if(req.getIsPage()){
                sb.append(" where hosp.areaId = ? order by hosp.createDate limit ?,? ");
                records = Db.find(sb.toString(), req.getAreaId(),req.getPageIndex(),Constants.PAGE_NUM);
            }else{
                sb.append(" where hosp.areaId = ? order by hosp.createDate ");
                records = Db.find(sb.toString(), req.getAreaId());
            }
		    
		} else if (StringUtils.isNotEmpty(req.getKeyword())) {
		    if(req.getIsPage()){
		        sb.append(" where hosp.hospitalName like ? order by hosp.createDate limit ?,? ");
		        records = Db.find(sb.toString(), "%"+req.getKeyword()+"%",req.getPageIndex(),Constants.PAGE_NUM);
		    }else{
		        sb.append(" where hosp.hospitalName like ? order by hosp.createDate ");
		        records = Db.find(sb.toString(), "%"+req.getKeyword()+"%");
		    }
		} else {
		    if(req.getIsPage()){
		        sb.append(" order by hosp.createDate limit ?,?");
		        records = Db.find(sb.toString(),req.getPageIndex(),Constants.PAGE_NUM);
		    }else{
		        sb.append(" order by hosp.createDate ");
		        records = Db.find(sb.toString());
		    }
		}

		if (null == records) {
			return Response.SUCCESS;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("hospitalLists",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

	
}