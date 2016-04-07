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
import com.net.toooen.api.request.drugs.DrugsDetailRequest;
import com.net.toooen.api.request.drugs.DrugsListRequest;
import com.net.toooen.api.request.drugs.DrugsSeekAddRequest;
import com.net.toooen.api.request.drugs.DrugsSeekDetailRequest;
import com.net.toooen.api.request.drugs.DrugsSeekListRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.entity.InterfaceKey;
import com.net.toooen.service.DrugsService;
import com.net.toooen.util.Constants;

@Controller
@RequestMapping("/app/drugs")
public class AppDrugsController extends BaseController {

	@Autowired
	protected DrugsService drugsService;

	private static final Logger logger = LoggerFactory.getLogger(AppDrugsController.class);

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
				// 药品求助列表
				case Request.DRIGS_SEEK_LIST:
					resp = drigsSeekList(param);
					break;
				// 药品列表
				case Request.DRIGS_LIST:
					resp = drigsList(param);
					break;
				// 药品详情
				case Request.DRIGS_DETAIL:
					resp = drigsDetail(param);
					break;
				// 药品求助添加
				case Request.DRIGS_SEEK_ADD:
					resp = drigsSeekAdd(param);
					break;
				// 药品求助详情
				case Request.DRIGS_SEEK_DETAIL:
					resp = drigsSeekDetail(param);
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
	 * 药品求助列表
	 * 
	 * @param param
	 * @return Response
	 */
	private Response drigsSeekList(String param) {
		Gson gson = new Gson();
		DrugsSeekListRequest req = gson.fromJson(param,
				DrugsSeekListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);
		return drugsService.drigsSeekList(req);
	}

	/**
	 * 药品列表
	 * 
	 * @param param
	 * @return Response
	 */
	private Response drigsList(String param) {
		Gson gson = new Gson();
		DrugsListRequest req = gson.fromJson(param, DrugsListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);
		return drugsService.drigsList(req);
	}

	/**
	 * 药品详情
	 * 
	 * @param param
	 * @return Response
	 */
	private Response drigsDetail(String param) {
		Gson gson = new Gson();
		DrugsDetailRequest req = gson.fromJson(param, DrugsDetailRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;

		}

		return drugsService.drigsDetail(req);
	}

	/**
	 * 药品求助添加
	 * 
	 * @param param
	 * @return Response
	 */
	private Response drigsSeekAdd(String param) {
		Gson gson = new Gson();
		DrugsSeekAddRequest req = gson.fromJson(param,
				DrugsSeekAddRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;

		}

		return drugsService.drigsSeekAdd(req);
	}

	/**
	 * 药品求助详情
	 * 
	 * @param param
	 * @return Response
	 */
	private Response drigsSeekDetail(String param) {
		Gson gson = new Gson();
		DrugsSeekDetailRequest req = gson.fromJson(param, DrugsSeekDetailRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;

		}

		return drugsService.drigsSeekDetail(req);
	}

}
