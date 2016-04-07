package com.net.toooen.service;

import com.net.toooen.api.request.news.NewsListRequest;
import com.net.toooen.api.request.news.NewsSelectNumRequest;
import com.net.toooen.api.response.Response;


public interface NewsService {

	/**
     * 资讯列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP013",
                   "newsGroup":资讯群体;0为患者,1为医生,
                   "isTop":是否置顶;主页资讯,
                   "newsType":资讯类型;1为会诊服务,2为检测服务,3国内资讯,
                   "pageIndex":当前页
              }
     * <p>
     * R:{"resultMap":{
			"newsLists":[ 
			{
				"id":资讯ID,
				"title":"标题",
				"titleimage":"标题图片(此处返回的是一个字符串数组,后续或许会做成集图效果.如:["news/**.jpg",""],但目前只有一张图片)",
				"newsurl":资讯html页面,
				"selectnum":查看个数,
				"createdate":"创建时间"
			}..
		   ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response newsList(NewsListRequest req);
	
	 /**
     * 资讯查看次数
     * 
     * <p>
     * DEMO: text={"msgId":"APP039","newsId":资讯ID,"userId":用户ID}
     * <p>
     * R:{"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response newsSelectNum(NewsSelectNumRequest req);

}
