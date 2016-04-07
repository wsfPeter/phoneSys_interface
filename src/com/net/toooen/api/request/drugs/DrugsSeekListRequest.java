package com.net.toooen.api.request.drugs;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @author wangShaoFeng
 * @Description: 药品求助列表请求参数
 * @date 2016年3月15日
 */
public class DrugsSeekListRequest extends Request {

    private static final long serialVersionUID = -1040875841975018956L;

    /**
     * @Fields userId : 用户Id
     */
    private Integer userId;

    /**
     * @Fields keyword : 关键字(药品名称)搜索
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
