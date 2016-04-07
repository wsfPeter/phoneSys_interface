package com.net.toooen.api.request.user;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: UserListRequest
 * @Description: 用户列表请求参数
 * @author wangshaofeng
 * @date 2016年3月12日 上午8:57:56
 */
public class UserListRequest extends Request {

	private static final long serialVersionUID = 8244689018931421388L;

	/**
	 * @Fields userId : 用户ID
	 */
	private Integer userId;

	/**
	 * @Fields userType : 用户类型;如果为医生获取的则是患者列表,如果为患者获取的则是医生列表
	 */
	private Integer userType;

	/**
	 * @Fields isRelation : 显示关联用户(true);
	 */
	private Boolean isRelation;

	/**
	 * @Fields keyword : 关键字(用户姓名搜索)
	 */
	private String keyword;

	/**
	 * @Fields pageIndex : 当前页
	 */
	private Integer pageIndex;

	@NotNull
	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	@NotNull
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Boolean getIsRelation() {
		return isRelation;
	}

	public void setIsRelation(Boolean isRelation) {
		this.isRelation = isRelation;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
