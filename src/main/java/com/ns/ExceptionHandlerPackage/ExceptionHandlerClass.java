package com.ns.ExceptionHandlerPackage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerClass {

	Logger LOG=LogManager.getLogger(this.getClass());
	

	/**
	 * 500
	 * @param ex
	 * @return
	 */
	 @ExceptionHandler(Exception.class)
	 @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	  public ModelAndView programException(Exception ex) {
		 ModelAndView mav=new ModelAndView();
		 System.out.println(ex);
		 ex.printStackTrace();
		 LOG.error("程序或服务器出现异常", ex);
		 mav.addObject("exceptionEx", ex);
		 mav.setViewName("/404.html");
         return mav;
     }
	 
	
}
