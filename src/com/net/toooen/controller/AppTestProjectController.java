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
import com.net.toooen.api.request.testProject.ApplyTestProjectListRequest;
import com.net.toooen.api.request.testProject.ApplyTestProjectRequest;
import com.net.toooen.api.request.testProject.DelTestProjectFolderRequest;
import com.net.toooen.api.request.testProject.JoinTestProjectFolderRequest;
import com.net.toooen.api.request.testProject.ReportSelectRequest;
import com.net.toooen.api.request.testProject.ReportUserListRequest;
import com.net.toooen.api.request.testProject.TestProjectDetailRequest;
import com.net.toooen.api.request.testProject.TestProjectFolderListRequest;
import com.net.toooen.api.request.testProject.TestProjectListRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.entity.InterfaceKey;
import com.net.toooen.service.TestProjectService;
import com.net.toooen.util.Constants;

@Controller
@RequestMapping("/app/testProject")
public class AppTestProjectController extends BaseController {

	@Autowired
	protected TestProjectService testProjectService;

	private static final Logger logger = LoggerFactory.getLogger(AppTestProjectController.class);

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
				// 检测项目列表
				case Request.TEST_PROJECT_LIST:
					resp = testProjectList(param);
					break;
				// 检测项目详情
				case Request.TEST_PROJECT_DETAIL:
					resp = testProjectDetail(param);
					break;
				// 申请检测项目
				case Request.APPLY_TEST_PROJECT:
					resp = applyTestProject(param);
					break;
				// 我的检测列表
				case Request.APPLY_TESTPROJECTLIST:
					resp = applyTestProjectList(param);
					break;
				// 加入检测项目申请夹
				case Request.JOIN_TP_FOLDER:
					resp = joinTpFolder(param);
					break;
				// 检测项目申请夹列表
				case Request.TP_FOLDERLIST:
					resp = tpFolderlist(param);
					break;
				// 删除检测项目申请夹
				case Request.DEL_TP_FOLDER:
					resp = delTpFolder(param);
					break;
				// 报告查询
				case Request.REPORT_SELECT:
					resp = reportSelect(param);
					break;
				// 检测项目省份医院
				case Request.TP_HOSPITAL:
					resp = testProjectService.testProjectHspital();
					break;
				// (患者列表)报告查询
				case Request.REPORT_USER_LIST:
					resp = reportUserLis(param);
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
	 * @Title: testProjectList
	 * @Description: 检测项目列表
	 * @param @param param
	 * @param @return
	 * @return Response
	 * @throws
	 */
	private Response testProjectList(String param) {
		Gson gson = new Gson();
		TestProjectListRequest req = gson.fromJson(param,
				TestProjectListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);

		return testProjectService.testProjectList(req);
	}

	/**
	 * @Title: testProjectDetail
	 * @Description: 检测项目详情
	 * @param @param param
	 * @param @return
	 * @return Response
	 * @throws
	 */
	private Response testProjectDetail(String param) {
		Gson gson = new Gson();
		TestProjectDetailRequest req = gson.fromJson(param,
				TestProjectDetailRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return testProjectService.testProjectDetail(req);
	}

	/**
	 * @Title: applyTestProject
	 * @Description: 申请检测项目
	 * @param param
	 * @param @return
	 * @return Response
	 * @throws
	 */
	private Response applyTestProject(String param) {
		Gson gson = new Gson();
		ApplyTestProjectRequest req = gson.fromJson(param,
				ApplyTestProjectRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		
		if(req.getTpfIds().size() == 0){
			return Response.ERROR_PARAM;
		}

		return testProjectService.applyTestProject(req);
	}

	
	/**
	 * 我的检测列表
	 * 
	 * @param param
	 * @return Response
	 */
	private Response applyTestProjectList(String param) {
		Gson gson = new Gson();
		ApplyTestProjectListRequest req = gson.fromJson(param,
				ApplyTestProjectListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);
		return testProjectService.applyTestProjectList(req);
	}

	/**
	 * 删除检测项目申请夹
	 * 
	 * @param param
	 * @return Response
	 */
	private Response delTpFolder(String param) {
		Gson gson = new Gson();
		DelTestProjectFolderRequest req = gson.fromJson(param,
				DelTestProjectFolderRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return testProjectService.delTpFolder(req);
	}

	/**
	 * 检测项目申请夹列表
	 * 
	 * @param param
	 * @return Response
	 */
	private Response tpFolderlist(String param) {
		Gson gson = new Gson();
		TestProjectFolderListRequest req = gson.fromJson(param,
				TestProjectFolderListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return testProjectService.tpFolderlist(req);
	}

	/**
	 * 加入检测项目申请夹
	 * 
	 * @param param
	 * @return Response
	 */
	private Response joinTpFolder(String param) {
		Gson gson = new Gson();
		JoinTestProjectFolderRequest req = gson.fromJson(param,
				JoinTestProjectFolderRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		if(req.getTpIds().size() == 0){
			return Response.ERROR_PARAM;
		}
		
		return testProjectService.joinTpFolder(req);
	}

	/**
	 * @Title: reportSelect
	 * @Description: 报告查询
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response reportSelect(String param) {
		Gson gson = new Gson();
		ReportSelectRequest req = gson.fromJson(param,
				ReportSelectRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);
		return testProjectService.reportSelect(req);
	}
	
	/**
	 * @Title: relationUserList
	 * @Description: 报告查询
	 * @return Response
	 * @throws
	 */
	private Response reportUserLis(String param) {
		Gson gson = new Gson();
		ReportUserListRequest req = gson.fromJson(param,
				ReportUserListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		
		return testProjectService.reportUserLis(req);
	}

	
}
