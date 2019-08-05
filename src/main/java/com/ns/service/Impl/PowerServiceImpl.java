package com.ns.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.PageUtil;
import com.ns.dao.IPowerDao;
import com.ns.entity.Menu;
import com.ns.entity.Role;
import com.ns.entity.RoleMenu;
import com.ns.service.IPowerService;

@Service
@Transactional
public class PowerServiceImpl implements IPowerService {

	@Autowired
	private IPowerDao powerDao;

	@Override
	public List<Role> QueryAllRole(PageUtil page) {
		int start=(page.getPage()-1)*page.getRows();
		page.setStart(start);
		return powerDao.QueryAllRole(page);
	}

	@Override
	public int QueryAllRoleTotal() {
		return powerDao.QueryAllRoleTotal();
	}

	@Override
	public List<Menu> QueryAllMenu(Menu menu) {
		return powerDao.QueryAllMenu(menu);
	}

	@Override
	public List<RoleMenu> GetSetMenu(Integer id) {
		return powerDao.GetSetMenu(id);
	}

	@Override
	public int SetRoleMenu(List<Integer> addRoleMenu, List<Integer> deleteRoleMenu, Integer roleId) {
		int AddResult=0;
		int DeleteResult=0;
		if(addRoleMenu.size()>0){
			for(int i=0;i<addRoleMenu.size();i++){
				AddResult+=powerDao.AddRoleMenu(addRoleMenu.get(i),roleId);
			}
		}
		
		if(deleteRoleMenu.size()>0){
			for(int i=0;i<deleteRoleMenu.size();i++){
				DeleteResult+=powerDao.deleteRoleMenu(deleteRoleMenu.get(i),roleId);
			}
		}
		 
		return AddResult+DeleteResult;
	}

	/**
	 * 添加角色
	 */
	@Override
	public int AddRole(Role role) {
		return powerDao.AddRole(role);
	}

	/**
	 * 修改角色
	 */
	@Override
	public int UpdateRole(Role role) {
		return powerDao.UpdateRole(role);
	}

	/**
	 * 删除角色
	 */
	@Override
	public int DeteRole(Role role) {
		powerDao.DeteRoleMenu(role);
		powerDao.DeteRoleUser(role);
		return powerDao.DeteRole(role);
	}

	/**
	 * 搜索角色
	 */
	@Override
	public List<Role> SearchRole(Role role) {
		return powerDao.SearchRole(role);
	} 
	
}
