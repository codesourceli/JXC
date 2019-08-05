package com.ns.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.PageUtil;
import com.ns.dao.AdminUserDao;
import com.ns.entity.Goods;
import com.ns.entity.GoodsType;
import com.ns.entity.Menu;
import com.ns.entity.Role;
import com.ns.entity.RoleMenu;
import com.ns.entity.User;
import com.ns.entity.UserRole;
import com.ns.service.IAdminUserService;

@Service
@Transactional
public class AdminUserServiceImpl implements IAdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;

	/**
	 * 根据user id查询相对应的角色用户表
	 */
	@Override
	public List<UserRole> QueryUserRole(User user) {
		return adminUserDao.QueryUserRole(user);
	}

	/**
	 * 查询所有角色
	 */
	@Override
	public List<Role> QueryRole() {
		return adminUserDao.QueryRole();
	}

	
	/**
	 * 加载出左侧节点菜单
	 */
	@Override
	public List<Menu> QueryMenu(UserRole uR) {	
		List<RoleMenu> RM= adminUserDao.QueryRoleMenu(uR);
		
		//取出根节点
		List<Menu> RootNode=new ArrayList<Menu>();
		for(int i=0;i<RM.size();i++){
			Menu mer=RM.get(i).getMenu();
			if(mer.getState().equals("1")){
				mer.setState("closed");
				Map<String,Object> attributes=new HashMap<String,Object>();
				attributes.put("url",mer.getUrl());
				mer.setAttributes(attributes);
				RootNode.add(mer);
			}
			
		}
		//取出叶子节点
		List<Menu> LeafNode=new ArrayList<Menu>();
		for(int i=0;i<RM.size();i++){
			Menu mel=RM.get(i).getMenu();
			if(mel.getState().equals("0")){
				mel.setState("open");
				Map<String,Object> attributes=new HashMap<String,Object>();
				attributes.put("url",mel.getUrl());
				mel.setAttributes(attributes);
				LeafNode.add(mel);
			}
		}
		
		
		//让叶子节点与根节点循环匹配
		List<Menu> children=new ArrayList<Menu>();
		List<Menu> RootLeaf=new ArrayList<Menu>();
		for(int rn=0;rn<RootNode.size();rn++){
			Menu rmenu =RootNode.get(rn);
			for(int ln=0;ln<LeafNode.size();ln++){
				Menu lmenu = new Menu();
				 lmenu=LeafNode.get(ln);
				if(rmenu.getId()==lmenu.getpId()){
					children.add(lmenu);
				//	children.clear();
				}
				if(ln==LeafNode.size()-1&&children!=null&&children.size()>0){
					rmenu.setChildren(children);
					RootLeaf.add(rmenu);
					children=new ArrayList<Menu>();
					//children.clear();
					//RootLeaf.clear();
				}
			}
		//	RootLeafSum.add
		}
		
		List<Menu> RootLeafSum=new ArrayList<Menu>();
		for(int rn=0;rn<RootNode.size();rn++){
			Menu rmenu =RootNode.get(rn);
			for(int ln=0;ln<RootLeaf.size();ln++){
				Menu lmenu = new Menu();
				lmenu=RootLeaf.get(ln);
				if(rmenu.getId()==lmenu.getpId()){
					children.add(lmenu);
				//	children.clear();
				}
				if(ln==RootLeaf.size()-1&&children!=null&&children.size()>0){
					rmenu.setChildren(children);
					RootLeafSum.add(rmenu);
					children=new ArrayList<Menu>();
					//children.clear();
					//RootLeaf.clear();
				}
			}
			
		}
		
		return RootLeafSum;
	}

	
	/**
	 * 当前库存查询
	 */
	@Override
	public List<Goods> QueryStock(PageUtil page,Goods goods) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return adminUserDao.QueryStock(page,goods);
	}

	/**
	 * 查询库存总数
	 */
	@Override
	public long QueryStockTotal() {
		return adminUserDao.QueryStockTotal();
	}

	/**
	 * 加载商品类别
	 */
	@Override
	public List<GoodsType> loadGoodsType(GoodsType goodsType) {
		return adminUserDao.loadGoodsType(goodsType);
	}

	@Override
	public UserRole QueryUserRoleById(User user,Role role) {
		return adminUserDao.QueryUserRoleById(user,role);
	}


	
}
