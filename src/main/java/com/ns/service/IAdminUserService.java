package com.ns.service;

import java.util.List;

import com.ns.Util.PageUtil;
import com.ns.entity.Goods;
import com.ns.entity.GoodsType;
import com.ns.entity.Menu;
import com.ns.entity.Role;
import com.ns.entity.User;
import com.ns.entity.UserRole;

public interface IAdminUserService  {

	List<UserRole> QueryUserRole(User user);

	List<Role> QueryRole();

	List<Menu> QueryMenu(UserRole uR);

	List<Goods> QueryStock(PageUtil page, Goods goods);

	long QueryStockTotal();

	List<GoodsType> loadGoodsType(GoodsType gdt);

	UserRole QueryUserRoleById(User user, Role role);

}
