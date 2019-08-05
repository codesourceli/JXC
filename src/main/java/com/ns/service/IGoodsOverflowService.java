package com.ns.service;

import java.util.List;

import com.ns.entity.Goods;
import com.ns.entity.OverflowList;
import com.ns.entity.OverflowListGoods;

public interface IGoodsOverflowService {

	int GoodsOverflowSave(OverflowList overflowList, List<OverflowListGoods> overflowListGoods);

	List<Goods> QueryAlarmGoods();

	List<OverflowList> QueryOverflow(OverflowList overflow);

	List<OverflowListGoods> QueryOverflowListGoods(OverflowList overflow);

}
