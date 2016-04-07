package com.net.toooen.api.request.testProject;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: JoinTestProjectFolderRequest
 * @Description: 加入检测项目申请夹
 * @author wangshaofeng
 * @date 2016年3月26日 下午3:21:05
 */
public class JoinTestProjectFolderRequest extends Request {

	private static final long serialVersionUID = 5148862707475226456L;

	/**
	 * @Fields userId : 用户ID
	 */
	private Integer userId;

	/**
	 * @Fields tpIds : 检测项目ID;[1,2,3]
	 */
	private List<Integer> tpIds;

	@NotNull
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@NotNull
	public List<Integer> getTpIds() {
		return tpIds;
	}

	public void setTpIds(List<Integer> tpIds) {
		this.tpIds = tpIds;
	}

}
