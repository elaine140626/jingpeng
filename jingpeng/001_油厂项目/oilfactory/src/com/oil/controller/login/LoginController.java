package com.oil.controller.login;

import java.io.IOException;
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

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.UserEntity;
import com.oil.model.Userinfo;
import com.oil.service.login.LoginService;
import com.oil.util.Consts;
import com.oil.util.MessageUtil;
import com.oil.util.PropertyUtil;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
		
	/**
	 * 登录验证
	 * @param 获取用户名和密码
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/login.action")
	public @ResponseBody ResponseInfo login(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();	
		// 获取用户信息
		Userinfo userInfo = loginService.login(map);
		/*// 是否存在用户
		if(userInfo != null) {
			info.setCode("success");
			// 用户信息存储session中
			request.getSession().setAttribute("user", userInfo);
		}else {
			info.setCode("error");
			// 用户或密码错误!
			info.setMessage(PropertyUtil.getProperties("M0001"));
		}*/
		if(userInfo != null) {
//			List<IsSameUser> isSameUser = loginService.getUserSession(userInfo);
//			if(isSameUser.size()>0) {
//				// 用户同时登陆
//				info.setCode(MessageUtil.SERVER_ERROR);
//				info.setMessage(MessageUtil.LANDINGATTHESAMETIME);
//			}else if(request.getSession().getAttribute("user")!=null) {
//				//删除之前登陆的session 重新定义新用户session
//				HttpSession session = request.getSession();
//				Userinfo userinfo = (Userinfo) session.getAttribute("user");
//				int result = loginService.delUserSession(userinfo);
//				if(result>0) {
//					session.removeAttribute("user");
//					loginService.addUserSession(userInfo);
//					request.getSession().setAttribute("user", userInfo);
//				}
//			}else {
//				loginService.addUserSession(userInfo);
				request.getSession().setAttribute(Consts.SESSION_USER_KEY, userInfo);
				request.getSession().setAttribute("user", userInfo);
//			}
		} else {
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//账号或密码错误
            info.setMessage(MessageUtil.ACCOUNT_PASSWORD_ERROR);
		}
		
		return info;
	}
	
    // app登录验证
    @RequestMapping(value = { "/loginApp.json" })
    public ResponseEntity<Map<String, Object>> collection(@RequestParam(value="username")String username, @RequestParam(value="password")String password){
    	
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("username", username);
    	param.put("password", password);
    	
    	// 登录验证
    	Userinfo userInfo = loginService.login(param);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", userInfo);
		if(userInfo != null) {
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		}else {
			map.put("type", "PASSWORD_ERROR");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);	
		}
    }
	
	/**
	 * 获取当前登录用户的信息
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getUser.action")
	public @ResponseBody Userinfo getValue(HttpServletRequest request) {
		// 通过session获取账号密码
		HttpSession session = request.getSession();

		// 用户信息
		Userinfo userInfo = (Userinfo) session.getAttribute("user");
		if(userInfo == null) {
			userInfo = new Userinfo();
		}
		return userInfo;
	}
	
	/**
	 * 用户名验证
	 * @param 用户名
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getUserInfo.action")
	public @ResponseBody ResponseInfo getUserInfo(@RequestParam HashMap<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();		
		List<UserEntity> userInfo = loginService.getUserInfo(map);
		
		// 是否存在用户
		if(userInfo != null && userInfo.size() > 0) {
			// 用户已经存在!
			info.setMessage(PropertyUtil.getProperties("M0002"));
			info.setCode("error");
		}else {
			info.setCode("success");
		}
		return info;
	}
	
	/**
	 * 获取用户的信息
	 * @param 用户名
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getUserInfoList.action")
	public @ResponseBody DataTablesResponseInfo getUserInfoList(@RequestParam HashMap<String, Object> map) throws IOException {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<UserEntity> userInfo = loginService.getUserInfo(map);
		info.setData(userInfo);
		return info;
	}

	/**
	 * 用户注册
	 * @param 用户信息
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/insertUserInfo.action")
	public @ResponseBody ResponseInfo insertUserInfo(@RequestParam HashMap<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 添加用户	
		int result = loginService.insertUserInfo(map);
		
		// 是否存在用户
		if(result > 0) {
			info.setCode("success");
			// 注册成功
			info.setMessage(PropertyUtil.getProperties("M0003"));
		}else {
			info.setCode("error");
			// 注册失败
			info.setMessage(PropertyUtil.getProperties("M0004"));
		}
		return info;
	}
	
	/**
	 * 更新用户信息
	 * @param 用户信息
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/updateUserInfo.action")
	public @ResponseBody ResponseInfo updateUserInfo(@RequestParam HashMap<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 更新用户
		int result = loginService.updateUserInfo(map);
		
		// 是否存在用户
		if(result > 0) {
			info.setCode("success");
			// 操作成功
			info.setMessage(PropertyUtil.getProperties("M0005"));
		}else {
			info.setCode("error");
			// 操作失败
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
	
	/**
	 * 删除用户信息
	 * @param 用户id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/deleteUserInfo.action")
	public @ResponseBody ResponseInfo deleteUserInfo(@RequestParam HashMap<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 删除用户
		int result = loginService.deleteUserInfo(map);
		
		// 是否存在用户
		if(result > 0) {
			info.setCode("success");
			// 操作成功
			info.setMessage(PropertyUtil.getProperties("M0005"));
		}else {
			info.setCode("error");
			// 操作失败
			info.setMessage(PropertyUtil.getProperties("M0006"));
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
		Userinfo userInfo = (Userinfo) session.getAttribute("user");
		int result = loginService.delUserSession(userInfo);
		if(result>0) {
			session.removeAttribute("user");
		}else {
			if(session.getAttribute("user") != null) {
				session.removeAttribute("user");
			}
			// 删除失败
/*			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage("跳转失败");*/
		}
		return info;
}
}
