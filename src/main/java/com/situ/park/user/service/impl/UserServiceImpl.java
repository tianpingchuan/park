package com.situ.park.user.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.park.base.domain.PageData;
import com.situ.park.user.dao.UserDao;
import com.situ.park.user.domain.User;
import com.situ.park.user.service.UserService;
import com.situ.park.util.JSONUtils;
import com.situ.park.util.MD5Utils;
import com.situ.park.util.PageUtils;

@Service
public class UserServiceImpl implements Serializable, UserService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDao userDao;

	@Override
	public Long userSave1(User user) {
		user.setUserType(1);
		user.setActiveFlag(1);
		user.setCreateBy("admin");
		user.setCreateDate(new Date());
		user.setIfLock(1);
//		MD5加密，不可逆
		String userPass = MD5Utils.encode(user.getUserPass());
		user.setUserPass(userPass);
		return userDao.save(user);
	}

	@Override
	public Long userSave2(User user, String createBy) {
		user.setUserType(2);
		user.setActiveFlag(1);
		user.setCreateBy(createBy);
		user.setCreateDate(new Date());
		user.setIfLock(1);
		String userPass = MD5Utils.encode(user.getUserPass());
		user.setUserPass(userPass);
		return userDao.save(user);
	}
	

	@Override
	public Long userSave3(User user) {
		user.setUserType(2);
		user.setActiveFlag(1);
		user.setCreateBy("sys");
		user.setCreateDate(new Date());
		user.setIfLock(1);
		String userPass = MD5Utils.encode(user.getUserPass());
		user.setUserPass(userPass);
		return userDao.save(user);
	}
	
	@Override
	public User login(User user) {
		String userPass = MD5Utils.encode(user.getUserPass());
		user.setUserPass(userPass);
		User userFind = userDao.login(user);
		return userFind;
	}

	@Override
	public PageData buildPageData(Integer pageNo, User searchUser) {
		// 查询出数据总数
		Integer dataCount = userDao.getCount(searchUser);
		return PageUtils.buildPageData(dataCount, pageNo);
	}

	@Override
	public List<User> findByPage(Integer pageNo, User searchUser) {
		// limit查询数据开始的下标
		Integer firstResult = (pageNo - 1) * PageUtils.PAGE_ROWS;
		// limit查询数据 要显示的条数
		Integer maxResults = PageUtils.PAGE_ROWS;
		return userDao.findByPage(searchUser, firstResult, maxResults);
	}

	@Override
	public User findOneById(Long rowId) {
		return userDao.get(rowId);
	}

	@Override
	public Integer doUpdate(User user, String createBy) {
		String userPass = MD5Utils.encode(user.getUserPass());
		user.setUserPass(userPass);
		user.setUpdateBy(createBy);
		user.setUpdateDate(new Date());
		userDao.update(user);
		return 1;
	}

	@Override
	public Integer doDelete(Long rowId) {
		userDao.delete(rowId);
		return 1;
	}

	@Override
	@PostConstruct
	/**
	 * @项目开启自动调用	保证在项目开启的时候至少有一个管理员
	 * @当UserServiceImpl这个实例生成以后，会马上自动调用这个方法
	 */
	public void initAdmin() {
		String userCode = "123";
		String userPass = "123";
		User user = userDao.getByCode(userCode);
		if (user == null) {
			user = new User();
			user.setUserCode(userCode);
			user.setUserName("田平川");
			user.setUserPass(MD5Utils.encode(userPass));
			user.setUserType(1);
			user.setUserPhone("17788778877");
			user.setActiveFlag(1);
			user.setCreateBy("admin");
			user.setCreateDate(new Date());
			user.setIfLock(1);
			userDao.save(user);
		}
	}

	@Override
	/**
	 *@ 唯一性检验
	 */
	public String checkUserName(String fieldId, String fieldValue) {
		User user = userDao.getByName(fieldValue);
		Boolean bool = user != null ? false : true;
		return JSONUtils.buildValidationInfo(fieldId, bool);
	}

	@Override
	public String checkUserCode(String fieldId, String fieldValue) {
		User user = userDao.getByCode(fieldValue);
		Boolean bool = user != null ? false : true;
		return JSONUtils.buildValidationInfo(fieldId, bool);
	}

	@Override
	public Integer doLock(Long rowId) {
		userDao.doLock(rowId);
		return 1;
	}

	@Override
	public Integer doNoLock(Long rowId) {
		userDao.doNoLock(rowId);
		return 1;
	}

	@Override
	public User findUserByCodeAndId(String userCode, Long rowId) {
		return userDao.findUserByCodeAndId(userCode, rowId);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findUserByCode(String userCode) {
		return userDao.getByCode(userCode);
	}

	@Override
	public void doUpdateLogin(User login) {
		login.setEndIp("sys");
		login.setEndLoginDate(new Date());
		userDao.doUpdateLogin(login);
	}


}
