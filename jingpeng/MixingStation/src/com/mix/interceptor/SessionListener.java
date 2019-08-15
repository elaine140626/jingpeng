package com.mix.interceptor;

import javax.servlet.annotation.WebListener;
/**
 * session监听
 */
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mix.model.User_Info;
import com.mix.service.login.LoginService;

@WebListener
public class SessionListener implements HttpSessionListener {

	private LoginService loginService;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(
                arg0.getSession().getServletContext());
		loginService = applicationContext.getBean(LoginService.class);
		// session销毁时清除数据库记录
		User_Info userInfo = (User_Info)arg0.getSession().getAttribute("user");
		if(userInfo!=null) {
			int result = loginService.delUserSession(userInfo);
			if(result>0) {
				arg0.getSession().removeAttribute("user");
			}
		}
		
	}

}