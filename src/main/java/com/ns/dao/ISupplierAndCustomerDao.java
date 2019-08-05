package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;

public interface ISupplierAndCustomerDao {

	List<PurchaseList> CountPurchaseList(@Param(value = "purchaseList") PurchaseList purchaseList,@Param(value = "begin") String begin,@Param(value = "end") String end);

	List<ReturnList> CountReturnList(@Param(value = "returnlist") ReturnList returnlist,@Param(value = "begin") String begin,@Param(value = "end") String end);

	List<PurchaseListGoods> QueryPurchaseListGoodsBillGoods(Integer id);

	List<ReturnListGoods> QueryReturnListGoodsBillGoods(Integer id);

	List<SaleList> CountSaleList(@Param(value = "saleList") SaleList saleList,@Param(value = "begin") String begin,@Param(value = "end") String end);

	List<CustomerReturnList> CountCustomerReturnList(@Param(value = "customerReturnList") CustomerReturnList customerReturnList,@Param(value = "begin") String begin,@Param(value = "end") String end);

	List<CustomerReturnListGoods> QueryCustomerReturnListBillGoods(Integer id);

	List<SaleListGoods> QuerySaleListBillGoods(Integer id);

}
