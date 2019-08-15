package com.oil.service.dispath;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;

public interface NextProductionPlanService {
	// 获取生产计划list
	List<NextProductionPlanEntity> getProductionPlanList(Map<String, Object> param);
	
	// 新增生产计划
	int insertProductionPlan(NextProductionPlanEntity data);
	
	// 修改生产计划
	int updateProductionPlan(NextProductionPlanEntity data);
	
	// 删除生产计划
	int delProductionPlan(Map<String, Object> param);
	
	// 编号判重
	int getPlanNumber(Map<String, Object> param);
	
	// 获取客户的销售订单明细数据
	Map<String, Object> getSalesList(Map<String, Object> param);
}
