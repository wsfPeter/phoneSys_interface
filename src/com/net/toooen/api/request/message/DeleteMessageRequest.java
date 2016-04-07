package com.net.toooen.api.request.message;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: DeleteMessageRequest
 * @Description: 删除消息
 * @author wangshaofeng
 * @date 2016年3月10日 下午6:59:35
 */
public class DeleteMessageRequest extends Request {

    private static final long serialVersionUID = -2681617505029789811L;

    /**
     * @Fields userMsgId : 用户消息ID
     */
    private Integer userMsgId;

    /**
     * @Fields msgType : 消息类型;1为我发送的消息,2为我接收的消息
     */
    private Integer msgType;

    @NotNull
    public Integer getUserMsgId() {
        return userMsgId;
    }

    public void setUserMsgId(Integer userMsgId) {
        this.userMsgId = userMsgId;
    }

    @NotNull
    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

}
