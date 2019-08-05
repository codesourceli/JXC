package com.ns.Controller.SellManageController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.entity.SaleList;
import com.ns.entity.User;
import com.ns.service.ISaleBillService;

@Controller
@RequestMapping("/SaleBill")
public class SaleBillController {

	@Autowired
	private ISaleBillService saleBillService;
	
	/**
	 * 销售单据查询
	 * @param saleList
	 * @return
	 */
	@RequestMapping("/SearchBill")
	@ResponseBody
	public Object SearchBill(SaleList saleList,HttpSession session){
		User user=(User) session.getAttribute("user");
		List<SaleList> salelist=saleBillService.SearchBill(saleList);
		for (int i = 0; i < salelist.size(); i++) {
			salelist.get(i).setUser(user);
		}
		return salelist;
	}
	
	
	/**
	 * 删除单据
	 * @return
	 */
	@RequestMapping("/deleteBill")
	@ResponseBody
	public Object deleteBill(SaleList saleList){
		int result = saleBillService.deleteBill(saleList);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setSuccess(true);
			json.setMsg("删除成功");
			return json;
		}
		return null;
	}
	
	
	
	
	
	
}
