package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.PageUtil;
import com.ns.dao.IBasicDataDao;
import com.ns.entity.Customer;
import com.ns.entity.Supplier;
import com.ns.service.IBasicDataService;

@Service
@Transactional
public class BasicDataServiceImpl implements IBasicDataService {

	@Autowired
	private IBasicDataDao basicDataDao;

	/**
	 * 查询供应商
	 */
	@Override
	public List<Supplier> QuerySupplier(Supplier supplier, PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return basicDataDao.QuerySupplier(supplier,page);
	}

	/**
	 * 查询供应商总数量
	 */
	@Override
	public int QuerySupplierCount() {
		return basicDataDao.QuerySupplierCount();
	}

	/**
	 * 添加供应商
	 */
	@Override
	public int AddSupplier(Supplier supplier) {
		return basicDataDao.AddSupplier(supplier);
	}

	/**
	 * 修改供应商
	 */
	@Override
	public int UpdateSupplier(Supplier supplier) {
		return basicDataDao.UpdateSupplier(supplier);
	}

	/**
	 * 删除供应商
	 */
	@Override
	public int DeleteSupplier(List<Integer> id) {
		int result=0;
		for (int i = 0; i < id.size(); i++) {
			result+=basicDataDao.DeleteSupplier(id.get(i));
		}
		return result;
	}

	/**
	 * 搜索客户
	 */
	@Override
	public List<Customer> SearchCustomer(Customer customer, PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return basicDataDao.SearchCustomer(customer,page);
		
	}

	/**
	 * 查询客户总数量
	 */
	@Override
	public int QueryCustomerCount() {
		return basicDataDao.QueryCustomerCount();
	}

	
	/**
	 * 添加客户
	 */
	@Override
	public int AddCustomer(Customer customer) {
		return basicDataDao.AddCustomer(customer);
	}

	/**
	 * 修改客户
	 */
	@Override
	public int UpdateCustomer(Customer customer) {
		return basicDataDao.UpdateCustomer(customer);
	}

	@Override
	public int DeleteCustomer(List<Integer> id) {
		int result=0;
		for (int i = 0; i < id.size(); i++) {
			result+=basicDataDao.DeleteCustomer(id.get(i));
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
