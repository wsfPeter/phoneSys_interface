package com.net.toooen.service;

import com.net.toooen.api.request.message.DeleteMessageRequest;
import com.net.toooen.api.request.message.MessageRequest;
import com.net.toooen.api.request.message.MyMessageRequest;
import com.net.toooen.api.request.message.UpdateMessageRequest;
import com.net.toooen.api.response.Response;

public interface MessageService {

	/**
	 * 发送消息
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP007","sendUserId":发送消息用户ID,"receiveUserId":接收消息用户ID,
	 *              "title":"标题","content":"内容","msgType":1为系统消息,2为通知消息,3为确认消息,"actionType":操作类型;1为关联用户,
                  }
	 * <p>
	 * R:{"message":"成功。"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response sendMessage(MessageRequest request);
	
	/**
	 * 更新消息
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP008","userMsgId":用户消息ID,"receiveUserId":接收消息用户ID,
	 *              "isRead":是否已读,"isConfirm":是否确定,"actionType":操作类型;1为关联用户
                  }
	 * <p>
	 * R:{"message":"成功。"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response updateMessage(UpdateMessageRequest request);

	/**
	 * 我的消息
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP009","sendUserId":发送消息用户ID,"receiveUserId":接收消息用户ID,
	 			   "pageIndex":当前页
	 			  }
	 * <p>
	 * R:{"resultMap":{
			"myMessages": 
			{
				"id":用户发送的消息ID,
				"title":"标题",
				"content":"内容",
				
				 //如果获取的是接收消息列表,出参中会加入以下字段
				"isread":是否已读,
				"isconfirm":是否确定(如果返回为空,表示没有进行任何操作,为false时已经拒绝 关联,为true时已经 关联)
			}
		},"code":"0","message":"成功。"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response myMessage(MyMessageRequest req);

	/**
	 * 删除消息
	 * 
	 * <p>
	 * DEMO: text={"msgId":"APP010","userMsgId":消息ID,"msgType":消息类型;1为我发送的消息,2为我接收的消息}
	 * <p>
	 * R:{"message":"成功。"}
	 * 
	 * @param request
	 * @param Response
	 */
	public Response deleteMessage(DeleteMessageRequest req);

}
