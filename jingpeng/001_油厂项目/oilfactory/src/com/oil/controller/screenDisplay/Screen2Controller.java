package com.oil.controller.screenDisplay;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.screenDisplay.Screen2Entity;
import com.oil.service.screenDisplay.Screen2Service;

@Controller
@RequestMapping("/Screen2")
public class Screen2Controller {
	@Autowired
	Screen2Service screen2Service;
	
    // 总数统计
	@RequestMapping("/getCountList.action")
	public @ResponseBody List<Map<String, Object>> getCountList(@RequestParam Map<String, Object> map){
		return screen2Service.getCountList(map);	
	}
	
	// 出库车辆信息List
	@RequestMapping("/getScreen2List.action")
	public @ResponseBody List<Screen2Entity> getScreen2List(@RequestParam Map<String, Object> map){
		return screen2Service.getScreen2List(map);
	}
	
	// echar
	@RequestMapping("/getEchar.action")
	public@ResponseBody List<Map<String, Object>> getEchar(Map<String, Object> map){
		return screen2Service.getEchar(map);
	}
}
