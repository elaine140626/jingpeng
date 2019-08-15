package com.blindSample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blindSample.model.AutoCollectionEntity;
import com.blindSample.model.BlindInfoEntity;
import com.blindSample.model.VideoSurveillance;
import com.blindSample.service.VideoSurveillanceService;
import com.jingpeng.dao.DataTablesResponseInfo;

@Controller
@RequestMapping("/VideoSurveillance")
public class VideoSurveillanceController {

	@Autowired
	VideoSurveillanceService videoSurveillanceService;
	
	@RequestMapping("/getAll.html")
	@ResponseBody
	public Map<String, Object> getVideoSurveillance(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<VideoSurveillance> vsList = videoSurveillanceService.getVideoSurveillance(map);
		map.put("vsList", vsList);
		return map;
	}
	
	@RequestMapping("/getById.html")
	@ResponseBody
	public List<VideoSurveillance> getVideoSurveillanceById(@RequestParam Map<String, Object> param){
		
		return videoSurveillanceService.getVideoSurveillanceById(param);
	}
	
	// 获取用户权限下试验室的试验数量
	@RequestMapping("/getTestInfoCount")
	@ResponseBody
	public List<Map<String, Object>> getTestInfoList(HttpServletRequest request, @RequestParam Map<String, Object> param){
		return videoSurveillanceService.getTestInfoList(param);
	}
	
	// 自动采集数量
	@RequestMapping("/getAutoCollectionCount")
	@ResponseBody
	public List<Map<String, Object>> getAutoCollectionCount(HttpServletRequest request, @RequestParam Map<String, Object> param){
		return videoSurveillanceService.getAutoCollectionCount(param);
	}
	
	// 自动采集明细
	@RequestMapping("/getAutoCollectionList")
	public @ResponseBody DataTablesResponseInfo getAutoCollectionList(@RequestParam Map<String, Object> param){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		System.out.println(param);
		List<AutoCollectionEntity> list =  videoSurveillanceService.getAutoCollectionList(param);
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {	
				list.get(i).setSerialNumber(i+1);					
			}
			dtri.setData(list);
		}		
		return dtri;
	}
	
	// 盲样数量
	@RequestMapping("/getBlindCount")
	@ResponseBody
	public List<Map<String, Object>> getBlindCount(HttpServletRequest request, @RequestParam Map<String, Object> param){
		List<Map<String, Object>> list = videoSurveillanceService.getBlindCount(param);		
		if(list.size() == 0) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("blindCount", 0);
			map.put("completedBlindCount", 0);
			map.put("qualifiedBlindCount", 0);
			list.add(map);
		}
		return list;
	}
	
	// 盲样明细
	@RequestMapping("/getBlindList")
	public @ResponseBody DataTablesResponseInfo getBlindList(@RequestParam Map<String, Object> param){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<BlindInfoEntity> list =  videoSurveillanceService.getBlindList(param);
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {	
				list.get(i).setSerialNumber(i+1);					
			}
			dtri.setData(list);
		}		
		return dtri;
	}
	
}
