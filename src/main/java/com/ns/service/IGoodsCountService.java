package com.ns.service;

import java.text.ParseException;
import java.util.List;

import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.SaleCount;
import com.ns.entity.SaleListGoods;

public interface IGoodsCountService {

	List<PurchaseListGoods> PurchaselistGoodsCount(PurchaseListGoods purchaseListGoods, String begin, String end);

	List<ReturnListGoods> PurchaselistGoodsCount(ReturnListGoods returnListGoods, String begin, String end);

	List<SaleListGoods> SaleListGoodsCount(SaleListGoods saleListGoods, String begin, String end);

	List<CustomerReturnListGoods> CustomerReturnListGoodsCount(CustomerReturnListGoods customerReturnListGoods,String begin, String end);

	List<SaleCount> QuerySaleDayCount(String begin, String end, String type) throws ParseException;

}
