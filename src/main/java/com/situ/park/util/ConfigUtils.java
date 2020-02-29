package com.situ.park.util;

import java.io.Serializable;

public class ConfigUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	管理员登录用户放在session中的实例名
	public static final String LOGIN_USER = "user";
	
//	买家用户放在session中的实例名
	public static final String LOGIN_BUYER = "userBuyer";
//	管理员cookie名
	public static final String COOKIE_NAME = "WWW.PARK";
	
//	用户cookie名
	public static final String COOKIE_BUYER_NAME = "WWW.PARK.USER";
	
	public static final String TO_BASE_FILE = "E:/webfiles/";

}
