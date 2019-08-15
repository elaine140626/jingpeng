package com.oil.controller.screenDisplay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.Exportmeasure;
import com.oil.service.screenDisplay.QualityService;

@Controller
@RequestMapping("/quality")
public class QualityController {

	@Autowired
	private QualityService qualityService;
	
	//查询待出厂检测数据
	@RequestMapping("/getAllBeforeQuality.action")
	@ResponseBody
	public List<Exportmeasure> getAllBeforeQuality(){
		return qualityService.getAllBeforeQuality();
	}
	
	//待下发生产工艺通知单
	@RequestMapping("/getAllPlanQuality.action")
	@ResponseBody
	public List<NextProductionPlanEntity> getAllPlanQuality(){
		return qualityService.getAllPlanQuality();
	}
	
}
