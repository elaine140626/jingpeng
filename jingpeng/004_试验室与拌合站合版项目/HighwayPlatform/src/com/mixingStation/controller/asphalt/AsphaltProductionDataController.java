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
import com.mixingStation.model.asphalt.Asph_TargetPropDetailed;
import com.mixingStation.model.asphalt.AsphaltGradDataAnalysis;
import com.mixingStation.model.asphalt.AsphaltProductionData;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.AsphaltPropDataAnalysis;
import com.mixingStation.service.asphalt.AsphaltProductionDataService;

@Controller
@RequestMapping("/asphaltProductionData")
public class AsphaltProductionDataController {

	@Autowired
	private AsphaltProductionDataService asphaltProductionDataService;
	
	/**
	 * 获取生产数据列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getProductionList.action")
	public @ResponseBody DataTablesResponseInfo getMaterialList(@RequestParam Map<String, Object> map){
		return asphaltProductionDataService.getProductionList(map);	
	}
	
	/**
	 * 查询生产数据明细
	 * @param asphalt_ProductionData
	 * @return
	 */
	@RequestMapping("/getAsphaltPropDataAnalysis.action")
	public @ResponseBody Map<String, Object> getAsphaltPropDataAnalysis(AsphaltProductionPlan asphaltProductionPlan) {
		Map<String, Object> map = new HashMap();
		try {
			AsphaltProductionData asphaltProductionData = new AsphaltProductionData();
			//数据明细  生产计划编号 -产品名称型号 -盘重量
			asphaltProductionData = asphaltProductionDataService.getAsphaltProductionDataByID(asphaltProductionPlan).get(0);
			map.put("asphaltProductionData", asphaltProductionData);
			//数据明细  采集重量 - 采集配比 -生产配比 -实际偏差
			List<AsphaltPropDataAnalysis> asphaltPropDataAnalysisList = asphaltProductionDataService.getAsphaltPropDataAnalysis(asphaltProductionPlan);
			map.put("asphaltPropDataAnalysisList", asphaltPropDataAnalysisList);
			//级配信息
			asphaltProductionPlan.setGradId(asphaltProductionData.getGradId());
			List<Map<String, Object>> asphaltGradDataAnalysisList = asphaltProductionDataService.asphaltGradDataAnalysis(asphaltProductionPlan);
			map.put("asphaltGradDataAnalysisList", asphaltGradDataAnalysisList);
			//物料信息
			List<Asph_TargetPropDetailed> materialConsumption  = asphaltProductionDataService.getMaterialConsumption(asphaltProductionData);
			map.put("materialConsumption", materialConsumption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
