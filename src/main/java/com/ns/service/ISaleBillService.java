package com.ns.service;

import java.util.List;

import com.ns.entity.SaleList;

public interface ISaleBillService {

	List<SaleList> SearchBill(SaleList saleList);

	int deleteBill(SaleList saleList);

}
