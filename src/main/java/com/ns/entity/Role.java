package com.ns.entity;

/**
 * 角色实体
 * @author java1234 小锋 老师
 *
 */
public class Role {

	private Integer id; // 编号
	
	private String name; // 角色名称
	
	private String remarks; // 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", remarks=" + remarks + "]";
	}
	
	
	

}
