package com.mixingStation.controller.cement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.cement.CementProductionDatasDetails;
import com.mixingStation.service.cement.CementProductionDataService;


/**
 * 
 * @Title 水泥的控制器
 * @author ygt
 * @date 2018年9月29日
 */
@Controller
@RequestMapping("/cementProductionData")
public class CementProductionDataController {
	
	@Autowired
	private CementProductionDataService cementProductionDataService;
	
	/**
	 * 查询水泥的基础数据
	 */
	@RequestMapping("/getCement_ProductionDatas.action")
	@ResponseBody
	public  DataTablesResponseInfo getCement_ProductionDatas(@RequestParam Map<String, Object> map) {
		return cementProductionDataService.getCement_ProductionDatas(map);
	}
	
	/**
	 * 查询水泥的详情
	 */
	@RequestMapping("/getcement_ProductionDatasDetails.action")
	@ResponseBody
	public  List<CementProductionDatasDetails> getcement_ProductionDatasDetails(@RequestParam Map<String, Object> map) {
		return cementProductionDataService.getcement_ProductionDatasDetails(map);
	}
}
