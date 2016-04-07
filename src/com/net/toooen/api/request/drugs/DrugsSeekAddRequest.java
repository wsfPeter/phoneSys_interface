package com.net.toooen.api.request.drugs;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: DrugsSeekAddRequest
 * @Description: 药品求助添加请求参数
 * @author wangshaofeng
 * @date 2016年3月15日 下午6:49:10
 */
public class DrugsSeekAddRequest extends Request {

	private static final long serialVersionUID = 5272695149268086270L;

	/**
	 * @Fields userId : 用户ID
	 */
	private Integer userId;

	/**
	 * @Fields contact : 联系人
	 */
	private String contact;

	/**
	 * @Fields contactPhone : 联系电话
	 */
	private String contactPhone;

	/**
	 * @Fields drugsName : 药品名称
	 */
	private String drugsName;

	/**
	 * @Fields content : 内容
	 */
	private String content;

	/**
	 * @Fields remark : 备注
	 */
	private String remark;

	@NotNull
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@NotBlank
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@NotBlank
	@Length(max = 11)
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@NotBlank
	public String getDrugsName() {
		return drugsName;
	}

	public void setDrugsName(String drugsName) {
		this.drugsName = drugsName;
	}

	@NotBlank
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
