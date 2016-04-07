package com.net.toooen.api.request.testProject;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ApplyTestProjectRequest
 * @Description: 申请检测项目
 * @author wangshaofeng
 * @date 2016年3月13日 下午5:31:42
 */
public class ApplyTestProjectRequest extends Request {

	private static final long serialVersionUID = 7329496229361960785L;

	/**
	 * @Fields userId : 用户id
	 */
	private Integer userId;

	/**
	 * @Fields patientName : 患者名称
	 */
	private String patientName;

	/**
	 * @Fields hospId : 项目所在的医院ID
	 */
	private Integer hospId;

	/**
	 * @Fields tpfId : [1,2,3]申请夹
	 */
	private List<Integer> tpfIds;

	/**
	 * @Fields gsHospital : 收件医院
	 */
	private String gsHospital;

	/**
	 * @Fields gsTitle : 收件科室
	 */
	private String gsTitle;

	/**
	 * @Fields gsDoctor : 收件医生
	 */
	private String gsDoctor;

	/**
	 * @Fields gsMobile : 收件手机号
	 */
	private String gsMobile;

	/**
	 * @Fields totalPrice : 总价
	 */
	private Double totalPrice;

	@NotNull
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@NotNull
	public Integer getHospId() {
		return hospId;
	}

	public void setHospId(Integer hospId) {
		this.hospId = hospId;
	}

	@NotBlank
	public String getGsHospital() {
		return gsHospital;
	}

	public void setGsHospital(String gsHospital) {
		this.gsHospital = gsHospital;
	}

	@NotBlank
	public String getGsTitle() {
		return gsTitle;
	}

	public void setGsTitle(String gsTitle) {
		this.gsTitle = gsTitle;
	}

	@NotBlank
	public String getGsDoctor() {
		return gsDoctor;
	}

	public void setGsDoctor(String gsDoctor) {
		this.gsDoctor = gsDoctor;
	}

	@NotBlank
	public String getGsMobile() {
		return gsMobile;
	}

	public void setGsMobile(String gsMobile) {
		this.gsMobile = gsMobile;
	}

	@NotNull
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@NotBlank
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@NotNull
	public List<Integer> getTpfIds() {
		return tpfIds;
	}

	public void setTpfIds(List<Integer> tpfIds) {
		this.tpfIds = tpfIds;
	}

}
