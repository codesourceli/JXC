package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.Util.PageUtil;
import com.ns.entity.Goods;
import com.ns.entity.GoodsUnit;

public interface IGoodsManageDao {

	Goods SearchCode();

	List<GoodsUnit> SearchAllUnit();

	int AddGoodsUnit(GoodsUnit unit);

	int DeleteUnit(GoodsUnit unit);

	int saveGoods(Goods goods);

	int UpdateGoods(Goods goods);

	int DeleteGoods(Goods goods);

	List<Goods> QueryStockGoods(@Param(value="goods") Goods goods,@Param(value="page") PageUtil page);

	List<Goods> QueryInventoryGoods(PageUtil page);

	int QueryStockGoodsTotal();

	int QueryInventoryGoodsTotal();

	int AddToRepository(Goods goods);

	int deleteStorage(Goods goods);


}
