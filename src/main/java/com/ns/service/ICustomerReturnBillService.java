package com.ns.service;

import java.util.List;

import com.ns.entity.CustomerReturnList;

public interface ICustomerReturnBillService {

	List<CustomerReturnList> SearchCustomerReturnBill(CustomerReturnList customerReturnList);

	int deleteBill(CustomerReturnList customerReturnList);

}
