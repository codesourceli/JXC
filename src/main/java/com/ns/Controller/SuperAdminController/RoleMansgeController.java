package com.ns.Controller.SuperAdminController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.JsonUtil;
import com.ns.Util.PageUtil;
import com.ns.entity.Log;
import com.ns.entity.Menu;
import com.ns.entity.Role;
import com.ns.entity.RoleMenu;
import com.ns.service.ILogService;
import com.ns.service.IPowerService;

/**
 * 角色管理
 * @author 李安
 *
 */

@Controller
@RequestMapping("/Power")
public class RoleMansgeController {

	@Autowired
	private IPowerService powerService; 
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 查询角色
	 * @param page
	 * @return
	 */
	@RequestMapping("/Role")
	@ResponseBody
	public Object Role(PageUtil page){
		List<Role> role=powerService.QueryAllRole(page);
		int total=powerService.QueryAllRoleTotal();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", role);
		map.put("total", total);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询角色"));
		return map;
	}

	/**
	 * 查询菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping("/Menu")
	@ResponseBody
	public Object Menu(Menu menu){
		List<Menu> menuList=powerService.QueryAllMenu(menu);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询菜单"));
		return menuList;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/GetSetMenu")
	@ResponseBody
	public Object GetSetMenu(Integer id){
		List<RoleMenu> menuList=powerService.GetSetMenu(id);
		List<Integer> menuId=new ArrayList<Integer>();
		for (int i = 0; i < menuList.size(); i++) {
			menuId.add(menuList.get(i).getMenu().getId());
		}
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询匹配选中菜单节点"));
		return menuId;
	}

	/**
	 * 权限菜单设置
	 * @param id
	 * @param RoleId
	 * @return
	 */
	@RequestMapping("/SetRole")
	@ResponseBody
	public Object SetRole(@RequestParam("id[]") List<Integer> id,Integer RoleId){
		System.out.println(id);
		System.out.println(RoleId);
		List<RoleMenu> menuList=powerService.GetSetMenu(RoleId);
		List<Integer> MenuId=new ArrayList<Integer>();
		for (int j = 0; j < menuList.size(); j++) {
			MenuId.add(menuList.get(j).getMenu().getId());
		}
		List<Integer> AddRoleUser=new ArrayList<Integer>();
		for(int j=0;j<id.size();j++){
			if(!MenuId.contains(id.get(j))){   //id有的 MenuId没有 增加   !MenuId.contains(id.get(j))    add(id)
				AddRoleUser.add(id.get(j));            //MenuId 有的 id 没有  删除       id.contains(MenuId.get(i))    delete(menuId) 	 
			}
		}
		List<Integer> deleteRoleUser=new ArrayList<Integer>();
		for(int j=0;j<MenuId.size();j++){
			if(!id.contains(MenuId.get(j))){   
				deleteRoleUser.add(MenuId.get(j));         	 
			}
		}
		
		int result = powerService.SetRoleMenu(AddRoleUser,deleteRoleUser,RoleId);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"权限菜单设置"));
		return null;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	@RequestMapping("/AddRole")
	@ResponseBody
	public Object AddRole(Role role){
		int result = powerService.AddRole(role);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("添加成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.ADD_ACTION,"添加角色"));
		return null;
	}

	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@RequestMapping("/UpdateRole")
	@ResponseBody
	public Object UpdateRole(Role role){
		System.out.println(role);
		int result = powerService.UpdateRole(role);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("修改成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.UPDATE_ACTION,"修改角色"));
		return null;
	}

	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	@RequestMapping("/DeteRole")
	@ResponseBody
	public Object DeteRole(Role role){
		int result = powerService.DeteRole(role);
		if(result>0){
			JsonUtil json=new JsonUtil();
			json.setMsg("删除成功");
			json.setSuccess(true);
			return json;
		}
		logService.SaveLog(new Log(Log.DELETE_ACTION,"删除角色"));
		return null;
	}
	
	/**
	 * 搜索角色
	 * @param role
	 * @return
	 */
	@RequestMapping("/SearchRole")
	@ResponseBody
	public Object SearchRole(Role role){
		List<Role> roleList= powerService.SearchRole(role);
		logService.SaveLog(new Log(Log.DELETE_ACTION,"查询角色"));
		return roleList;
	}
	
	
}
