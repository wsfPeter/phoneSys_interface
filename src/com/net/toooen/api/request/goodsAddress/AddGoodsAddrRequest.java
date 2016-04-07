package com.net.toooen.api.request.goodsAddress;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: AddGoodsAddrRequest
 * @Description: 添加收件地址请求参数
 * @author wangshaofeng
 * @date 2016年3月13日 下午8:32:59
 */
public class AddGoodsAddrRequest extends Request {

    private static final long serialVersionUID = 5272695149268086270L;

    /**
     * @Fields userId : 用户ID
     */
    private Integer userId;

    /**
     * @Fields gaName : 收货人
     */
    private String gaName;

    /**
     * @Fields gaAddress : 收货地址
     */
    private String gaAddress;

    /**
     * @Fields gaMoblie : 联系电话
     */
    private String gaMoblie;

    /**
     * @Fields workunit : 工作单位
     */
    private String workunit;

    /**
     * @Fields dept : 部门
     */
    private String dept;

    /**
     * @Fields code : 邮编
     */
    private Integer code;

    /**
     * @Fields isMain : 是否为默认
     */
    private Boolean isMain;

    @NotBlank
    public String getGaName() {
        return gaName;
    }

    public void setGaName(String gaName) {
        this.gaName = gaName;
    }

    public String getGaAddress() {
        return gaAddress;
    }

    public void setGaAddress(String gaAddress) {
        this.gaAddress = gaAddress;
    }

    @NotBlank
    public String getGaMoblie() {
        return gaMoblie;
    }

    public void setGaMoblie(String gaMoblie) {
        this.gaMoblie = gaMoblie;
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @NotNull
    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    @NotNull
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
