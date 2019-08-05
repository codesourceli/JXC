package com.ns.dao;

import com.ns.entity.Log;

public interface ILogDao {

	void SaveLog(Log log);

	int DeleteLog(int num);

}
