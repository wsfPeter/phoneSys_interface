package com.net.toooen.api.request.drugs;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @author wangShaoFeng
 * @Description: 药品列表请求参数
 * @date 2016年3月15日
 */
public class DrugsListRequest extends Request {

    private static final long serialVersionUID = -1040875841975018956L;

    /**
     * @Fields dtId : 药品类型ID
     */
    private Integer dtId;

    /**
     * @Fields keyword : 关键字(药品名称)搜索
     */
    private String keyword;

    /**
     * @Fields pageIndex : 当前页
     */
    private Integer pageIndex;

    public Integer getDtId() {
        return dtId;
    }

    public void setDtId(Integer dtId) {
        this.dtId = dtId;
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
