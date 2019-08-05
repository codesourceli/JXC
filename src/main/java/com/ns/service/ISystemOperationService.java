package com.ns.service;

import java.util.List;

import com.ns.Util.PageUtil;
import com.ns.entity.Log;
import com.ns.entity.User;

public interface ISystemOperationService {

	List<Log> QueryAllLog(Log log, PageUtil page, User user);

	int QueryAllLogTotal(Log log, User user);

	int modifyPassword(User user);

}
