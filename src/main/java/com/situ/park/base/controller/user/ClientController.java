package com.situ.park.base.controller.user;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.situ.park.user.domain.User;
import com.situ.park.user.service.UserService;
import com.situ.park.util.ConfigUtils;

@Controller
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CLIENT_INDEX = "client/index";
	private static final String CLIENT_LOGIN = "client/login";
	private static final String CLIENT_LOGIN_IN = "client/login_in";

	@Autowired
	private UserService userService;

	@RequestMapping(path = { "/index", "/" })
	public ModelAndView goLoginIn(ModelAndView modelAndView, HttpSession session,
			@CookieValue(value = ConfigUtils.COOKIE_BUYER_NAME, required = false) String remember) {
		if (remember != null) {
			String[] array = remember.split(":");
			String userCode = array[0];
			Long rowId = Long.parseLong(array[1]);
//			使用usercode和id到数据库中查询用户实例
			User user = userService.findUserByCodeAndId(userCode, rowId);
			if (user != null) {// 判断有这个用户
//				满足用户登录状态
//				有登陆用户将用户信息放入session
				session.setAttribute(ConfigUtils.LOGIN_BUYER, user);
				userService.doUpdateLogin(user);
				modelAndView.setViewName(CLIENT_INDEX);
			} else {
				modelAndView.setViewName(CLIENT_LOGIN);
			}
		} else {
			modelAndView.setViewName(CLIENT_LOGIN);
		}
		
		return modelAndView;
	}

	/**
	 * 
	 * @Title: login
	 * @Description:(登录查找用户)
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = { "/login" })
	public Integer login(User user) {
		user.setUserType(2);
		User userFind = userService.login(user);
		if (userFind == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * 
	 * @Title: goIndex
	 * @Description:(登录成功进客户首页)
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(path = { "/success/{login}/{remember}" })
	public ModelAndView goIndex(ModelAndView modelAndView, @PathVariable String login, @PathVariable String remember,
			HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		User findUser = userService.findUserByCode(login);
		if (remember.equals("undefined")) {
			remember = "0";
		}
//		判断是否需要自动登录
		if (Integer.parseInt(remember) == 1 && findUser != null) {
//			不建议将用户CODE和用户密码写入到Cookie中
			String value = findUser.getUserCode() + ":" + findUser.getRowId();
			Cookie cookie = new Cookie(ConfigUtils.COOKIE_BUYER_NAME, value);
//			设置最长记录时间为7天
			cookie.setMaxAge(60 * 60 * 24 * 7);
//			设置cookie的路径
			cookie.setPath(request.getContextPath() + "/");
//			写出Cookie
			response.addCookie(cookie);
		} else {// 否则不需要自动登录
//			将Cookie删除掉
			Cookie cookie = new Cookie(ConfigUtils.COOKIE_BUYER_NAME, "");
//			将相同名称的Cookie的最大登录时间设置成0并且写出，就是删除Cookie
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath() + "/");
			response.addCookie(cookie);
		}
		userService.doUpdateLogin(findUser);
		modelAndView.setViewName(CLIENT_INDEX);
		session.setAttribute(ConfigUtils.LOGIN_BUYER, findUser);
		modelAndView.addObject(ConfigUtils.LOGIN_BUYER, findUser);
		return modelAndView;
	}

	/**
	 * @退出客户登录状态
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/loginout")
	public ModelAndView loginOut(ModelAndView modelAndView, HttpSession session) {
		session.removeAttribute(ConfigUtils.LOGIN_BUYER);
		modelAndView.setViewName(CLIENT_LOGIN);
		return modelAndView;
	}
	
	/**
	 * @进登录页面
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/gologin")
	public ModelAndView goLogin(ModelAndView modelAndView, HttpSession session) {
		modelAndView.setViewName(CLIENT_LOGIN);
		return modelAndView;
	}
	
	/**
	 * @注册
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/loginin")
	public ModelAndView loginIn(ModelAndView modelAndView, HttpSession session) {
		modelAndView.setViewName(CLIENT_LOGIN_IN);
		return modelAndView;
	}
}
