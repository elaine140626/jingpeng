package com.mixingStation.controller.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.userInfo.UserService;
import com.mixingStation.util.MessageUtil;

@Controller
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/mixLogin.action")
	public String login() {
		return "/mixLogin/mixStationLogin";
	}
	
	@RequestMapping("/getUserinfo.action")
	public @ResponseBody DataTablesResponseInfo getUserinfo(HttpServletRequest request, UserInfo user) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			List<Map<String, Object>> users = new ArrayList();
			List<UserInfo> list = userService.getUserinfo(user);
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {
					Map<String, Object> map = new HashMap();
					String org_Name = "";
					user.setPowerOrgID(list.get(i).getPowerOrgID());
					List<OrganizationInfo> orgNames = userService.getOrgName(user);
					for(int j = 0; j < orgNames.size(); j++) {
						org_Name += orgNames.get(j).getOrgName();
						if(j+1 < orgNames.size()) {
							org_Name += "&nbsp;|&nbsp;";
						}
					}
					map.put("serialNumber", i+1);
					map.put("str_user_Name", list.get(i).getUserName());
					map.put("str_name", list.get(i).getName());
					map.put("org_Name", org_Name);
					map.put("userId", list.get(i).getId());
					users.add(map);
				}
			}
			dtri.setData(users);
			return dtri;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/getshowInfo.action")
	public @ResponseBody Map<String, Object> getshowInfo(UserInfo user) {
		Map<String, Object> map = new HashMap();
		try {
			if(user.getPowerOrgID() == 0) {
				user.setPowerOrgID(1);
			}
			List<UserInfo> list = userService.getUserinfo(user);
			List<OrganizationInfo> orgs = userService.getOrgName(user);
			map.put("orgs", orgs);
			map.put("user", list.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/updateUser.action")
	public @ResponseBody ResponseInfo updateUser(HttpServletRequest request,UserInfo user) {
		ResponseInfo info = new ResponseInfo();
		try {
			userService.updateUser(user);
			//request.getSession().invalidate();
			//成功处理请求提示 200
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
	
	@RequestMapping("/del.action")
	public @ResponseBody ResponseInfo del(UserInfo user) {
		ResponseInfo info = new ResponseInfo();
		try {
			userService.del(user);
			//成功处理请求提示 200
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
	/**
	 * 退出
	 */
	@RequestMapping("/out.action")
	public String out(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/User/mixLogin.action";
	}
}
