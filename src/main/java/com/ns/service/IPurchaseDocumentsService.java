package com.ns.service;

import java.util.List;

import com.ns.entity.PurchaseList;

public interface IPurchaseDocumentsService {

	List<PurchaseList> searchPurchase(PurchaseList purchaseList);

	int deletePurchase(PurchaseList purchaseList);

}
