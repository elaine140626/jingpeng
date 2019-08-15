package com.oil.dao.productionManagement;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;

public interface CompleteConfirmDao {
	// 生产完成确认
	List<NextProductionPlanEntity> getCompleteConfirm(Map<String, Object> map);
}
