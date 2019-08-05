package com.ns.service.Impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.DateUtils;
import com.ns.dao.ILogDao;
import com.ns.entity.Log;
import com.ns.entity.User;
import com.ns.service.ILogService;

@Service
@Transactional
public class LogServiceImpl implements ILogService {
	
	@Autowired
	private ILogDao logDao;
	
	@Autowired
	private HttpSession session;

	/* (non-Javadoc)
	 * @see com.ns.service.ILogService#SaveLog(com.ns.entity.Log)
	 */
	@Override
	public void SaveLog(Log log) {
		User user = (User) session.getAttribute("user");
		log.setUser(user);
		log.setTime(DateUtils.getDayOfMonth());
		logDao.SaveLog(log);
	}

	@Override
	public int DeleteLog(int num) {
		return logDao.DeleteLog(num);
	}
	
}
