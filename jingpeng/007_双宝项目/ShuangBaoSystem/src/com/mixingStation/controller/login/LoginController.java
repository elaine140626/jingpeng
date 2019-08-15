package com.mixingStation.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.userInfo.IsSameUser;
import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.userInfo.MixUserInfoService;
import com.mixingStation.util.Consts;
import com.mixingStation.util.MessageUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private MixUserInfoService userInfoService;
	
	/**
	 * @since 获取当前登录用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSessionUserInfo.action")
	public @ResponseBody ResponseInfo userLogin(HttpServletRequest request,OrganizationInfo organizationInfo) {
		ResponseInfo info = new ResponseInfo();
		// 获取session
		HttpSession session = request.getSession();
		// 获取当前登录用户信息
		UserInfo userInfo = (UserInfo)session.getAttribute("mixUser");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userInfo.getId());
		map.put("parentId",userInfo.getPowerOrgID());
		/*if(userInfo.getPowerOrgID() == 0) {
			OrganizationInfo org = new OrganizationInfo();
			org.setId(0);
			org.setOrgName("管理员权限");
			Map<String, Object> User = new HashMap<String, Object>();
			User.put("userInfo", userInfo);
			User.put("Org", org);
			info.setBean(User);
		}else {
			List<OrganizationInfo> org = userInfoService.getUser(map);
			Map<String, Object> User = new HashMap<String, Object>();
			User.put("userInfo", userInfo);
			User.put("Org", org);
			info.setBean(User);
		}*/
		List<OrganizationInfo> org = userInfoService.getUser(map);
		Map<String, Object> User = new HashMap<String, Object>();
		User.put("userInfo", userInfo);
		User.put("Org", org);
		info.setBean(User);
		return info;
	}
	
	/**
	 * @since 用户登录
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/userLogin.action")
	public @ResponseBody ResponseInfo userLogin(HttpServletRequest request, UserInfo user) {
		ResponseInfo info = new ResponseInfo();
		List<UserInfo> list = userInfoService.queryUserInfo(user);
			if(list != null && list.size() == 1) {
				user =  list.get(0);
				List<IsSameUser> isSameUser = userInfoService.getUserSession(user);
				if(isSameUser.size()>0) {
					// 用户同时登陆
					info.setCode(MessageUtil.SERVER_ERROR);
					info.setMessage(MessageUtil.LANDINGATTHESAMETIME);
				}else if(request.getSession().getAttribute("mixUser")!=null) {
					//删除之前登陆的session 重新定义新用户session
					HttpSession session = request.getSession();
					UserInfo userinfo = (UserInfo) session.getAttribute("mixUser");
					int result = userInfoService.delUserSession(userinfo);
					if(result > 0) {
						session.removeAttribute("mixUser");
						userInfoService.addUserSession(user);
						request.getSession().setAttribute("mixUser", user);
						info.setBean(user);
					}
				}else {
					userInfoService.addUserSession(user);
					user = (UserInfo) list.get(0);
					request.getSession().setAttribute(Consts.SESSION_USER_KEY, user);
					request.getSession().setAttribute("mixUser", user);
					info.setBean(user);
				}
			} else {
				//服务器遇到错误 500
				info.setCode(MessageUtil.SERVER_ERROR);
				//账号或密码错误
                info.setMessage(MessageUtil.ACCOUNT_PASSWORD_ERROR);
                info.setBean(user);
			}
		return info;
	}
	
	/**
	 * 移除用户session
	 * @param request
	 * @return
	 */
	@RequestMapping("/delUserSession.action")
	@ResponseBody
	public ResponseInfo delUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ResponseInfo info = new ResponseInfo();
		// 用户信息
		UserInfo userInfo = (UserInfo) session.getAttribute("user");
		int result = userInfoService.delUserSession(userInfo);
		if(result>0) {
			session.removeAttribute("user");
		}else {
			// 删除失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage("跳转失败");
		}
		return info;
	}
	
	/**
	 * @since 获取所有所属机构
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOrg.action")
	@ResponseBody
	public List<OrganizationInfo> getOrg(HttpServletRequest request) {
		List<OrganizationInfo> list = userInfoService.getOrg();
		return list;
	}
	
	/**
	 * @since 用户注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser.action")
	public @ResponseBody ResponseInfo addUser(HttpServletRequest request, UserInfo userInfo) {
		ResponseInfo info = new ResponseInfo();
		try {
			List<UserInfo> list = userInfoService.queryUserInfo(userInfo);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//用户名已经存在
				info.setMessage(MessageUtil.USERNAME_EXISTENCE);
			} else {
				userInfoService.addUser(userInfo);
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
}
