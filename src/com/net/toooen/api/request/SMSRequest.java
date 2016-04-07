package com.net.toooen.api.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wangShaoFeng
 * @Description: 短信下发
 * @date 2016年3月9日
 */
public class SMSRequest extends Request {

    private static final long serialVersionUID = 7071521976420351185L;

    /**
     * @Fields mobile : 手机号
     */
    private String mobile;

    /**
     * @Fields type : 短信类型（1注册 2修改密码 3信息变更）
     */
    private Integer type;

    @NotBlank
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @NotNull
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
