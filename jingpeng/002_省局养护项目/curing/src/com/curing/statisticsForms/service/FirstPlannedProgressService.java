package com.curing.statisticsForms.service;

import java.util.List;
import java.util.Map;

import com.curing.statisticsForms.model.FirstPlannedProgress;
import com.curing.statisticsForms.model.FirstPlannedProgressEntity;
import com.curing.statisticsForms.model.FirstPlannedProgressSum;

public interface FirstPlannedProgressService {
	// 第一批计划进度List取得
	List<FirstPlannedProgressEntity> getFirstPlannedProgressList(Map<String, Object> map);
	
	// 第一批计划进度合计
	List<FirstPlannedProgressSum> getFirstPlannedProgressSum(Map<String, Object> map);
	
	// 第一批计划进度 单条取得
	List<FirstPlannedProgress> getFirstPlannedProgressByCityId(Map<String, Object> map);
		
	// 新增第一批计划进度 
	int insertFirstPlannedProgress(FirstPlannedProgress firstPlannedProgress);
}
