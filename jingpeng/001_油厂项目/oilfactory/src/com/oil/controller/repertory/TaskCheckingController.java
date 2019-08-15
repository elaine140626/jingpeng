package com.oil.controller.repertory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.repertory.TaskCheckingEntity;
import com.oil.service.repertory.TaskCheckingService;

@Controller
@RequestMapping("/taskChecking")
public class TaskCheckingController {
	@Autowired
	TaskCheckingService taskCheckingService;
	
	/**
	 * 获取页面list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getInfoList.action")
	public @ResponseBody List<TaskCheckingEntity> getInfoList(@RequestParam Map<String, Object> map){
		return taskCheckingService.getList(map);	
	}

	/**
	 * 获取页面list信息
	 * @param 
	 * @return
	 */
	@RequestMapping("/getProductionPlanList.action")
	public @ResponseBody DataTablesResponseInfo getProductionPlanList(@RequestParam Map<String, Object> map){
		DataTablesResponseInfo data = new DataTablesResponseInfo();
		data.setData(taskCheckingService.getProductionPlanList(map));
		return data;	
	}
}
