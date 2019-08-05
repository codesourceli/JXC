package com.ns.dao;

import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.Goods;

public interface ISaleReturnDao {

	int SaveReturnBill(CustomerReturnList customerReturnList);

	int SaveReturnListGoods(CustomerReturnListGoods customerReturnListGoods);

	void UpdateInventoryQuantity(Goods goods);

}
