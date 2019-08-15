package com.MixStation.controller;

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

import com.MixStation.model.ProductAnalysisEntity;
import com.MixStation.service.PlatformAsphaltProductAnalysisService;

@Controller
@RequestMapping("/PlatformAsphaltProductAnalysis")
public class PlatformAsphaltProductAnalysisController{
	
	@Autowired
	private PlatformAsphaltProductAnalysisService  platformAsphaltProductAnalysisService;
	
	/**
	 * 产能分析 沥青原材料取得
	 **/
	@RequestMapping("/getAsphaltMaterialInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getAsphaltMaterialInfo(HttpServletRequest request,@RequestParam Map<String, Object> param){
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();		
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		String[] str = orgId.split(",");
		if(str.length>1) {
			for(int i=1;i<str.length;i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				param.put("orgId",str[i]);
				List<ProductAnalysisEntity> list = platformAsphaltProductAnalysisService.getAsphaltMaterialInfo(param);
				String orgName = platformAsphaltProductAnalysisService.getOrgNameById(param);
				map.put("orgName", orgName);
				map.put("list", list);
				listMap.add(map);
			}
		}			
		return listMap;
	}
	
	/**
	 * 产能分析 echar
	 **/
	@RequestMapping("/getAsphaltProductAnalysisList.action")
	@ResponseBody
	public List<Map<String,Object>> getAsphaltProductAnalysisList(HttpServletRequest request,@RequestParam Map<String, Object> param){	
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();		
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		String[] str = orgId.split(",");
		// 按日：1  按月：2 按年：3
		String date = param.get("date").toString();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("date",date);
		if(str.length>1) {
			for(int i=1;i<str.length;i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				param.put("orgId",str[i]);
				List<ProductAnalysisEntity> list = platformAsphaltProductAnalysisService.getAsphaltProductAnalysisList(param);
				String orgName = platformAsphaltProductAnalysisService.getOrgNameById(param);
				map.put("orgName", orgName);
				map.put("list", list);
				listMap.add(map);
			}
		}
		return listMap;
	}
	
	/**
	 * 石油比下拉列表
	 **/
	@RequestMapping("/getMaterialList.action")
	@ResponseBody
	public List<Map<String,Object>> getMaterialList(HttpServletRequest request,@RequestParam Map<String, Object> param){	
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();		
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_Id").toString();
		String[] str = orgId.split(",");
		param.put("orgId",str);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		//param.put("date",date);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = platformAsphaltProductAnalysisService.getMaterialList(param);
		map.put("list", list);
		listMap.add(map);
		return listMap;
	}
	
	/**
	 * 石油比echar
	 **/
	@RequestMapping("/getAggregateEchar.action")
	@ResponseBody
	public List<Map<String,Object>> getAggregateEchar(HttpServletRequest request,@RequestParam Map<String, Object> param){	
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();		
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_Id").toString();
		if( param.get("pid").equals("") || param.get("pid")==null) {
			param.put("Product_ID", "");
		}else {
			param.put("Product_ID", Integer.parseInt(param.get("pid").toString()));
		}
		String[] str = orgId.split(",");
		param.put("orgId",str);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		//param.put("date",date);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = platformAsphaltProductAnalysisService.getAggregateEchar(param);
		map.put("list", list);
		listMap.add(map);
		return listMap;
	}
}