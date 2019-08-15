package com.oil.dao.screenDisplay;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;

public interface ProductionPlanDao {
	// 获取当天最新生产计划list
	List<NextProductionPlanEntity> getProductionPlanList();
	
	// 获取当天生产计划完成情况
	List<Map<String, Object>> getProductionPlanCount(); 
}
