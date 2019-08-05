package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.Util.PageUtil;
import com.ns.entity.Customer;
import com.ns.entity.Supplier;

public interface IBasicDataDao {

	List<Supplier> QuerySupplier(@Param(value="supplier") Supplier supplier,@Param(value="page") PageUtil page);

	int QuerySupplierCount();

	int AddSupplier(Supplier supplier);

	int UpdateSupplier(Supplier supplier);

	int DeleteSupplier(Integer id);

	List<Customer> SearchCustomer(@Param(value="customer") Customer customer,@Param(value="page") PageUtil page);

	int QueryCustomerCount();

	int AddCustomer(Customer customer);

	int UpdateCustomer(Customer customer);

	int DeleteCustomer(Integer integer);

}
