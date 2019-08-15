package com.oil.service.productionManagement;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.system.WareHouseInfo;

public interface ConfirmProductionTaskService {
	// 计划调度表获取(生产任务确认)
	List<NextProductionPlanEntity>  getPlanMeasure(Map<String,Object> map);
	
	// 生产任务更新 &&生产任务确认
	int updateProductionTask(NextProductionPlanEntity nextProductionPlanEntity); 
	
	// 获取储位信息
	List<WareHouseInfo> getWareHouseInfoList();
}
