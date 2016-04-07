package com.net.toooen.api.request.user;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ModifyUserInfoRequest
 * @Description: 修改用户信息请求参数
 * @author wangshaofeng
 * @date 2016年3月11日 下午8:15:24
 */
public class ModifyUserInfoRequest extends Request {

	private static final long serialVersionUID = -5754142263506564997L;

	/**
	 * @Fields paramMap : 信息更新列表
	 */
	private Map<String,String> paramMap;

	/**
	 * @Fields userId : 用户ID
	 */
	private Integer userId;

	@NotNull
	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	@NotNull
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
