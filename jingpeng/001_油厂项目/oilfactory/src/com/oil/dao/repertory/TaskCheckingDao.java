package com.oil.dao.repertory;
import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.repertory.TaskCheckingEntity;

public interface TaskCheckingDao {

	// 获取生产计划list
	List<NextProductionPlanEntity> getProductionPlanList(Map<String, Object> param);
	
	// 获取原材料的内容
	List<TaskCheckingEntity> getList(Map<String, Object> map);
}
