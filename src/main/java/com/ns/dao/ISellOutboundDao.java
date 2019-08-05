package com.ns.dao;

import java.util.List;

import com.ns.entity.Customer;
import com.ns.entity.Goods;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;

public interface ISellOutboundDao {

	List<Customer> QueryCustomer();

	int SaveBill(SaleList saleList);

	void SavesaleListGoods(SaleListGoods saleListGoods);

	Goods QueryGoods(Integer goodsId);

	void UpdateInventoryQuantity(Goods goods);

}
