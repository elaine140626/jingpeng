package com.curing.projectInfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectInfo.model.ProjectInfoSummaryEntity;

@Repository
public interface ProjectInfoSummaryDao{
	// 工程信息List取得
	List<ProjectInfoSummaryEntity> getProjectInfoSummaryList(Map<String, Object> map);
	
	// 新增工程信息
	int insertProjectInfoSummary(ProjectInfoSummaryEntity projectInfoSummaryEntity);
	
	// 更新工程信息
	int updateProjectInfoSummary(ProjectInfoSummaryEntity projectInfoSummaryEntity);
	
	// 删除工程信息
	int deleteProjectInfoSummary(ProjectInfoSummaryEntity projectInfoSummaryEntity);

}
