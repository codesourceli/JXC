package com.ns.Controller.PurchaseController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ns.Util.DateUtils;
import com.ns.Util.JsonUtil;
import com.ns.entity.Log;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.Supplier;
import com.ns.entity.User;
import com.ns.service.ILogService;
import com.ns.service.IReturnOutBoundService;

@Controller
@RequestMapping("/ReturnOutBound")
public class ReturnOutBoundController {
	
	@Autowired
	private IReturnOutBoundService returnOutBoundService;
	
	@Autowired
	private ILogService logService;
	/*
	 * 退货单号
	 */
	@RequestMapping("/TH")
	@ResponseBody
	public String dh(){
		String TH="TH";
		String dh_date=DateUtils.currentYYYYMMDDHHmmss();
		Integer dh_random=RandomUtils.nextInt(10, 99);
		String dh=TH+dh_date+dh_random;
		return dh;
	}
	
	
	/**
	 * 退货出库
	 * @param object
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/StockRemoval")
	@ResponseBody
	public Object StockRemoval(@RequestBody List<Object> object,HttpSession session) throws IOException{
		User user=(User) session.getAttribute("user");
		
		List<ReturnListGoods> returnListGoods=new ArrayList<ReturnListGoods>();
		ReturnList returnList=new ReturnList();
		Supplier supplier=new Supplier();
		for (int i=0;i<object.size();i++) {
			Object obj=object.get(i);
			if(i==0){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				  JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,ReturnListGoods.class);// clz.selGenType().getClass() 
				  returnListGoods =(List<ReturnListGoods>)mapper.readValue(jsonString, javaType); 
			}
			if(i==1){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				returnList=(ReturnList)mapper.readValue(jsonString, ReturnList.class);
			}
			if(i==2){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				supplier=(Supplier)mapper.readValue(jsonString, Supplier.class);
			}
		} 
		

		returnList.setSupplier(supplier);
		returnList.setUser(user);
		int result =returnOutBoundService.StockRemoval(returnList,returnListGoods);
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"更新商品信息"));
		if(result==returnListGoods.size()+1){
			JsonUtil json=new JsonUtil();
			json.setMsg("出库成功");
			json.setSuccess(true);
			return json;
		}
		return null;
	}

}
