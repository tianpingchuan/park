package com.situ.park.user.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.situ.park.base.domain.BaseClass;

@Alias("User")
public class User extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userType;// 用户类型1：商家；2买家
	private String userName;// 用户名称
	private String userCode;// 用户账号
	private String userPass;// 用户密码
	private String userPhone;// 用户手机
	private Integer ifLock;// 是否锁定1：不锁定；0：锁定
	private String endIp;// 最后登录IP
	private Date endLoginDate;// 最后登录时间

	public Integer getUserType() {
		return userType;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getUserPass() {
		return userPass;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public Integer getIfLock() {
		return ifLock;
	}

	public String getEndIp() {
		return endIp;
	}

	public Date getEndLoginDate() {
		return endLoginDate;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setIfLock(Integer ifLock) {
		this.ifLock = ifLock;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}

	public void setEndLoginDate(Date endLoginDate) {
		this.endLoginDate = endLoginDate;
	}

	@Override
	public String toString() {
		return "User [userType=" + userType + ", userName=" + userName + ", userCode=" + userCode + ", userPass="
				+ userPass + ", userPhone=" + userPhone + ", ifLock=" + ifLock + ", endIp=" + endIp + ", endLoginDate="
				+ endLoginDate + "]";
	}

}
