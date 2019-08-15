package com.jingpeng.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.service.Organization_InfoService;
import com.kdt.base.exception.BusinessException;

@Controller
@RequestMapping("/Organization")
public class Organization_InfoController {

	@Autowired
	private Organization_InfoService organization_Infoservice;
	
	@RequestMapping("/getOrganization_Infos")
	@ResponseBody
	public List<Organization_Info> getOrganization_Infos (Organization_Info organization_Info){
		try {
			return organization_Infoservice.getorganization_Infos(organization_Info);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/getAsphaltmixingstation")
	@ResponseBody
	public List<Organization_Info> getAsphaltmixingstation (HttpServletRequest request ,@RequestParam String name){
		Core_User_Info  user = (Core_User_Info) request.getSession().getAttribute("user");
		String[] str = user.getI_power_Org_Id().split(",");
		List<Integer> orgids = new ArrayList<Integer>();
		for(int i = 0; i < str.length; i++) {
			if(str[i] != null && !"".equals(str[i])) {
				orgids.add(Integer.valueOf(str[i]));
			}
		}
		//Organization_Info organization_Info = new  Organization_Info();
		try {
			List<Organization_Info>  list =	organization_Infoservice.getAsphaltmixingstation(orgids);
			return list;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/getCementmixingstation")
	@ResponseBody
	public List<Organization_Info> getCementmixingstation (HttpServletRequest request,@RequestParam Map<String ,Object> map){
		
		//Organization_Info organization_Info = new  Organization_Info();
		Core_User_Info  user = (Core_User_Info) request.getSession().getAttribute("user");
		String[] str = user.getI_power_Org_Id().split(",");
		List<Integer> orgids = new ArrayList<Integer>();
		for(int i = 0; i < str.length; i++) {
			if(str[i] != null && !"".equals(str[i])) {
				orgids.add(Integer.valueOf(str[i]));
			}
		}
		try {
			return organization_Infoservice.getCementmixingstation(orgids);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("/getWaterstabilitymixingstation")
	@ResponseBody
	public List<Organization_Info> getWaterstabilitymixingstation (HttpServletRequest request,@RequestParam Map<String ,Object> map ){
		Core_User_Info  user = (Core_User_Info) request.getSession().getAttribute("user");
		String[] str = user.getI_power_Org_Id().split(",");
		List<Integer> orgids = new ArrayList<Integer>();
		for(int i = 0; i < str.length; i++) {
			if(str[i] != null && !"".equals(str[i])) {
				orgids.add(Integer.valueOf(str[i]));
			}
		}
		try {
			return organization_Infoservice.getWaterstabilitymixingstation(orgids);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
}
