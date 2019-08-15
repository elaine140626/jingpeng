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
import com.MixStation.service.PlatformCementProductAnalysisService;

@Controller
@RequestMapping("/PlatformCementProductAnalysis")
public class PlatformCementProductAnalysisController{
	
	@Autowired
	private PlatformCementProductAnalysisService  platformCementProductAnalysisService;
	
	@Autowired
	private PlatformAsphaltProductAnalysisService  platformAsphaltProductAnalysisService;
	
	/**
	 * 产能分析 水泥原材料取得
	 **/
	@RequestMapping("/getCementMaterialInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getCementMaterialInfo(HttpServletRequest request,@RequestParam Map<String, Object> param){
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
				List<ProductAnalysisEntity> list = platformCementProductAnalysisService.getCementMaterialInfo(param);
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
	@RequestMapping("/getCementProductAnalysisList.action")
	@ResponseBody
	public List<Map<String,Object>> getCementProductAnalysisList(HttpServletRequest request,@RequestParam Map<String, Object> param){	
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
				List<ProductAnalysisEntity> list = platformCementProductAnalysisService.getCementProductAnalysisList(param);
				String orgName = platformAsphaltProductAnalysisService.getOrgNameById(param);
				map.put("orgName", orgName);
				map.put("list", list);
				listMap.add(map);
			}
		}
		return listMap;
	}
	
}