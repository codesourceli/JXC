package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.IStockInventoryDao;
import com.ns.dao.ReturnOutBoundDao;
import com.ns.entity.Goods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.service.IReturnOutBoundService;

@Service
@Transactional
public class ReturnOutBoundServiceImpl implements IReturnOutBoundService {

	@Autowired
	private ReturnOutBoundDao returnOutBoundDao;
	
	@Autowired
	private IStockInventoryDao stockInventoryDao;
	/**
	 * 退货出库
	 */
	@Override
	public int StockRemoval(ReturnList returnList, List<ReturnListGoods> returnListGoods) {
		//1、保存退货单
		int ReturnResultBill = returnOutBoundDao.SaveReturnBill(returnList);
		int EntityReturn =0;
		if(ReturnResultBill>0){
			//2、商品实体出库
			for(int i=0;i<returnListGoods.size();i++){
				returnListGoods.get(i).setReturnList(returnList);
				EntityReturn+=returnOutBoundDao.SaveReturnEntity(returnListGoods.get(i));
				Goods goods=new Goods();
				goods=stockInventoryDao.QueryGoods(returnListGoods.get(i).getGoodsId());
				int num=goods.getInventoryQuantity()-returnListGoods.get(i).getNum();
				goods.setInventoryQuantity(num);
				goods.setState(2);
				//修改库存
				returnOutBoundDao.UpdateInventoryQuantity(goods);
			}
			
		}
		int total=ReturnResultBill+EntityReturn;
		return total;
	}
	
	
}
