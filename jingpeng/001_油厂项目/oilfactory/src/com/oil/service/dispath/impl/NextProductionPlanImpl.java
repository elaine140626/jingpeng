package com.oil.service.dispath.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.dispath.NextProductionPlanDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.sales.SalesOrdersInfoEntity;
import com.oil.service.dispath.NextProductionPlanService;

@Service
public class NextProductionPlanImpl implements NextProductionPlanService{

	@Autowired
	NextProductionPlanDao nextProductionPlanDao;
	
	// 获取生产计划list
	public List<NextProductionPlanEntity> getProductionPlanList(Map<String, Object> param) {
		return nextProductionPlanDao.getProductionPlanList(param);
	}

	// 新增生产计划
	public int insertProductionPlan(NextProductionPlanEntity data) {
		int result = nextProductionPlanDao.insertProductionPlan(data);
		
		return result;
	}

	// 修改生产计划
	public int updateProductionPlan(NextProductionPlanEntity data) {
		int result = nextProductionPlanDao.updateProductionPlan(data);
		return result;
	}

	// 删除生产计划
	public int delProductionPlan(Map<String, Object> param) {
		return nextProductionPlanDao.delProductionPlan(param);
	}

	// 编号判重
	public int getPlanNumber(Map<String, Object> param) {
		return nextProductionPlanDao.getPlanNumber(param);
	}

	// 获取客户的销售订单明细数据
	public Map<String, Object> getSalesList(Map<String, Object> param) {
		Map<String, Object> data = new HashMap<String, Object>();		
		// 获取客户对应的销售订单
        List<SalesOrdersInfoEntity> salesOrdersList = nextProductionPlanDao.getSalesOrderList(param);
        data.put("salesOrdersList", salesOrdersList);	
		return data;
	}
}
