package com.ns.Controller.SellManageController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.entity.CustomerReturnList;
import com.ns.entity.Log;
import com.ns.service.ICustomerReturnBillService;
import com.ns.service.ILogService;

/**
 * 客户退单查询
 * @author 李安
 *
 */
@Controller
@RequestMapping("/CustomerReturnBill")
public class CustomerReturnBillController {

	@Autowired
	private ICustomerReturnBillService customerReturnBillService;

	@Autowired
	private ILogService logService;
	
	@RequestMapping("/SearchCustomerReturnBill")
	@ResponseBody
	public Object SearchCustomerReturnBill(CustomerReturnList customerReturnList){
		List<CustomerReturnList> list = customerReturnBillService.SearchCustomerReturnBill(customerReturnList);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询客户退货单据"));
		return list;
	}
	
	@RequestMapping("/deleteBill")
	@ResponseBody
	public Object deleteBill(CustomerReturnList customerReturnList){
		int result = customerReturnBillService.deleteBill(customerReturnList);
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除客户退货单据"));
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("删除成功");
			json.setSuccess(true);
			return json;
		}
		return null;
	}
	
	
}
