package com.oil.service.productionManagement.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.dispath.NextProductionPlanDao;
import com.oil.dao.productionManagement.ConfirmProductionTaskDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.system.WareHouseInfo;
import com.oil.service.productionManagement.ConfirmProductionTaskService;

@Service
@Transactional
public class ConfirmProductionTaskServiceImpl implements ConfirmProductionTaskService{

	@Autowired
	private ConfirmProductionTaskDao confirmProductionTaskDao;
	@Autowired
	private NextProductionPlanDao nextProductionPlanDao;
	
	// 计划调度表获取(生产任务确认)
	public List<NextProductionPlanEntity>  getPlanMeasure(Map<String,Object> map){
		return confirmProductionTaskDao.getPlanMeasure(map);
	}
	
	// 生产任务更新 &&生产任务确认
	public int updateProductionTask(NextProductionPlanEntity nextProductionPlanEntity) {
		return nextProductionPlanDao.updateProductionPlan(nextProductionPlanEntity);
	}

	// 获取储位信息
	public List<WareHouseInfo> getWareHouseInfoList() {
		return confirmProductionTaskDao.getWareHouseInfoList();
	}
}
