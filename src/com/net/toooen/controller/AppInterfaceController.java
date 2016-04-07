package com.net.toooen.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.AreaListRequest;
import com.net.toooen.api.request.BackPasswordRequest;
import com.net.toooen.api.request.CaptchaRequest;
import com.net.toooen.api.request.HospitalListRequest;
import com.net.toooen.api.request.LoginRequest;
import com.net.toooen.api.request.RegisterRequest;
import com.net.toooen.api.request.ReplaceMobileRequest;
import com.net.toooen.api.request.Request;
import com.net.toooen.api.request.SMSRequest;
import com.net.toooen.api.request.VersionRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.entity.InterfaceKey;
import com.net.toooen.service.AppInterfaceService;
import com.net.toooen.util.Constants;
import com.net.toooen.util.FileUtil;

@Controller
@RequestMapping("/app")
public class AppInterfaceController extends BaseController {

	@Autowired
	protected AppInterfaceService appInterfaceService;

	private static final Logger logger = LoggerFactory
			.getLogger(AppInterfaceController.class);

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
				// 注册
				case Request.REGISTER:
					resp = register(param);
					break;
				// 登录
				case Request.LOGIN:
					resp = login(param);
					break;
				// 下发短信
				case Request.SEND_SMS:
					resp = sendSMS(param);
					break;
				// 验证码校验
				case Request.CHECK_CAPTCHA:
					resp = checkCaptcha(param);
					break;
				// 找回密码
				case Request.BACK_PASSWORD:
					resp = backPassword(param);
					break;
				// 版本升级
				case Request.VERSION_UPGRADE:
					resp = versionUpgrade(param);
					break;
				// 更换手机号
				case Request.REPLACE_MOBILE:
					resp = replaceMobile(param);
					break;
				// 疾病列表
				case Request.DISEASE_LIST:
					resp = appInterfaceService.diseaseList();
					break;
				// 区域列表
				case Request.AREA_LIST:
					resp = areaList(param);
					break;
				// 医院列表
				case Request.HOSPITAL_LIST:
					resp = hospitalList(param);
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
	 * @Title: fileUpload
	 * @Description: 文件上传 请求格式：http://服务器:端口/toooen_interface/app/fileUpload.do
	 *               请求参数 folderType(文件夹类型;必填"1"为头像,"2"为会诊资料) files(必填.文件数组)
	 *               返回参数 {"resultMap":{ "filePaths":["xx/xx.jpg"..]
	 *               },"code":"0","message":"成功。"}
	 * @return void
	 * @throws
	 */
	@RequestMapping("/fileUpload.do")
	public void fileUpload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam MultipartFile[] files) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		long start = System.currentTimeMillis();

		Response resp = null;
		Gson gson = new Gson();
		// 返回filePaths列表
		List<String> filePaths = new ArrayList<String>();
		String folder = null;
		String filePath = null;
		try {
			String folderType = request.getParameter("folderType");
			if (StringUtils.isNotEmpty(folderType)) {
				if ("1".equals(folderType)) {
					folder = Constants.UPLOAD_TXPIC;
				} else if ("2".equals(folderType)) {
					folder = Constants.UPLOAD_TXPIC;
				}

				if (StringUtils.isNotEmpty(folder)) {
					for (MultipartFile file : files) {
						if (!file.isEmpty()) {
							filePath = FileUtil.fileUpload(file, folder);
							filePaths.add(filePath);
						}
					}
				}

				ResultResponse result = new ResultResponse();
				result.getResultMap().put("filePaths", filePaths);
				resp = result;
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
	 * @Title: AppQrcode
	 * @Description: 
	 *               下载安卓APP：http://服务器:端口/toooen_interface/app/appQrcode.do?versionGroup
	 *               =0|1
	 * 
	 * @return void
	 * @throws
	 */
	@RequestMapping("/appQrcode.do")
	public void appQrcode(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-type",
				"application/vnd.android.package-archive");
		response.setCharacterEncoding("UTF-8");
		
		FileInputStream fis = null;
		OutputStream os = null;
		
		String sql = "select versionName,downLoadAddr from version where versionName = (select max(versionName) from version where versionType = 0 and channel = ? and versionGroup = ?)";
		try {
			// 版本群体(0为患者,1为医生)
			String versionGroup = request.getParameter("versionGroup");
			if (StringUtils.isEmpty(versionGroup)) {
				versionGroup = "1";
			}
			Record record = Db.findFirst(sql, "web", versionGroup);
			String fileName = record.getStr("versionName") + "exueyebing_"
					+ ".apk";
			File file = new File(Constants.FILE_UPLOAD_PATH
					+ record.getStr("downLoadAddr"));
			os = response.getOutputStream();
			if (file.exists()) {
				fis = new FileInputStream(
						file.getAbsolutePath());
				byte[] b = new byte[(int) file.length()];
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ java.net.URLEncoder.encode(fileName, "utf-8"));
				fis.read(b);
				os.write(b);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
				try {
					if(null != fis){
						fis.close();
					}
					if(null != os){
						os.close();
					}
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
		}
	}

	/**
	 * @Title: register
	 * @Description: 注册
	 * @param
	 * @return
	 */
	private Response register(String param) {
		Gson gson = new Gson();
		RegisterRequest req = gson.fromJson(param, RegisterRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		if (req.getUserType() == 1) {
			if (StringUtils.isEmpty(req.getDeptPhone())
					|| StringUtils.isEmpty(req.getDoctorCode())
					|| StringUtils.isEmpty(req.getWorkunit())) {
				return Response.ERROR_PARAM;
			}
		}
		return appInterfaceService.register(req);
	}

	/**
	 * @Title: login
	 * @Description: 登录
	 * @param
	 * @return
	 */
	private Response login(String param) {
		Gson gson = new Gson();
		LoginRequest req = gson.fromJson(param, LoginRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		return appInterfaceService.login(req);
	}

	/**
	 * @Title: sendSMS
	 * @Description: 短信下发
	 * @param
	 * @return
	 */
	private Response sendSMS(String param) {
		Gson gson = new Gson();
		SMSRequest req = gson.fromJson(param, SMSRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		return appInterfaceService.sendSMS(req);
	}

	/**
	 * @Title: checkCaptcha
	 * @Description: 验证码校验
	 * @param
	 * @return
	 */
	private Response checkCaptcha(String param) {
		Gson gson = new Gson();
		CaptchaRequest req = gson.fromJson(param, CaptchaRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		return appInterfaceService.checkCaptcha(req);
	}

	/**
	 * @Title: backPassword
	 * @Description: 找回密码
	 * @param param
	 * @return Response
	 * @throws
	 */
	public Response backPassword(String param) {
		Gson gson = new Gson();
		BackPasswordRequest req = gson.fromJson(param,
				BackPasswordRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		return appInterfaceService.backPassword(req);
	}

	/**
	 * @Title: versionUpgrade
	 * @Description: 版本升级
	 * @return Response
	 * @throws
	 */
	private Response versionUpgrade(String param) {
		Gson gson = new Gson();
		VersionRequest req = gson.fromJson(param, VersionRequest.class);
		if (null == req.getVersionGroup()) {
			// 默认请求为医生版本
			req.setVersionGroup(1);
		}
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		return appInterfaceService.versionUpgrade(req);
	}

	
	/**
	 * 更换手机号
	 * 
	 * @param param
	 * @return Response
	 */
	private Response replaceMobile(String param) {
		Gson gson = new Gson();
		ReplaceMobileRequest req = gson.fromJson(param,
				ReplaceMobileRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}
		return appInterfaceService.replaceMobile(req);
	}
	
	/**
	 * @Title: areaList
	 * @Description: 区域列表
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response areaList(String param) {
		Gson gson = new Gson();
		AreaListRequest req = gson.fromJson(param, AreaListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		return appInterfaceService.areaList(req);
	}

	/**
	 * @Title: hospitalList
	 * @Description: 医院列表
	 * @param param
	 * @return Response
	 * @throws
	 */
	private Response hospitalList(String param) {
		Gson gson = new Gson();
		HospitalListRequest req = gson.fromJson(param,
				HospitalListRequest.class);
		if (!isValid(req)) {
			return Response.ERROR_PARAM;
		}

		if (req.getIsPage()) {
			if (null == req.getPageIndex()) {
				return Response.ERROR_PARAM;
			} else {
				req.setPageIndex((req.getPageIndex() - 1) * Constants.PAGE_NUM);
			}
		}
		return appInterfaceService.hospitalList(req);
	}
	
}
