package com.net.toooen.api.request.testProject;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: TestProjectDetailRequest
 * @Description: 检测项目详情请求参数
 * @author wangshaofeng
 * @date 2016年3月13日 下午4:24:07
 */
public class TestProjectDetailRequest extends Request {

	private static final long serialVersionUID = -571791952192774622L;

	/**
	 * @Fields id : 检测项目ID
	 */
	private Integer id;

	public Integer getId() {
		return id;
	}

	@NotNull
	public void setId(Integer id) {
		this.id = id;
	}

}
