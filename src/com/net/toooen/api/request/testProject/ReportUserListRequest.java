package com.net.toooen.api.request.testProject;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ReportUserListRequest  
 * @Description: 报告查询的患者列表搜索
 * @author wangshaofeng
 * @date 2016年3月30日 下午9:21:41 
 */
public class ReportUserListRequest extends Request {

	private static final long serialVersionUID = -2787519673933747884L;

	/**
     * @Fields userId : 用户id
     */
    private Integer userId;

    /**
     * @Fields keyword : 关键字(患者名称)搜索
     */
    private String keyword;

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
