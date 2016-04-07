package com.net.toooen.api.request.testProject;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ApplyTestProjectListRequest  
 * @Description: 我的检测列表请求参数 
 * @author wangshaofeng
 * @date 2016年3月24日 下午10:26:47 
 */
public class ApplyTestProjectListRequest extends Request {

	private static final long serialVersionUID = -414452786704533417L;

	/**
     * @Fields userId : 用户id
     */
    private Integer userId;

    /**
     * @Fields keyword : 关键字(患者名)搜索
     */
    private String keyword;

    /**
     * @Fields pageIndex : 当前页
     */
    private Integer pageIndex;

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

    @NotNull
    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
