package com.situ.park.user.service;

import java.util.List;

import com.situ.park.base.domain.PageData;
import com.situ.park.user.domain.User;

public interface UserService {
	
	Long userSave1(User user);
	
	Long userSave2(User user, String createBy);
	
	Long userSave3(User user);
	
	User login(User user);
	
	PageData buildPageData(Integer pageNo, User searchUser);

	List<User> findByPage(Integer pageNo, User searchUser);
	
	User findOneById(Long rowId);

	Integer doUpdate(User user, String createBy);

	Integer doDelete(Long rowId);
	
	void initAdmin();

	String checkUserName(String fieldId, String fieldValue);

	String checkUserCode(String fieldId, String fieldValue);

	Integer doLock(Long rowId);

	Integer doNoLock(Long rowId);

	User findUserByCodeAndId(String userCode, Long rowId);
	
	List<User> findAll();

	User findUserByCode(String userCode);

	/**
	 * 修改登录时间
	 * @param login
	 */
	void doUpdateLogin(User login);


}
