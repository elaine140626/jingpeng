package com.interceptor;

import javax.servlet.annotation.WebListener;
/**
 * session监听
 */
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.highwayPlatform.model.PtUserInfo;
import com.highwayPlatform.service.UserInfoService;
import com.mixingStation.model.userInfo.UserInfo;

@WebListener
public class SessionListener implements HttpSessionListener {

	private UserInfoService userInfoService;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(
                arg0.getSession().getServletContext());
		userInfoService = applicationContext.getBean(UserInfoService.class);
		// session销毁时清除数据库记录
		// 平台登录用户
		PtUserInfo userInfo = (PtUserInfo)arg0.getSession().getAttribute("user");
		// 拌合站登录用户
		UserInfo mixUserInfo = (UserInfo)arg0.getSession().getAttribute("mixUser");
		if(userInfo != null) {
			int result = userInfoService.delUserSession(userInfo);
			if(result>0) {
				arg0.getSession().removeAttribute("user");
			}
		} else if (mixUserInfo != null) {
			int result = userInfoService.delMixUserSession(mixUserInfo);
			if(result>0) {
				arg0.getSession().removeAttribute("mixUser");
			}
		}
		
	}

}
