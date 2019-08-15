package com.oil.service.productionManagement;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;

public interface QualityCheckingService {
	// 计划调度表获取(生产任务确认)
	List<NextProductionPlanEntity>  getQualityCheck(Map<String,Object> map);
}
