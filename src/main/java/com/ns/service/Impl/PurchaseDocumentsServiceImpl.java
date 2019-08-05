package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.IPurchaseDocumentsDao;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.service.IPurchaseDocumentsService;

@Service
@Transactional
public class PurchaseDocumentsServiceImpl implements IPurchaseDocumentsService {

	@Autowired
	private IPurchaseDocumentsDao purchaseDocumentsDao;

	/**
	 * 查询进货单据
	 */
	@Override
	public List<PurchaseList> searchPurchase(PurchaseList purchaseList) {
		return purchaseDocumentsDao.searchPurchase(purchaseList);
	}

	
	/**
	 * 删除单据以及该单据的商品实体
	 */
	@Override
	public int deletePurchase(PurchaseList purchaseList) {
		purchaseDocumentsDao.deletePurchaseGoodslist(purchaseList);
		int	resultDeletePurchase = purchaseDocumentsDao.deletePurchase(purchaseList);
		return resultDeletePurchase;
	}
	
}
