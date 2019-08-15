package com.oil.dao.productionManagement;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.ProductionManagementEntity;

public interface ActualMaterialConsumptionDao {
	// 计划调度表获取（实际原材料消耗）
	List<NextProductionPlanEntity> getPlanMeasure(Map<String, Object> map);
	
	// 生产管理表更新
	int updateProductionManagement(ProductionManagementEntity productionManagementEntity);
	
	// 物料信息表更新（库存减少）
	int reduceStock(ProductionManagementEntity productionManagementEntity);
	
	// 物料信息表更新（库存增加）
	int increaseStock(NextProductionPlanEntity NextProductionPlanEntity);
}
