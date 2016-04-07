package com.net.toooen.api.request;

/**
 * @ClassName: HospitalListRequest  
 * @Description: 医院列表请求参数 
 * @author wangshaofeng
 * @date 2016年3月12日 上午8:14:30 
 */
public class HospitalListRequest extends Paging {

	private static final long serialVersionUID = 3845558356213589032L;

	/**
	 * @Fields areaId : 区域ID
	 */
	private Integer areaId;

	/**
	 * @Fields keyword : 关键字(医院名称)搜索
	 */
	private String keyword;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	

}
