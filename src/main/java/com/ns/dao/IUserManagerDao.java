package com.ns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ns.Util.PageUtil;
import com.ns.entity.Role;
import com.ns.entity.User;
import com.ns.entity.UserRole;

public interface IUserManagerDao {

	List<User> QueryAllUser(PageUtil page);

	int QueryAllUserTotal();

	List<Role> QueryAllRole();

	List<UserRole> QueryRolePart(Integer id);

	int AddRoleUser(@Param(value="roleId") Integer roleId,@Param(value="userId") Integer userId);

	int deleteRoleUser(@Param(value="roleId") Integer roleId,@Param(value="userId") Integer userId);

	int AddUser(User user);

	int UpdateUser(@Param(value="user")User user,@Param(value="id")Integer id);

	void DeleteUserRole(Integer id);

	int DeleteUser(Integer id);

}
