package com.testRoom.controller;

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

import com.highwayPlatform.model.DataTablesResponseInfo;
import com.highwayPlatform.model.PtUserInfo;
import com.highwayPlatform.model.ResponseInfo;
import com.testRoom.model.ActualCompletionQuantity;
import com.testRoom.model.AppBridgeEntity;
import com.testRoom.model.AppRoadEntity;
import com.testRoom.model.ConstructionAuthorization;
import com.testRoom.model.EngineeringDesignContent;
import com.testRoom.service.ConstructionProgressService;

@Controller
@RequestMapping("/ConstructionProgress")
public class ConstructionProgressController {
	
	@Autowired
	ConstructionProgressService constructionProgressService; 
	
	//获取该用户权限标段下的标段信息
	@RequestMapping("/getConstructionList.action")
	@ResponseBody
	public  List<ConstructionAuthorization> getTestRoomList(HttpServletRequest request){		
		HttpSession session = request.getSession();
		// 获取当前登录用户的试验室权限
		String userTestDetailed = (String) session.getAttribute("userTestDetailed");
		String[] param = {};
		if(userTestDetailed != null && !("").equals(userTestDetailed)) {
			param = userTestDetailed.split(",");
		}	
		// 检索条件设置
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户的id
		map.put("uniqueIdentifier", param);
		List<ConstructionAuthorization> dataList = constructionProgressService.getConstructionList(map);
		return dataList;
	}
	
	// 查询实际完成工作量
	@RequestMapping("/getActualCompletionQuantity.action")
	@ResponseBody
	public DataTablesResponseInfo getActualCompletionQuantity(HttpServletRequest request, @RequestParam Map<String, Object> map){		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		HttpSession session = request.getSession();
		// 获取当前登录用户的试验室权限
		String userTestDetailed = (String) session.getAttribute("userTestDetailed");
		String[] param = {};
		if(userTestDetailed != null && !("").equals(userTestDetailed)) {
			param = userTestDetailed.split(",");
		}	
		// 检索条件设置
		// 获取当前登录用户的id
		map.put("uniqueIdentifierList", param);
		// 查询工程设计量内容
		List<ActualCompletionQuantity> dataList = constructionProgressService.getActualCompletionQuantity(map);
		dtri.setData(dataList);
		return dtri;
	}
	
	// 获取路和桥梁明细
	@RequestMapping("/getInfoById.action")
	@ResponseBody
	public Map<String, Object> getInfoById(@RequestParam Map<String, Object> map){		
		return constructionProgressService.getInfoById(map);
	}
	
	// 查询工程设计量内容 
	@RequestMapping("/getEngineeringDesignContent.action")
	@ResponseBody
	public List<EngineeringDesignContent> getEngineeringDesignContent(@RequestParam Map<String, Object> map){		
		return constructionProgressService.getEngineeringDesignContent(map);
	}
	
	// 新增工程设计量内容 
	@RequestMapping("/addEngineeringDesignContent.action")
	@ResponseBody
	public ResponseInfo addEngineeringDesignContent(HttpServletRequest request, @RequestBody EngineeringDesignContent engineeringDesignContent){
		HttpSession session = request.getSession();
		// 获取当前登录用户的信息
		PtUserInfo obj = (PtUserInfo) session.getAttribute("user");
		engineeringDesignContent.setCreater(obj.getName());
		return constructionProgressService.addEngineeringDesignContent(engineeringDesignContent);
	}
	
	// 新增施工内容信息
	@RequestMapping("/addInfo.action")
	@ResponseBody
	public ResponseInfo addInfo(HttpServletRequest request, @RequestParam Map<String, Object> map){
		HttpSession session = request.getSession();
		// 获取当前登录用户的信息
		PtUserInfo obj = (PtUserInfo) session.getAttribute("user");
		map.put("Creater", obj.getName());
		return constructionProgressService.addInfo(map);
	}
	
	// 删除施工内容信息
	@RequestMapping("/delInfo.action")
	@ResponseBody
	public ResponseInfo delInfo(HttpServletRequest request, @RequestParam Map<String, Object> map){
		HttpSession session = request.getSession();
		// 获取当前登录用户的信息
		PtUserInfo obj = (PtUserInfo) session.getAttribute("user");
		map.put("Reviser", obj.getName());
		return constructionProgressService.delInfo(map);
	}
	
	// 获取累计值
	@RequestMapping("/getSum.action")
	@ResponseBody
	public String getSum(@RequestParam Map<String, Object> map) {
		Double result =  constructionProgressService.getSum(map);
		if(result != null) {
			return result.toString();
		}else {
			return "0";
		}
	}
	
	// App接口 路
	@RequestMapping("/getAppRoadByUniqueIdentifier.action")
	@ResponseBody
	public Map<String, Object> getAppRoadByUniqueIdentifier(@RequestParam Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppRoadEntity appRoadEntity = constructionProgressService.getAppRoadByUniqueIdentifier(map);
		if (appRoadEntity != null) {
			resultMap.put("code", "success");
			resultMap.put("list", appRoadEntity);
		} else {
			resultMap.put("code", "error");
		}		
		return resultMap;
	}	
	
	// App接口 桥
	@RequestMapping("/getAppBridgeByRoad.action")
	@ResponseBody
	public Map<String, Object> getAppBridgeByRoad(@RequestParam Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppBridgeEntity appBridgeEntity = constructionProgressService.getAppBridgeByRoad(map);
		if (appBridgeEntity != null) {
			resultMap.put("code", "success");
			resultMap.put("list", appBridgeEntity);
		} else {
			resultMap.put("code", "error");
		}
		return resultMap;
	}
}
