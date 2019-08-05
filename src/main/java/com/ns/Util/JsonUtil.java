package com.ns.Util;


/**
 * 请求操作成功，返回的说明帮助类
 * @author 李安
 *
 */
public class JsonUtil {

	private String msg;
	private boolean success;
	private Object obj;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	@Override
	public String toString() {
		return "JsonUtil [msg=" + msg + ", success=" + success + ", obj=" + obj + "]";
	}

	
	
	
	
	
}
