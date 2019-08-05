package com.ns.Itercepter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ns.Util.DateUtils;
import com.ns.entity.User;


public class CheckLoginIntercepter implements HandlerInterceptor{
	
	Logger LOG=LogManager.getLogger(this.getClass());
	
    private Date startTimeMillis; // 开始时间  
    private Date endTimeMillis; // 结束时间  
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		startTimeMillis = DateUtils.getDayOfMonth(); // 记录方法开始执行的时间
		String uri=request.getRequestURI();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null){
			LOG.info("当前未登录");
			response.sendRedirect(request.getContextPath()+"/login.html");
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		endTimeMillis = DateUtils.getDayOfMonth(); // 记录方法执行完成的时间  
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		LOG.info("User:"+user.getUserName()+"；"+"startTimeMillis:"+DateUtils.getStrByDate(startTimeMillis,"yyyy-MM-dd HH:mm:ss")+"；"  +"endTimeMillis:"+DateUtils.getStrByDate(endTimeMillis,"yyyy-MM-dd HH:mm:ss")+"；"+"param:"
				+request.getQueryString()+"；"+"Url:"+request.getRequestURL());
		
	}

	
}
