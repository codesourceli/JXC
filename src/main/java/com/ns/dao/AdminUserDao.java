package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.Util.PageUtil;
import com.ns.entity.Goods;
import com.ns.entity.GoodsType;
import com.ns.entity.Menu;
import com.ns.entity.Role;
import com.ns.entity.RoleMenu;
import com.ns.entity.User;
import com.ns.entity.UserRole;

public interface AdminUserDao {

	List<UserRole> QueryUserRole(User user);

	List<Role> QueryRole();

	List<RoleMenu> QueryRoleMenu(UserRole uR);

	List<Menu> QyeryMenu(List<RoleMenu> rM);

	int SetUpRole(@Param(value = "role") Integer role,@Param(value = "user") Integer user);

	List<Goods> QueryStock(@Param(value = "page") PageUtil page,@Param(value = "goods") Goods goods);

	long QueryStockTotal();

	List<GoodsType> loadGoodsType(GoodsType goodsType);

	UserRole QueryUserRoleById(@Param(value = "user") User user,@Param(value = "role") Role role);

	

}
