package com.net.toooen.service;

import com.net.toooen.api.request.goodsAddress.AddGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.DeleteGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.EditGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.GoodsAddrListRequest;
import com.net.toooen.api.response.Response;


public interface GoodsAddressService {

	/**
     * 我的收件地址列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP028","userId":用户ID,"isMain":是否默认}
     * <p>
     * R:{"resultMap":{
			"goodsAddress":
			{
				"id":id,
				"ganame":"收件人",
				"gaaddress":"收件地址",
				"gamoblie":"收件人电话",
				"title":"部门(又名科室)",
				"hospname":"医院名称",
				"ismain":"是否为默认",
				"code":邮编
			}
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response goodsAddrList(GoodsAddrListRequest req);
	
	/**
     * 添加收件地址
     * 
     * <p>
     * DEMO: text={"msgId":"APP029","userId":用户ID,"gaName":"收件人","gaAddress":"收件地址","gaMoblie":"收件人电话",
                    "dept":"部门(又名科室)","workunit":"医院名称","isMain":是否默认,"code":邮编}
     * <p>
     * R:{"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
    public Response goodsAddrAdd(AddGoodsAddrRequest req);

	/**
     * 编辑收件地址
     * 
     * <p>
     * DEMO: text={"msgId":"APP030","id":id,"userId":用户ID,"gaName":"收件人","gaAddress":"收件地址","gaMoblie":"收件人电话",
     				"dept":"部门(又名科室)","workunit":"医院名称","isMain":是否默认,"code":邮编}
     * <p>
     * R:{"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response goodsAddrEdit(EditGoodsAddrRequest req);


	/**
     * 删除收件地址
     * 
     * <p>
     * DEMO: text={"msgId":"APP031","id":id}
     * <p>
     * R:{"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
    public Response goodsAddrDelete(DeleteGoodsAddrRequest req);

}
