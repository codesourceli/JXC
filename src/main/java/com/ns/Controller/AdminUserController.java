package com.ns.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.Util.PageUtil;
import com.ns.Util.TreeNodeUtil;
import com.ns.entity.Goods;
import com.ns.entity.GoodsType;
import com.ns.entity.Menu;
import com.ns.entity.Role;
import com.ns.entity.User;
import com.ns.entity.UserRole;
import com.ns.service.IAdminUserService;

/**
 * 用户登录以及初始化库存页面数据控制器
 * @author 李安
 *
 */
@Controller
public class AdminUserController {

	@Autowired
	private IAdminUserService adminUserService;
	
	/**
	 * 用户登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/QueryUser")
	@ResponseBody
	public Object ChooseRole(HttpSession session){
		User user=(User) session.getAttribute("user");
		//查询当前用户是否有角色
		List<UserRole> uR = adminUserService.QueryUserRole(user);	
		//有角色则加载左边菜单栏   没有角色则返回提示让用户创建出一个角色
		if(uR.size()>1){
			JsonUtil json=new JsonUtil();
			json.setMsg("此用户所拥有的角色超过一个，请先选择一个角色");
			json.setSuccess(false);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("json", json);
			map.put("uR", uR);
			return map;
		}else{
			JsonUtil json=new JsonUtil();
			json.setSuccess(true);
			return json;
		}
			
		
	}

	/**
	 * 初始化tree左部菜单栏
	 * @param uR
	 * @return
	 */
	public List<Menu> QueryMenu(UserRole uR) {
		List<Menu> tree= adminUserService.QueryMenu(uR);
		return tree;
	}
	
	/**
	 * 前台查询tree菜单（调用QueryMenu方法）
	 * @param session
	 * @return
	 */
	@RequestMapping("/QueryTree")
	@ResponseBody
	public Object QueryTree(HttpSession session,Role role) {
		User user=(User) session.getAttribute("user");
		UserRole uR = new UserRole();
		if(role.getId()!=null){
		/*	//向数据库中写入当前用户选中需要创建的角色,然后返回改用户角色数据
			uR=adminUserService.SetUpRole(user,role);*/
			uR= adminUserService.QueryUserRoleById(user,role);
		}
		List<Menu> tree=QueryMenu(uR);
		return tree;
	}

	
	/**
	 * 查询出所有角色，让没有角色的用户创建使用一个角色
	 * @return
	 */
	@RequestMapping("/QueryRole")
	@ResponseBody
	public List<Role> TosetRole() {
		return adminUserService.QueryRole();
	}
	
	/**
	 * 当前库存查询
	 * @param page
	 * @param goods
	 * @return
	 */
	@RequestMapping("/MainStock")
	@ResponseBody
	public Map<String,Object> Stock(PageUtil page,Goods goods){
		List<Goods> stock= adminUserService.QueryStock(page,goods);  //查库存
		long StockTotal=adminUserService.QueryStockTotal();   //查询总数用来分页
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", stock);
		map.put("total", StockTotal);
		return map;
	}
	
	
	/**
	 * 加载商品类别
	 * @return
	 */
	@RequestMapping("/loadGoodsType")
	@ResponseBody
	public Object loadGoodsType(GoodsType gdt){
		List<GoodsType> goodsType=adminUserService.loadGoodsType(gdt);
		List<TreeNodeUtil> tree=new ArrayList<TreeNodeUtil>();
		
		for (GoodsType treeNode : goodsType) {
			TreeNodeUtil node=new TreeNodeUtil();
			node.setId(treeNode.getId());
			node.setText(treeNode.getName());
			node.setIconCls(treeNode.getIcon());
			if(treeNode.getState()==1){
				node.setState("closed");
			}else{
				node.setState("open");
			}
			
			tree.add(node);
		}
		return tree;
	}
	
	
	
	
	
	
}
