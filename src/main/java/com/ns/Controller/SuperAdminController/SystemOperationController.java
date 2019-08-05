package com.ns.Controller.SuperAdminController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.DateUtils;
import com.ns.Util.JsonUtil;
import com.ns.Util.PageUtil;
import com.ns.entity.Log;
import com.ns.entity.User;
import com.ns.service.ILogService;
import com.ns.service.ISystemOperationService;

@Controller
@RequestMapping("/SystemOperation")
public class SystemOperationController {

	@Autowired
	private ISystemOperationService systemOperationService;
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 查询日志
	 * @param log
	 * @param user
	 * @param page
	 * @return
	 */
	@RequestMapping("/QueryLog")
	@ResponseBody
	public Object QueryLog(Log log,User user,PageUtil page){
		List<Log> logList=systemOperationService.QueryAllLog(log,page,user);
		System.out.println();
		int total=systemOperationService.QueryAllLogTotal(log,user);
		logService.SaveLog(new Log(Log.SEARCH_ACTION,"查询日志"));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", logList);
		map.put("total",total);
		return map;
	}
	

	/**
	 * 修改密码
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping("/modifyPassword")
	@ResponseBody
	public Object modifyPassword(HttpSession session,User user){
		User SeesionUser=(User) session.getAttribute("user");
		SeesionUser.setPassword(user.getPassword());
		int result = systemOperationService.modifyPassword(SeesionUser);
		if(result>0){
			logService.SaveLog(new Log(Log.DELETE_ACTION,"修改密码"));
			session.invalidate();
			JsonUtil json=new JsonUtil();
			json.setMsg("密码修改成功，请重新登录");
			json.setSuccess(true);
			return json;
		}
		return null;
	}
	
	/**
	 * 安全退出
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping("/LogOut")
	@ResponseBody
	public boolean LogOut(HttpSession session){
		logService.SaveLog(new Log(Log.LOGOUT_ACTION,"安全退出"));
		session.invalidate();
		return true;
	}
	
	
}
