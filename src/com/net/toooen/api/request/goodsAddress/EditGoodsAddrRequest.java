package com.net.toooen.api.request.goodsAddress;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: EditGoodsAddrRequest
 * @Description: 编辑收件地址请求参数
 * @author wangshaofeng
 * @date 2016年3月13日 下午8:30:01
 */
public class EditGoodsAddrRequest extends Request {

    private static final long serialVersionUID = -5946679440389837745L;

    /**
     * @Fields id : 收件ID
     */
    private Integer id;

    /**
     * @Fields userId : 用户Id
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

    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
