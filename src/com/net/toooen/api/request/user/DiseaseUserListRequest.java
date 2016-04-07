package com.net.toooen.api.request.user;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @author wangShaoFeng
 * @Description: 根据医院搜索用户请求参数
 * @date 2016年4月7日
 */
public class DiseaseUserListRequest extends Request {

    private static final long serialVersionUID = 7569703895236194380L;

    /**
     * @Fields userType : 用户类型;0为患者,1为医生
     */
    private Integer userType;

    /**
     * @Fields diseaseName : 疾病名称
     */
    private String diseaseName;

    /**
     * @Fields pageIndex : 当前页
     */
    private Integer pageIndex;

    @NotNull
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
