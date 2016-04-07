package com.net.toooen.api.request.goodsAddress;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: DeleteGoodsAddrRequest
 * @Description: 删除我的收件地址请求参数
 * @author wangshaofeng
 * @date 2016年3月13日 下午9:13:13
 */
public class DeleteGoodsAddrRequest extends Request {

	private static final long serialVersionUID = 6158468428358020655L;

	/**
	 * @Fields id : 收件ID
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
