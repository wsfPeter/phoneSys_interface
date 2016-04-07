package com.net.toooen.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.applyConsultation.ApplyConsultationRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationDetailRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationRecordRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationUpdateRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.service.ApplyConsultationService;
import com.net.toooen.util.Constants;
import com.net.toooen.util.JsonUtil;

@Service
public class ApplyConsultationServiceImpl implements ApplyConsultationService {
	
	@Override
	public Response applyConsultation(ApplyConsultationRequest req) {
		Record record = new Record().set("consType", req.getConsType())
				.set("attendDoctorId", req.getAttendDoctorId())
				.set("patientName", req.getPatientName())
				.set("sex", req.getSex()).set("age", req.getAge())
				.set("diseases", req.getDiseases())
				.set("applyBook", req.getApplyBook())
				.set("illnessUrls", req.getIllnessUrls()).set("adConfrim", 0)
				.set("progress", 0).set("createDate", new Date());

		// 判断传递参数中ID是否有值
		if (null != req.getPatientId()) {
			record.set("patientId", req.getPatientId());
		}
		if (null != req.getConsExpertId()) {
			record.set("consExpertId", req.getConsExpertId());
		}
		
		Record recordHosp = Db.findFirst("select hospitalName from hospital where id = (select hospitalId from user where id = ?)",req.getAttendDoctorId());
		record.set("title", recordHosp.getStr("hospitalName")+"+"+req.getPatientName());

		if (Db.save("apply_consultation", record)) {
			return Response.SUCCESS;
		} else {
			return Response.ERROR_SERVER;
		}
	}

	@Override
	public Response consultationRecord(ConsultationRecordRequest req) {
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"select id,patientName,hospName,consType from apply_consultation ");
		if (1 == req.getRecordType()) {
			sb.append(" where attendDoctorId = ? order by createDate desc  limit ?,?");
			records = Db.find(sb.toString(), req.getUserId(),req.getPageIndex(),Constants.PAGE_NUM);
		} else if (2 == req.getRecordType()) {
			sb.append(" where consExpertId = ?  order by createDate desc limit ?,?");
			records = Db.find(sb.toString(), req.getUserId(),req.getPageIndex(),Constants.PAGE_NUM);
		}

		if (null == records) {
			return Response.SUCCESS;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("consRecords",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

	@Override
	public Response consultationDetail(ConsultationDetailRequest req) {
		StringBuilder sb = new StringBuilder(
				"select appCons.id as id,patientName,hospName,u.name as doctorName,u.mobile as doctorMobile,appCons.sex as sex,appCons.age as age,appCons.diseases as diseases,startDiagResult,expertName,consType,agreeBook,applyBook,illnessUrls,idCardPic,consResult,adConfrim,progress from apply_consultation appCons ");
		sb.append(" left join user u on appCons.attendDoctorId = u.id where appCons.id = ?");
		Record record = Db.findFirst(sb.toString(), req.getId());
		if (null == record) {
			return Response.ERROR_SERVER;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("consInfo",
					JsonUtil.getMapByJfinalRecord(record));
			return resp;
		}
	}

	@Override
	public Response consultationUpdate(ConsultationUpdateRequest req) {
		Record record = new Record().set("id", req.getId());
		if (null != req.getConsExpertId()) {
			record.set("consExpertId", req.getConsExpertId());
		}
		if (StringUtils.isNotEmpty(req.getExpertName())) {
			record.set("expertName", req.getExpertName());
		}
		if (StringUtils.isNotEmpty(req.getStartDiagResult())) {
			record.set("startDiagResult", req.getStartDiagResult());
		}
		if (StringUtils.isNotEmpty(req.getAgreeBook())) {
			record.set("agreeBook", req.getAgreeBook());
		}
		if (StringUtils.isNotEmpty(req.getApplyBook())) {
			record.set("applyBook", req.getApplyBook());
		}
		if (StringUtils.isNotEmpty(req.getIllnessUrls())) {
			record.set("illnessUrls", req.getIllnessUrls());
		}
		if (StringUtils.isNotEmpty(req.getConsResult())) {
			record.set("consResult", req.getConsResult());
		}
		if (null != req.getProgress()) {
			record.set("progress", req.getProgress());
		}

		if (Db.update("apply_consultation", "id", record)) {
			return Response.SUCCESS;
		} else {
			return Response.ERROR_SERVER;
		}
	}

	

}