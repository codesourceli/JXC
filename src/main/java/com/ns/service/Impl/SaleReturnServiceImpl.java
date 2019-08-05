package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.ISaleReturnDao;
import com.ns.dao.IStockInventoryDao;
import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.Goods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.service.ISaleReturnService;

@Service
@Transactional
public class SaleReturnServiceImpl implements ISaleReturnService {
	
	
	@Autowired
	private ISaleReturnDao saleReturnDao;

	@Autowired
	private IStockInventoryDao stockInventoryDao;

	
	@Override
	public int ReturnGoods(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> customerReturnListGoods) {

		//保存退货单
		int savebillReturnResult=saleReturnDao.SaveReturnBill(customerReturnList);
		int SaveReturnGoodsResult=0;
		if(savebillReturnResult<=0){
			return 0;
		}
		//、保存退货每个实体商品单 ()
		for(int i=0;i<customerReturnListGoods.size();i++){
			customerReturnListGoods.get(i).setCustomerReturnList(customerReturnList);
			SaveReturnGoodsResult+=saleReturnDao.SaveReturnListGoods(customerReturnListGoods.get(i));
			Goods goods=new Goods();
			goods=stockInventoryDao.QueryGoods(customerReturnListGoods.get(i).getGoodsId());
			int num=goods.getInventoryQuantity()+customerReturnListGoods.get(i).getNum();
			goods.setInventoryQuantity(num);
			goods.setState(2);
			//修改库存
			if(SaveReturnGoodsResult>i){
				saleReturnDao.UpdateInventoryQuantity(goods);
			}
			
		}
		return SaveReturnGoodsResult;
	}
	
}
