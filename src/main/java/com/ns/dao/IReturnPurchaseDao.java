package com.ns.dao;

import java.util.List;

import com.ns.entity.ReturnList;

public interface IReturnPurchaseDao {

	List<ReturnList> searchReturn(ReturnList returnList);

	void DeleteReturnListGoods(ReturnList returnList);

	int DeleteReturnList(ReturnList returnList);

}
