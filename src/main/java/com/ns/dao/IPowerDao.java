package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.Util.PageUtil;
import com.ns.entity.Menu;
import com.ns.entity.Role;
import com.ns.entity.RoleMenu;

public interface IPowerDao {

	List<Role> QueryAllRole(PageUtil page);

	int QueryAllRoleTotal();

	List<Menu> QueryAllMenu(Menu menu);

	List<RoleMenu> GetSetMenu(Integer id);

	int AddRoleMenu(@Param(value="AddId") Integer AddId,@Param(value="roleId") Integer roleId);

	int deleteRoleMenu(@Param(value="DeleteId") Integer DeleteId,@Param(value="roleId") Integer roleId);

	int AddRole(Role role);

	int UpdateRole(Role role);

	int DeteRole(Role role);

	List<Role> SearchRole(Role role);

	void DeteRoleUser(Role role);

	void DeteRoleMenu(Role role);

}
