package com.winshare.userlogin.login;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wx
 * @Date: 2019-04-04 16:24
 * @Description:
 **/
@Controller
public class LoginController {

	@GetMapping("/")
	public String index() {
		return "login";
	}

	/**
	 * 欢迎页
	 *
	 * @return
	 */
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}


	// 预先设置好的正确的用户名和密码，用于登录验证
	private String rightUserName = "admin";
	private String rightPassword = "admin";

	/**
	 * 登录校验
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (null == username || null == password) {
			return "redirect:/";
		}

		// 前端传回的密码实际为用户输入的：用户名（小写）+ 密码（小写）组合的字符串生成的md5值
		// 此处先通过后台保存的正确的用户名和密码计算出正确的md5值，然后和前端传回来的作比较
//		String md5info = rightUserName.toLowerCase() + rightPassword.toLowerCase();
//		String realPassword = DigestUtils.md5DigestAsHex(md5info.getBytes());
//		if (!password.equals(realPassword)) {
//			return "redirect:/";
//		}

		if (username.equals(rightUserName) && password.equals(rightPassword)) {
			// 校验通过时，在session里放入一个标识
			// 后续通过session里是否存在该标识来判断用户是否登录
			request.getSession().setAttribute("username", "admin");
			return "redirect:/welcome";
		}

		return "redirect:/";
	}

	/**
	 * 注销登录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginout")
	public String loginOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = request.getSession().getId();
		Cookie cookie = new Cookie("duxu", "123");
		// cookie.setValue("456"); //此处覆盖123的值
		response.addCookie(cookie);
		return "sessionId=" + sessionId;
	}
}