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
import com.net.toooen.api.request.user.DiseaseUserListRequest;
import com.net.toooen.api.request.user.ExpertDetailRequest;
import com.net.toooen.api.request.user.ExpertListRequest;
import com.net.toooen.api.request.user.HospUserListRequest;
import com.net.toooen.api.request.user.ModifyUserInfoRequest;
import com.net.toooen.api.request.user.MyRelationNumRequest;
import com.net.toooen.api.request.user.RelationExistsRequest;
import com.net.toooen.api.request.user.UserDetailRequest;
import com.net.toooen.api.request.user.UserListRequest;
import com.net.toooen.api.request.user.UserRelationListRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.entity.InterfaceKey;
import com.net.toooen.service.UserService;
import com.net.toooen.util.Constants;

@Controller
@RequestMapping("/app/user")
public class AppUserController extends BaseController {

	@Autowired
	protected UserService userService;

	private static final Logger logger = LoggerFactory
			.getLogger(AppUserController.class);

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
				// 修改用户信息
				case Request.MODIFY_USER:
					resp = modifyUserInfo(param);
					break;
				// 专家列表
				case Request.EXPERT_LIST:
					resp = expertList(param);
					break;
				// 用户列表
				case Request.USER_LIST:
					resp = userList(param);
					break;
				// 用户详情
				case Request.USER_DETAIL:
					resp = userDetail(param);
					break;
				// 用户之间是否已关联
				case Request.RELATION_EXISTS:
					resp = relationExists(param);
					break;
				// 我的关联人数
				case Request.MY_RELATION_NUM:
					resp = myRelationNum(param);
					break;
				// 用户关联列表
				case Request.RELATION_USER_LIST:
					resp = relationUserList(param);
					break;
				// 专家详情
				case Request.EXPERT_DETAIL:
					resp = expertDetail(param);
					break;
				// 根据医院搜索用户
				case Request.HOSP_USER_LIST:
				    resp = hospUserList(param);
				    break;
				// 根据疾病搜索用户
				case Request.DISEASE_USER_LIST:
				    resp = diseaseUserList(param);
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
	 * @Title: modifyUserInfo
	 * @Description:修改用户信息
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response modifyUserInfo(String param) {
		Gson gson = new Gson();
		ModifyUserInfoRequest req = gson.fromJson(param,
				ModifyUserInfoRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		return userService.modifyUserInfo(req);
	}

	/**
	 * @Title: expertList
	 * @Description: 专家列表
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response expertList(String param) {
		Gson gson = new Gson();
		ExpertListRequest req = gson.fromJson(param, ExpertListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);
		return userService.expertList(req);
	}

	/**
	 * @Title: userList
	 * @Description: 用户列表
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response userList(String param) {
		Gson gson = new Gson();
		UserListRequest req = gson.fromJson(param, UserListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		
		if(null != req.getIsRelation()){
			if(null == req.getUserId()){
				return Response.ERROR_PARAM;
			}
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);

		return userService.userList(req);
	}

	/**
	 * @Title: userDetail
	 * @Description: 用户详情
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response userDetail(String param) {
		Gson gson = new Gson();
		UserDetailRequest req = gson.fromJson(param, UserDetailRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return userService.userDetail(req);
	}

	/**
	 * @Title: relationExists
	 * @Description: 用户之间的关联关系是否存在
	 * @param @param param
	 * @param @return
	 * @return Response
	 * @throws
	 */
	private Response relationExists(String param) {
		Gson gson = new Gson();
		RelationExistsRequest req = gson.fromJson(param,
				RelationExistsRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return userService.relationExists(req);
	}

	

	/**
	 * 我的关联人数
	 * 
	 * @param param
	 * @return Response
	 */
	private Response myRelationNum(String param) {
		Gson gson = new Gson();
		MyRelationNumRequest req = gson.fromJson(param,
				MyRelationNumRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;

		}

		return userService.myRelationNum(req);
	}


	/**
	 * 专家详情
	 * 
	 * @param param
	 * @return Response
	 */
	private Response expertDetail(String param) {
		Gson gson = new Gson();
		ExpertDetailRequest req = gson.fromJson(param,
				ExpertDetailRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;

		}

		return userService.expertDetail(req);
	}
	
	/**
	 * 用户关联列表
	 * 
	 * @param param
	 * @return Response
	 */
	private Response relationUserList(String param) {
		Gson gson = new Gson();
		UserRelationListRequest req = gson.fromJson(param, UserRelationListRequest.class);

		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);

		return userService.relationUserList(req);
	}

	/**
     * 根据医院搜索医生
     * 
     * @param param
     * @return Response
     */
    private Response hospUserList(String param) {
        Gson gson = new Gson();
        HospUserListRequest req = gson.fromJson(param, HospUserListRequest.class);

        if (!isValid(req)) {
            return Response.ERROR_PARAM;
        }

        req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);

        return userService.hospUserList(req);
    }

    /**
     * 根据疾病搜索医生
     * 
     * @param param
     * @return Response
     */
     private Response diseaseUserList(String param) {
         Gson gson = new Gson();
         DiseaseUserListRequest req = gson.fromJson(param, DiseaseUserListRequest.class);

         if (!isValid(req)) {
             return Response.ERROR_PARAM;
         }

         req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);

         return userService.diseaseUserList(req);
     }
}
