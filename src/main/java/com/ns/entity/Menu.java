package com.ns.entity;

import java.util.List;
import java.util.Map;

/**
 * 菜单实体
 * @author java1234 小锋 老师
 *
 */
public class Menu {

	private Integer id; // 编号
	
	private String text; // 菜单名称
	
	private String url; // 菜单地址
	
	private String state; // 菜单节点类型 1 根节点open    0 叶子节点
	
	private String iconCls; // 图标
	
	private Integer pId; // 父菜单Id

	private List<Menu> children;
	private Map<String,Object> attributes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", text=" + text + ", url=" + url + ", state=" + state + ", iconCls=" + iconCls
				+ ", pId=" + pId + ", children=" + children + ", attributes=" + attributes + "]";
	}


	
	
	
}
