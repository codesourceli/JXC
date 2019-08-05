package com.ns.dao;

import java.util.List;

import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;

public interface IPurchaseDocumentsDao {

	List<PurchaseList> searchPurchase(PurchaseList purchaseList);

	int deletePurchase(PurchaseList purchaseList);

	int deletePurchaseGoodslist(PurchaseList purchaseList);

}
