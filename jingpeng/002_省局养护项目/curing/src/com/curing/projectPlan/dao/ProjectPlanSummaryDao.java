package com.curing.projectPlan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectPlan.model.ProjectPlanSummaryEntity;

@Repository
public interface ProjectPlanSummaryDao {

	// 工程计划List取得
	List<ProjectPlanSummaryEntity> getProjectPlanSummaryList(Map<String, Object> map);
	
	// 更新工程计划
	int updateProjectPlanSummary(ProjectPlanSummaryEntity projectPlanSummaryEntity);
	
	// 删除工程计划
	int deleteProjectPlanSummary(ProjectPlanSummaryEntity projectPlanSummaryEntity);
}
