package com.ns.service;

import java.util.List;

import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;

public interface IReturnOutBoundService {

	int StockRemoval(ReturnList returnList, List<ReturnListGoods> returnListGoods);

}
