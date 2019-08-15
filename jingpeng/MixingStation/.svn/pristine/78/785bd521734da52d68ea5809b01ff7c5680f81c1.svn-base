package com.mix.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.model.Organization_Info;
import com.mix.model.ResponseInfo;
import com.mix.model.User_Info;
import com.mix.service.login.RegisterService;
import com.mix.util.MessageUtil;
@Controller
@RequestMapping("/Register")
public class RegisterController{
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/register.action")
	public String user() {
		return "/view/user/register";
	}
	/**
	 * 客户端注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/addUser.action")
	public @ResponseBody ResponseInfo addUser(User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			List<User_Info> list = registerService.getUserName(user);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//用户名已经存在
				info.setMessage(MessageUtil.USERNAME_EXISTENCE);
			} else {
				registerService.addUser(user);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//注册成功
				info.setMessage(MessageUtil.LOGIN_SUCCESSFUL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//注册失败
			info.setMessage(MessageUtil.LOGIN_ERROR);
		}
		return info;
	}
	
	/**
	 * 获得组织
	 * @return
	 */
	@RequestMapping("/getOrg.action")
	public @ResponseBody List<Organization_Info> getOrg(){
		List<Organization_Info> list;
		try {
			list = registerService.getOrg();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
