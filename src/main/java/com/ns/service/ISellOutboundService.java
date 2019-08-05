package com.ns.service;

import java.util.List;

import com.ns.entity.Customer;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;

public interface ISellOutboundService {

	List<Customer> QueryCustomer();

	int Outbound(SaleList saleList, List<SaleListGoods> saleListGoods);

}
