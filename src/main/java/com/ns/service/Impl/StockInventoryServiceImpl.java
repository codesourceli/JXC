package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.PageUtil;
import com.ns.dao.IStockInventoryDao;
import com.ns.entity.Goods;
import com.ns.entity.GoodsType;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.Supplier;
import com.ns.service.IStockInventoryService;

@Service
@Transactional
public class StockInventoryServiceImpl implements IStockInventoryService {

	@Autowired
	private IStockInventoryDao stockInventoryDao;

	/**
	 * 初始化进货入库的商品列表
	 */
	@Override
	public List<Goods> QueryCommodity(PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return stockInventoryDao.QueryCommodity(page);
	}

	/**
	 * 查询供货商
	 */
	@Override
	public List<Supplier> QuerySupplier() {
		return stockInventoryDao.QuerySupplier();
	}

	/**
	 * 根据商品类别查询商品的数量
	 */
	@Override
	public long QueryGoodsWithparametersTotal(GoodsType goodsType) {
		return stockInventoryDao.QueryGoodsWithparametersTotal(goodsType);
	}
	
	/**
	 * 根据商品类别查询商品
	 */
	@Override
	public List<Goods> QueryGoodsWithparameters(GoodsType goodsType,PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return  stockInventoryDao.QueryGoodsWithparameters(goodsType,page);
	}

	/**
	 * 增加商品类别
	 */
	@Override
	public int AddGoodsType(GoodsType goodsType) {
		return stockInventoryDao.AddGoodsType(goodsType);
	}

	/**
	 * 设置父级节点状态
	 */
	@Override
	public void SetParentNode(GoodsType goodsType) {
		stockInventoryDao.SetParentNode(goodsType);
	}

	/**
	 * 商品type_id置空
	 * 删除类别
	 */
	@Override
	public int GoodsModifier(GoodsType goodsType) {
		//1、先把关联的当前类别的商品type_id置为空
		int Emptyresult = stockInventoryDao.EmptyModifier(goodsType);
		//2、删除当前类别
		int Deleteresult = stockInventoryDao.DeleteGoodsType(goodsType);
		return Deleteresult;
	}

	/**
	 * 根据codeOrName查询总数量
	 */
	@Override
	public long queryParamsWithCoderOrNameTotal(Goods goods) {
		long result=stockInventoryDao.queryParamsWithCoderOrNameTotal(goods);
		return result;
	}
	
	/**
	 * 根据CoderOrName搜索商品
	 */
	@Override
	public List<Goods> queryParamsWithCoderOrName(Goods goods, PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return stockInventoryDao.queryParamsWithCoderOrName(goods,page);
	}

	
	/**
	 * 根据id查询商品
	 */
	@Override
	public Goods queryGooodsWithId(Goods goods) {
		return stockInventoryDao.queryGooodsWithId(goods);
	}

	/**
	 * 入库
	 */
	@Override
	public int PutInStorage(PurchaseList purchaseList, List<PurchaseListGoods> purchaseListGoods) {
		//1、保存进货单
		int resultBill = stockInventoryDao.PutInStorageList(purchaseList);
		
		int resultEntity=0;
		//2、商品实体入库
		if(resultBill>0){
			for(int i=0;i<purchaseListGoods.size();i++){
				purchaseListGoods.get(i).setPurchaseList(purchaseList);
				resultEntity+=stockInventoryDao.PutInStorageListGoods(purchaseListGoods.get(i));
				 //2.1  修改商品库存 和 成本均价 以及上次进价
					//计算成本均价  得到成本均价结果
				Goods goods=new Goods();
				Goods Newgoods=new Goods();
				goods=stockInventoryDao.QueryGoods(purchaseListGoods.get(i).getGoodsId());
				
				int sum = goods.getInventoryQuantity()+purchaseListGoods.get(i).getNum();
				Newgoods.setInventoryQuantity(sum);
				Newgoods.setLastPurchasingPrice(purchaseListGoods.get(i).getPrice());
				Newgoods.setId(purchaseListGoods.get(i).getGoodsId());
				Newgoods.setPurchasingPrice(purchaseListGoods.get(i).getPrice());
				Newgoods.setState(2);
				/*
				 * 成本均价计算方式处还不理解
				 * if(goods.getPurchasingPrice()!=purchaseListGoods.get(i).getPrice()){
					float purchasing_price=(goods.getPurchasingPrice()*goods.getInventoryQuantity()+purchaseListGoods.get(i).getPrice()*purchaseListGoods.get(i).getNum())/goods.getInventoryQuantity()+purchaseListGoods.get(i).getNum();
					Newgoods.setPurchasingPrice(purchasing_price);
				}*/
				//修改商品    
				int result = stockInventoryDao.UpdateGoods(Newgoods);
				System.out.println(result);
			}
		}
		
		return resultBill+resultEntity;
	}









	
}
