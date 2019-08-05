package com.ns.Controller.PurchaseController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.ns.Util.DateUtils;
import com.ns.Util.JacksonUtil;
import com.ns.Util.JsonUtil;
import com.ns.Util.PageUtil;
import com.ns.dao.AdminUserDao;
import com.ns.entity.DamageListGoods;
import com.ns.entity.Goods;
import com.ns.entity.GoodsType;
import com.ns.entity.Log;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.Supplier;
import com.ns.entity.User;
import com.ns.service.ILogService;
import com.ns.service.IStockInventoryService;


@Controller
@RequestMapping("/StockInventory")
public class StockInventoryController {
	
	@Autowired
	private IStockInventoryService stockInventoryService;
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 初始化进货入库的商品列表
	 * @return
	 */
	@RequestMapping("/stock_main")
	@ResponseBody
	public Object main(PageUtil page ){
		List<Goods>  goods= stockInventoryService.QueryCommodity(page);
		long result =adminUserDao.QueryStockTotal();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", goods);
		map.put("total", result);
		return map;
	}
	
	/**
	 * 查询供货商
	 * @return
	 */
	@RequestMapping("/supplier")
	@ResponseBody
	public Object supplier(){
		List<Supplier> supplier =stockInventoryService.QuerySupplier();
		return supplier;
	}
	
	
	/**
	 * 根据商品类别节点id查询商品
	 * @param goodsType
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryParams")
	@ResponseBody
	public Object queryParams(GoodsType goodsType,PageUtil page){
		long result=stockInventoryService.QueryGoodsWithparametersTotal(goodsType);
		if(result>0){
			List<Goods> goods=stockInventoryService.QueryGoodsWithparameters(goodsType,page);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("total", result);
			map.put("rows", goods);
			return map;
		}
		if(result<=0){
			List<Goods> goods=new ArrayList<Goods>();
			return goods;
		}
		return null;
	}
	
	
	/**
	 * 增加商品类别
	 * @param goodsType
	 * @return
	 */
	@RequestMapping("/saveGoodsType")
	@ResponseBody
	public Object saveGoodsType(GoodsType goodsType){
		JsonUtil res=new JsonUtil();
	/*	若有需求，先判断此类别是否存在
		GoodsType checkType=stockInventoryService.CheckType(goodsType);
		if(checkType.getId()==null){
			res.setMsg("保存失败,此类别已存在");
			res.setSuccess(true);
			return res;
		}
		*/
		if(goodsType.getState()==0){
			//设置它的父级节点状态为closed 0
			goodsType.setState(1);
			stockInventoryService.SetParentNode(goodsType);
		}
		goodsType.setState(0);
		goodsType.setIcon("icon-folder");
		int result=stockInventoryService.AddGoodsType(goodsType);
		logService.SaveLog(new Log(Log.ADD_ACTION,"增加类别"));
		if(result>0){
			res.setMsg("保存成功");
			res.setSuccess(true);
			return res;
		}else{
			res.setMsg("保存失败");
			res.setSuccess(true);
			return res;
		}
		
	}
	
	
	/**
	 * 删除类别
	 * @param goodsType
	 * @return
	 */
	@RequestMapping("/DeleteGoodsType")
	@ResponseBody
	public Object DeleteGoodsType(GoodsType goodsType){
		int result = stockInventoryService.GoodsModifier(goodsType);
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除类别"));
		JsonUtil res=new JsonUtil();
		if(result>0){
			res.setMsg("删除成功");
			res.setSuccess(true);
			return res;
		}else{
			res.setMsg("删除失败");
			res.setSuccess(true);
			return res;
		}
	}
	
	
	/**
	 * 根据CoderOrName搜索商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/queryParamsWithCoderOrName")
	@ResponseBody
	public Object queryParamsWithCoderOrName(Goods goods,PageUtil page,HttpSession session){
		User user=(User) session.getAttribute("user");
		long result=stockInventoryService.queryParamsWithCoderOrNameTotal(goods);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询商品信息"));
		if(result>0){
			List<Goods> goodslist = stockInventoryService.queryParamsWithCoderOrName(goods,page);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("total", result);
			map.put("rows", goodslist);
			return map;
		}
		if(result<=0){
			List<Goods> Nullgoods=new ArrayList<Goods>();
			return Nullgoods;
		}
		return null;
	}
	
	@RequestMapping("/CommodityWarehousing")
	@ResponseBody
	public Object CommodityWarehousing(@RequestBody List<Object> object,HttpSession session) throws JsonParseException, JsonMappingException, IOException{
		User user=(User) session.getAttribute("user");
		for (Object object2 : object) {
			System.out.println("object2:"+object2);
		}
		
		List<PurchaseListGoods> purchaseListGoods=new ArrayList<PurchaseListGoods>();
		PurchaseList purchaseList=new PurchaseList();
		Supplier supplier=new Supplier();
		for (int i=0;i<object.size();i++) {
			Object obj=object.get(i);
			if(i==0){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				  JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,PurchaseListGoods.class);// clz.selGenType().getClass() 
				  purchaseListGoods =(List<PurchaseListGoods>)mapper.readValue(jsonString, javaType); 
			}
			if(i==1){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				purchaseList=(PurchaseList)mapper.readValue(jsonString, PurchaseList.class);
			}
			if(i==2){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				supplier=(Supplier)mapper.readValue(jsonString, Supplier.class);
			}
		} 
		purchaseList.setSupplier(supplier);
		purchaseList.setUser(user);
		int result = stockInventoryService.PutInStorage(purchaseList,purchaseListGoods);
		logService.SaveLog(new Log(Log.ADD_ACTION,"添加进货单"));
		if(result==purchaseListGoods.size()+1){
			JsonUtil json=new JsonUtil();
			json.setMsg("入库成功");
			json.setSuccess(true);
			return json;
		}
		return null;
	}
	
	/**
	 * 根据id查询单个商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/queryGooodsWithId")
	@ResponseBody
	public Object queryGooodsWithId(Goods goods){
		goods=stockInventoryService.queryGooodsWithId(goods);
		return goods;
	}
	
	
	/*
	 * 进货单号
	 */
	@RequestMapping("/dh")
	@ResponseBody
	public String dh(){
		String JH="JH";
		String dh_date=DateUtils.currentYYYYMMDDHHmmss();
		Integer dh_random=RandomUtils.nextInt(10, 99);
		String dh=JH+dh_date+dh_random;
		return dh;
	}
	
	

	
	
	
	
}
