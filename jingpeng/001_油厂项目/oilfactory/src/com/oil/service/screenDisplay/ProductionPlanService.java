package com.oil.service.screenDisplay;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;

public interface ProductionPlanService {
	// 获取当天最新生产计划list
	List<NextProductionPlanEntity> getProductionPlanList();
	
	// 获取当天生产计划完成情况
	List<Map<String, Object>> getProductionPlanCount();
}
