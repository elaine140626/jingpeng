package com.highwayPlatform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.highwayPlatform.model.DataTablesResponseInfo;
import com.highwayPlatform.model.PtUserInfo;
import com.highwayPlatform.model.ResponseInfo;
import com.highwayPlatform.model.cookieModel;
import com.highwayPlatform.service.UserInfoService;
import com.highwayPlatform.util.MessageUtilBlindSample;

@Controller
@RequestMapping("/UserInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	// 获取用户信息
	@RequestMapping("/getPtUserInfo.action")
	public @ResponseBody cookieModel getValue(HttpServletRequest request) {
		// 通过session获取账号密码
		HttpSession session = request.getSession();

		// 用户信息
		PtUserInfo userInfo = (PtUserInfo) session.getAttribute("user");
		cookieModel cookie = new cookieModel();
		if (userInfo != null) {
			// 用户的id
			cookie.setId(userInfo.getId());
			// 用户名
			cookie.setUserCode(userInfo.getUserCode());
			// 姓名
			cookie.setName(userInfo.getName());
			// 试验室权限
			cookie.setUserTestDetailed(session.getAttribute("userTestDetailed").toString());
			// 拌合站权限
			cookie.setUserOrgDetailed(session.getAttribute("userOrgDetailed").toString());
			// 用户的权限
			cookie.setRoletype(userInfo.getRoleType());
			// 用户的角色
			cookie.setIssystemuser(userInfo.getIsSystemUser());
			// 收样权限
			cookie.setIsCollector(userInfo.getIsCollector());
		}

		return cookie;
	}

	// 获取试验室树形结构
	@RequestMapping("/getTestRoomList.action")
	@ResponseBody
	public DataTablesResponseInfo getTestRoomName(@RequestParam HashMap<String, Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<Map<String, Object>> list = userInfoService.getTestRoomList(map);
		dtr.setData(list);
		return dtr;
	}

	// 获取拌合站树形结构
	@RequestMapping("/getOrgInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getOrgInfoList(@RequestParam HashMap<String, Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<Map<String, Object>> list = userInfoService.getOrgInfoList();
		dtr.setData(list);
		return dtr;
	}

	// 接收map参数，查询实验室名称
	@RequestMapping("/insertUserInfo.action")
	@ResponseBody
	public ResponseInfo addTestRoomName(@RequestParam HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		// 添加用户，返回用户的id
		int result = userInfoService.insertUserInfo(map);
		if(result > 0) {
			if(result == 2) {
				// 用户名已经存在
				info.setCode(MessageUtilBlindSample.SERVER_ERROR);
				info.setMessage(MessageUtilBlindSample.USERNAME_EXISTENCE);
			}else {
				// 注册成功
				info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
				info.setMessage(MessageUtilBlindSample.LOGIN_SUCCESSFUL);
			}
			
		}else {
			// 注册失败
			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
			info.setMessage(MessageUtilBlindSample.LOGIN_ERROR);
		}
		return info;
	}

	// 登录
	@RequestMapping("/userLogin.action")
	public @ResponseBody ResponseInfo userLogin(HttpServletRequest request, @RequestParam HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		// 登录验证
		PtUserInfo data = userInfoService.getUserLogin(map, request);
		if (data == null) {	
			// 登录失败
			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
			info.setMessage(MessageUtilBlindSample.ACCOUNT_PASSWORD_ERROR);
		}else {
//			List<IsSameUser> isSameUser = userInfoService.getUserSession(data);
//			if(isSameUser.size()>0) {
//				// 用户同时登陆
//				info.setCode(MessageUtilBlindSample.SERVER_ERROR);
//				info.setMessage(MessageUtilBlindSample.LANDINGATTHESAMETIME);
//			}else if(request.getSession().getAttribute("user")!=null){
//				//删除之前登陆的session 重新定义新用户session
				HttpSession session = request.getSession();
//				PtUserInfo user = (PtUserInfo) session.getAttribute("user");
//				int result = userInfoService.delUserSession(user);
				session.removeAttribute("user");
//				userInfoService.addUserSession(data);
				request.getSession().setAttribute("user", data);
//			}else {
//				userInfoService.addUserSession(data);
//				request.getSession().setAttribute("user", data);
//			}
		} 
		return info;
	}
	
	// app登录验证
    @RequestMapping(value = { "/loginApp.json" })
    public ResponseEntity<Map<String, Object>> collection(@RequestParam(value="username")String username, @RequestParam(value="password")String password, HttpServletRequest request){
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	// 登录验证
    	param.put("userCode", username);
    	param.put("passWord", password);
    	PtUserInfo data = userInfoService.getUserLogin(param, request);
    	
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", data);
		if(data != null) {
//			//获取拌合站权限
//			map.put("userOrgDetailed", request.getSession().getAttribute("userOrgDetailed"));
			//试验室权限
			map.put("userTestDetailed", request.getSession().getAttribute("userTestDetailed"));
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		}else {
			map.put("type", "PASSWORD_ERROR");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);	
		}
    }

	// 获取用户信息
	@RequestMapping("/getPtUserList.action")
	@ResponseBody
	public DataTablesResponseInfo getUserInformation(@RequestParam HashMap<String, Object> map,
			HttpServletRequest request) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();

		// 获取当前用户信息
		List<Map<String, Object>> dataList = userInfoService.getUserInfo(map);

		// 返回
		dtr.setData(dataList);
		return dtr;
	}

	// 删除用户
	@RequestMapping("/deleteUserInfo.action")
	public @ResponseBody ResponseInfo testUserdel(@RequestParam HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		// 删除试验室的
		int result = userInfoService.deleteUserInfo(map);
		if(result > 0) {
			// 删除成功
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		}else {
			// 删除失败
			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
			info.setMessage(MessageUtilBlindSample.OPERATION_FAILED);
		}
		return info;
	}

	// 修改用户信息
	@RequestMapping("/updateUserInfo.action")
	@ResponseBody
	public ResponseInfo updateTestUser(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		
		// 更新用户信息
		int result = userInfoService.updateUserInfo(map);
		if(result > 0) {
			// 修改成功
			this.delUserSession(request);
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		}else {
			// 修改失败
			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
			info.setMessage(MessageUtilBlindSample.OPERATION_FAILED);
		}
		
		return info;
	}
	
	// 移除用户session
	@RequestMapping("/delUserSession.action")
	@ResponseBody
	public ResponseInfo delUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ResponseInfo info = new ResponseInfo();
//		// 用户信息
//		PtUserInfo userInfo = (PtUserInfo) session.getAttribute("user");
//		int result = userInfoService.delUserSession(userInfo);
//		if(result>0) {
			session.removeAttribute("user");
//		}else {
//			// 删除失败
//			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
//			info.setMessage("跳转失败");
//		}
		return info;
		}
}
