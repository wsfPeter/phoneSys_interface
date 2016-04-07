package com.net.toooen.api.request.drugs;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: DrugsDetailRequest  
 * @Description: 药品详情请求参数
 * @author wangshaofeng
 * @date 2016年3月15日 下午6:39:52 
 */
public class DrugsDetailRequest extends Request {

	private static final long serialVersionUID = -571791952192774622L;

	/**
	 * @Fields id : 药品ID
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
