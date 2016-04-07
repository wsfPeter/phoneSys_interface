package com.net.toooen.api.request.applyConsultation;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ConsultationDetailRequest
 * @Description: 会诊记录详情请求参数
 * @author wangshaofeng
 * @date 2016年3月12日 下午1:27:25
 */
public class ConsultationDetailRequest extends Request {

	private static final long serialVersionUID = -1941142195513682309L;

	/**
	 * @Fields id : 会诊ID
	 */
	private Integer id;

	@NotNull
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
