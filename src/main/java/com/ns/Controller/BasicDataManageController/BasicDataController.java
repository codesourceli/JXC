package com.ns.Controller.BasicDataManageController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.Util.PageUtil;
import com.ns.entity.Customer;
import com.ns.entity.Log;
import com.ns.entity.Supplier;
import com.ns.service.IBasicDataService;
import com.ns.service.ILogService;

@Controller
@RequestMapping("/BasicDataManage")
public class BasicDataController {

	@Autowired
	private IBasicDataService basicDataService;
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 搜索供应商
	 * @param supplier
	 * @param page
	 * @return
	 */
	@RequestMapping("/supplier")
	@ResponseBody
	public Object supplierManage(Supplier supplier,PageUtil page){
		List<Supplier> supplierList=basicDataService.QuerySupplier(supplier,page);
		int sum=basicDataService.QuerySupplierCount();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows",supplierList);
		map.put("total",sum);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"供应商查询"));
		return map;
	}
	
	/**
	 * 增加供应商
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/AddSupplier")
	@ResponseBody
	public Object AddSupplier(Supplier supplier){
		int result=basicDataService.AddSupplier(supplier);
		Map<String,Object> map=new HashMap<String,Object>();
		if(result>0){
			map.put("supplier",supplier);
			JsonUtil json=new JsonUtil();
			json.setMsg("添加成功");
			json.setSuccess(true);
			map.put("json",json);
			return map;
		}
		logService.SaveLog(new Log(Log.ADD_ACTION,"添加供应商"));
		return null;
	}
	
	/**
	 * 修改供应商
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/UpdateSupplier")
	@ResponseBody
	public Object UpdateSupplier(Supplier supplier){
		int result=basicDataService.UpdateSupplier(supplier);
		Map<String,Object> map=new HashMap<String,Object>();
		if(result>0){
			map.put("supplier",supplier);
			JsonUtil json=new JsonUtil();
			json.setMsg("修改成功");
			json.setSuccess(true);
			map.put("json",json);
			return map;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"修改供应商"));
		return null;
	}
	
	
	/**
	 * 删除供应商
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/DeleteSupplier")
	@ResponseBody
	public Object DeleteSupplier(@RequestParam("id[]") List<Integer> id){
		int result=basicDataService.DeleteSupplier(id);
		if(result==id.size()){
			JsonUtil json=new JsonUtil();
			json.setMsg("删除成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除供应商"));
		return null;
	}
	
	
	/**
	 * 搜索客户
	 * @param customer
	 * @param page
	 * @return
	 */
	@RequestMapping("/Customer")
	@ResponseBody
	public Object Customer(Customer customer,PageUtil page){
		List<Customer> customerList = basicDataService.SearchCustomer(customer,page);
		int sum=basicDataService.QueryCustomerCount();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows",customerList);
		map.put("total",sum);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"搜索客户"));
		return map;
	}

	/**
	 * 添加用户
	 * @param customer
	 * @return
	 */
	@RequestMapping("/AddCustomer")
	@ResponseBody
	public Object AddCustomer(Customer customer){
		int result = basicDataService.AddCustomer(customer);
		Map<String,Object> map=new HashMap<String,Object>();
		if(result>0){
			map.put("customer",customer);
			JsonUtil json=new JsonUtil();
			json.setMsg("添加成功");
			json.setSuccess(true);
			map.put("json",json);
			return map;
		}
		logService.SaveLog(new Log(Log.ADD_ACTION,"添加客户"));
		return null;
	}

	/**
	 * 修改客户
	 * @param customer
	 * @return
	 */
	@RequestMapping("/UpdateCustomer")
	@ResponseBody
	public Object UpdateCustomer(Customer customer){
		int result=basicDataService.UpdateCustomer(customer);
		Map<String,Object> map=new HashMap<String,Object>();
		if(result>0){
			map.put("supplier",customer);
			JsonUtil json=new JsonUtil();
			json.setMsg("修改成功");
			json.setSuccess(true);
			map.put("json",json);
			return map;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"修改客户"));
		return null;
	}
	

	
	@RequestMapping("/DeleteCustomer")
	@ResponseBody
	public Object DeleteCustomer(@RequestParam("id[]") List<Integer> id){
		int result=basicDataService.DeleteCustomer(id);
		if(result==id.size()){
			JsonUtil json=new JsonUtil();
			json.setMsg("删除成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除客户"));
		return null;
	}
	
	
}























