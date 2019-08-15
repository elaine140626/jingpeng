package com.mixingStation.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mixingStation.model.userInfo.UserInfo;

/**
 * @since controller的超类，包含公共的属性和方法 
 * @author Administrator
 */
public class BaseController {
	
	/**
	 * @since 获取当前登录用户信息
	 * @param request
	 * @return
	 */
	protected UserInfo getLoginUser (HttpServletRequest request) {
		// 获取session
		HttpSession session = request.getSession();
		// 获取当前登录用户信息
		UserInfo userInfo = (UserInfo)session.getAttribute("mixUser");
		return userInfo;
	}
}
