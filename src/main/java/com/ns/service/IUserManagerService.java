package com.ns.service;

import java.util.List;

import com.ns.Util.PageUtil;
import com.ns.entity.Role;
import com.ns.entity.User;
import com.ns.entity.UserRole;

public interface IUserManagerService {

	List<User> QueryAllUser(PageUtil page);

	int QueryAllUserTotal();

	List<Role> QueryAllRole();

	List<UserRole> QueryRolePart(Integer id);

	int SetUserRole(List<Integer> addRoleMenu, List<Integer> deleteRoleMenu, Integer userId);

	int AddUser(User user);

	int UpdateUser(User user, Integer id);

	int DeleteUser(Integer id);

}
