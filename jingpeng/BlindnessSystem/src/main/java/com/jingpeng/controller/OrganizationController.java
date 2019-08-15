package com.jingpeng.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.model.BlindSampDetailed;
import com.jingpeng.model.BlindSampleInfo;
import com.jingpeng.model.OrganizationInfo;
import com.jingpeng.model.QrCodeInfo;
import com.jingpeng.model.UserInfo;
import com.jingpeng.service.BlindSampleInfoService;
import com.jingpeng.service.OrganizationInfoService;
import com.jingpeng.service.UserInfoService;
import com.jingpeng.util.Consts;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.JDBCUtil;
import com.jingpeng.util.Message;
import com.jingpeng.util.PageInfoUtil;
import com.jingpeng.util.Urls;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

import net.sf.json.JSONArray;

@Controller
public class OrganizationController extends KDController<Object> {
	//返回code key
	public final static String APPCONTROLLER_MESSAGECODE_FILED = "messageCode";
	//返回code key
	public final static String APPCONTROLLER_MESSAGE_FILED = "message";
	
	
	
	@Autowired
	private OrganizationInfoService organizationInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private BlindSampleInfoService blindSampleInfoService;
	
	
	/*
	 * 根据角色获取权限
	 * tongn
	 * 2018.7.19
	 */
	
	@RequestMapping(Urls.BLINDNESSSYSTEM_GET_ORGANIZATIONINFO)
	public @ResponseBody Map<String, Object> getOrganizationInfo(String roleType) {
		
		Map<String,Object> result = new HashMap<String,Object>();		
		Map<String,Object> param = new HashMap<String,Object>();
		List<OrganizationInfo>  organizationInfoList = null;
		Map<String,List<OrganizationInfo>> organizationInfoMap  = null;
			
			UserInfo user =(UserInfo) request.getSession().getAttribute("user");
			if(user!=null) {
			int orgId = user.getOrgId();
			if(orgId != 1) {
			if("search".equals(roleType)) {
				
				param.put("orgId", orgId);
			}
			}
			
		}
		
		try {	 
				    //单位信息
				    organizationInfoList = organizationInfoService.getOrgInfo(param);
					 if(organizationInfoList!=null&&organizationInfoList.size()>0) {
				     //按照单位等级存储数据
				    organizationInfoMap  = changeOrganizationInfo(organizationInfoList);
				    
				    result.put("organizationInfoMap", organizationInfoMap);
				    
					result.put("organizationInfoList", organizationInfoList);
			 }
		 	     
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SUCCESS_CODE);
		result.put(APPCONTROLLER_MESSAGE_FILED, Message.SUCCESSFUL_OPERATION);
				
		return result;
	}

	private Map<String, List<OrganizationInfo>> changeOrganizationInfo(List<OrganizationInfo> organizationInfoList) {
		
		String str = "level";
		
		Map<String, List<OrganizationInfo>> result = new HashMap<String, List<OrganizationInfo>>();
		
		if(organizationInfoList.size() == 1) {
			
			result.put(str+String.valueOf(organizationInfoList.get(0).getParentId()), organizationInfoList);
		}
		else {
			
			for(OrganizationInfo organizationInfo : organizationInfoList) {
				
				if(result.containsKey(str+String.valueOf(organizationInfo.getParentId()))) {
					
					List<OrganizationInfo>  list = result.get(str+String.valueOf(organizationInfo.getParentId()));
					list.add(organizationInfo);
					result.put(str+String.valueOf(organizationInfo.getParentId()), list);
					
				}else {
					
					List<OrganizationInfo> list = new ArrayList<OrganizationInfo>();
					list.add(organizationInfo);
					result.put(str+String.valueOf(organizationInfo.getParentId()), list);
				}
			}
			
			
		}
		
		return result;
	}
	
}
