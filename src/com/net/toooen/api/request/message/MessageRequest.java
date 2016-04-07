package com.net.toooen.api.request.message;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.net.toooen.api.request.Request;

/**
 * @author wangShaoFeng
 * @Description: 消息请求参数
 * @date 2016年3月10日
 */
public class MessageRequest extends Request {

    private static final long serialVersionUID = -8240468343426231563L;

    /**
     * @Fields sendUserId : 发送消息用户ID
     */
    private Integer sendUserId;

    /**
     * @Fields receiveUserId : 接收消息用户ID
     */
    private Integer receiveUserId;

    /**
     * @Fields title : 标题
     */
    private String title;

    /**
     * @Fields content : 内容
     */
    private String content;

    /**
     * @Fields msgType : 消息类型;1为系统消息,2为通知消息,3为确认消息
     */
    private Integer msgType;

    /**
     * @Fields actionType : 操作类型;1为关联用户
     */
    private Integer actionType;

    @NotNull
    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotNull
    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

}
