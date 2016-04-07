package com.net.toooen.api.request;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AreaListRequest
 * @Description: 区域列表请求参数
 * @author wangshaofeng
 * @date 2016年3月12日 上午7:35:05
 */
public class AreaListRequest extends Request {

	private static final long serialVersionUID = -6118369562546407734L;

	/**
	 * @Fields priovinceId : 省份ID
	 */
	private Integer priovinceId;

	/**
	 * @Fields cityId : 城市ID
	 */
	private Integer cityId;

	/**
	 * @Fields getHospital : 是否获取医院
	 */
	private Boolean getHospital;

	public Integer getPriovinceId() {
		return priovinceId;
	}

	public void setPriovinceId(Integer priovinceId) {
		this.priovinceId = priovinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@NotNull
	public Boolean getGetHospital() {
		return getHospital;
	}

	public void setGetHospital(Boolean getHospital) {
		this.getHospital = getHospital;
	}

}
