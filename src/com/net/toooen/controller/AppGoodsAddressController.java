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
import com.net.toooen.api.request.goodsAddress.AddGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.DeleteGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.EditGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.GoodsAddrListRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.entity.InterfaceKey;
import com.net.toooen.service.GoodsAddressService;

@Controller
@RequestMapping("/app/goodsAddr")
public class AppGoodsAddressController extends BaseController {

	@Autowired
	protected GoodsAddressService goodsAddressService;

	private static final Logger logger = LoggerFactory.getLogger(AppGoodsAddressController.class);

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
				// 我的收件地址列表
				case Request.GOOD_ADDR_LIST:
					resp = goodsAddrList(param);
					break;
				// 添加收件地址
				case Request.GOOD_ADDR_ADD:
					resp = goodsAddrAdd(param);
					break;
				// 编辑收件地址
				case Request.GOOD_ADDR_EDIT:
					resp = goodsAddrEdit(param);
					break;
				// 删除收件地址
				case Request.GOOD_ADDR_DELETE:
					resp = goodsAddrDelete(param);
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
	 * @Title: goodAddrList
	 * @Description: 我的收件地址列表
	 * @param @param param
	 * @param @return
	 * @return Response
	 * @throws
	 */
	private Response goodsAddrList(String param) {
		Gson gson = new Gson();
		GoodsAddrListRequest req = gson.fromJson(param,
				GoodsAddrListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return goodsAddressService.goodsAddrList(req);
	}

	/**
	 * @Title: goodsAddrEdit
	 * @Description: 编辑收件地址
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response goodsAddrEdit(String param) {
		Gson gson = new Gson();
		EditGoodsAddrRequest req = gson.fromJson(param,
				EditGoodsAddrRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return goodsAddressService.goodsAddrEdit(req);
	}

	/**
	 * @Title: goodsAddrAdd
	 * @Description: 添加收件地址
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response goodsAddrAdd(String param) {
		Gson gson = new Gson();
		AddGoodsAddrRequest req = gson.fromJson(param,
				AddGoodsAddrRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return goodsAddressService.goodsAddrAdd(req);
	}

	/**
	 * @Title: goodsAddrDelete
	 * @Description: 删除我的收件地址
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response goodsAddrDelete(String param) {
		Gson gson = new Gson();
		DeleteGoodsAddrRequest req = gson.fromJson(param,
				DeleteGoodsAddrRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return goodsAddressService.goodsAddrDelete(req);
	}


}
