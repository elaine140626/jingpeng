package com.oil.service.productionManagement;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.ProductionManagementEntity;

public interface ActualMaterialConsumptionService {
	// 计划调度表获取（实际原材料消耗）
	List<NextProductionPlanEntity> getPlanMeasure(Map<String, Object> map);
	
	// 生产管理表获取 
	List<ProductionManagementEntity> getProductionManagement(Map<String, Object> map);	
	
	// 原材料消耗更新
	int updateActualMaterialConsumption(NextProductionPlanEntity nextProductionPlanEntity
			,List<ProductionManagementEntity> productionManagementList);
	
	// 原材料消耗提交
	int submitActualMaterialConsumption(NextProductionPlanEntity nextProductionPlanEntity);
}
