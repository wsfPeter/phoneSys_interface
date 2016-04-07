package com.net.toooen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.news.NewsListRequest;
import com.net.toooen.api.request.news.NewsSelectNumRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.service.NewsService;
import com.net.toooen.util.Constants;
import com.net.toooen.util.JsonUtil;

@Service
public class NewsServiceImpl implements NewsService {

	@Override
	public Response newsList(NewsListRequest req) {
		List<Record> records = null;
		String sql = "select id,title,titleImage,newsUrl,selectNum,DATE_FORMAT(createDate,'%Y-%m-%d') AS createDate from news where isPulish = true and  newsGroup in (?,2) ";
		if (null != req.getIsTop()) {
			sql += " and isTop = ? order by createDate desc limit ?,? ";
			records = Db.find(sql, req.getNewsGroup(), req.getIsTop(),req.getPageIndex(),Constants.PAGE_NUM);
		} else if (null != req.getNewsType()) {
			sql += " and newsType = ? order by createDate desc  limit ?,?";
			records = Db.find(sql, req.getNewsGroup(), req.getNewsType(),req.getPageIndex(),Constants.PAGE_NUM);
		} else {
			return Response.ERROR_SERVER;
		}

		if (null == records) {
			return Response.SUCCESS;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("newsLists",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}
	
	@Override
	public Response newsSelectNum(NewsSelectNumRequest req) {
		Record record = null;
		record = Db.findFirst("select count(*) as num from news_view where newsId = ? and userId = ?",req.getNewsId(),req.getUserId());
		if(record.getLong("num") == 0){
			record = new Record().set("newsId", req.getNewsId())
					.set("userId", req.getUserId())
					.set("createDate", new Date());
			Db.save("news_view", record);
		}
		
		return Response.SUCCESS;
	}
	
}