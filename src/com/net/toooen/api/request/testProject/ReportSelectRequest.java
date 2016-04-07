package com.net.toooen.api.request.testProject;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ReportSelectRequest
 * @Description: 报告查询请求参数
 * @author wangshaofeng
 * @date 2016年3月13日 下午9:39:14
 */
public class ReportSelectRequest extends Request {

	private static final long serialVersionUID = -3261000016663062659L;

	/**
	 * @Fields userId : 用户id
	 */
	private Integer userId;

	/**
	 * @Fields patientName : 患者名称
	 */
	private String patientName;

	/**
	 * @Fields pageIndex : 当前页
	 */
	private Integer pageIndex;

	@NotNull
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@NotBlank
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@NotNull
	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

}
