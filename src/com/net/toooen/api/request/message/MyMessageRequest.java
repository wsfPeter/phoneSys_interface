package com.net.toooen.api.request.message;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: MyMessageRequest
 * @Description: 我的消息请求参数
 * @author wangshaofeng
 * @date 2016年3月10日 下午6:50:27
 */
public class MyMessageRequest extends Request {

	private static final long serialVersionUID = -6880790641023802906L;

	/**
	 * @Fields sendUserId : 发送消息用户ID
	 */
	private Integer sendUserId;

	/**
	 * @Fields receiveUserId : 接收消息用户ID
	 */
	private Integer receiveUserId;
	
	/**  
	* @Fields pageIndex : 索引页
	*/ 
	private Integer pageIndex;

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

	@NotNull
    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
	
}
