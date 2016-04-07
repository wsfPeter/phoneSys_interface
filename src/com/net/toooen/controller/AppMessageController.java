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
import com.net.toooen.api.request.message.DeleteMessageRequest;
import com.net.toooen.api.request.message.MessageRequest;
import com.net.toooen.api.request.message.MyMessageRequest;
import com.net.toooen.api.request.message.UpdateMessageRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.entity.InterfaceKey;
import com.net.toooen.service.MessageService;
import com.net.toooen.util.Constants;

@Controller
@RequestMapping("/app/message")
public class AppMessageController extends BaseController {

	@Autowired
	protected MessageService messageService;

	private static final Logger logger = LoggerFactory.getLogger(AppMessageController.class);

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
				// 发送消息
				case Request.SEND_MESSAGE:
					resp = sendMessage(param);
					break;
				// 更新消息
				case Request.UPDATE_MESSAGE:
					resp = updateMessage(param);
					break;
				// 我的消息
				case Request.MY_MESSAGE:
					resp = myMessage(param);
					break;
				// 我的消息
				case Request.DELETE_MESSAGE:
					resp = deleteMessage(param);
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
	 * 发送消息
	 * 
	 * @param param
	 * @return Response
	 */
	private Response sendMessage(String param) {
		Gson gson = new Gson();
		MessageRequest req = gson.fromJson(param, MessageRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		
		if(req.getMsgType() != 1){
		    if(null == req.getReceiveUserId()){
		        return Response.ERROR_PARAM;
		    }
		}
		
		// 消息类型为3时,操作类型必填:目前只有关联用户操作
		if (req.getMsgType() == 3) {
			if (null == req.getActionType()) {
				return Response.ERROR_PARAM;
			}
		}
		return messageService.sendMessage(req);
	}

	/**
	 * @Title: updateMessage
	 * @Description: 更新消息
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response updateMessage(String param) {
		Gson gson = new Gson();
		UpdateMessageRequest req = gson.fromJson(param,
				UpdateMessageRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		// 已读消息和确定消息字段不能同时为空
		if (null == req.getIsConfirm() && null == req.getIsRead()) {
			return Response.ERROR_PARAM;
		} else if (null != req.getIsConfirm() && null != req.getIsRead()) {
			return Response.ERROR_PARAM;
		}

		// 确定消息不为空时,操作类型字段也不能为空
		if (null != req.getIsConfirm()) {
			if (null == req.getActionType()) {
				return Response.ERROR_PARAM;
			}
		}

		return messageService.updateMessage(req);
	}

	/**
	 * @Title: myMessage
	 * @Description: 我的消息
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response myMessage(String param) {
		Gson gson = new Gson();
		MyMessageRequest req = gson.fromJson(param, MyMessageRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		// 我发送消息和我接收消息字段不能同时为空
		if (null == req.getSendUserId() && null == req.getReceiveUserId()) {
			return Response.ERROR_PARAM;
		} else if (null != req.getSendUserId()
				&& null != req.getReceiveUserId()) {
			return Response.ERROR_PARAM;
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);

		return messageService.myMessage(req);
	}

	/**
	 * @Title: deleteMessage
	 * @Description: 删除消息
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response deleteMessage(String param) {
		Gson gson = new Gson();
		DeleteMessageRequest req = gson.fromJson(param,
				DeleteMessageRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		return messageService.deleteMessage(req);
	}
	
}
