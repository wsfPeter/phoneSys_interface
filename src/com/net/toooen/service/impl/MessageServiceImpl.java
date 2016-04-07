package com.net.toooen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.message.DeleteMessageRequest;
import com.net.toooen.api.request.message.MessageRequest;
import com.net.toooen.api.request.message.MyMessageRequest;
import com.net.toooen.api.request.message.UpdateMessageRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.service.MessageService;
import com.net.toooen.util.Constants;
import com.net.toooen.util.JsonUtil;

@Service
public class MessageServiceImpl implements MessageService {

	@Override
	public Response sendMessage(MessageRequest request) {
		// 保存消息
		Record record = new Record();
		record.set("sendUserId", request.getSendUserId())
				.set("title", request.getTitle())
				.set("content", request.getContent())
				.set("msgType", request.getMsgType())
				.set("createDate", new Date());
		if (null != request.getActionType()) {
			record.set("actionType", request.getActionType());
		}
		Db.save("message", record);

		// 保存用户消息
		Record userMsgRecord = new Record();
		userMsgRecord.set("msgId", record.getLong("id"))
				.set("receiveUserId", request.getReceiveUserId())
				.set("isRead", false);
		Db.save("user_message", userMsgRecord);
		return Response.SUCCESS;
	}

	@Override
	public Response updateMessage(UpdateMessageRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("update user_message set ");
		if (null != request.getIsRead()) {
			sb.append(" isRead = ? where msgId = ? and receiveUserId = ?");
			Db.update(sb.toString(), request.getIsRead(),
					request.getUserMsgId(), request.getReceiveUserId());
		} else if (null != request.getIsConfirm()) {
			sb.append(" isConfirm = ? where msgId = ? and receiveUserId = ?");
			Db.update(sb.toString(), request.getIsConfirm(),
					request.getUserMsgId(), request.getReceiveUserId());

			// 关联用户操作
			if (request.getIsConfirm() && request.getActionType() == 1) {
				// 获取发送消息的用户ID
				Integer sendUserId = Db.findFirst(
						"select sendUserId from message where id = ?",
						request.getUserMsgId()).getInt("sendUserId");

				// 保存患者和医生之间的关系
				Record patientDoctor = new Record();

				String sql = "select id,userType from user where id in (?,?)";
				List<Record> records = Db.find(sql, sendUserId,
						request.getReceiveUserId());
				for (Record r : records) {
					// 患者
					if (r.getInt("userType") == 0) {
						patientDoctor.set("patientId", r.getInt("id"));
						// 医生
					} else if (r.getInt("userType") == 1) {
						patientDoctor.set("doctorId", r.getInt("id"));
					}
				}
				// 保存关联
				Db.save("patient_doctor", patientDoctor);
			}

		}
		return Response.SUCCESS;
	}

	@Override
	public Response myMessage(MyMessageRequest req) {
		List<Record> records = null;
		
		if (null != req.getSendUserId()) {
			StringBuilder sb = new StringBuilder("select id,title,content from message where sendUserId = ? order by createDate desc limit ?,?  ");
			records = Db.find(sb.toString(), req.getSendUserId(),req.getPageIndex(),Constants.PAGE_NUM);
		} else if (null != req.getReceiveUserId()) {
			String sql = "select um.id as id,title,content,um.isRead,isConfirm from message mess left join user_message um on mess.id = um.msgId where receiveUserId = ? order by createDate desc limit ?,?";
			records = Db.find(sql, req.getReceiveUserId(),req.getPageIndex(),Constants.PAGE_NUM);
		}
		
		if (null == records) {
			return Response.SUCCESS;
		} else {
			ResultResponse resp = new ResultResponse();
			resp.getResultMap().put("myMessages",
					JsonUtil.getJsonObjByjfinalList(records));
			return resp;
		}
	}

	@Override
	public Response deleteMessage(DeleteMessageRequest req) {
		// 从我发送的消息列表中删除消息
		if (req.getMsgType() == 1) {
			Record record = new Record().set("msgId", req.getUserMsgId());
			Db.deleteById("user_message", record);
			Db.deleteById("message", req.getUserMsgId());
		} else if (req.getMsgType() == 2) {
			Db.deleteById("user_message", req.getUserMsgId());
		}
		return Response.SUCCESS;
	}
}