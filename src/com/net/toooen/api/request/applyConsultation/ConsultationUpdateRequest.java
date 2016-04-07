package com.net.toooen.api.request.applyConsultation;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ConsultationUpdateRequest
 * @Description: 会诊修改请求参数
 * @author wangshaofeng
 * @date 2016年3月12日 下午2:23:45
 */
public class ConsultationUpdateRequest extends Request {

	private static final long serialVersionUID = -5032674820650050709L;

	/**
	 * @Fields id : 会诊ID(必填)
	 */
	private Integer id;

	/**
	 * @Fields consExpertId : 会诊专家ID;
	 */
	private Integer consExpertId;

	/**
	 * @Fields expertName : 会诊专家姓名
	 */
	private String expertName;

	/**
	 * @Fields agreeBook : 知情同意书(是从文件上传中获取的路径)
	 */
	private String agreeBook;

	/**
	 * @Fields applyBook : 申请单(是从文件上传中获取的路径)
	 */
	private String applyBook;

	/**
	 * @Fields illnessUrls : 患者病史资料(是从文件上传中获取的路径,保存格式["*.jpg","*.jpg"..])
	 */
	private String illnessUrls;

	/**
	 * @Fields startDiagResult : 初步诊疗结果
	 */
	private String startDiagResult;

	/**
	 * @Fields consResult : 会诊结论
	 */
	private String consResult;

	/**
	 * @Fields progress : 会诊进度;0为发起申请,1为材料审核,2为专家确定,3会诊中,4为完成
	 */
	private Integer progress;

	@NotNull
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConsExpertId() {
		return consExpertId;
	}

	public void setConsExpertId(Integer consExpertId) {
		this.consExpertId = consExpertId;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getAgreeBook() {
		return agreeBook;
	}

	public void setAgreeBook(String agreeBook) {
		this.agreeBook = agreeBook;
	}

	public String getApplyBook() {
		return applyBook;
	}

	public void setApplyBook(String applyBook) {
		this.applyBook = applyBook;
	}

	public String getIllnessUrls() {
		return illnessUrls;
	}

	public void setIllnessUrls(String illnessUrls) {
		this.illnessUrls = illnessUrls;
	}

	public String getStartDiagResult() {
		return startDiagResult;
	}

	public void setStartDiagResult(String startDiagResult) {
		this.startDiagResult = startDiagResult;
	}

	public String getConsResult() {
		return consResult;
	}

	public void setConsResult(String consResult) {
		this.consResult = consResult;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

}
