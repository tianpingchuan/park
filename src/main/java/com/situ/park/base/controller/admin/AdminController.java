package com.situ.park.base.controller.admin;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.situ.park.user.domain.User;
import com.situ.park.user.service.UserService;
import com.situ.park.util.ConfigUtils;

@Controller
public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String ADMIN_INDEX = "admin/index";
	private static final String ADMIN_LOGIN = "admin/login";
	
	@Autowired
	private UserService userService;
	
	/**
	 * @进管理首页
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/admin")
	public ModelAndView goLoginIn(ModelAndView modelAndView, HttpSession session,
			@CookieValue(value = "WWW.PARK", required = false) String remember) {

		if (remember != null) {
			String[] array = remember.split(":");
			String userCode = array[0];
			Long rowId = Long.parseLong(array[1]);
//			使用usercode和id到数据库中查询用户实例
			User user = userService.findUserByCodeAndId(userCode, rowId);
			if (user != null) {// 判断有这个用户
//				满足用户登录状态
//				有登陆用户将用户信息放入session
				session.setAttribute(ConfigUtils.LOGIN_USER, user);
				userService.doUpdateLogin(user);
			}
		}
		if (session.getAttribute(ConfigUtils.LOGIN_USER) != null) {// 判断为登录状态
			modelAndView.setViewName(ADMIN_INDEX);
		} else {// 判断为非登陆状态
			modelAndView.setViewName(ADMIN_LOGIN);
		}
		return modelAndView;
	}

}
