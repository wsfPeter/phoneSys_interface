package com.net.toooen.api.request.user;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ExpertListRequest
 * @Description: 专家列表请求参数
 * @author wangshaofeng
 * @date 2016年3月12日 上午12:23:51
 */
public class ExpertListRequest extends Request {

    private static final long serialVersionUID = 8244689018931421388L;

    /**
     * @Fields expertBeilType : 专家会诊类型;1病例专家,2病理专家,3病例和病理专家
     */
    private Integer expertBeilType;

    /**
     * @Fields keyword : 关键字(姓名搜索)
     */
    private String keyword;

    /**
     * @Fields pageIndex : 当前页
     */
    private Integer pageIndex;

    public Integer getExpertBeilType() {
        return expertBeilType;
    }

    public void setExpertBeilType(Integer expertBeilType) {
        this.expertBeilType = expertBeilType;
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
