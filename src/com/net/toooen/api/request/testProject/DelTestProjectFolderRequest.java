package com.net.toooen.api.request.testProject;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: DelTestProjectFolderRequest
 * @Description: 删除检测项目申请夹
 * @author wangshaofeng
 * @date 2016年3月26日 下午3:19:31
 */
public class DelTestProjectFolderRequest extends Request {

	private static final long serialVersionUID = -3484056961896412829L;

	/**
	 * @Fields id : 检测项目申请夹Id
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
