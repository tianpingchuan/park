package com.situ.park.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.park.user.domain.User;

@Repository
public interface UserDao {

	Long save(User user);

	User login(User user);

	List<User> findByPage(@Param("searchUser") User searchUser, @Param("firstResult") Integer firstResult,@Param("maxResults") Integer maxResults);

	Integer getCount(@Param("searchUser") User searchUser);
	
	User get(Long rowId);

	void update(User user);

	void delete(Long rowId);

	/**
	 * 自动登录查询
	 * @param usercode
	 * @param id
	 * @return
	 */
	User findUserByCodeAndId(@Param("userCode")String userCode, @Param("rowId")Long rowId);

	/**
	 * @通过名称查询
	 * @param fieldValue
	 * @return
	 */
	User getByName(String userName);

	/**
	 * @通过账号查询
	 * @param fieldValue
	 * @return
	 */
	User getByCode(String userCode);

	void doLock(Long rowId);

	void doNoLock(Long rowId);

	List<User> findAll();
	
	void doUpdateLogin(User user);
}
