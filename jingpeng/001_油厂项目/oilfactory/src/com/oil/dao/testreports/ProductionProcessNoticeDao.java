package com.oil.dao.testreports;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.ProductionManagementEntity;

public interface ProductionProcessNoticeDao {
	// 计划调度表获取
	List<NextProductionPlanEntity> getPlanMeasure(Map<String, Object> map);
	
	// 生产管理表获取 
	List<ProductionManagementEntity> getProductionManagement(Map<String, Object> map);	
	
	// 生产管理表插入
	int insertProductionManagement(ProductionManagementEntity productionManagementEntity);
	
	// 生产管理表更新删除
	int deleteProductionManagement(ProductionManagementEntity productionManagementEntity);
}
