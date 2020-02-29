package com.situ.park.user.controller;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.situ.park.user.domain.User;
import com.situ.park.user.service.UserService;
import com.situ.park.util.ConfigUtils;
import com.situ.park.util.ContextUtils;
import com.situ.park.util.PageUtils;

@Controller
@RequestMapping("/user")
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(UserController.class);
	private static final String PAGE_LOGIN_IN_USER = "user/user_login_in";
	private static final String PAGE_INDEX_USER = "user/user_index";
	private static final String PAGE_LIST_USER = "user/user_list";
	private static final String PAGE_GO_LOGIN = "admin/login";
	private static final String PAGE_GO_BUYER_INDEX = "buyer/index_user";
	
	
	@Autowired
	private UserService userService;

	

	/**
	 * @进管理员登录
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/gologin")
	public ModelAndView goLogin(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_GO_LOGIN);
		return modelAndView;
	}
	
	/**
	 * @进商城首页
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/buyerindex")
	public ModelAndView goBuyerIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_GO_BUYER_INDEX);
		return modelAndView;
	}
	
	/**
	 * @进管理员注册
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/loginin")
	public ModelAndView goLoginIn(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_LOGIN_IN_USER);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	/**
	 * @管理员新增
	 * @param user
	 * @return
	 */
	public Long doAddUser1(User user) {
		LOG.debug("传进的User参数="+user);
		return userService.userSave1(user);
	}
	
	/**
	 * @买家新增
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add2")
	public Long doAddUSer(User user) {
		String createBy = ContextUtils.getCreateBy();
		return userService.userSave2(user,createBy);
	}
	
	@ResponseBody
	@RequestMapping("/dologin")
	/**
	 * @登录管理员后台
	 * @param modelAndView
	 * @return
	 */	
	public ModelAndView goIndex1(ModelAndView modelAndView,User user,String remember,HttpServletResponse response,HttpServletRequest request) {
		user.setUserType(1);
		User login = userService.login(user);
		
//		判断是否需要自动登录
		if(remember != null && Integer.parseInt(remember) == 1 && login != null) {
//			不建议将用户CODE和用户密码写入到Cookie中
			String value = login.getUserCode()+":"+login.getRowId();
			Cookie cookie = new Cookie(ConfigUtils.COOKIE_NAME,value);
//			设置最长记录时间为7天
			cookie.setMaxAge(60*60*24*7);
//			设置cookie的路径
			cookie.setPath(request.getContextPath() + "/");
//			写出Cookie
			response.addCookie(cookie);
		} else {//否则不需要自动登录
//			将Cookie删除掉
			Cookie cookie = new Cookie(ConfigUtils.COOKIE_NAME,"");
//			将相同名称的Cookie的最大登录时间设置成0并且写出，就是删除Cookie
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath() + "/");
			response.addCookie(cookie);
		}
		
		if (login!=null) {
			//保存最后登录时间
			userService.doUpdateLogin(login);
			modelAndView.addObject("user", login);
			modelAndView.setViewName("admin/index");
		} else {
			modelAndView.setViewName("admin/filed");
		}
		return modelAndView;
	}
