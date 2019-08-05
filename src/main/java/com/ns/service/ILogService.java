package com.ns.service;


import com.ns.entity.Log;

public interface ILogService {

	void SaveLog(Log log);

	int DeleteLog(int i);
	
}
