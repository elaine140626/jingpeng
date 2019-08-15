package com.curing.projectMetering.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectMetering.model.ProjectTenderingSummaryEntity;

@Repository
public interface ProjectTenderingSummaryDao {
	
	// 工程招标List取得
	List<ProjectTenderingSummaryEntity> getProjectTenderingSummaryList(Map<String, Object> map);

	// 修改工程招标
	int updateProjectTenderingSummary(ProjectTenderingSummaryEntity projectTenderingSummaryEntity);
	
	// 删除工程招标
	int deleteProjectTenderingSummary(ProjectTenderingSummaryEntity projectTenderingSummaryEntity);
}
