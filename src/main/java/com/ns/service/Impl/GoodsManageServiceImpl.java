package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.PageUtil;
import com.ns.dao.IGoodsManageDao;
import com.ns.entity.Goods;
import com.ns.entity.GoodsUnit;
import com.ns.service.IGoodsManageService;

@Service
@Transactional
public class GoodsManageServiceImpl implements IGoodsManageService {

	@Autowired
	private IGoodsManageDao goodsManageDao;

	
	/**
	 * 查询商品最后一条记录获取code
	 */
	@Override
	public Goods SearchCode() {
		return goodsManageDao.SearchCode();
	}

	/**
	 * 查询所有商品单位
	 */
	@Override
	public List<GoodsUnit> SearchAllUnit() {
		return goodsManageDao.SearchAllUnit();
	}
	
	/**
	 * 添加商品单位
	 */
	@Override
	public int AddGoodsUnit(GoodsUnit unit) {
		return goodsManageDao.AddGoodsUnit(unit);
	}

	/**
	 * 删除商品单位
	 */
	@Override
	public int DeleteUnit(GoodsUnit unit) {
		return goodsManageDao.DeleteUnit(unit);
	}

	/**
	 * 添加商品
	 */
	@Override
	public int saveGoods(Goods goods) {
		return goodsManageDao.saveGoods(goods);
	}

	/**
	 * 修改商品
	 */
	@Override
	public int UpdateGoods(Goods goods) {
		return goodsManageDao.UpdateGoods(goods);
	}

	/**
	 * 删除商品
	 */
	@Override
	public int DeleteGoods(Goods goods) {
		return goodsManageDao.DeleteGoods(goods);
	}

	@Override
	public List<Goods> QueryStockGoods(Goods goods, PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return goodsManageDao.QueryStockGoods(goods,page);
	}

	@Override
	public List<Goods> QueryInventoryGoods(PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return goodsManageDao.QueryInventoryGoods(page);
	}

	@Override
	public int QueryInventoryGoodsTotal() {
		return goodsManageDao.QueryInventoryGoodsTotal();
	}

	@Override
	public int QueryStockGoodsTotal() {
		return goodsManageDao.QueryStockGoodsTotal();
	}

	/**
	 * 期初增加商品到仓库
	 */
	@Override
	public int AddToRepository(Goods goods) {
		return goodsManageDao.AddToRepository(goods);
	}

	@Override
	public int deleteStorage(Goods goods) {
		return goodsManageDao.deleteStorage(goods);
	}

	
	
	
}
