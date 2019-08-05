package com.ns.service;

import java.util.List;

import com.ns.entity.ReturnList;

public interface IReturnPurchaseService {

	List<ReturnList> searchReturn(ReturnList returnList);

	int deleteReturn(ReturnList returnList);

}
