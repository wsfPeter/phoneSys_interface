package com.net.toooen.api.request.message;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: UpdateMessageRequest
 * @Description: 接收人更新消息请求参数
 * @author wangshaofeng
 * @date 2016年3月10日 下午6:27:44
 */
public class UpdateMessageRequest extends Request {

	private static final long serialVersionUID = -2681617505029789811L;

	/**
	 * @Fields userMsgId : 用户消息ID
	 */
	private Integer userMsgId;

	/**
	 * @Fields receiveUserId : 接收消息用户ID
	 */
	private Integer receiveUserId;

	/**
	 * @Fields isRead : 是否已读
	 */
	private Boolean isRead;

	/**
	 * @Fields isConfirm : 是否确定
	 */
	private Boolean isConfirm;

	/**
	 * @Fields actionType : 操作类型;1为关联用户
	 */
	private Integer actionType;

	@NotNull
	public Integer getUserMsgId() {
		return userMsgId;
	}

	public void setUserMsgId(Integer userMsgId) {
		this.userMsgId = userMsgId;
	}

	@NotNull
	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

}
