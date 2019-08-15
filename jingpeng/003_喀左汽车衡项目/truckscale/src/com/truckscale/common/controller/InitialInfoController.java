package com.truckscale.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truckscale.common.service.InitialInfoService;

@Controller
@RequestMapping("/InitialInfo")
public class InitialInfoController {
	@Autowired
	private InitialInfoService initialInfoService;
	
	// 数据字典取得
	@RequestMapping("/getDataDictionary.action")
	@ResponseBody
	public List<Map<String,Object>> getDataDictionary(@RequestParam HashMap<String, Object> map){
		List<Map<String,Object>> list = initialInfoService.getDataDictionary(map);
		return list;
	}
	
}
