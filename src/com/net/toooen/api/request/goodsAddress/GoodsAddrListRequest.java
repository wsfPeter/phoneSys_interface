package com.net.toooen.api.request.goodsAddress;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: GoodsAddrListRequest
 * @Description: 我的收件地址列表请求参数
 * @author wangshaofeng
 * @date 2016年3月13日 下午8:08:29
 */
public class GoodsAddrListRequest extends Request {

	private static final long serialVersionUID = -4516865132457855216L;

	/**
	 * @Fields userId : 用户ID
	 */
	private Integer userId;

	/**
	 * @Fields isMain : 是否默认
	 */
	private Boolean isMain;

	@NotNull
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}

}