//	public String goIndex(HttpSession session,User user) {
//		session.setAttribute("user", userService.login(user));
//		return "1";
//	}
	
	/**
	 * @进用户管理首页
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX_USER);
		return modelAndView;
	}
	
	/**
	 * @查询所有的记录
	 * @param pageNo 分页的页号
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/find/{pageNo}")
	public ModelAndView findAllRole(@PathVariable Integer pageNo,User searchUser, ModelAndView modelAndView) {
		LOG.debug("这是页面提交的查询条件"+searchUser);		
//		解决将提交问题为''设置成null，解决mapper-xml中的查询不用写！=''问题
		searchUser = PageUtils.buildSearchParam(searchUser);
		LOG.debug(searchUser);
		// 要展示的列表数据
		modelAndView.addObject("userList", userService.findByPage(pageNo,searchUser));
		// 分页信息
		modelAndView.addObject("pageData", userService.buildPageData(pageNo,searchUser));
		modelAndView.setViewName(PAGE_LIST_USER);
		return modelAndView;
	}
	
	
	/**
	 * @进修改
	 * @param rowId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/goupdate/{rowId}")
	public User goupdate(@PathVariable("rowId")Long rowId) {
		return userService.findOneById(rowId);
	}
	
	@ResponseBody
	@RequestMapping("/doupdate")
	/**
	 * 执行用户修改
	 * @param role
	 * @return
	 */
	public Integer doUpdate(User user) {
		String createBy = ContextUtils.getCreateBy();
		return userService.doUpdate(user,createBy);
	}
	
	/**
	 * 执行删除
	 * @param rowId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dodelete/{rowId}")
	public Integer doDelete(@PathVariable("rowId")Long rowId) {
		return userService.doDelete(rowId);
	}
	
	/**
	 * 执行锁定
	 * @param rowId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/lock/{rowId}")
	public Integer doLock(@PathVariable("rowId")Long rowId) {
		return userService.doLock(rowId);
	}
	/**
	 * 执行解锁
	 * @param rowId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/nolock/{rowId}")
	public Integer doNoLock(@PathVariable("rowId")Long rowId) {
		return userService.doNoLock(rowId);
	}
	
	/**
	 * @退出管理员登录状态
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/loginout")
	public ModelAndView loginOut(ModelAndView modelAndView,HttpSession session) {
		session.removeAttribute(ConfigUtils.LOGIN_USER);
		modelAndView.setViewName(PAGE_GO_LOGIN);
		return modelAndView;
	}
	
	/**
	 * @判断name是否被使用fieldId=roleName&fieldValue=%E5%BC%A0%E4%B8%89&_=1576485199991
	 */
	@ResponseBody
	@RequestMapping("/checkUserName")
	public String checkUserName(String fieldId,String fieldValue) {
		return userService.checkUserName(fieldId,fieldValue);
	}
	/**
	 * @判断name是否被使用fieldId=roleName&fieldValue=%E5%BC%A0%E4%B8%89&_=1576485199991
	 */
	@ResponseBody
	@RequestMapping("/checkUserCode")
	public String checkUserCode(String fieldId,String fieldValue) {
		return userService.checkUserCode(fieldId,fieldValue);
	}
	
	/**
	 * @用户登录
	 * @param modelAndView
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path= {"/buyerlogin"})
	public Integer buyerLogin(HttpSession session,User user,String remember,HttpServletResponse response,HttpServletRequest request) {
		user.setUserType(2);
		User login = userService.login(user);
//		判断是否需要自动登录
		if(remember != null && Integer.parseInt(remember) == 1) {
//			不建议将用户CODE和用户密码写入到Cookie中
			String value = login.getUserCode()+":"+login.getRowId();
			Cookie cookie = new Cookie(ConfigUtils.COOKIE_BUYER_NAME,value);
//			设置最长记录时间为7天
			cookie.setMaxAge(60*60*24*7);
//			设置cookie的路径
			cookie.setPath(request.getContextPath() + "/");
//			写出Cookie
			response.addCookie(cookie);
		} else {//否则不需要自动登录
//			将Cookie删除掉
			Cookie cookie = new Cookie(ConfigUtils.COOKIE_BUYER_NAME,"");
//			将相同名称的Cookie的最大登录时间设置成0并且写出，就是删除Cookie
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath() + "/");
			response.addCookie(cookie);
		}
		
		if (login!=null) {
			userService.doUpdateLogin(login);
			session.setAttribute(ConfigUtils.LOGIN_BUYER, login);
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * @退出用户登录状态
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/loginoutbuyer")
	public ModelAndView loginOutBuyer(ModelAndView modelAndView,HttpSession session) {
		session.removeAttribute(ConfigUtils.LOGIN_BUYER);
		modelAndView.setViewName(PAGE_GO_BUYER_INDEX);
		return modelAndView;
	}
	
	/**
	 * @买家注册
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/buyerloginadd")
	public Long doAddBuyer(User user) {
		return userService.userSave3(user);
	}
	

	
	/**
	 * 查询个人信息
	 * @param rowId
	 * @return
	 */
	@RequestMapping("/index/{userCode}")
	public ModelAndView goUserPerson(ModelAndView modelAndView,@PathVariable("userCode")String userCode) {
		modelAndView.addObject("buyerUserPersonal", userService.findUserByCode(userCode));
		modelAndView.setViewName("buyer/buyer_index");
		return modelAndView;
	}
	
	/**
	 * 获取验证码
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/code/{code}")
	public Integer checkCode(ModelAndView modelAndView,@PathVariable("code")String code,HttpServletRequest request) {
		//判断验证码是否正确		
		Object authcode = request.getSession().getAttribute("codeValidate");
		String authcodeone=(String) authcode;
		if(authcodeone.equalsIgnoreCase(code)) {
			return 1;
		}
		return 2;
	}

}
