package com.mix.controller.login;

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

import com.mix.model.Core_User_Info;
import com.mix.model.DataTablesResponseInfo;
import com.mix.model.Organization_Info;
import com.mix.model.ResponseInfo;
import com.mix.model.User_Info;
import com.mix.service.login.LoginService;
import com.mix.service.login.RegisterService;
import com.mix.service.login.UserService;
import com.mix.util.MessageUtil;

@Controller
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UserService useService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private RegisterService registerService;
	private User_Info user;
	private Core_User_Info cuser;
	
	@RequestMapping("/userinfo.action")
	public String userinfo() {
		return "/view/user/userinfo";
	}
	
	@RequestMapping("/getUserinfo.action")
	public @ResponseBody DataTablesResponseInfo getUserinfo(HttpServletRequest request, User_Info user) {
		
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		try {
			List<Map<String, Object>> users = new ArrayList();
			List<User_Info> list = useService.getUserinfo(obj);
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {
					Map<String, Object> map = new HashMap();
					String org_Name = "";
					List<Map<String, String>> orgNames = useService.getOrgName(list.get(i));
					for(int j = 0; j < orgNames.size(); j++) {
						org_Name += orgNames.get(j).get("str_org_Name");
						if(j+1 < orgNames.size()) {
							org_Name += "&nbsp;|&nbsp;";
						}
					}
					String operate = "<span><a href='javascript:void(0);' onclick='show(\""+list.get(i).getI_id()+"\");' title=\"修改\">修改</a><a href='javascript:void(0);' onclick='del(\""+list.get(i).getI_id()+"\");' title=\"删除\">删除</a>";
					map.put("serialNumber", i+1);
					map.put("str_user_Name", list.get(i).getStr_user_Name());
					map.put("str_name", list.get(i).getStr_name());
					map.put("org_Name", org_Name);
					map.put("operate", operate);
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
	
	@RequestMapping("/getPlatformUserinfo.action")
	public @ResponseBody DataTablesResponseInfo getPlatformUserinfo(HttpServletRequest request, Core_User_Info user) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			List<Map<String, Object>> users = new ArrayList();
			List<Core_User_Info> list = useService.getPlatformUserinfo(user);
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {
					Map<String, Object> map = new HashMap();
					String org_Name = "";
					List<Integer> orgids = new ArrayList();
					String[] str = list.get(i).getI_power_Org_Id().split(",");
					for(int j = 0; j < str.length; j++) {
						if(!str[j].equals("")) {
							orgids.add(Integer.parseInt(str[j]));
						}
					}
					List<Map<String, String>> orgNames = useService.getPlatformOrgName(orgids);
					for(int j = 0; j < orgNames.size(); j++) {
						org_Name += orgNames.get(j).get("str_org_Name");
						if(j+1 < orgNames.size()) {
							org_Name += "&nbsp;|&nbsp;";
						}
					}
					String operate = "<span><a href='javascript:void(0);' onclick='show(\""+list.get(i).getI_id()+"\");' title=\"修改\">修改</a><a href='javascript:void(0);' onclick='del(\""+list.get(i).getI_id()+"\");' title=\"删除\">删除</a>";
					map.put("serialNumber", i+1);
					map.put("str_user_Name", list.get(i).getStr_user_Name());
					map.put("str_name", list.get(i).getStr_name());
					map.put("org_Name", org_Name);
					map.put("operate", operate);
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
	
	@RequestMapping("/show.action")
	public String user(User_Info user) {
		this.user = user;
		return "/view/user/user_show";
	}
	
	@RequestMapping("/platformShow.action")
	public String platformShow(Core_User_Info user) {
		this.cuser = user;
		return "/view/lq/user_show";
	}
	
	@RequestMapping("/getshowInfo.action")
	public @ResponseBody Map<String, Object> getshowInfo() {
		Map<String, Object> map = new HashMap();
		try {
			List<User_Info> list = useService.getUserinfo(this.user);
			List<Organization_Info> orgs = registerService.getOrg();
			map.put("orgs", orgs);
			map.put("user", list.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getPlatformShowInfo.action")
	public @ResponseBody Map<String, Object> getPlatformShowInfo() {
		Map<String, Object> map = new HashMap();
		try {
			List<Core_User_Info> list = useService.getPlatformShowInfo(cuser);
			String[] str = list.get(0).getI_power_Org_Id().split(",");
			List<Integer> orgs = new ArrayList();
			for(int i = 0; i < str.length; i++) {
				if(!str[i].equals("")) {
					orgs.add(Integer.parseInt(str[i]));
				}
			}
			List<Map<String, String>> orgNames = useService.getPlatformOrgName(orgs);
			map.put("orgNames", orgNames);
			map.put("user", list.get(0));
			map.put("i_power_Org_Id", list.get(0).getI_power_Org_Id());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/updateUser.action")
	public @ResponseBody ResponseInfo updateUser(HttpServletRequest request,User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			useService.updateUser(user);
			request.getSession().invalidate();
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
	
	@RequestMapping("/updatePlatformUser.action")
	public @ResponseBody ResponseInfo updatePlatformUser(Core_User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			useService.updatePlatformUser(user);
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
	
	@RequestMapping("/delPlatform.action")
	public @ResponseBody ResponseInfo delPlatform(Core_User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			useService.delPlatform(user);
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
	public @ResponseBody ResponseInfo del(User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			useService.del(user);
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
	
}
