package com.net.toooen.api.request.testProject;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: TestProjectFolderListRequest  
 * @Description: 检测项目申请夹列表 
 * @author wangshaofeng
 * @date 2016年3月26日 下午3:23:26 
 */
public class TestProjectFolderListRequest extends Request {

	private static final long serialVersionUID = -8302568093599289385L;

	/**
	 * @Fields userId : 用户ID
	 */
	private Integer userId;

	/**
	 * @Fields hospId : 医院ID
	 */
	private Integer hospId;

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

}
