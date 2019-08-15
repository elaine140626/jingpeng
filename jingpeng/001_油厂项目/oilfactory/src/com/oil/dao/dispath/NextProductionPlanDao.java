package com.oil.dao.dispath;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.sales.SalesOrdersInfoEntity;

public interface NextProductionPlanDao {

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
	
	// 获取客户对应的销售订单
	List<SalesOrdersInfoEntity> getSalesOrderList(Map<String, Object> param);
}
