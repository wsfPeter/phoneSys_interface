package com.net.toooen.api.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wangShaoFeng
 * @Description: 验证码校验
 * @date 2016年3月9日
 */
public class CaptchaRequest extends Request {

    private static final long serialVersionUID = -1401793037908218096L;

    /**
     * @Fields mobile : 手机号
     */
    private String mobile;

    /**
     * @Fields captcha : 验证码
     */
    private String captcha;

    @NotBlank
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @NotBlank
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
