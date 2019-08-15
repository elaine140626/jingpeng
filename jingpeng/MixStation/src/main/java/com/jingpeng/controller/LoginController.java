package com.jingpeng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.LoginService;
import com.jingpeng.util.Consts;
import com.jingpeng.util.MessageUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
@RequestMapping("/Login")
public class LoginController extends KDController<Object>{
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login.html")
	public String login() {
		return "/lq/login";
	}
	
	@RequestMapping("/platformLogin.html")
	public String platformLogin() {
		return "/lq/platformLogin";
	}
	
	@RequestMapping("/userLogin.html")
	public @ResponseBody ResponseInfo userLogin(HttpServletRequest request, User_Info user) {
		ResponseInfo info = new ResponseInfo();
		List list;
		try {
			list = loginService.userLogin(user);
			if(list != null && list.size() == 1) {
				user = (User_Info) list.get(0);
				request.getSession().setAttribute(Consts.SESSION_USER_KEY, user);
                request.getSession().setAttribute("user", user);
			} else {
				//服务器遇到错误 500
				info.setCode(MessageUtil.SERVER_ERROR);
				//账号或密码错误
                info.setMessage(MessageUtil.ACCOUNT_PASSWORD_ERROR);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	@RequestMapping("/platformUserLogin.html")
	public @ResponseBody ResponseInfo platformLogin(HttpServletRequest request, User_Info user) {
		ResponseInfo info = new ResponseInfo();
		List list;
		try {
			list = loginService.userLoginPlatform(user);
			if(list != null && list.size() == 1) {
				Core_User_Info cuser = (Core_User_Info) list.get(0);
				request.getSession().setAttribute(Consts.SESSION_USER_KEY, cuser);
                request.getSession().setAttribute("user", cuser);
			} else {
				//服务器遇到错误 500
				info.setCode(MessageUtil.SERVER_ERROR);
				//账号或密码错误
                info.setMessage(MessageUtil.ACCOUNT_PASSWORD_ERROR);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
}
