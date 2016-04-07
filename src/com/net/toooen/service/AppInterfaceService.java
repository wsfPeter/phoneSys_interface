package com.net.toooen.service;

import com.net.toooen.api.request.AreaListRequest;
import com.net.toooen.api.request.BackPasswordRequest;
import com.net.toooen.api.request.CaptchaRequest;
import com.net.toooen.api.request.HospitalListRequest;
import com.net.toooen.api.request.LoginRequest;
import com.net.toooen.api.request.RegisterRequest;
import com.net.toooen.api.request.ReplaceMobileRequest;
import com.net.toooen.api.request.SMSRequest;
import com.net.toooen.api.request.VersionRequest;
import com.net.toooen.api.response.Response;

public interface AppInterfaceService {

	/**
	 * 注册
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP001",
	  				"userType":0(患者)/1(医生),
	 				"nickName":"昵称",
	  				"password":"MD5加密",
	  				"mobile":"手机号",
	  				"name":"真实姓名",
	  				"workunit":"工作单位",
	  				"hospId":"医院Id",
	 				"deptPhone":"科室电话",
	 				"doctorCode":"医师资格证编号"
	 			}
	 * <p>
	 * R:{"code":0,"message":"成功"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response register(RegisterRequest request);

	/**
	 * 登录
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP002","loginName":"昵称/手机号","password":"MD5加密",
	 * "loginType":0(患者)/1(医生),
	 * "pushId":"推送ID","systemType":1(android)/2(ios),"version":"版本号"}
	 * <p>
	 * R:{"resultMap":
			{"userInfo": 
			   { 
				"id":"1", 
				"name":"真实姓名", 
		 		"nickname":"昵称",
				"mobile":"手机号",
				"txpic":"头像",
				"email":"邮箱"
	         }
	      }
	   },"code":"0","message":"成功。"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response login(LoginRequest request);

	/**
	 * 发送短信
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP003","mobile":"15171416030","type":"1"}
	 * <p>
	 * R:{"code":0,"message":"成功"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response sendSMS(SMSRequest request);

	/**
	 * 验证码验证
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP004","mobile":"15171416030","captcha":"1234"}
	 * <p>
	 * R:{"code":0,"message":"成功"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response checkCaptcha(CaptchaRequest request);

	/**
	 * 修改密码
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP005","mobile":"15171416030","password":"MD5加密"}
	 * <p>
	 * R:{"code":0,"message":"成功"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response backPassword(BackPasswordRequest request);

	/**
	 * 版本升级
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP006","versionName":"版本号","versionType":" 版本类型;0为andrion,1为ios",
	 *             "channel":"渠道","versionGroup":0为患者端,1为医生端}
	 * <p>
	 * R:{"resultMap":{"versionInfo": {"versionname":"版本号","versiontype":版本类型;0为andrion,1为ios",
	 * "downloadaddr":"下载地址","length":安装包大小,"isneed":是否强制升级,"channel":"渠道","content":"更新内容"}
	 * },"code":"0","message":"成功。"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response versionUpgrade(VersionRequest request);
	
	

	/**
     * 更换手机号
     * 
     * <p>
     * DEMO: text={"msgId":"APP011","newMobile":"新手机号","oldMobile":"旧手机号","password":"MD5加密"}
     * <p>
     * R:{"message":"成功。"}
     * 
     * @param request
     * @param Response
     */
    public Response replaceMobile(ReplaceMobileRequest req);

	/**
     * 疾病列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP014"}
     * <p>
     * R:{"resultMap":{
			"diseaseLists":[ 
			{
				"id":疾病ID,
				"diseasename":"疾病名称"
			}...
		  ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response diseaseList();

	/**
     * 区域列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP016","priovinceId":省份ID,"cityId":城市ID,"getHospital":是否获取医院列表}
     * <p>
     * R:{"resultMap":{
			"areaLists":[ 
			{
				"id":区域ID,
				"name":"名称",
				"fullname":"全名"
				//当getHospital为true时,返回区域下的医院
				"hospitallist":[
				   "id":医院ID,
					"hospitalname":"医院名称",
					"hospitallogo":"医院LOGO图片地址"
				]
			}...
		  ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response areaList(AreaListRequest req);

	/**
     * 医院列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP017",
                   "areaId":区域ID,
                   "keyword":"关键字(医院名称)搜索",
                   "isPage":是否分页(true|false),"pageIndex":当前页
             }
     * <p>
     * R:{"resultMap":{
			"hospitalLists":[ 
			{
				"id":医院ID,
				"areaid":区域ID,
				"fullname":"区域全名",
				"hospitalname":"医院名称",
				"hospitallogo":"医院LOGO图片地址"
			}...
		  ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response hospitalList(HospitalListRequest req);
	

}
