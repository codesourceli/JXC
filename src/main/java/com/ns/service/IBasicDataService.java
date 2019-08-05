package com.ns.service;

import java.util.List;

import com.ns.Util.PageUtil;
import com.ns.entity.Supplier;

public interface IBasicDataService {

	List<Supplier> QuerySupplier(Supplier supplier, PageUtil page);

	int QuerySupplierCount();

	int AddSupplier(Supplier supplier);

	int UpdateSupplier(Supplier supplier);

	int DeleteSupplier(List<Integer> id);

	List<com.ns.entity.Customer> SearchCustomer(com.ns.entity.Customer customer, PageUtil page);

	int QueryCustomerCount();

	int AddCustomer(com.ns.entity.Customer customer);

	int UpdateCustomer(com.ns.entity.Customer customer);

	int DeleteCustomer(List<Integer> id);

}
