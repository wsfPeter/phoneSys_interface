package com.net.toooen.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.net.toooen.api.request.Request;
import com.net.toooen.api.request.applyConsultation.ApplyConsultationRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationDetailRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationRecordRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationUpdateRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.entity.InterfaceKey;
import com.net.toooen.service.ApplyConsultationService;
import com.net.toooen.util.Constants;

@Controller
@RequestMapping("/app/applyCons")
public class AppApplyConsultationController extends BaseController {

	@Autowired
	protected ApplyConsultationService applyConsultationService;

	private static final Logger logger = LoggerFactory.getLogger(AppApplyConsultationController.class);

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
				// 申请会诊服务
				case Request.APPLY_CONSULTATION:
					resp = applyConsultation(param);
					break;
				// 会诊记录列表
				case Request.CONSULTATION_RECORD:
					resp = consultationRecord(param);
					break;
				// 会诊记录详情
				case Request.CONSULTATION_DETAIL:
					resp = consultationDetail(param);
					break;
				// 会诊修改
				case Request.CONSULTATION_UPDATE:
					resp = consultationUpdate(param);
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
	 * @Title: applyConsultation
	 * @Description: 申请会诊服务
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response applyConsultation(String param) {
		Gson gson = new Gson();
		ApplyConsultationRequest req = gson.fromJson(param,
				ApplyConsultationRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		// 如果病例会诊,知情书不能为空,病理会诊,身份证照片不能为空
		if (1 == req.getConsType()) {
			if (StringUtils.isEmpty(req.getAgreeBook())) {
				return Response.ERROR_PARAM;
			}
		} else if (2 == req.getConsType()) {
			if (StringUtils.isEmpty(req.getIdCardPic())) {
				return Response.ERROR_PARAM;
			}
		}

		return applyConsultationService.applyConsultation(req);
	}

	/**
	 * @Title: consultationRecord
	 * @Description: 会诊记录
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response consultationRecord(String param) {
		Gson gson = new Gson();
		ConsultationRecordRequest req = gson.fromJson(param,
				ConsultationRecordRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);

		return applyConsultationService.consultationRecord(req);
	}

	/**
	 * @Title: consultationDetail
	 * @Description: 会诊记录详情
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response consultationDetail(String param) {
		Gson gson = new Gson();
		ConsultationDetailRequest req = gson.fromJson(param,
				ConsultationDetailRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return applyConsultationService.consultationDetail(req);
	}

	/**
	 * @Title: consultationUpdate
	 * @Description: 会诊记录修改
	 * @param @param param
	 * @param @return
	 * @return Response
	 * @throws
	 */
	private Response consultationUpdate(String param) {
		Gson gson = new Gson();
		ConsultationUpdateRequest req = gson.fromJson(param,
				ConsultationUpdateRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return applyConsultationService.consultationUpdate(req);
	}

}
