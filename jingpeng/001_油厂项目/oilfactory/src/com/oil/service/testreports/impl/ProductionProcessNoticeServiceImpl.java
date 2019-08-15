package com.oil.service.testreports.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.dispath.NextProductionPlanDao;
import com.oil.dao.testreports.ProductionProcessNoticeDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.ProductionManagementEntity;
import com.oil.service.testreports.ProductionProcessNoticeService;

@Service
@Transactional
public class ProductionProcessNoticeServiceImpl implements ProductionProcessNoticeService{

	@Autowired
	private ProductionProcessNoticeDao productionProcessNoticeDao;
	@Autowired
	private NextProductionPlanDao nextProductionPlanDao;
	
	// 计划调度表获取
	public List<NextProductionPlanEntity> getPlanMeasure(Map<String, Object> map){
		return productionProcessNoticeDao.getPlanMeasure(map);
	}
	
	// 生产管理表获取 
	public List<ProductionManagementEntity> getProductionManagement(Map<String, Object> map){
		return productionProcessNoticeDao.getProductionManagement(map);
	}	
	
	// 通知单更新
	public int updateProductionProcessNotice(NextProductionPlanEntity nextProductionPlanEntity
			,List<ProductionManagementEntity> productionManagementList) {
		int res = 0;
		// 生产计划更新
		res += nextProductionPlanDao.updateProductionPlan(nextProductionPlanEntity);
		// 生产管理表更新删除
		res += productionProcessNoticeDao.deleteProductionManagement(productionManagementList.get(0));
		if (productionManagementList.size()>0) {
			for (int i=0;i<productionManagementList.size();i++) {				
				// 生产管理表插入
				res += productionProcessNoticeDao.insertProductionManagement(productionManagementList.get(i));
			}
		}
		
		return res;
	}
	
	// 通知单审核
	public int examineProductionProcessNotice(NextProductionPlanEntity nextProductionPlanEntity) {
		int res = 0;
		// 生产计划更新
		res += nextProductionPlanDao.updateProductionPlan(nextProductionPlanEntity);
		return res;
	}

}
