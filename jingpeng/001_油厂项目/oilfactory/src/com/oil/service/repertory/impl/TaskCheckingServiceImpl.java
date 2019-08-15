package com.oil.service.repertory.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.repertory.TaskCheckingDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.repertory.TaskCheckingEntity;
import com.oil.service.repertory.TaskCheckingService;

@Service
public class TaskCheckingServiceImpl implements TaskCheckingService{

	@Autowired
	TaskCheckingDao taskCheckingDao;
	
	// 获取原材料的内容
	public List<TaskCheckingEntity> getList(Map<String, Object> map) {
		return taskCheckingDao.getList(map);
	}

	// 生产计划明细
	public List<NextProductionPlanEntity> getProductionPlanList(Map<String, Object> param) {
		return taskCheckingDao.getProductionPlanList(param);
	}

}
