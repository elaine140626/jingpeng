package com.jingpeng.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.UserInfo;
import com.jingpeng.service.UserInfoService;
import com.jingpeng.util.Consts;
import com.jingpeng.util.MessageUtil;
import com.kdt.base.exception.BusinessException;

@Controller
@RequestMapping("/TestUser")
public class UserController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("addUser.html")
	public String testUser(HttpServletRequest request) {
		return "/lq/user";
	}
	
	@RequestMapping("login.html")
	public String testUserLogin(HttpServletRequest request) {
		return "/lq/login";
	}
	@RequestMapping("userinfo.html")
	public String userinfo(HttpServletRequest request) {
		return "/test/userinfo";
	}
	@RequestMapping("usershow.html")
	public String usershow(HttpServletRequest request) {
		return "/test/usershow";
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/addTestUser.html")
	public @ResponseBody ResponseInfo addUser(UserInfo user) throws BusinessException {
		ResponseInfo info = new ResponseInfo();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userCode", user.getUserCode());
		//param.put("password", user.getPassword());
		UserInfo userInfoRe = userInfoService.getUserInfo(param);
		if(userInfoRe == null) {	
			int row = userInfoService.addUserInfo(user);
			if(row>0) {
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//注册成功
				info.setMessage(MessageUtil.LOGIN_SUCCESSFUL);
			}
		}else {
			//多种选择 300
			info.setCode(MessageUtil.SERVER_ERROR);
			//用户名已经存在
			info.setMessage(MessageUtil.USERNAME_EXISTENCE);
		}
		return info;
	}
	
	
	
	@RequestMapping("/userLogin.html")
	public @ResponseBody ResponseInfo userLogin(HttpServletRequest request, UserInfo user) {
		ResponseInfo info = new ResponseInfo();
		List list;
		try {
			list = userInfoService.userLogin(user);
			if(list != null && list.size() == 1) {
			user = (UserInfo) list.get(0);
			//request.getSession().setAttribute(Consts.SESSION_USER_KEY, user);
			request.getSession().setAttribute("user", user);
			/*if(user.getRoleType()==1) {
				//error
				info.setCode(MessageUtil.SERVER_ERROR);
				//没有权限
			    info.setMessage(MessageUtil.NO_AUTHORITY);
			}else {*/
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//登录成功
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			/*}*/
			} else {
				//error
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
	
	@RequestMapping("/getUserinfo.html")
	public @ResponseBody DataTablesResponseInfo getPlatformUserinfo(HttpServletRequest request, @RequestParam Map<String, Object> user) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			
			List<Map<String, Object>> list = userInfoService.getUserinfo(user);
			for(int i= 0; i < list.size(); i++) {
				String operate = "<span><a href='javascript:void(0);' onclick='show(\""+list.get(i).get("i_id")+"\");' title=\"修改\">修改</a><a href='javascript:void(0);' onclick='del(\""+list.get(i).get("i_id")+"\");' title=\"删除\">删除</a>";
				list.get(i).put("serialNumber", i+1);
				list.get(i).put("details", operate);
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/delUserInfo.html")
	public @ResponseBody ResponseInfo delUserInfo(UserInfo user,HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		try {
			userInfoService.delUserInfo(user);
			if(user.getOrgId()==1) {
				info.setAmount("1");				
			}else {
				info.setAmount("2");
				request.getSession().removeAttribute("user");
			}
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	@RequestMapping("/getshowInfo.html")
	public @ResponseBody DataTablesResponseInfo getshowInfo(@RequestParam Map<String, Object> user) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			List<Map<String, Object>> list = userInfoService.getUserinfoById(user);
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/updateUser.html")
	public @ResponseBody ResponseInfo updateUser(HttpServletRequest request,UserInfo user) {
		ResponseInfo info = new ResponseInfo();
		try {
			List list;
			userInfoService.updateUser(user);
		/*	list = userInfoService.userLogin(user);
			user = (UserInfo) list.get(0);
			request.getSession().setAttribute("user", user);*/
			//成功处理请求提示 200
			UserInfo users = (UserInfo)request.getSession().getAttribute("user");
			info.setAmount(users.getOrgId().toString());
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
}