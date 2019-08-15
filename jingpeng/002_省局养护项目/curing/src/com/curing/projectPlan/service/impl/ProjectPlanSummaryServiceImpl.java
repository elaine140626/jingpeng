package com.curing.projectPlan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectPlan.dao.ProjectPlanSummaryDao;
import com.curing.projectPlan.model.ProjectPlanSummaryEntity;
import com.curing.projectPlan.service.ProjectPlanSummaryService;

@Service
@Transactional
public class ProjectPlanSummaryServiceImpl implements ProjectPlanSummaryService{

	@Autowired
	private ProjectPlanSummaryDao projectPlanSummaryDao;
	
	// 工程计划List数据
	public List<ProjectPlanSummaryEntity> getProjectPlanSummaryList(Map<String, Object> map) {
		return projectPlanSummaryDao.getProjectPlanSummaryList(map);
	}

	// 更新工程计划
	public int updateProjectPlanSummary(ProjectPlanSummaryEntity projectPlanSummaryEntity) {
		return projectPlanSummaryDao.updateProjectPlanSummary(projectPlanSummaryEntity);
	}

	// 删除工程计划
	public int deleteProjectPlanSummary(ProjectPlanSummaryEntity projectPlanSummaryEntity) {
		return projectPlanSummaryDao.deleteProjectPlanSummary(projectPlanSummaryEntity);
	}

}
