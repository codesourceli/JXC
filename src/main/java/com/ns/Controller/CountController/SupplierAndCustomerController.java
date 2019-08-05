package com.ns.Controller.CountController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.Log;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;
import com.ns.service.ILogService;
import com.ns.service.ISupplierAndCustomerService;


/**
 * 供应商与客户统计管理
 * @author 李安
 *
 */
@Controller
@RequestMapping("/SupplierAndCuntomerCount")
public class SupplierAndCustomerController {

	@Autowired
	private ISupplierAndCustomerService supplierAndCustomerService;

	@Autowired
	private ILogService logService;
	
	/**
	 * 供应商统计
	 * @param purchaseList
	 * @param returnList
	 * @param begin
	 * @param end
	 * @return
	 */
	@RequestMapping("/SearchSupplierCount")
	@ResponseBody
	public Object SearchSupplierCount(PurchaseList purchaseList,ReturnList returnList,String begin,String end){
		List<PurchaseList> purchaselist=supplierAndCustomerService.CountPurchaseList(purchaseList,begin,end);
		List<ReturnList> returnlist=supplierAndCustomerService.CountReturnList(returnList,begin,end);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("purchaselist", purchaselist);
		map.put("returnlist", returnlist);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"供应商统计"));
		return map;
	}

	
	
	
	@RequestMapping("/QueryBillGoods")
	@ResponseBody
	public Object QueryBillGoods(Integer id,String type){
		if(type.equals("进货单")){
			List<PurchaseListGoods> list=supplierAndCustomerService.QueryPurchaseListGoodsBillGoods(id);
			return list;
		}
		if(type.equals("退货单")){
			List<ReturnListGoods> list=supplierAndCustomerService.QueryReturnListGoodsBillGoods(id);
			return list;
		}
		if(type.equals("销售单")){
			List<SaleListGoods> list=supplierAndCustomerService.QuerySaleListBillGoods(id);
			return list;
		}
		if(type.equals("客户退货单")){
			List<CustomerReturnListGoods> list=supplierAndCustomerService.QueryCustomerReturnListBillGoods(id);
			return list;
		}
		return null;
	}
	
	
	
	/**
	 * 客户统计
	 * @param saleList
	 * @param customerReturnList
	 * @param begin
	 * @param end
	 * @return
	 */
	@RequestMapping("/SearchCuntomerCount")
	@ResponseBody
	public Object SearchCuntomerCount(SaleList saleList,CustomerReturnList customerReturnList,String begin,String end){
		List<SaleList> salelist=supplierAndCustomerService.CountSaleList(saleList,begin,end);
		List<CustomerReturnList> customerReturnlist=supplierAndCustomerService.CountCustomerReturnList(customerReturnList,begin,end);
		System.out.println(salelist);
		System.out.println(customerReturnlist);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("salelist", salelist);
		map.put("customerReturnlist", customerReturnlist);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"客户统计"));
		return map;
	}
	
	
	
	
	
	
	
	
}
