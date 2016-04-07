package com.net.toooen.api.request.user;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: MyRelationNumRequest
 * @Description: 我的关联人数参数请求
 * @author wangshaofeng
 * @date 2016年3月16日 下午9:36:44
 */
public class MyRelationNumRequest extends Request {

	private static final long serialVersionUID = -1040875841975018956L;

	/**
	 * @Fields userId : 用户Id
	 */
	private Integer userId;

	/**
	 * @Fields userType : 用户类型;0为患者,1为医生
	 */
	private Integer userType;

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
