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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ns.Util.DateUtils;
import com.ns.Util.JacksonUtil;
import com.ns.Util.JsonUtil;
import com.ns.entity.Customer;
import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.Log;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.User;
import com.ns.service.ILogService;
import com.ns.service.ISaleReturnService;

/**
 * 客户退货
 * @author 李安
 *
 */
@Controller
@RequestMapping("SaleReturn")
public class SaleReturnController {
	
	@Autowired
	private ISaleReturnService saleReturnService;

	@Autowired
	private ILogService logService;
	/**
	 * 客户退货单号
	 * @return
	 */
	@RequestMapping("/dh")
	@ResponseBody
	public String dh(){
		String XT="XT";
		String dh_date=DateUtils.currentYYYYMMDDHHmmss();
		Integer dh_random=RandomUtils.nextInt(10, 99);
		String dh=XT+dh_date+dh_random;
		return dh;
	}
	
	
	/**
	 * 退货 
	 * @param object
	 * @param session
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/ReturnGoods")
	@ResponseBody
	public Object Outbound(@RequestBody List<Object> object,HttpSession session) throws JsonParseException, JsonMappingException, IOException{
		User user=(User) session.getAttribute("user");
		List<CustomerReturnListGoods> customerReturnListGoods=new ArrayList<CustomerReturnListGoods>();
		CustomerReturnList customerReturnList=new CustomerReturnList();
		Customer customer=new Customer();
		for (int i=0;i<object.size();i++) {
			Object obj=object.get(i);
			if(i==0){
				customerReturnListGoods=JacksonUtil.<CustomerReturnListGoods>jsonConverList(obj,CustomerReturnListGoods.class);
			}
			if(i==1){
				customerReturnList=JacksonUtil.<CustomerReturnList>jsonConverObject(obj,CustomerReturnList.class);
			}
			if(i==2){
				customer=JacksonUtil.<Customer>jsonConverObject(obj,Customer.class);
			}
		} 
		customerReturnList.setUser(user);
		customerReturnList.setCustomer(customer);
		
		System.out.println(customerReturnListGoods);
		System.out.println(customerReturnList);
		System.out.println(customer);
		int result = saleReturnService.ReturnGoods(customerReturnList,customerReturnListGoods);
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"客户退货"));

		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("保存退货单成功");
			json.setSuccess(true);
			return json;
		}
		
		return null;
		
	}
	
	
}
