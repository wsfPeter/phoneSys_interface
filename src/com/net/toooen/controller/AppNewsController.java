package com.net.toooen.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.net.toooen.api.request.Request;
import com.net.toooen.api.request.news.NewsListRequest;
import com.net.toooen.api.request.news.NewsSelectNumRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.entity.InterfaceKey;
import com.net.toooen.service.NewsService;
import com.net.toooen.util.Constants;

@Controller
@RequestMapping("/app/news")
public class AppNewsController extends BaseController {

	@Autowired
	protected NewsService newsService;

	private static final Logger logger = LoggerFactory
			.getLogger(AppNewsController.class);

	@RequestMapping(value = "/service.do")
	public void service(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String param = request.getParameter(InterfaceKey.text);

		long start = System.currentTimeMillis();
		logger.info("json request >>> " + param);

		Response resp = null;
		Gson gson = new Gson();
		try {
			Request req = gson.fromJson(param, Request.class);
			if (req != null) {
				switch (req.getMsgId().toUpperCase()) {
				// 资讯列表
				case Request.NEWS_LIST:
					resp = newsList(param);
					break;
				// 资讯查看个数
				case Request.NEWS_SELECTNUM:
					resp = newsSelectNum(param);
					break;
				default:
					resp = Response.ERROR_NOT_METHOD;
					break;
				}
			} else {
				resp = Response.ERROR_PARAM;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp = Response.ERROR_SERVER;
		}

		if (resp != null) {
			try {
				String result = gson.toJson(resp);
				response.getWriter().write(result);
				response.getWriter().close();

				long end = System.currentTimeMillis();
				logger.info("json response >>> 接口耗时：" + (end - start) + " >>> "
						+ result);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}


	/**
	 * @Title: newsList
	 * @Description: 资讯列表
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response newsList(String param) {
		Gson gson = new Gson();
		NewsListRequest req = gson.fromJson(param, NewsListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		// 是否置顶和资讯类型不能同时为空
		if (null == req.getIsTop() && null == req.getNewsType()) {
			return Response.ERROR_PARAM;
		}

		// 是否置顶和资讯类型不能同时不为空
		if (null != req.getIsTop() && null != req.getNewsType()) {
			return Response.ERROR_PARAM;
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);
		return newsService.newsList(req);
	}
	
	/**
	 * 资讯查看次数
	 * 
	 * @param param
	 * @return Response
	 */
	private Response newsSelectNum(String param) {
		Gson gson = new Gson();
		NewsSelectNumRequest req = gson.fromJson(param,
				NewsSelectNumRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;

		}

		return newsService.newsSelectNum(req);
	}
}
