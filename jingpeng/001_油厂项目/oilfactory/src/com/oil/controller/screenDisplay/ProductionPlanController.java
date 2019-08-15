package com.oil.controller.screenDisplay;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.service.screenDisplay.ProductionPlanService;

@Controller
@RequestMapping("/sdProductionPlan")
public class ProductionPlanController {

	@Autowired
	ProductionPlanService productionPlanService;
	
	@RequestMapping("/getProductionPlanList.action")
	@ResponseBody
	public List<NextProductionPlanEntity> getProductionPlanList() {
		return productionPlanService.getProductionPlanList();
	}
	
	@RequestMapping("/getProductionPlanCount.action")
	@ResponseBody
	public List<Map<String, Object>> getProductionPlanCount() {
		return productionPlanService.getProductionPlanCount();
	}
}
