package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.Goods;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;

public interface IGoodsCountDao {

	List<PurchaseListGoods> PurchaselistGoodsCount(@Param(value="purchaseListGoods") PurchaseListGoods purchaseListGoods);

	List<PurchaseList> PurchaselistCount(@Param(value="begin") String begin,@Param(value="end") String end);

	List<ReturnListGoods> ReturnListGoodsCount(ReturnListGoods returnListGoods);

	List<ReturnList> ReturnListCount(@Param(value="begin") String begin,@Param(value="end") String end);

	List<SaleListGoods> SaleListGoodsCount(SaleListGoods saleListGoods);

	List<SaleList> SaleListCount(@Param(value="begin") String begin,@Param(value="end") String end);

	List<CustomerReturnListGoods> CustomerReturnListGoodsCount(CustomerReturnListGoods customerReturnListGoods);

	List<CustomerReturnList> CustomerReturnListCount(@Param(value="begin") String begin,@Param(value="end") String end);

	Goods QueryGoodsById(Integer goodsId);
}
