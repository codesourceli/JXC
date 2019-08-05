package com.ns.service;

import java.util.List;

import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;

public interface ISupplierAndCustomerService {

	List<PurchaseList> CountPurchaseList(PurchaseList purchaseList, String begin, String end);

	List<ReturnList> CountReturnList(ReturnList returnList, String begin, String end);

	List<PurchaseListGoods> QueryPurchaseListGoodsBillGoods(Integer id);

	List<ReturnListGoods> QueryReturnListGoodsBillGoods(Integer id);

	List<SaleList> CountSaleList(SaleList saleList, String begin, String end);

	List<CustomerReturnList> CountCustomerReturnList(CustomerReturnList customerReturnList, String begin, String end);

	List<SaleListGoods> QuerySaleListBillGoods(Integer id);

	List<CustomerReturnListGoods> QueryCustomerReturnListBillGoods(Integer id);

}
