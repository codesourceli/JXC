package com.ns.Controller.PurchaseController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.entity.Log;
import com.ns.entity.PurchaseList;
import com.ns.entity.User;
import com.ns.service.ILogService;
import com.ns.service.IPurchaseDocumentsService;

@Controller
@RequestMapping("/PurchaseDocuments")
public class PurchaseDocumentsController {

	@Autowired
	private IPurchaseDocumentsService purchaseDocumentsService; 
	
	@Autowired
	private ILogService logService;
	/**
	 * 查询进货单据
	 * @param purchaseList
	 * @return
	 */
	@RequestMapping("/searchPurchase")
	@ResponseBody
	public Object searchPurchase(PurchaseList purchaseList,HttpSession session){
		User user=(User) session.getAttribute("user");
		List<PurchaseList> list  = purchaseDocumentsService.searchPurchase(purchaseList);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询进货单据"));
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setUser(user);
		}
		return list;
	}
	
	/**
	 * 删除单据以及该单据的商品实体
	 * @param purchaseList
	 * @return
	 */
	@RequestMapping("/deletePurchase")
	@ResponseBody
	public Object deletePurchase(PurchaseList purchaseList){
		System.out.println(purchaseList);
		int result = purchaseDocumentsService.deletePurchase(purchaseList);
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除进货单据"));
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setSuccess(true);
			json.setMsg("删除成功");
			return json;
		}else{
			JsonUtil json=new JsonUtil();
			json.setSuccess(false);
			json.setMsg("删除失败");
			return json;
		}
	}
	
}
