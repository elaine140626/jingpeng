package com.curing.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.login.model.UserInfo;
import com.curing.login.service.LoginService;

@Controller
@RequestMapping("/Login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	// 用户登录验证
	@RequestMapping("/login.action")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		List<UserInfo> dataList = loginService.getUserInfo(map);
		Map<String, Object> data = new HashMap<String, Object>();
		if(dataList != null && dataList.size() > 0) {
			// session
			request.getSession().setAttribute("user", dataList.get(0));
			data.put("user", dataList.get(0));
		}else {
			data.put("user", null);
		}
		return data;
	};
	
	
	// 获取用户信息
	@RequestMapping("/getUserInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getUserInfoList(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<UserInfo> dataList = loginService.getUserInfo(map);
		dInfo.setData(dataList);
		return dInfo;
	};
	
	/**
	 * 获取当前登录用户的信息
	 * @throws IOException 
	 */
	@RequestMapping("/getUser.action")
	public @ResponseBody UserInfo getValue(HttpServletRequest request) {
		// 通过session获取账号密码
		HttpSession session = request.getSession();

		// 用户信息
		UserInfo userInfo = (UserInfo) session.getAttribute("user");
		if(userInfo == null) {
			userInfo = new UserInfo();
		}
		return userInfo;
	}
		
	// 用户名判重
	@RequestMapping("/getUserNameCount.action")
	@ResponseBody
	public ResponseInfo getUserNameCount(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int res = loginService.getUserName(map);
		if (res>0) {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorUserName);
		} else {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		}
		return info;	
	};
		
	// 保存用户信息操作
	@RequestMapping("/saveUserInfo.action")
	@ResponseBody
	public ResponseInfo saveUserInfo(@RequestBody UserInfo userInfo) {
		ResponseInfo info = new ResponseInfo();
		int res = loginService.saveUserInfo(userInfo);
		if (res > 0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
			
		}
		return info;
	};
	
	// 修改用户信息操作
	@RequestMapping("/updateUserInfo.action")
	@ResponseBody
	public ResponseInfo updateUserInfo(@RequestBody UserInfo userInfo) {
		ResponseInfo info = new ResponseInfo();
		int res = loginService.updateUserInfo(userInfo);
		if (res > 0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
			
		}
		return info;
	};
	
	// 删除用户信息
	@RequestMapping("/deleteUserInfo.action")
	@ResponseBody
	public ResponseInfo deleteUserInfo(@RequestBody UserInfo userInfo) {
		ResponseInfo info = new ResponseInfo();
		int res = loginService.deleteUserInfo(userInfo);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;
	};
}
