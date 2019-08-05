package com.ns.Util;

import java.util.List;
import java.util.Map;

/**
 * 返回符合easyui-tree格式的工具类
 * @author 李安
 *
 */
public class TreeNodeUtil {

	private int id;
	private String text;
	private String iconCls;
	private boolean checked;
	private Map<String,Object> attributes;
	private String state;
	private  List<TreeNodeUtil> children;
	private Integer pid;
	private String url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<TreeNodeUtil> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNodeUtil> children) {
		this.children = children;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "TreeNodeUtil [id=" + id + ", text=" + text + ", iconCls=" + iconCls + ", checked=" + checked
				+ ", attributes=" + attributes + ", state=" + state + ", children=" + children + ", pid=" + pid
				+ ", url=" + url + "]";
	}
	
	
	
	
}
