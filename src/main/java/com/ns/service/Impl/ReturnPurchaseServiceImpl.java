package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.IReturnPurchaseDao;
import com.ns.entity.ReturnList;
import com.ns.service.IReturnPurchaseService;

@Service
@Transactional
public class ReturnPurchaseServiceImpl implements IReturnPurchaseService {

	@Autowired
	private IReturnPurchaseDao returnPurchaseDao;

	/**
	 * 查询退货单据
	 */
	@Override
	public List<ReturnList> searchReturn(ReturnList returnList) {
		return returnPurchaseDao.searchReturn(returnList);
	}

	
	/**
	 * 删除单据以及它的商品实体
	 */
	@Override
	public int deleteReturn(ReturnList returnList) {
		returnPurchaseDao.DeleteReturnListGoods(returnList);
		int result = returnPurchaseDao.DeleteReturnList(returnList);
		return result;
	} 
	
}
