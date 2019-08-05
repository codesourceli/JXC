package com.ns.dao;

import java.util.List;

import com.ns.entity.CustomerReturnList;

public interface ICustomerReturnBillDao {

	List<CustomerReturnList> SearchCustomerReturnBill(CustomerReturnList customerReturnList);

	void deleteCustomerReturnListGoods(CustomerReturnList customerReturnList);

	int deleteBill(CustomerReturnList customerReturnList);

}
