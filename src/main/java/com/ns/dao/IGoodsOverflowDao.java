package com.ns.dao;

import java.util.List;

import com.ns.entity.Goods;
import com.ns.entity.OverflowList;
import com.ns.entity.OverflowListGoods;

public interface IGoodsOverflowDao {

	int SaveOverflowBill(OverflowList overflowList);

	int SaveOverflowListGoods(OverflowListGoods overflowListGoods);

	void UpdateInventoryQuantity(Goods goods);

	List<Goods> QueryAlarmGoods();

	List<OverflowList> QueryOverflow(OverflowList overflow);

	List<OverflowListGoods> SelectOverflowListGoods(OverflowList overflow);

}
