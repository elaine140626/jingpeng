package com.oil.dao.productionManagement;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.system.WareHouseInfo;

public interface ConfirmProductionTaskDao {
	// 计划调度表获取(生产任务确认)
	List<NextProductionPlanEntity>  getPlanMeasure(Map<String,Object> map);
	
	// 获取储位信息
	List<WareHouseInfo> getWareHouseInfoList();
}
