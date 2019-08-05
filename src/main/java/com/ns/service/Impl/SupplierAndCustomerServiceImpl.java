package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.dao.ISupplierAndCustomerDao;
import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;
import com.ns.service.ISupplierAndCustomerService;

@Service
@Transactional
public class SupplierAndCustomerServiceImpl implements ISupplierAndCustomerService {

	@Autowired
	private ISupplierAndCustomerDao supplierAndCustomerDao;

	/**
	 * 统计进货供应商
	 */
	@Override
	public List<PurchaseList> CountPurchaseList(PurchaseList purchaseList, String begin, String end) {
		return supplierAndCustomerDao.CountPurchaseList(purchaseList,begin,end);
	}

	/**
	 * 统计退货供应商
	 */
	@Override
	public List<ReturnList> CountReturnList(ReturnList returnList, String begin, String end) {
		return supplierAndCustomerDao.CountReturnList(returnList,begin,end);
	}

	/**
	 * 查询进货单实体
	 */
	@Override
	public List<PurchaseListGoods> QueryPurchaseListGoodsBillGoods(Integer id) {
		return supplierAndCustomerDao.QueryPurchaseListGoodsBillGoods(id);
	}

	/**
	 * 查询退货单实体
	 */
	@Override
	public List<ReturnListGoods> QueryReturnListGoodsBillGoods(Integer id) {
		return supplierAndCustomerDao.QueryReturnListGoodsBillGoods(id);
	}

	
	/**
	 * 统计客户
	 */
	@Override
	public List<SaleList> CountSaleList(SaleList saleList, String begin, String end) {
		return supplierAndCustomerDao.CountSaleList(saleList,begin,end);
	}

	/**
	 * 统计客户
	 */
	@Override
	public List<CustomerReturnList> CountCustomerReturnList(CustomerReturnList customerReturnList, String begin,String end) {
		return supplierAndCustomerDao.CountCustomerReturnList(customerReturnList,begin,end);
	}

	/**
	 * 查询销售实体
	 */
	@Override
	public List<SaleListGoods> QuerySaleListBillGoods(Integer id) {
		return supplierAndCustomerDao.QuerySaleListBillGoods(id);
	}

	/**
	 * 查询客户退货实体
	 */
	@Override
	public List<CustomerReturnListGoods> QueryCustomerReturnListBillGoods(Integer id) {
		return supplierAndCustomerDao.QueryCustomerReturnListBillGoods(id);
	}
	
}
