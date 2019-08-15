package com.jingpeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.RegisterService;
import com.jingpeng.util.MessageUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
@RequestMapping("/Register")
public class RegisterController extends KDController<Object>{
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/register.html")
	public String user() {
		return "/user/user";
	}
	
	/**
	 * 平台跳页
	 * @return
	 */
	@RequestMapping("/platformRegister.html")
	public String platformUser() {
		return "/lq/user";
	}
	
	/**
	 * 平台获得组织
	 * @return
	 */
	@RequestMapping("/getPlatformOrg.html")
	public @ResponseBody List getPlatformOrg(){
		try {
			List<Map<String, Object>> list = registerService.getTopPlatformOrg();
			return list;
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 平台注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/addPlatformUser.html")
	public @ResponseBody ResponseInfo addPlatformUser(Core_User_Info core_User_Info) {
		ResponseInfo info = new ResponseInfo();
		try {
			List<Core_User_Info> list = registerService.getPlatformUserName(core_User_Info);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//用户名已经存在
				info.setMessage(MessageUtil.USERNAME_EXISTENCE);
			} else {
				registerService.addPlatformUser(core_User_Info);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//注册成功
				info.setMessage(MessageUtil.LOGIN_SUCCESSFUL);
			}
		} catch (BusinessException e) {
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
	 * 客户端注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/addUser.html")
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
		} catch (BusinessException e) {
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
	@RequestMapping("/getOrg.html")
	public @ResponseBody List<Organization_Info> getOrg(){
		List<Organization_Info> list;
		try {
			list = registerService.getOrg();
			return list;
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
