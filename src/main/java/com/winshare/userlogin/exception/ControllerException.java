package com.winshare.userlogin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: wx
 * @Date: 2019-04-04 16:29
 * @Description:
 **/
@ControllerAdvice
public class ControllerException {


	@ExceptionHandler(Exception.class)
	public ModelAndView errorPage() {
		return new ModelAndView("4xx");
	}

}
