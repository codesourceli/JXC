package com.ns.dao;

import java.util.List;

import com.ns.entity.SaleList;

public interface ISaleBillDao {

	List<SaleList> SearchBill(SaleList saleList);

	void deleteSaleListGoods(SaleList saleList);

	int deleteBill(SaleList saleList);


}
