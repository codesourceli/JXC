package com.ns.service;

import java.util.List;

import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;

public interface ISaleReturnService {

	int ReturnGoods(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> customerReturnListGoods);

}
