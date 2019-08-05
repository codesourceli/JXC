package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.ISaleBillDao;
import com.ns.entity.SaleList;
import com.ns.service.ISaleBillService;

@Service
@Transactional
public class SaleBillServiceImpl implements ISaleBillService {

	@Autowired
	private ISaleBillDao saleBillDao;

	
	/**
	 * 查找销售单据以及商品实体
	 */
	@Override
	public List<SaleList> SearchBill(SaleList saleList) {
		return saleBillDao.SearchBill(saleList);
	}


	
	/**
	 * 删除单据
	 */
	@Override
	public int deleteBill(SaleList saleList) {
		saleBillDao.deleteSaleListGoods(saleList);
		int result=saleBillDao.deleteBill(saleList);
		return result;
	}
	
}
