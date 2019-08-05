package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.PageUtil;
import com.ns.dao.ISystemOperationDao;
import com.ns.entity.Log;
import com.ns.entity.User;
import com.ns.service.ISystemOperationService;

@Service
@Transactional
public class SystemOperationServiceImpl implements ISystemOperationService {

	@Autowired
	private ISystemOperationDao systemOperationDao;

	/**
	 * 查询日志
	 */
	@Override
	public List<Log> QueryAllLog(Log log, PageUtil page,User user) {
		User userLog=systemOperationDao.QueryUserByName(user);
		log.setUser(userLog);
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return systemOperationDao.QueryAllLog(log,page);
	}

	/**
	 * 查询日志总数
	 */
	@Override
	public int QueryAllLogTotal(Log log,User user) {
		User useLogr=systemOperationDao.QueryUserByName(user);
		log.setUser(useLogr);
		return systemOperationDao.QueryAllLogTotal(log);
	}

	/**
	 * 修改密码
	 */
	@Override
	public int modifyPassword(User user) {
		return systemOperationDao.modifyPassword(user);
	}
	
}
