package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.ICustomerReturnBillDao;
import com.ns.entity.CustomerReturnList;
import com.ns.service.ICustomerReturnBillService;

@Service
@Transactional
public class CustomerReturnBillServiceImpl implements ICustomerReturnBillService {

	@Autowired
	private ICustomerReturnBillDao customerReturnBillDao;

	/**
	 * 查询客户退货单据
	 */
	@Override
	public List<CustomerReturnList> SearchCustomerReturnBill(CustomerReturnList customerReturnList) {
		return customerReturnBillDao.SearchCustomerReturnBill(customerReturnList);
	}

	/**
	 * 删除单据
	 */
	@Override
	public int deleteBill(CustomerReturnList customerReturnList) {
		customerReturnBillDao.deleteCustomerReturnListGoods(customerReturnList);
		int result=customerReturnBillDao.deleteBill(customerReturnList);
		return result;
	} 
	
	
}
