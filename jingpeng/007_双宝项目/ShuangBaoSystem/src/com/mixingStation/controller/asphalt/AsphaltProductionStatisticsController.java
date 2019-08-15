package com.mixingStation.controller.asphalt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.service.asphalt.AsphaltProductionStatisticsService;

@Controller
@RequestMapping("/asphaltProductionStatistics")
public class AsphaltProductionStatisticsController {

	@Autowired
	private AsphaltProductionStatisticsService asphaltProductionStatisticsService;
	
	//查询沥青生产生产统计列表
	@RequestMapping("/getAsphaltProductionStatistics.action")
	@ResponseBody
	public DataTablesResponseInfo getAsphaltProductionStatistics(@RequestParam Map<String,Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<Map<String,Object>> data = asphaltProductionStatisticsService.getAsphaltProductionStatistics(map);
		for(int i = 0; i < data.size(); i++) {
			data.get(i).put("serialNumber", i+1);
		}
		dtr.setData(data);
		return dtr;
	}
	
	//树状图
	@RequestMapping("/getBar.action")
	@ResponseBody
	public Map<String, Integer> getBar(@RequestParam Map<String,Object> map) {
		//总盘数
		int total = 0;
		//合格盘数
		int qualified = 0;
		//不合格盘数
		int unqualified = 0;
		//计算结果
		Map<String,Integer> result = new HashMap<String,Integer>();
				
		List<Map<String, Object>> list = asphaltProductionStatisticsService.getAsphaltProductionStatistics(map);
		if(list!=null&&list.size()>0) {
			for(Map maplist : list) {
				total = total+ (Integer)maplist.get("total");
				unqualified = unqualified + (Integer)maplist.get("unqualified");
				qualified = qualified + (Integer)maplist.get("standard");
			}
			//qualified = total - unqualified;
		}
		result.put("total", total);
		result.put("standard", qualified);
		result.put("unqualified", unqualified);
		return result;
	}
	
}
