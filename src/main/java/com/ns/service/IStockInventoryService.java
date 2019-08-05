package com.ns.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.Util.PageUtil;
import com.ns.entity.Goods;
import com.ns.entity.GoodsType;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.Supplier;

public interface IStockInventoryService {

	List<Goods> QueryCommodity(PageUtil page);

	List<Supplier> QuerySupplier();

	List<Goods> QueryGoodsWithparameters(@Param(value="goodsType") GoodsType goodsType,@Param(value="page") PageUtil page);

	long QueryGoodsWithparametersTotal(GoodsType goodsType);

	int AddGoodsType(GoodsType goodsType);

	void SetParentNode(GoodsType goodsType);

	int GoodsModifier(GoodsType goodsType);

	List<Goods> queryParamsWithCoderOrName(Goods goods, PageUtil page);

	long queryParamsWithCoderOrNameTotal(Goods goods);

	Goods queryGooodsWithId(Goods goods);

	int PutInStorage(PurchaseList purchaseList, List<PurchaseListGoods> purchaseListGoods);



}
