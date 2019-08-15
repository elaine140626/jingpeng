package com.mix.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.model.IsSameUser;
import com.mix.model.ResponseInfo;
import com.mix.model.User_Info;
import com.mix.service.login.LoginService;
import com.mix.util.Consts;
import com.mix.util.MessageUtil;
@Controller
@RequestMapping("/Login")
public class LoginController{
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login.action")
	public String login() {
		return "/view/lq/login";
	}
	
	@RequestMapping("/userLogin.action")
	public @ResponseBody ResponseInfo userLogin(HttpServletRequest request, User_Info user) {
		ResponseInfo info = new ResponseInfo();
		List list = loginService.userLogin(user);
			if(list != null && list.size() == 1) {
				user = (User_Info) list.get(0);
				List<IsSameUser> isSameUser = loginService.getUserSession(user);
				if(isSameUser.size()>0) {
					// 用户同时登陆
					info.setCode(MessageUtil.SERVER_ERROR);
					info.setMessage(MessageUtil.LANDINGATTHESAMETIME);
				}else if(request.getSession().getAttribute("user")!=null) {
					//删除之前登陆的session 重新定义新用户session
					HttpSession session = request.getSession();
					User_Info userinfo = (User_Info) session.getAttribute("user");
					int result = loginService.delUserSession(userinfo);
					if(result>0) {
						session.removeAttribute("user");
						loginService.addUserSession(user);
						request.getSession().setAttribute("user", user);
					}
				}else {
					loginService.addUserSession(user);
					user = (User_Info) list.get(0);
					request.getSession().setAttribute(Consts.SESSION_USER_KEY, user);
					request.getSession().setAttribute("user", user);
				}
			} else {
				//服务器遇到错误 500
				info.setCode(MessageUtil.SERVER_ERROR);
				//账号或密码错误
                info.setMessage(MessageUtil.ACCOUNT_PASSWORD_ERROR);
			}
		return info;
	}
	// 移除用户session
		@RequestMapping("/delUserSession.action")
		@ResponseBody
		public ResponseInfo delUserSession(HttpServletRequest request) {
			HttpSession session = request.getSession();
			ResponseInfo info = new ResponseInfo();
			// 用户信息
			User_Info userInfo = (User_Info) session.getAttribute("user");
			int result = loginService.delUserSession(userInfo);
			if(result>0) {
				session.removeAttribute("user");
			}else {
				// 删除失败
				info.setCode(MessageUtil.SERVER_ERROR);
				info.setMessage("跳转失败");
			}
			return info;
	}
}
