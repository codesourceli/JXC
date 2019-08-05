package com.ns.SpringQuartz;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ns.entity.Log;
import com.ns.service.ILogService;

public class LogQuartz {
	
	Logger LOG=LogManager.getLogger(this.getClass());
	
	@Autowired
	private ILogService logService;
	
	public void excute(){
		
		try {
			int result = logService.DeleteLog(2);
			System.out.println("删除成功："+result);
			logService.SaveLog(new Log(Log.DELETE_ACTION,"删除操作日志"));
			LOG.info("删除"+result+"行日志成功");
		} catch (Exception e) {
			LOG.info(e);
		}
		
		
	} 
	
}
