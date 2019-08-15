package com.jingpeng.controller;

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

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.RegisterService;
import com.jingpeng.service.UseService;
import com.jingpeng.util.MessageUtil;
import com.kdt.base.exception.BusinessException;

@Controller
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UseService useService;
	@Autowired
	private RegisterService registerService;
	private User_Info user;
	private Core_User_Info cuser;
	
	@RequestMapping("/userinfo.html")
	public String userinfo() {
		return "/user/userinfo";
	}
	
	@RequestMapping("/platformUserinfo.html")
	public String platformUserinfo() {
		return "/lq/userinfo";
	}
	
	@RequestMapping("/getUserinfo.html")
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
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/getPlatformUserinfo.html")
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
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/show.html")
	public String user(User_Info user) {
		this.user = user;
		return "/user/user_show";
	}
	
	@RequestMapping("/platformShow.html")
	public String platformShow(Core_User_Info user) {
		this.cuser = user;
		return "/lq/user_show";
	}
	
	@RequestMapping("/getshowInfo.html")
	public @ResponseBody Map<String, Object> getshowInfo() {
		Map<String, Object> map = new HashMap();
		try {
			List<User_Info> list = useService.getUserinfo(this.user);
			List<Organization_Info> orgs = registerService.getOrg();
			map.put("orgs", orgs);
			map.put("user", list.get(0));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getPlatformShowInfo.html")
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
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/updateUser.html")
	public @ResponseBody ResponseInfo updateUser(User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			useService.updateUser(user);
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
	
	@RequestMapping("/updatePlatformUser.html")
	public @ResponseBody ResponseInfo updatePlatformUser(Core_User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			useService.updatePlatformUser(user);
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
	
	@RequestMapping("/delPlatform.html")
	public @ResponseBody ResponseInfo delPlatform(Core_User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			useService.delPlatform(user);
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
	
	@RequestMapping("/del.html")
	public @ResponseBody ResponseInfo del(User_Info user) {
		ResponseInfo info = new ResponseInfo();
		try {
			useService.del(user);
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
	
}
