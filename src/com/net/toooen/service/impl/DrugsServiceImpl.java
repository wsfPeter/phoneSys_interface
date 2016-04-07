package com.net.toooen.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.drugs.DrugsDetailRequest;
import com.net.toooen.api.request.drugs.DrugsListRequest;
import com.net.toooen.api.request.drugs.DrugsSeekAddRequest;
import com.net.toooen.api.request.drugs.DrugsSeekDetailRequest;
import com.net.toooen.api.request.drugs.DrugsSeekListRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.service.DrugsService;
import com.net.toooen.util.Constants;
import com.net.toooen.util.JsonUtil;

@Service
public class DrugsServiceImpl implements DrugsService {

	@Override
	public Response drigsSeekList(DrugsSeekListRequest req) {
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"select id,drugsName,checkState from drugs_seek where userId = ? ");
		if (StringUtils.isNotEmpty(req.getKeyword())) {
			sb.append(" and drugsName like ? order by createDate desc limit ?,? ");
			records = Db.find(sb.toString(), req.getUserId(), "%"+req.getKeyword()+"%",
					req.getPageIndex(), Constants.PAGE_NUM);
		} else {
			sb.append(" order by createDate desc limit ?,?");
			records = Db.find(sb.toString(), req.getUserId(),req.getPageIndex(), Constants.PAGE_NUM);
		}

		if (null == records) {
			return Response.SUCCESS;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("drugsList",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

	@Override
	public Response drigsList(DrugsListRequest req) {
		List<Record> records = null;
		StringBuilder sb = new StringBuilder(
				"select id,drugsName,drugsPic from drugs");
		if (StringUtils.isNotEmpty(req.getKeyword())) {
			sb.append(" where drugsName like ? order by createDate desc limit ?,? ");
			records = Db.find(sb.toString(), req.getDtId(),"%"+req.getKeyword()+"%",
					req.getPageIndex(), Constants.PAGE_NUM);
		} else {
		    sb.append(" order by createDate desc limit ?,? ");
		    records = Db.find(sb.toString(), req.getPageIndex(),
		            Constants.PAGE_NUM);
		}

		if (null == records) {
			return Response.SUCCESS;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("drugsList",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

	@Override
	public Response drigsDetail(DrugsDetailRequest req) {
		String sql = "select id,dtId,drugsName,drugsPic,drugsExplain,remark from drugs where isEnabled = true and id = ?";
		Record  record = Db.findFirst(sql,req.getId());
		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("drugs",
				JsonUtil.getMapByJfinalRecord(record));
		return resp;
	}

	@Override
	public Response drigsSeekAdd(DrugsSeekAddRequest req) {
		Record record = new Record()
			.set("userId", req.getUserId())
			.set("contact", req.getContact())
			.set("contactPhone", req.getContactPhone())
			.set("drugsName", req.getDrugsName())
			.set("content", req.getContent())
			.set("createDate", new Date());
		if(StringUtils.isNotEmpty(req.getRemark())){
			record.set("remark", req.getRemark());
		}
		Db.save("drugs_seek" , record);
		return Response.SUCCESS;
	}

	@Override
	public Response drigsSeekDetail(DrugsSeekDetailRequest req) {
		String sql = "select id,contact,contactPhone,drugsName,content,checkState,remark from drugs_seek where id = ? order by createDate desc ";
		Record  record = Db.findFirst(sql,req.getId());
		ResultResponse resp = new ResultResponse();
		resp.getResultMap().put("drugsSeek",
				JsonUtil.getMapByJfinalRecord(record));
		return resp;
	}
	
}