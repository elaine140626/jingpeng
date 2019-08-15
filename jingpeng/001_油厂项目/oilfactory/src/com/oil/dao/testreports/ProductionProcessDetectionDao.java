package com.oil.dao.testreports;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;

public interface ProductionProcessDetectionDao {
	// 计划调度表获取(生产过程检测)
	List<NextProductionPlanEntity>  getPlanMeasure(Map<String,Object> map);
	
	// 质检不合格需要调整的场合，回到生产工艺通知单
	int updateState(NextProductionPlanEntity nextProductionPlanEntity);
}
