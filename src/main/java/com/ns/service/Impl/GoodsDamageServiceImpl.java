package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.IGoodsDamageDao;
import com.ns.dao.IStockInventoryDao;
import com.ns.entity.DamageList;
import com.ns.entity.DamageListGoods;
import com.ns.entity.Goods;
import com.ns.service.IGoodsDamageService;

@Service
@Transactional
public class GoodsDamageServiceImpl implements IGoodsDamageService {

	@Autowired
	private IGoodsDamageDao	goodsDamageDao;

	@Autowired
	private IStockInventoryDao stockInventoryDao;
	
	/**
	 * 报损单以及商品入库
	 */
	@Override
	public int GoodsDamageSave(DamageList damageList, List<DamageListGoods> damageListGoods) {
		//保存报损单
		int DamageBillResult=goodsDamageDao.SaveDamageBill(damageList);
		if(DamageBillResult<=0){
			return 0;
		}
		int SavedamageGoodsResult=0;
		//保存报损实体
		for(int i=0;i<damageListGoods.size();i++){
			damageListGoods.get(i).setDamageList(damageList);
			SavedamageGoodsResult+=goodsDamageDao.SaveDamageListGoods(damageListGoods.get(i));
			Goods goods=new Goods();
			goods=stockInventoryDao.QueryGoods(damageListGoods.get(i).getGoodsId());
			int num=goods.getInventoryQuantity()-damageListGoods.get(i).getNum();
			goods.setInventoryQuantity(num);
			goods.setState(2);
			//修改库存以及状态
			if(SavedamageGoodsResult>i){
				goodsDamageDao.UpdateInventoryQuantity(goods);
			}
			
		}
		return SavedamageGoodsResult;
	}

	
	/**
	 * 查询报损单
	 */
	@Override
	public List<DamageList> QueryDamage(DamageList damageList) {
		return goodsDamageDao.QueryDamage(damageList);
	}


	/**
	 * 查询报损单商品实体
	 */
	@Override
	public List<DamageListGoods> QueryDamageListGoods(DamageList damageList) {
		return goodsDamageDao.SelectDamageListGoods(damageList);
	}
	
	
	
	
	
}
