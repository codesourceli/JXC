package com.ns.entity;


/**
 * 用户角色关联实体
 * @author java1234 小锋 老师
 *
 */
public class UserRole {

	private Integer id; // 编号
	
	private User user; // 用户
	
	private Role role; // 角色

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", user=" + user + ", role=" + role + "]";
	}
	
	
	
}
