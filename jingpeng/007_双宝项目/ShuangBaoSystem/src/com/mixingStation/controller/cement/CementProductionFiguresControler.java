package com.mixingStation.controller.cement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.service.cement.CementProductionFiguresService;
import com.mixingStation.util.MessageUtil;

@Controller
@RequestMapping("/cementProductionFigures")
public class CementProductionFiguresControler{
	
	@Autowired
	private CementProductionFiguresService cementProductionFiguresService;
	/**
	 * 获取list
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCementProductionStatisticsList.action")
	public @ResponseBody DataTablesResponseInfo getCementProductionStatisticsList(@RequestParam Map<String, Object> map){
		return cementProductionFiguresService.getCementProductionStatisticsList(map);	
	}
	
	/**
	 * 获取柱状图数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCementProductionData.action")
	public @ResponseBody Map<String,Integer> getCementProductionData(@RequestParam Map<String, Object> map){
		return cementProductionFiguresService.getCementProductionData(map);	
	}
}
