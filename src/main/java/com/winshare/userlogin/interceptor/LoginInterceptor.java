package com.winshare.userlogin.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wx
 * @Date: 2019-04-04 16:55
 * @Description:
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 检查每个到来的请求对应的session域中是否有登录标识
		Object loginName = request.getSession().getAttribute("username");
		if (null == loginName || !(loginName instanceof String)) {
			// 未登录，重定向到登录页
			response.sendRedirect("/");
			return false;
		}
		String userName = (String) loginName;
		System.out.println("当前用户已登录，登录的用户名为： " + userName);
		return true;
	}

	/**
	 * 在请求被处理后，视图渲染之前调用
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在整个请求结束后调用
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
