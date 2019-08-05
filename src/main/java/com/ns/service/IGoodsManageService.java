package com.ns.service;

import java.util.List;

import com.ns.Util.PageUtil;
import com.ns.entity.Goods;
import com.ns.entity.GoodsUnit;

public interface IGoodsManageService {

	Goods SearchCode();

	List<GoodsUnit> SearchAllUnit();

	int AddGoodsUnit(GoodsUnit unit);

	int DeleteUnit(GoodsUnit unit);

	int saveGoods(Goods goods);

	int UpdateGoods(Goods goods);

	int DeleteGoods(Goods goods);

	List<Goods> QueryStockGoods(Goods goods, PageUtil page);

	List<Goods> QueryInventoryGoods(PageUtil page);

	int QueryInventoryGoodsTotal();

	int QueryStockGoodsTotal();

	int AddToRepository(Goods goods);

	int deleteStorage(Goods goods);


}
