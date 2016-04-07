package com.net.toooen.service;

import com.net.toooen.api.request.applyConsultation.ApplyConsultationRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationDetailRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationRecordRequest;
import com.net.toooen.api.request.applyConsultation.ConsultationUpdateRequest;
import com.net.toooen.api.response.Response;

public interface ApplyConsultationService {

	 /**
     * 申请会诊服务
     * <p>
     * DEMO: text={
      			   "msgId":"APP021",
                   "consType":会诊类型(必填);(1为病例,2为病理),
                   "attendDoctorId":主治医生ID(必填),
                   "patientId":患者ID(如果是从数据库中获取,请带上此参数的值),
                   "patientName":"患者名称(必填)",
                   "sex":性别(必填);0为男,1为女,
                   "age":年龄(必填),
                   "diseases":"初诊病症类型(必填);多个疾病用逗号分隔,如:x病,s病",
                   "consExpertId":会诊专家ID;(必填),
                   "agreeBook":"知情同意书(是从文件上传中获取的路径)",
                   "applyBook":"申请单(必填)(是从文件上传中获取的路径)",
                   "illnessUrls":"患者病史资料(必填);(是从文件上传中获取的路径,保存格式["*.jpg","*.jpg"..])",
                   "idCardPic":"身份证复印件;(是从文件上传中获取的路径,保存格式["*.jpg","*.jpg"])",
                  }
     * <p>
     * R:
        {"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response applyConsultation(ApplyConsultationRequest req);

	/**
     * 会诊记录列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP022","userId":用户Id,"recordType":记录类型;1为发起,2为受邀,"pageIndex":索引页}
     * <p>
     * R:{"resultMap":{
			"consRecords":[ 
			{
				"id":会诊ID,
				"patientname":"患者姓名",
				"hospName":"医院名称",
				"constype":会诊类型;(1为病例,2为病理)
			}...
		  ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response consultationRecord(ConsultationRecordRequest req);

	/**
     * 会诊记录详情
     * 
     * <p>
     * DEMO: text={"msgId":"APP023","id":会诊Id}
     * <p>
     * R:{"resultMap":{
			"consInfo":
			{
				"id":会诊ID,
				"patientname":"患者姓名",
				"hospname":"医院名称",
				"doctorname":"医生姓名,
				"doctormobile":"医生手机号",
				"sex":性别;0为男,1为女,
				"age":年龄,
				"diseases":"初诊疾病类型",
				"startdiagresult":"初步诊疗结果",
				"expertname":"会诊专家姓名",
				"constype":会诊类型;(1为病例,2为病理),
				"agreebook":"知情同意书",
				"applybook":"会诊申请单",
				"illnessurls":"患者病史资料",
				"idcardpic":"身份证照片",
				"consresult":"会疹结论",
				"adconfrim":主治医生确定;0为未确认,1为已拒绝,2为同意,
				"progress":会诊进度;0为发起申请,1为材料审核,2为专家确定,3会诊中,4为完成
			}
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response consultationDetail(ConsultationDetailRequest req);

	/**
     * 会诊记录修改
     * 
     * <p>
     * DEMO: text={
      			   "msgId":"APP024",
                   "id":会诊Id(必填),
                   "consExpertId":会诊专家ID;(从数据库中获取,请带上此参数的值),
                   "expertName":"会诊专家姓名",
                   "startDiagResult":"初步诊疗结果",
                   "agreeBook":"知情同意书(是从文件上传中获取的路径)",
                   "applyBook":"申请单(是从文件上传中获取的路径)",
                   "illnessUrls":"患者病史资料(是从文件上传中获取的路径,保存格式["*.jpg","*.jpg"..])",
                   "consResult":"会诊结论(是从文件上传中获取的路径)",
                   "progress":"会诊进度;0为发起申请,1为材料审核,2为专家确定,3会诊中,4为完成"
                  }
     * <p>
     * R:
        {"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response consultationUpdate(ConsultationUpdateRequest req);

}
