package com.net.toooen.api.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wangShaoFeng
 * @Description: 更换手机号
 * @date 2016年3月11日
 */
public class ReplaceMobileRequest extends Request {

    private static final long serialVersionUID = -788371320382763334L;

    /**
     * @Fields newMobile : 新手机号
     */
    private String newMobile;

    /**
     * @Fields oldMobile : 旧手机号
     */
    private String oldMobile;

    /**
     * @Fields password : 密码
     */
    private String password;

    @NotBlank
    @Length(max = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    @Length(max = 11)
    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    @NotBlank
    @Length(max = 11)
    public String getOldMobile() {
        return oldMobile;
    }

    public void setOldMobile(String oldMobile) {
        this.oldMobile = oldMobile;
    }

}
