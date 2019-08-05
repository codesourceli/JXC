package com.ns.dao;

import com.ns.entity.Goods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;

public interface ReturnOutBoundDao {

	int SaveReturnBill(ReturnList returnList);

	int SaveReturnEntity(ReturnListGoods returnListGoods);

	void UpdateInventoryQuantity(Goods goods);

}
