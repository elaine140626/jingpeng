package com.oil.service.testreports;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;

public interface ProductionProcessDetectionService {
	// 计划调度表获取(生产过程检测)
	List<NextProductionPlanEntity>  getPlanMeasure(Map<String,Object> map);
	
	// 生产过程检测
	int updateProductionProcessDetection(NextProductionPlanEntity nextProductionPlanEntity); 
}
