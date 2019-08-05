package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.PageUtil;
import com.ns.dao.IUserManagerDao;
import com.ns.entity.Role;
import com.ns.entity.User;
import com.ns.entity.UserRole;
import com.ns.service.IUserManagerService;

@Service
@Transactional
public class UserManagerServiceImpl implements IUserManagerService {

	@Autowired
	private IUserManagerDao userManagerDao;

	/**
	 * 查询所有用户
	 */
	@Override
	public List<User> QueryAllUser(PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return userManagerDao.QueryAllUser(page);
	}

	/**
	 * 查询所有用户总数
	 */
	@Override
	public int QueryAllUserTotal() {
		return userManagerDao.QueryAllUserTotal();
	}

	/**
	 * 查询角色
	 */
	@Override
	public List<Role> QueryAllRole() {
		return userManagerDao.QueryAllRole();
	}

	/**
	 * 查找该用户下所属的角色
	 */
	@Override
	public List<UserRole> QueryRolePart(Integer id) {
		return userManagerDao.QueryRolePart(id);
	}

	@Override
	public int SetUserRole(List<Integer> addRoleUser, List<Integer> deleteRoleUser, Integer userId) {
		int AddResult=0;
		int DeleteResult=0;
		if(addRoleUser.size()>0){
			for(int i=0;i<addRoleUser.size();i++){
				AddResult+=userManagerDao.AddRoleUser(addRoleUser.get(i),userId);
			}
		}
		
		if(deleteRoleUser.size()>0){
			for(int i=0;i<deleteRoleUser.size();i++){
				DeleteResult+=userManagerDao.deleteRoleUser(deleteRoleUser.get(i),userId);
			}
		}
		 
		return AddResult+DeleteResult;
	}

	/**
	 * 添加用户
	 */
	@Override
	public int AddUser(User user) {
		return userManagerDao.AddUser(user);
	}

	/**
	 * 修改用户
	 */
	@Override
	public int UpdateUser(User user,Integer id) {
		return userManagerDao.UpdateUser(user,id);
	}

	@Override
	public int DeleteUser(Integer id) {
		userManagerDao.DeleteUserRole(id);
		int result = userManagerDao.DeleteUser(id);
		return result;
	}
	
	
}
