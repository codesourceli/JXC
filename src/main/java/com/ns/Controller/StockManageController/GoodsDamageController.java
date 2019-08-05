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
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.DamageList;
import com.ns.entity.DamageListGoods;
import com.ns.entity.Log;
import com.ns.entity.User;
import com.ns.service.IGoodsDamageService;
import com.ns.service.ILogService;

/**
 * 库存报损
 * @author 李安
 *
 */
@Controller
@RequestMapping("/GoodsDamage")
public class GoodsDamageController {
	
	@Autowired
	private IGoodsDamageService goodsDamageService;
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 报损商品入库
	 * @param object
	 * @param session
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/GoodsDamageSave")
	@ResponseBody
	public Object GoodsDamageSave(@RequestBody List<Object> object,HttpSession session) throws JsonProcessingException{
		User user=(User) session.getAttribute("user");
		DamageList damageList=new DamageList();
		List<DamageListGoods> damageListGoods=new ArrayList<DamageListGoods>();
		for (int i = 0; i < object.size(); i++) {
			Object obj=object.get(i);
			if(i==0){
				damageListGoods=JacksonUtil.<DamageListGoods>jsonConverList(obj,DamageListGoods.class);
			}
			if(i==1){
				damageList=JacksonUtil.<DamageList>jsonConverObject(obj,DamageList.class);
			}
		}
		damageList.setUser(user);
		int result = goodsDamageService.GoodsDamageSave(damageList,damageListGoods);
		logService.SaveLog(new Log(Log.ADD_ACTION,"商品报损入库"));

		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("保存成功");
			json.setSuccess(true);
			return json;	
		}
		return null;
	}
	
	
	/**
	 * 报损单号
	 * @return
	 */
	@RequestMapping("/dh")
	@ResponseBody
	public String dh(){
		String BS="BS";
		String dh_date=DateUtils.currentYYYYMMDDHHmmss();
		Integer dh_random=RandomUtils.nextInt(10, 99);
		String dh=BS+dh_date+dh_random;
		return dh;
	}
	
	
	/**
	 * 查询报损单
	 * @return
	 */
	@RequestMapping("/QueryDamage")
	@ResponseBody
	public Object QueryDamage(DamageList damageList){
		List<DamageList> damagelist = goodsDamageService.QueryDamage(damageList);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询报损单"));
		return damagelist;
	}
	
	/**
	 * 查询报损单商品实体
	 * @return
	 */
	@RequestMapping("/QueryDamageListGoods")
	@ResponseBody
	public Object QueryDamageListGoods(DamageList damageList){
		List<DamageListGoods> damageListGoods = goodsDamageService.QueryDamageListGoods(damageList);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询报损单商品实体"));
		return damageListGoods;
	}
	
	

}
