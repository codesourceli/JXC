package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.IGoodsOverflowDao;
import com.ns.dao.IStockInventoryDao;
import com.ns.entity.Goods;
import com.ns.entity.OverflowList;
import com.ns.entity.OverflowListGoods;
import com.ns.service.IGoodsOverflowService;

@Service
@Transactional
public class GoodsOverflowServiceImpl implements IGoodsOverflowService {
	
	@Autowired
	private IGoodsOverflowDao goodsOverflowDao;

	@Autowired
	private IStockInventoryDao stockInventoryDao;
	
	/**
	 * 保存报溢单以及商品
	 */
	@Override
	public int GoodsOverflowSave(OverflowList overflowList, List<OverflowListGoods> overflowListGoods) {
		//保存报溢单
		int OverflowBillResult=goodsOverflowDao.SaveOverflowBill(overflowList);
		if(OverflowBillResult<=0){
			return 0;
		}
		int SavedamageGoodsResult=0;
		//保存报溢实体
		for(int i=0;i<overflowListGoods.size();i++){
			overflowListGoods.get(i).setOverflowList(overflowList);
			SavedamageGoodsResult+=goodsOverflowDao.SaveOverflowListGoods(overflowListGoods.get(i));
			Goods goods=new Goods();
			goods=stockInventoryDao.QueryGoods(overflowListGoods.get(i).getGoodsId());
			int num=goods.getInventoryQuantity()+overflowListGoods.get(i).getNum();
			goods.setInventoryQuantity(num);
			goods.setState(2);
			//修改库存以及状态
			if(SavedamageGoodsResult>i){
				goodsOverflowDao.UpdateInventoryQuantity(goods);
			}
			
		}
		return SavedamageGoodsResult;
	}

	
	/**
	 * 库存报警
	 */
	@Override
	public List<Goods> QueryAlarmGoods() {
		return goodsOverflowDao.QueryAlarmGoods();
	}


	/**
	 * 报溢单
	 */
	@Override
	public List<OverflowList> QueryOverflow(OverflowList overflow) {
		return goodsOverflowDao.QueryOverflow(overflow);
	}

	/**
	 * 报溢单及商品实体
	 */
	@Override
	public List<OverflowListGoods> QueryOverflowListGoods(OverflowList overflow) {
		return goodsOverflowDao.SelectOverflowListGoods(overflow);
	}
	
	
}
