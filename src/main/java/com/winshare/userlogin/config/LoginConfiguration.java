package com.winshare.userlogin.config;





import com.winshare.userlogin.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: wx
 * @Date: 2019-04-04 16:57
 * @Description:
 **/
@Configuration
public class LoginConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	public LoginInterceptor loginInterceptor;
	@Override
	public  void addInterceptors(InterceptorRegistry registry){
		//注册拦截器
		InterceptorRegistration addInterceptor = registry.addInterceptor(loginInterceptor);

		// 拦截路径
		addInterceptor.addPathPatterns("/**");
		// 排除路径
		addInterceptor.excludePathPatterns("/");
		addInterceptor.excludePathPatterns("/login");
		addInterceptor.excludePathPatterns("/loginout");
		// 排除资源请求 (貌似其静态资源不设置排除也可以访问)
		addInterceptor.excludePathPatterns("/css/login/*.css");
		addInterceptor.excludePathPatterns("/js/login/**/*.js");
		addInterceptor.excludePathPatterns("/image/login/*.png");
	}

}