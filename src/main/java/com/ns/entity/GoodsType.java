package com.ns.entity;

import java.util.List;

/**
 * 商品类别实体
 * @author java1234 小锋 老师
 *
 */
public class GoodsType {

	private Integer id; // 编号
	
	private String name; // 类别名称
	
	private Integer state; // 菜单节点类型 1 根节点 0 叶子节点
	
	private String icon; // 图标
	
	private Integer pId; // 父菜单Id
	
	private List<GoodsType> goodsType;

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public List<GoodsType> getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(List<GoodsType> goodsType) {
		this.goodsType = goodsType;
	}

	@Override
	public String toString() {
		return "GoodsType [id=" + id + ", name=" + name + ", state=" + state + ", icon=" + icon + ", pId=" + pId
				+ ", goodsType=" + goodsType + "]";
	}

	
	
}
