package com.ns.service;

import java.util.List;

import com.ns.Util.PageUtil;
import com.ns.entity.Menu;
import com.ns.entity.Role;
import com.ns.entity.RoleMenu;

public interface IPowerService {

	List<Role> QueryAllRole(PageUtil page);

	int QueryAllRoleTotal();

	List<Menu> QueryAllMenu(Menu menu);

	List<RoleMenu> GetSetMenu(Integer id);

	int SetRoleMenu(List<Integer> AddRoleUser, List<Integer> deleteRoleUser, Integer roleId);

	int AddRole(com.ns.entity.Role role);

	int UpdateRole(com.ns.entity.Role role);

	int DeteRole(com.ns.entity.Role role);

	List<Role> SearchRole(com.ns.entity.Role role);

}
