package com.net.toooen.api.request.user;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ExpertDetailRequest
 * @Description: 专家详情请求参数
 * @author wangshaofeng
 * @date 2016年3月23日 下午8:56:29
 */
public class ExpertDetailRequest extends Request {

	private static final long serialVersionUID = -2882148116093766237L;

	/**
	 * @Fields id : 专家ID
	 */
	private Integer expertId;

	@NotNull
	public Integer getExpertId() {
		return expertId;
	}

	public void setExpertId(Integer expertId) {
		this.expertId = expertId;
	}

}
