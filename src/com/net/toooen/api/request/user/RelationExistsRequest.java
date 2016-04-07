package com.net.toooen.api.request.user;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: RelationExistsRequest
 * @Description: 判断患者和医生之间的关联关系是否存在请求参数
 * @author wangshaofeng
 * @date 2016年3月12日 上午10:50:09
 */
public class RelationExistsRequest extends Request {

	private static final long serialVersionUID = 6922600141627862721L;

	/**
	 * @Fields patientId : 患者ID
	 */
	private Integer patientId;

	/**
	 * @Fields doctorId : 医生ID
	 */
	private Integer doctorId;

	@NotNull
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@NotNull
	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

}
