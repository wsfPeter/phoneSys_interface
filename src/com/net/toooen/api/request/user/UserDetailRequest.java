package com.net.toooen.api.request.user;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: UserDetailRequest  
 * @Description: 用户详情请求参数 
 * @author wangshaofeng
 * @date 2016年3月12日 上午9:55:30 
 */
public class UserDetailRequest extends Request {

	private static final long serialVersionUID = 2756904530685723851L;

	/**
	 * @Fields userType : 用户类型;0为患者,1为医生
	 */
	private Integer userType;

	/**
	 * @Fields userId : 用户ID
	 */
	private Integer userId;

	@NotNull
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@NotNull
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
