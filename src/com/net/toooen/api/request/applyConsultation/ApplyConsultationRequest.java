package com.net.toooen.api.request.applyConsultation;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ApplyConsultationRequest
 * @Description: 申请会诊服务请求参数
 * @author wangshaofeng
 * @date 2016年3月12日 上午11:12:31
 */
public class ApplyConsultationRequest extends Request {

	private static final long serialVersionUID = -8625098915690315172L;

	/**
	 * @Fields consType : 会诊类型(必填);(1为病例,2为病理)
	 */
	private Integer consType;

	/**
	 * @Fields attendDoctorId : 主治医生ID(必填)
	 */
	private Integer attendDoctorId;

	/**
	 * @Fields patientId : 患者ID(如果是从数据库中获取,请带上此参数的值)
	 */
	private Integer patientId;

	/**
	 * @Fields patientName : 患者名称(必填)
	 */
	private String patientName;

	/**
	 * @Fields sex : 性别(必填);0为男,1为女
	 */
	private Integer sex;

	/**
	 * @Fields age : 年龄(必填)
	 */
	private Integer age;

	/**
	 * @Fields diseases : 初诊病症类型;多个疾病用逗号分隔(必填)
	 */
	private String diseases;

	/**
	 * @Fields consExpertId : 会诊专家ID;(从数据库中获取,请带上此参数的值)
	 */
	private Integer consExpertId;

	/**
	 * @Fields agreeBook : 知情同意书(是从文件上传中获取的路径)
	 */
	private String agreeBook;

	/**
	 * @Fields applyBook : 申请单(必填)(是从文件上传中获取的路径)
	 */
	private String applyBook;

	/**
	 * @Fields illnessUrls : 患者病史资料(必填);(是从文件上传中获取的路径,保存格式["*.jpg","*.jpg"..])
	 */
	private String illnessUrls;

	/**
	 * @Fields idCardPic : 身份证复印件;(是从文件上传中获取的路径,保存格式["*.jpg","*.jpg"])
	 */
	private String idCardPic;

	@NotNull
	public Integer getConsType() {
		return consType;
	}

	public void setConsType(Integer consType) {
		this.consType = consType;
	}

	@NotNull
	public Integer getAttendDoctorId() {
		return attendDoctorId;
	}

	public void setAttendDoctorId(Integer attendDoctorId) {
		this.attendDoctorId = attendDoctorId;
	}


	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@NotBlank
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@NotNull
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@NotNull
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDiseases() {
		return diseases;
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}

	public Integer getConsExpertId() {
		return consExpertId;
	}

	public void setConsExpertId(Integer consExpertId) {
		this.consExpertId = consExpertId;
	}

	public String getAgreeBook() {
		return agreeBook;
	}

	public void setAgreeBook(String agreeBook) {
		this.agreeBook = agreeBook;
	}

	@NotBlank
	public String getApplyBook() {
		return applyBook;
	}

	public void setApplyBook(String applyBook) {
		this.applyBook = applyBook;
	}

	@NotBlank
	public String getIllnessUrls() {
		return illnessUrls;
	}

	public void setIllnessUrls(String illnessUrls) {
		this.illnessUrls = illnessUrls;
	}

	public String getIdCardPic() {
		return idCardPic;
	}

	public void setIdCardPic(String idCardPic) {
		this.idCardPic = idCardPic;
	}

}
