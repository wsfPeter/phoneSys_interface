package com.net.toooen.api.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: LoginRequest
 * @Description: 登录请求参数
 * @author wangshaofeng
 * @date 2016年3月8日 下午7:56:51
 */
public class LoginRequest extends Request {

	private static final long serialVersionUID = -6892352768585167456L;

	/**
	 * @Fields loginName : 登录名
	 */
	private String loginName;

	/**
	 * @Fields password : 密码
	 */
	private String password;

	/**
	 * @Fields loginType : 登录类型;0为患者端,1为医生端
	 */
	private Integer loginType;

	/**
	 * @Fields pushId : 推送ID
	 */
	private String pushId;

	/**
	 * @Fields systemType : 系统类型(1、android 2、ios)
	 */
	private Integer systemType;

	/**
	 * @Fields version : 版本号
	 */
	private String version;

	@NotBlank
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@NotBlank
	@Length(max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	@NotNull
	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	@NotBlank
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@NotNull
	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

}
