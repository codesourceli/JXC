package com.ns.Controller.SuperAdminController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.Util.PageUtil;
import com.ns.entity.Log;
import com.ns.entity.Role;
import com.ns.entity.User;
import com.ns.entity.UserRole;
import com.ns.service.ILogService;
import com.ns.service.IUserManagerService;

/**
 * 用户管理
 * @author 李安
 *
 */
@Controller
@RequestMapping("/UserManager")
public class UserManagerController {

	@Autowired
	private IUserManagerService userManagerService; 
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 查询所有的用户
	 * @param page
	 * @return
	 */
	@RequestMapping("/User")
	@ResponseBody
	public Object User(PageUtil page) {
		List<User> user=userManagerService.QueryAllUser(page);
		int total=userManagerService.QueryAllUserTotal();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", user);
		map.put("total", total);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询用户"));
		return map;
	}

	/**
	 * 查询所有的角色
	 * @return
	 */
	@RequestMapping("/Role")
	@ResponseBody
	public Object Role() {
		List<Role> role=userManagerService.QueryAllRole();
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"用户管理查询角色"));
		return role;
	}
	

	/**
	 * 查找该用户下所属的角色
	 */
	@RequestMapping("/RolePart")
	@ResponseBody
	public Object RolePart(Integer id) {
		List<UserRole> Ur=userManagerService.QueryRolePart(id);
		logService.SaveLog(new Log(Log.SEARCH_ACTION," 查找当前用户下所属的角色"));
		return Ur;
	}
	

	/**
	 * 设置角色
	 * @param id
	 * @return
	 */
	@RequestMapping("/SetUserRole")
	@ResponseBody
	public Object SetUserRole(@RequestParam("id[]") List<Integer> id,Integer userId) {
		List<UserRole> Ur=userManagerService.QueryRolePart(userId);
		
		List<Integer> UrId=new ArrayList<Integer>();
		for (int i = 0; i < Ur.size(); i++) {
			UrId.add(Ur.get(i).getRole().getId());
		}
		
		List<Integer> AddRoleMenu=new ArrayList<Integer>();
		for(int j=0;j<id.size();j++){
			if(!UrId.contains(id.get(j))){    
				AddRoleMenu.add(id.get(j));       	 
			}
		}
		
		List<Integer> deleteRoleMenu=new ArrayList<Integer>();
		for(int j=0;j<UrId.size();j++){
			if(!id.contains(UrId.get(j))){   
				deleteRoleMenu.add(UrId.get(j));         	 
			}
		}
		
		
		int result = userManagerService.SetUserRole(AddRoleMenu,deleteRoleMenu,userId);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"设置角色"));
		
		return null;
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	@RequestMapping("/UpdateUser")
	@ResponseBody
	public Object UpdateUser(User user,Integer id){
		int result = userManagerService.UpdateUser(user,id);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setSuccess(true);
			json.setMsg("修改成功");
			return json;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"修改用户"));
		return null;
	}
	

	/**
	 * 添加用户
	 * @return
	 */
	@RequestMapping("/AddUser")
	@ResponseBody
	public Object AddUser(User user){
		int result = userManagerService.AddUser(user);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setSuccess(true);
			json.setMsg("添加成功");
			return json;
		}
		logService.SaveLog(new Log(Log.ADD_ACTION,"添加用户"));
		return null;
	}
	

	/**
	 * 删除用户
	 * @return
	 */
	@RequestMapping("/DeleteUser")
	@ResponseBody
	public Object DeleteUser(Integer id){
		int result = userManagerService.DeleteUser(id);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setSuccess(true);
			json.setMsg("删除成功");
			return json;
		}
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除用户"));
		return null;
	}
	
	
	
	
}
