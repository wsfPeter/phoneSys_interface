package com.net.toooen.api.request;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: Paging
 * @Description: 分页参数
 * @author wangshaofeng
 * @date 2016年3月13日 下午3:10:24
 */
public class Paging extends Request {

	private static final long serialVersionUID = 3780839617836576191L;

	/**
	 * @Fields isPage : 是否分页
	 */
	private Boolean isPage;

	/**
	 * @Fields pageIndex : 当前面
	 */
	private Integer pageIndex;

	@NotNull
	public Boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

}
