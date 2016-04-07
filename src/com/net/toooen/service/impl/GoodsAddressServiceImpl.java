package com.net.toooen.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.api.request.goodsAddress.AddGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.DeleteGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.EditGoodsAddrRequest;
import com.net.toooen.api.request.goodsAddress.GoodsAddrListRequest;
import com.net.toooen.api.response.Response;
import com.net.toooen.api.response.ResultResponse;
import com.net.toooen.service.GoodsAddressService;
import com.net.toooen.util.JsonUtil;

@Service
public class GoodsAddressServiceImpl implements GoodsAddressService {

	@Override
	public Response goodsAddrList(GoodsAddrListRequest req) {
		StringBuilder sb = new StringBuilder(
				"select id,gaName,gaAddress,gaMoblie,dept,workunit,isMain,code from goods_address where userId = ?");
		ResultResponse resp = new ResultResponse();
		if (null != req.getIsMain()) {
			sb.append(" and isMain = ? order by createDate desc");
			Record record = Db.findFirst(sb.toString(), req.getUserId(),
					req.getIsMain());
			resp.getResultMap().put("goodsAddress",
					JsonUtil.getMapByJfinalRecord(record));
		} else {
			sb.append(" order by createDate desc");
			List<Record> records = Db.find(sb.toString(), req.getUserId());
			resp.getResultMap().put("goodsAddress",
					JsonUtil.getJsonObjByjfinalList(records));
		}
		return resp;
	}

	@Override
	public Response goodsAddrAdd(AddGoodsAddrRequest req) {
		Record record = new Record().set("userId", req.getUserId())
				.set("gaName", req.getGaName())
				.set("gaMoblie", req.getGaMoblie())
				.set("isMain", req.getIsMain()).set("createDate", new Date())
				.set("gaAddress", req.getGaAddress())
				.set("dept", req.getDept()).set("workunit", req.getWorkunit())
				.set("code", req.getCode());

		if (req.getIsMain()) {
			updateGaMain(req.getUserId());
		}

		Db.save("goods_address", record);
		return Response.SUCCESS;
	}
	
	/**
	 * @Title: updateGaMain
	 * @Description: 更新用户收件地址的默认值
	 * @param userId
	 * @return void
	 * @throws
	 */
	private void updateGaMain(Integer userId) {
		String sql = "update goods_address set isMain = false where userId = ?";
		Db.update(sql, userId);
	}


	@Override
	public Response goodsAddrEdit(EditGoodsAddrRequest req) {
		Record record = new Record().set("id", req.getId()).set("isMain",
				req.getIsMain());

		if (StringUtils.isNotEmpty(req.getGaName())) {
			record.set("gaName", req.getGaName());
		}
		if (StringUtils.isNotEmpty(req.getGaMoblie())) {
			record.set("gaMoblie", req.getGaMoblie());
		}
		if (StringUtils.isNotEmpty(req.getGaAddress())) {
			record.set("gaAddress", req.getGaAddress());
		}
		if (StringUtils.isNotEmpty(req.getDept())) {
			record.set("dept", req.getDept());
		}
		if (StringUtils.isNotEmpty(req.getWorkunit())) {
			record.set("workunit", req.getWorkunit());
		}
		if (null != req.getCode()) {
			record.set("code", req.getCode());
		}
		if (req.getIsMain()) {
			updateGaMain(req.getUserId());
		}

		Db.update("goods_address", "id", record);
		return Response.SUCCESS;
	}
	
	@Override
	public Response goodsAddrDelete(DeleteGoodsAddrRequest req) {
		Db.deleteById("goods_address", "id", req.getId());
		return Response.SUCCESS;
	}
	
}