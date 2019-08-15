package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.CulvertMaintenanceNumberEntity;
import com.curing.projectSchedule.model.CulvertMaintenanceNumberSum;

@Repository
public interface CulvertMaintenanceNumberDao{
	// 工程进度（涵洞维修工程数量表）List取得
	List<CulvertMaintenanceNumberEntity> getCulvertMaintenanceNumberList(Map<String, Object> map);
	
	// 工程进度（涵洞维修工程数量表）合计
	List<CulvertMaintenanceNumberSum> getCulvertMaintenanceNumberSum(Map<String, Object> map);
	
	// 新增工程进度（涵洞维修工程数量表）
	int insertCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity culvertMaintenanceNumberEntity);
	
	// 更新工程进度（涵洞维修工程数量表）
	int updateCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity culvertMaintenanceNumberEntity);
	
	// 删除工程进度（涵洞维修工程数量表）
	int deleteCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity culvertMaintenanceNumberEntity);	
	
	List<Map<String, Object>> getCulvertMaintenanceNumberListEX(Map<String, Object> map);
}
