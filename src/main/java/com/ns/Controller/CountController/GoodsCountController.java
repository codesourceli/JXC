package com.ns.Controller.CountController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.Log;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.SaleCount;
import com.ns.entity.SaleListGoods;
import com.ns.service.IGoodsCountService;
import com.ns.service.ILogService;

/**
 * 商品（采购/销售）统计
 * @author 李安
 *
 */

@Controller
@RequestMapping("/GoodsCount")
public class GoodsCountController {

	@Autowired
	private IGoodsCountService goodsCountService;

	@Autowired
	private ILogService logService;
	
	/**
	 * 采购统计
	 * @param purchaseListGoods
	 * @param returnListGoods
	 * @param begin
	 * @param end
	 * @return
	 */
	@RequestMapping("/PurchaseCount")
	@ResponseBody
	public Object PurchaseCount(PurchaseListGoods purchaseListGoods,ReturnListGoods returnListGoods,String begin,String end){
		List<PurchaseListGoods> purchaselistGoods=goodsCountService.PurchaselistGoodsCount(purchaseListGoods,begin,end);
		List<ReturnListGoods> returnlistGoods=goodsCountService.PurchaselistGoodsCount(returnListGoods,begin,end);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"采购统计"));
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("purchaselistGoods", purchaselistGoods);
		map.put("returnlistGoods", returnlistGoods);
		return map;
	}
	
	
	
	@RequestMapping("/SaleCount")
	@ResponseBody
	public Object SaleCount(SaleListGoods saleListGoods,CustomerReturnListGoods customerReturnListGoods, String begin,String end){
		List<SaleListGoods> salelistgoods=goodsCountService.SaleListGoodsCount(saleListGoods,begin,end);
		List<CustomerReturnListGoods> customerreturnlistgoods=goodsCountService.CustomerReturnListGoodsCount(customerReturnListGoods,begin,end);
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("salelistgoods", salelistgoods);
		map.put("customerreturnlistgoods", customerreturnlistgoods);
		return map;
	}
	
	
	/**
	 * 统计分析
	 * @param begin
	 * @param end
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/SaleDayAndMouthCount")
	@ResponseBody
	public Object SaleDayCount(String begin,String end,String type) throws ParseException{
		List<SaleCount> saleCount=goodsCountService.QuerySaleDayCount(begin,end,type);
		List<String> date=new ArrayList<String>();
		List<Float> profit=new ArrayList<Float>();
		for (int i = 0; i < saleCount.size(); i++) {
			date.add(saleCount.get(i).getDate());
			profit.add(saleCount.get(i).getAmountProfit());
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("date",date);
		map.put("profit",profit);
		map.put("saleCount",saleCount);
		if(type.equals("day")){
			logService.SaveLog(new Log(Log.SEARCH_ACTION,"日统计分析"));
		}else{
			logService.SaveLog(new Log(Log.SEARCH_ACTION,"月统计分析"));
		}
		return map;
	}
	
}






















