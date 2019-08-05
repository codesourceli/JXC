package com.ns.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.Util.DateUtils;
import com.ns.Util.JsonUtil;
import com.ns.entity.Log;
import com.ns.entity.User;
import com.ns.service.ILogService;
import com.ns.service.IUserLoginSeivice;

@Controller
public class UserLoginController {
	
	@Autowired
	private IUserLoginSeivice userLoginSeivice;
	
	@Autowired
	private ILogService logService;
	
	
	@RequestMapping(value="/UserLogin")
	@ResponseBody
	public Object testlogin(HttpSession session, User user,String code){
		String checkcode=(String) session.getAttribute("checkcode");
		JsonUtil json=new JsonUtil();
		if(!checkcode.equals(code)){
			json.setMsg("验证码错误");
			json.setSuccess(false);
			return json;
		}
		 user=userLoginSeivice.ChecLoginkUser(user);
		if(user!=null){
			json.setSuccess(true);
			session.setAttribute("user",user);
			logService.SaveLog(new Log(Log.LOGIN_ACTION,"用户登录"));
			return json;
		}else{
			json.setMsg("此用户不存在");
			json.setSuccess(false);
			return json;
		}
	}

}
