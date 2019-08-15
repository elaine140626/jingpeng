package com.oil.service.testreports;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.ProductionManagementEntity;

public interface ProductionProcessNoticeService {
	// 计划调度表获取
	List<NextProductionPlanEntity> getPlanMeasure(Map<String, Object> map);
	
	// 生产管理表获取 
	List<ProductionManagementEntity> getProductionManagement(Map<String, Object> map);	
	
	// 通知单更新
	int updateProductionProcessNotice(NextProductionPlanEntity nextProductionPlanEntity
			,List<ProductionManagementEntity> productionManagementList);
	
	// 通知单审核
	int examineProductionProcessNotice(NextProductionPlanEntity nextProductionPlanEntity);
}
