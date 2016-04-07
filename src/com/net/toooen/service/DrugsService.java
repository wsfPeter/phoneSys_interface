package com.net.toooen.service;

import com.net.toooen.api.request.drugs.DrugsDetailRequest;
import com.net.toooen.api.request.drugs.DrugsListRequest;
import com.net.toooen.api.request.drugs.DrugsSeekAddRequest;
import com.net.toooen.api.request.drugs.DrugsSeekDetailRequest;
import com.net.toooen.api.request.drugs.DrugsSeekListRequest;
import com.net.toooen.api.response.Response;


public interface DrugsService {

	/**
     * 药品求助列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP033","userId":用户ID,"keyword":"关键字(药品名称)搜索",
                   "pageIndex":当前页
                   }
     * <p>
     * R:{"resultMap":{
            "drugsList":[ 
            {
                "id":id,
                "drugsname":"药品名称",
                "checkstate":处理状态;0为未处理,1为已处理
            }...
          ]
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
    public Response drigsSeekList(DrugsSeekListRequest req);

    /**
     * 药品列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP034","dtId":药品类型ID,"keyword":"关键字(药品名称)搜索","pageIndex":当前页
                   }
     * <p>
     * R:{"resultMap":{
            "drugsList":[ 
            {
                "id":id,
                "drugsname":"药品名称",
                "drugspic":"药品图片"
            }...
          ]
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response drigsList(DrugsListRequest req);

	 /**
     * 药品详情
     * 
     * <p>
     * DEMO: text={"msgId":"APP035","id":药品ID}
     * <p>
     * R:{"resultMap":{
            "drugs":
            {
                "id":id,
                "dtId":药品类型;1化疗药物,2为靶向药特,3为抗感染药物,4为其他
                "drugsname":"药品名称",
                "drugspic":"药品图片",
                "drugsexplain":"药品详情书",
                "remark":"备注"
            }
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response drigsDetail(DrugsDetailRequest req);

	 /**
     * 药品求助添加
     * 
     * <p>
     * DEMO: text={"msgId":"APP036","userId":用户ID,"contact":"联系人","contactPhone":"联系电话",
                   "drugsName":"药品名称","content":"内容","remark":"备注"
                   }
     * <p>
     * R:{code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response drigsSeekAdd(DrugsSeekAddRequest req);

	 /**
     * 药品求助详情
     * 
     * <p>
     * DEMO: text={"msgId":"APP037","id":ID}
     * <p>
     * R:{"resultMap":{
            "drugsSeek":
            {
                "id":id,
                "contact":"联系人",
                "contactphone":"联系电话",
     			"drugsname":"药品名称",
     			"content":"内容",
     			"checkstate":"处理状态;0为未处理,1为已处理",
     			"remark":"备注"
            }
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response drigsSeekDetail(DrugsSeekDetailRequest req);

}
