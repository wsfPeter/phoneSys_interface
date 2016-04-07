package com.net.toooen.api.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: VersionRequest
 * @Description: 版本请求参数
 * @author wangshaofeng
 * @date 2016年3月9日 下午6:35:42
 */
public class VersionRequest extends Request {

    private static final long serialVersionUID = 6318492479689799682L;

    /**
     * @Fields versionName : 版本名称
     */
    private String versionName;

    /**
     * @Fields channel : 渠道
     */
    private String channel;

    /**
     * @Fields versionType : 版本类型;0为andrion,1为ios
     */
    private Integer versionType;

    /**
     * @Fields versionGroup : 版本群体;0为患者,1为医生
     */
    private Integer versionGroup;

    @NotBlank
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @NotNull
    public Integer getVersionType() {
        return versionType;
    }

    public void setVersionType(Integer versionType) {
        this.versionType = versionType;
    }

    @NotNull
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(Integer versionGroup) {
        this.versionGroup = versionGroup;
    }

}
