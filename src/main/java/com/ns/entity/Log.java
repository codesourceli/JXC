package com.ns.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 系统日志实体
 * @author java1234 小锋 老师
 *
 */
public class Log {


	public final static String LOGIN_ACTION="登录操作";
	public final static String LOGOUT_ACTION="注销操作";
	public final static String SEARCH_ACTION="查询操作";
	public final static String UPDATE_ACTION="更新操作";
	public final static String ADD_ACTION="添加操作";
	public final static String DELETE_ACTION="删除操作";
	
	
	private Integer id; // 编号
	
	private String type; // 日志类型
	
	private User user; // 操作用户
	
	private String content; // 操作内容
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time; // 操作时间

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date btime; // 起始时间  搜索用到
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date etime; // 结束时间  搜索用到
	

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 * @param user
	 * @param content
	 * @param time
	 * @param btime
	 * @param etime
	 */
	public Log(String type, String content) {
		super();
		this.type = type;
		this.content = content;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonSerialize(using=CustomDateTimeSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getBtime() {
		return btime;
	}

	public void setBtime(Date btime) {
		this.btime = btime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", type=" + type + ", user=" + user + ", content=" + content + ", time=" + time
				+ ", btime=" + btime + ", etime=" + etime + "]";
	}


	
	
	
}
