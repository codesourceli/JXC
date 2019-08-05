package com.ns.Controller.StockManageController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ns.Util.DateUtils;
import com.ns.Util.JacksonUtil;
import com.ns.Util.JsonUtil;
import com.ns.entity.DamageList;
import com.ns.entity.DamageListGoods;
import com.ns.entity.Goods;
import com.ns.entity.Log;
import com.ns.entity.OverflowList;
import com.ns.entity.OverflowListGoods;
import com.ns.entity.User;
import com.ns.service.IGoodsOverflowService;
import com.ns.service.ILogService;
/**
 * 报溢单
 * @author 李安
 *
 */
@Controller
@RequestMapping("/GoodsOverflow")
public class GoodsOverflowController {
	
	@Autowired
	private IGoodsOverflowService goodsOverflowService;
	
	@Autowired
	private ILogService logService;
	/**
	 * 报溢商品入库
	 * @param object
	 * @param session
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/GoodsOverflowSave")
	@ResponseBody
	public Object GoodsDamageSave(@RequestBody List<Object> object,HttpSession session) throws JsonProcessingException{
		User user=(User) session.getAttribute("user");
		OverflowList overflowList=new OverflowList();
		List<OverflowListGoods> overflowListGoods=new ArrayList<OverflowListGoods>();
		for (int i = 0; i < object.size(); i++) {
			Object obj=object.get(i);
			if(i==0){
				overflowListGoods=JacksonUtil.<OverflowListGoods>jsonConverList(obj,OverflowListGoods.class);
			}
			if(i==1){
				overflowList=JacksonUtil.<OverflowList>jsonConverObject(obj,OverflowList.class);
			}
		}
		overflowList.setUser(user);
		System.out.println(overflowList);
		System.out.println(overflowListGoods);
		
		int result = goodsOverflowService.GoodsOverflowSave(overflowList,overflowListGoods);
		logService.SaveLog(new Log(Log.ADD_ACTION,"报溢单入库"));

		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("保存成功");
			json.setSuccess(true);
			return json;	
		}
		return null;
	}
	
	
	
	/**
	 * 报溢单号
	 * @return
	 */
	@RequestMapping("/dh")
	@ResponseBody
	public String dh(){
		String BY="BY";
		String dh_date=DateUtils.currentYYYYMMDDHHmmss();
		Integer dh_random=RandomUtils.nextInt(10, 99);
		String dh=BY+dh_date+dh_random;
		return dh;
	}
	
	
	/**
	 * 查询报溢单
	 * @return
	 */
	@RequestMapping("/QueryOverflow")
	@ResponseBody
	public Object QueryOverflow(OverflowList overflow){
		List<OverflowList> overflowList =goodsOverflowService.QueryOverflow(overflow);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询报溢单"));

		return overflowList;
	}
	
	/**
	 * 查询报溢单及商品实体
	 * @return
	 */
	@RequestMapping("/QueryOverflowListGoods")
	@ResponseBody
	public Object QueryOverflowListGoods(OverflowList overflow){
		List<OverflowListGoods> overflowListGoods =goodsOverflowService.QueryOverflowListGoods(overflow);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询报溢单商品实体"));
		return overflowListGoods;
	}
	
	
	
	
	
	
	/**
	 * 库存报警
	 * @return
	 */
	@RequestMapping("/listAlarm")
	@ResponseBody
	public Object alarm(){
		List<Goods> AlarmGoods=goodsOverflowService.QueryAlarmGoods();
		System.out.println(AlarmGoods);
		return AlarmGoods;
	}

}
