package com.net.toooen.api.request.news;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: NewsListRequest
 * @Description: 资讯列表请求参数
 * @author wangshaofeng
 * @date 2016年3月11日 下午9:35:34
 */
public class NewsListRequest extends Request {

    private static final long serialVersionUID = -3887829051790235655L;

    /**
     * @Fields newsGroup : 资讯群体;0为患者,1为医生
     */
    private Integer newsGroup;

    /**
     * @Fields isTop : 是否置顶;主页资讯
     */
    private Boolean isTop;

    /**
     * @Fields newsType : 资讯类型;1为会诊服务,2为检测服务,3国内资讯
     */
    private Integer newsType;

    /**
     * @Fields pageIndex : 当前页
     */
    private Integer pageIndex;

    @NotNull
    public Integer getNewsGroup() {
        return newsGroup;
    }

    public void setNewsGroup(Integer newsGroup) {
        this.newsGroup = newsGroup;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    @NotNull
    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
