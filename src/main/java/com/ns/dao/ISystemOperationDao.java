package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.Util.PageUtil;
import com.ns.entity.Log;
import com.ns.entity.User;

public interface ISystemOperationDao {

	List<Log> QueryAllLog(@Param(value="log") Log log,@Param(value="page") PageUtil page);

	int QueryAllLogTotal(Log log);

	User QueryUserByName(User user);

	int modifyPassword(User user);

}
