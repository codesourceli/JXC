package com.ns.entity;

/**
 * 用户实体
 * @author java1234 小锋 老师
 *
 */
public class User {

	private Integer id; // 编号
	
	private String userName; // 用户名
	
	private String password; // 密码
	
	private String trueName; // 真实姓名
	
	private String remarks; // 备注
	
	private String roles; 
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", userName=" + userName + ", password=" + password + ", trueName=" + trueName
				+ ", remarks=" + remarks + ", roles=" + roles + "]";
	}

	
	
}
