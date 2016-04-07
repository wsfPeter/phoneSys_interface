package com.net.toooen.api.request.user;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: UserRelationListRequest
 * @Description: 用户关联列表请求参数
 * @author wangshaofeng
 * @date 2016年3月12日 上午8:57:56
 */
public class UserRelationListRequest extends Request {

	private static final long serialVersionUID = -5974296599746172098L;

	/**
	 * @Fields userType : 用户类型;如果为医生获取的则是患者列表,如果为患者获取的则是医生列表
	 */
	private Integer userType;

	/**
	 * @Fields userId : 用户ID
	 */
	private Integer userId;

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

	@NotNull
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
