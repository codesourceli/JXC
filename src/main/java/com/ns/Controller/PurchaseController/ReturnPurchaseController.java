package com.ns.Controller.PurchaseController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.entity.Log;
import com.ns.entity.ReturnList;
import com.ns.entity.User;
import com.ns.service.ILogService;
import com.ns.service.IReturnPurchaseService;


/**
 * 退货单据查询
 * @author 李安
 *
 */
@Controller
@RequestMapping("/ReturnPurchase")
public class ReturnPurchaseController {

	@Autowired
	private IReturnPurchaseService returnPurchaseService;
	
	@Autowired
	private ILogService logService;
	/**
	 * 查询退货单据
	 * @param returnList
	 * @return
	 */
	@RequestMapping("/searchReturn")
	@ResponseBody
	public Object searchReturn(ReturnList returnList,HttpSession session){
		User user=(User) session.getAttribute("user");
		List<ReturnList> list=returnPurchaseService.searchReturn(returnList);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询退货单据"));
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setUser(user);
		}
		return list;
	}
	
	/**
	 * 删除单据
	 * @param returnList
	 * @return
	 */
	@RequestMapping("/deleteReturn")
	@ResponseBody
	public Object deleteReturn(ReturnList returnList){
		System.out.println(returnList);
		int result = returnPurchaseService.deleteReturn(returnList);
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除退货单据"));
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("删除成功");
			json.setSuccess(true);
			return json;
		}else{
			JsonUtil json=new JsonUtil();
			json.setMsg("删除失败");
			json.setSuccess(false);
			return json;
		}
	}
	
	
}
