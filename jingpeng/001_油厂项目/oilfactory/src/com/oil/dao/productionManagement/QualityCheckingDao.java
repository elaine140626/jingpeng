package com.oil.dao.productionManagement;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;

public interface QualityCheckingDao {

	// 提交质检申请
	List<NextProductionPlanEntity> getQualityCheck(Map<String, Object> map);
}
