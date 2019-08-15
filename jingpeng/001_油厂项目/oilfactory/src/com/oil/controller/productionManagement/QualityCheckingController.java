package com.oil.controller.productionManagement;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.service.productionManagement.QualityCheckingService;

@Controller
@RequestMapping("/qualityChecking")
public class QualityCheckingController {

	@Autowired
	QualityCheckingService qualityCheckingService;
	
	// 计划调度表获取(提交质检申请)
	@RequestMapping("/getQualityCheck.action")
	@ResponseBody
	public DataTablesResponseInfo getPlanMeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<NextProductionPlanEntity> list = qualityCheckingService.getQualityCheck(map);
		dInfo.setData(list);
		return dInfo;
	}
}
