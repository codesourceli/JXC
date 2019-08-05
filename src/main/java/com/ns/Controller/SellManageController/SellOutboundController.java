package com.ns.Controller.SellManageController;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ns.Util.DateUtils;
import com.ns.Util.JacksonUtil;
import com.ns.Util.JsonUtil;
import com.ns.entity.Customer;
import com.ns.entity.Log;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;
import com.ns.entity.Supplier;
import com.ns.entity.User;
import com.ns.service.ILogService;
import com.ns.service.ISellOutboundService;

/**
 * 销售出库
 * @author 李安
 *
 */
@Controller
@RequestMapping("/SellOutbound")
public class SellOutboundController {

	@Autowired
	private ISellOutboundService sellOutboundService; 
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 查询用户
	 * @return
	 */
	@RequestMapping("/QueryCustomer")
	@ResponseBody
	public Object QueryCustomer(){
		List<Customer> customer=sellOutboundService.QueryCustomer();
		return customer;
	}
	
	
	/**
	 * 销售出库单号
	 * @return
	 */
	@RequestMapping("/dh")
	@ResponseBody
	public String dh(){
		String XS="XS";
		String dh_date=DateUtils.currentYYYYMMDDHHmmss();
		Integer dh_random=RandomUtils.nextInt(10, 99);
		String dh=XS+dh_date+dh_random;
		return dh;
	}
	
	
	/**
	 * 出库 
	 * @param object
	 * @param session
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/Outbound")
	@ResponseBody
	public Object Outbound(@RequestBody List<Object> object,HttpSession session) throws JsonParseException, JsonMappingException, IOException{
		User user=(User) session.getAttribute("user");
		List<SaleListGoods> saleListGoods=new ArrayList<SaleListGoods>();
		SaleList saleList=new SaleList();
		Customer customer=new Customer();
		for (int i=0;i<object.size();i++) {
			Object obj=object.get(i);
			if(i==0){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				  JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,SaleListGoods.class);// clz.selGenType().getClass() 
				  saleListGoods =(List<SaleListGoods>)mapper.readValue(jsonString, javaType); 
			}
			if(i==1){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				saleList=(SaleList)mapper.readValue(jsonString, SaleList.class);
			}
			if(i==2){
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(obj);
				customer=(Customer)mapper.readValue(jsonString, Customer.class);
			}
		} 
		saleList.setCustomer(customer);
		saleList.setUser(user);
		System.out.println(saleListGoods);
		int result = sellOutboundService.Outbound(saleList,saleListGoods);
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"销售出库"));
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("出库成功");
			json.setSuccess(true);
			return json;
		}
		
		return null;
		
	}
	
	
	
}
