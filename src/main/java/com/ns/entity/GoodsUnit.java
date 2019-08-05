package com.ns.entity;

/**
 * 商品单位实体
 * @author java1234 小锋 老师
 *
 */
public class GoodsUnit {
	
	private Integer id; // 编号
	
	private String name; // 商品单位名称

	
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

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + "]";
	}
	
	
}
