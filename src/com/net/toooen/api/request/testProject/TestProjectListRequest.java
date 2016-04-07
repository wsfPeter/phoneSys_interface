package com.net.toooen.api.request.testProject;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: TestProjectListRequest
 * @Description: 检测项目列表
 * @author wangshaofeng
 * @date 2016年3月26日 下午7:59:39
 */
public class TestProjectListRequest extends Request {

    private static final long serialVersionUID = 3845558356213589032L;

    /**
     * @Fields hospId : 医院ID
     */
    private Integer hospId;

    /**
     * @Fields parentMenuId : 父菜单ID
     */
    private Integer parentMenuId;

    /**
     * @Fields keyword : 关键字(项目名称)搜索
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
    public Integer getHospId() {
        return hospId;
    }

    public void setHospId(Integer hospId) {
        this.hospId = hospId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

}
