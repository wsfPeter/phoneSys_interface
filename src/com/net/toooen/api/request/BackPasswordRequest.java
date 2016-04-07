package com.net.toooen.api.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: BackPasswordRequest
 * @Description: 找回密码
 * @author wangshaofeng
 * @date 2016年3月9日 下午7:05:30
 */
public class BackPasswordRequest extends Request {

	private static final long serialVersionUID = 7032694199214477497L;

	/**
	 * @Fields mobile : 手机号
	 */
	private String mobile;

	/**
	 * @Fields password : 密码
	 */
	private String password;

	@NotBlank
	@Length(max = 11)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@NotBlank
	@Length(max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
