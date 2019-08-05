package com.ns.Controller.BasicDataManageController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.Util.PageUtil;
import com.ns.entity.Goods;
import com.ns.entity.GoodsUnit;
import com.ns.entity.Log;
import com.ns.service.IGoodsManageService;
import com.ns.service.ILogService;

/**
 * 商品管理
 * @author 李安
 *
 */

@Controller
@RequestMapping("/GoodsManage")
public class GoodsManageController {
	
	@Autowired
	private IGoodsManageService goodsManageService; 
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 查询商品获取最后一条记录 经过修改获取商品编码
	 * @return
	 */
	@RequestMapping("/QueryCode")
	@ResponseBody
	public Object QueryCode(){
		Goods goods =goodsManageService.SearchCode();
		if(goods.getId()!=null){
			String code=goods.getCode();
			int index=code.lastIndexOf("0");
			String cdh=code.substring(index+1);
			String cdq=code.substring(0,index+1);
			Integer cc=Integer.parseInt(cdh)+1;
			String zero="";
			for(int i=0;i<cdq.length();i++){
				zero=zero+"0";
			}
			return zero+cc;
		}
		return null;
	}

	
	@RequestMapping("/unit")
	@ResponseBody
	public Object unit(){
		List<GoodsUnit> unit=goodsManageService.SearchAllUnit(); 
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询商品单位"));
		return unit;
	}
	
	/**
	 * 添加商品单位
	 * @param unit
	 * @return
	 */
	@RequestMapping("/AddGoodsUnit")
	@ResponseBody
	public Object AddGoodsUnit(GoodsUnit unit){
		 int result =goodsManageService.AddGoodsUnit(unit); 
		 if(result>0){
			 JsonUtil json=new JsonUtil();
			 json.setMsg("添加成功");
			 json.setSuccess(true);
			 json.setObj(unit);
			 return json;
		 }
		 logService.SaveLog(new Log(Log.ADD_ACTION,"添加商品单位"));
		return null;
	}

	/**
	 * 删除商品单位
	 * @param unit
	 * @return
	 */
	@RequestMapping("/DeleteUnit")
	@ResponseBody
	public Object DeleteUnit(GoodsUnit unit){
		int result =goodsManageService.DeleteUnit(unit); 
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("删除成功");
			json.setSuccess(true);
			json.setObj(unit);
			return json;
		}
		 logService.SaveLog(new Log(Log.DELETE_ACTION,"删除商品单位"));
		return null;
	}
	
	/**
	 * 添加商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/saveGoods")
	@ResponseBody
	public Object saveGoods(Goods goods){
		System.out.println(goods);
		int result = goodsManageService.saveGoods(goods);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("保存成功");
			json.setSuccess(true);
			return json;
		}
		 logService.SaveLog(new Log(Log.ADD_ACTION,"添加商品"));
		return null;
	}
	
	/**
	 * 修改商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/UpdateGoods")
	@ResponseBody
	public Object UpdateGoods(Goods goods){
		System.out.println(goods);
		int result = goodsManageService.UpdateGoods(goods);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("修改成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"修改商品"));
		return null;
	}
	
	/**
	 * 删除商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/DeleteGoods")
	@ResponseBody
	public Object DeleteGoods(Goods goods){
		System.out.println(goods);
		int result = goodsManageService.DeleteGoods(goods);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("删除成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.ADD_ACTION,"删除商品"));
		return null;
	}
	
	/*  期初库存   */
	
	/**
	 * 查询期初库存商品信息
	 * @param goods
	 * @param page
	 * @return
	 */
	@RequestMapping("/QueryStockGoods")
	@ResponseBody
	public Object QueryStockGoods(Goods goods,PageUtil page){
		List<Goods> goodsList=goodsManageService.QueryStockGoods(goods,page);
		int total=goodsManageService.QueryStockGoodsTotal();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", goodsList);
		map.put("total", total);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询期初库存商品信息"));
		return map;
	}

	/**
	 * 获得期初库存
	 * @param page
	 * @return
	 */
	@RequestMapping("/QueryInventoryGoods")
	@ResponseBody
	public Object QueryInventoryGoods(PageUtil page){
		List<Goods> goods=goodsManageService.QueryInventoryGoods(page);
		int total=goodsManageService.QueryInventoryGoodsTotal();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", goods);
		map.put("total", total);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"获得期初库存"));
		return map;
	}
	
	/**
	 * 期初增加商品到仓库
	 * @param goods
	 * @return
	 */
	@RequestMapping("/AddToRepository")
	@ResponseBody
	public Object AddToRepository(Goods goods){
		int AddToRepository = goodsManageService.AddToRepository(goods);
		if(AddToRepository>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("增加成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"期初增加商品到仓库"));
		return null;
	}
	
	/**
	 * 期初修改数量或成本价
	 * @param goods
	 * @return
	 */
	@RequestMapping("/UpdateNumOrPrice")
	@ResponseBody
	public Object UpdateNumOrPrice(Goods goods){
		int AddToRepository = goodsManageService.AddToRepository(goods);
		if(AddToRepository>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("修改成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"期初修改数量或成本价"));
		return null;
	}
	
	
	/**
	 * 删除期库存内的商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/deleteStorage")
	@ResponseBody
	public Object deleteStorage(Goods goods){
		int AddToRepository = goodsManageService.deleteStorage(goods);
		if(AddToRepository>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("修改成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除期库存内的商品"));
		return null;
	}
	
	
	
	
	


}
