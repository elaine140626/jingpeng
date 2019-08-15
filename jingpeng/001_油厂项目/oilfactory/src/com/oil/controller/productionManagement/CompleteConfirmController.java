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
import com.oil.service.productionManagement.CompleteConfirmService;

@Controller
@RequestMapping("/completeConfirm")
public class CompleteConfirmController {

	@Autowired
	CompleteConfirmService completeConfirmService;
	
	// 计划调度表获取(生产确认完成)
	@RequestMapping("/getCompleteConfirm.action")
	@ResponseBody
	public DataTablesResponseInfo getPlanMeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<NextProductionPlanEntity> list = completeConfirmService.getCompleteConfirm(map);
		dInfo.setData(list);
		return dInfo;
	}
}
