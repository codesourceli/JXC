package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.Util.PageUtil;
import com.ns.entity.Goods;
import com.ns.entity.GoodsType;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.Supplier;

public interface IStockInventoryDao {

	List<Goods> QueryCommodity(PageUtil page);

	List<Supplier> QuerySupplier();

	List<Goods> QueryGoodsWithparameters(@Param(value = "goodsType") GoodsType goodsType,@Param(value = "page") PageUtil page);

	long QueryGoodsWithparametersTotal(GoodsType goodsType);

	int AddGoodsType(GoodsType goodsType);

	void SetParentNode(GoodsType goodsType);

	int EmptyModifier(GoodsType goodsType);

	int DeleteGoodsType(GoodsType goodsType);

	long queryParamsWithCoderOrNameTotal(Goods goods);

	List<Goods> queryParamsWithCoderOrName(@Param(value = "goods") Goods goods,@Param(value = "page") PageUtil page);

	Goods queryGooodsWithId(Goods goods);
	
	int PutInStorageList(PurchaseList purchaseList);
	
	int PutInStorageListGoods(PurchaseListGoods purchaseListGoods);

	Goods QueryGoods(Integer id);

	int UpdateGoods(Goods newgoods);

	


}
