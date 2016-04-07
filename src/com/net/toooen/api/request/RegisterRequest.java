package com.net.toooen.api.request;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: RegisterRequest
 * @Description: 注册
 * @author zhujiang
 * @date 2015年3月19日 下午4:08:04
 * 
 */
public class RegisterRequest extends Request {

	private static final long serialVersionUID = 1797351504954053162L;

	public interface register extends Default {

	}

	/**
	 * @Fields userType : 用户类型;0为患者,1为医生
	 */
	private Integer userType;

	/**
	 * @Fields nickName : 昵称
	 */
	private String nickName;

	/**
	 * @Fields password : 密码
	 */
	private String password;

	/**
	 * @Fields mobile : 手机号
	 */
	private String mobile;

	/**
	 * @Fields name : 真实姓名
	 */
	private String name;

	/**
	 * @Fields workunit : 工作单位
	 */
	private String workunit;

	/**
	 * @Fields hospId : 医院ID
	 */
	private Integer hospId;

	/**
	 * @Fields deptPhone : 科室电话
	 */
	private String deptPhone;

	/**
	 * @Fields doctorCode : 医师资格证编号
	 */
	private String doctorCode;

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

	@NotNull
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	@NotBlank
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public Integer getHospId() {
		return hospId;
	}

	public void setHospId(Integer hospId) {
		this.hospId = hospId;
	}

	public String getDeptPhone() {
		return deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

}
