package com.curing.projectPlan.service;

import java.util.List;
import java.util.Map;

import com.curing.projectPlan.model.ProjectPlanSummaryEntity;

public interface ProjectPlanSummaryService {
	// 工程计划List取得
	List<ProjectPlanSummaryEntity> getProjectPlanSummaryList(Map<String, Object> map);
	
	// 更新工程计划
	int updateProjectPlanSummary(ProjectPlanSummaryEntity projectPlanSummaryEntity);
	
	// 删除工程计划
	int deleteProjectPlanSummary(ProjectPlanSummaryEntity projectPlanSummaryEntity);
}
