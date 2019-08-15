package com.oil.service.productionManagement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.dispath.NextProductionPlanDao;
import com.oil.dao.productionManagement.ActualMaterialConsumptionDao;
import com.oil.dao.testreports.ProductionProcessNoticeDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.ProductionManagementEntity;
import com.oil.service.productionManagement.ActualMaterialConsumptionService;

@Service
@Transactional
public class ActualMaterialConsumptionServiceImpl implements ActualMaterialConsumptionService{

	@Autowired
	private ProductionProcessNoticeDao productionProcessNoticeDao;
	@Autowired
	private NextProductionPlanDao nextProductionPlanDao;
	@Autowired
	private ActualMaterialConsumptionDao actualMaterialConsumptionDao;
	
	// 计划调度表获取（实际原材料消耗）
	public List<NextProductionPlanEntity> getPlanMeasure(Map<String, Object> map){
		return actualMaterialConsumptionDao.getPlanMeasure(map);
	}
	
	// 生产管理表获取 
	public List<ProductionManagementEntity> getProductionManagement(Map<String, Object> map){
		return productionProcessNoticeDao.getProductionManagement(map);
	}	
	
	// 原材料消耗更新
	public int updateActualMaterialConsumption(NextProductionPlanEntity nextProductionPlanEntity
			,List<ProductionManagementEntity> productionManagementList) {
		int res = 0;
		// 生产计划更新
		res += nextProductionPlanDao.updateProductionPlan(nextProductionPlanEntity);
		if (productionManagementList.size()>0) {
			for (int i=0;i<productionManagementList.size();i++) {				
				// 生产管理表更新
				res += actualMaterialConsumptionDao.updateProductionManagement(productionManagementList.get(i));
			}
		}
		
		return res;
	}
	
	// 原材料消耗提交
	public int submitActualMaterialConsumption(NextProductionPlanEntity nextProductionPlanEntity) {
		int res = 0;
		// 生产计划更新
		res += nextProductionPlanDao.updateProductionPlan(nextProductionPlanEntity);
		// 原材料消耗取得
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", nextProductionPlanEntity.getId());
		List<ProductionManagementEntity> list = productionProcessNoticeDao.getProductionManagement(map);
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				// 物料信息表更新（库存减少）
				res += actualMaterialConsumptionDao.reduceStock(list.get(i));
			}
		}	
		List<NextProductionPlanEntity> list2 = actualMaterialConsumptionDao.getPlanMeasure(map);
		// 物料信息表更新（库存增加）
		res += actualMaterialConsumptionDao.increaseStock(list2.get(0));
		return res;
	}

}
