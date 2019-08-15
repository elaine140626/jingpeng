package com.oil.service.screenDisplay.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.service.screenDisplay.ProductionPlanService;
import com.oil.dao.screenDisplay.ProductionPlanDao;

@Service
public class ProductionPlanServiceImpl implements ProductionPlanService{

	@Autowired
	ProductionPlanDao ProductionPlanDao;
	
	// 获取当天最新生产计划list
	@Override
	public List<NextProductionPlanEntity> getProductionPlanList() {
		return ProductionPlanDao.getProductionPlanList();
	}
	
	// 获取当天生产计划完成情况
	@Override
	public List<Map<String, Object>> getProductionPlanCount() {
		return ProductionPlanDao.getProductionPlanCount();
	}

}
