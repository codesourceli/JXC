package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.ISellOutboundDao;
import com.ns.dao.IStockInventoryDao;
import com.ns.entity.Customer;
import com.ns.entity.Goods;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;
import com.ns.service.ISellOutboundService;

@Service
@Transactional
public class SellOutboundServiceImpl implements ISellOutboundService{

	@Autowired
	private ISellOutboundDao sellOutboundDao;

	@Autowired
	private IStockInventoryDao stockInventoryDao;

	/**
	 * 查询所有客户
	 */
	@Override
	public List<Customer> QueryCustomer() {
		return sellOutboundDao.QueryCustomer();
	}

	/**
	 * 保存销售出库单以及商品实体
	 */
	@Override
	public int Outbound(SaleList saleList, List<SaleListGoods> saleListGoods) {
		//保存销售出库单
		int savebillResult=sellOutboundDao.SaveBill(saleList);
		if(savebillResult<=0){
			return 0;
		}
		//、保存销售出库的每个实体商品单 ()
		for(int i=0;i<saleListGoods.size();i++){
			saleListGoods.get(i).setSaleList(saleList);
			sellOutboundDao.SavesaleListGoods(saleListGoods.get(i));
			Goods goods=new Goods();
			goods=stockInventoryDao.QueryGoods(saleListGoods.get(i).getGoodsId());
			int num=goods.getInventoryQuantity()-saleListGoods.get(i).getNum();
			goods.setInventoryQuantity(num);
			goods.setState(2);
			sellOutboundDao.UpdateInventoryQuantity(goods);
		}
			
		return savebillResult;
	}
	
}
