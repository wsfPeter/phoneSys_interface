package com.net.toooen.api.request.news;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: NewsSelectNumRequest
 * @Description: 新闻查看次数请求参数
 * @author wangshaofeng
 * @date 2016年3月20日 下午4:17:37
 */
public class NewsSelectNumRequest extends Request {

	private static final long serialVersionUID = 7586245769047041158L;

	/**
	 * @Fields newsId : 新闻ID
	 */
	private Integer newsId;

	/**
	 * @Fields userId : 用户Id
	 */
	private Integer userId;

	@NotNull
	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
