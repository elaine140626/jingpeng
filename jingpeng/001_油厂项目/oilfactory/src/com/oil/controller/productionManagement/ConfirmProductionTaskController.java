package com.oil.controller.productionManagement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.system.WareHouseInfo;
import com.oil.service.productionManagement.ConfirmProductionTaskService;
import com.oil.util.PropertyUtil;

@Controller
@RequestMapping("/ConfirmProductionTask")
public class ConfirmProductionTaskController {
	@Autowired
	private ConfirmProductionTaskService confirmProductionTaskService;
	// 计划调度表获取(生产任务确认)
	@RequestMapping("/getPlanMeasure.action")
	@ResponseBody
	public DataTablesResponseInfo getPlanMeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<NextProductionPlanEntity> list = confirmProductionTaskService.getPlanMeasure(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 生产任务更新 &&生产任务确认
	@RequestMapping("/updateProductionTask.action")
	@ResponseBody
	public ResponseInfo updateProductionTask(NextProductionPlanEntity nextProductionPlanEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = confirmProductionTaskService.updateProductionTask(nextProductionPlanEntity);
		if (res>0) {
			// 操作成功
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			// 操作失败
			info.setCode("error");
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
	
	// 获取储位信息
	@RequestMapping("/getWareHouseInfoList.action")
	@ResponseBody
	public List<WareHouseInfo> getWareHouseInfoList(HttpServletRequest request){
		List<WareHouseInfo> list = confirmProductionTaskService.getWareHouseInfoList();
		return list;
	}
		
}
