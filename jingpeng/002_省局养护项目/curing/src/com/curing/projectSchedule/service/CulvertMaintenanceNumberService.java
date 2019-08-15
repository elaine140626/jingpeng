package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.CulvertMaintenanceNumberEntity;
import com.curing.projectSchedule.model.CulvertMaintenanceNumberSum;

public interface CulvertMaintenanceNumberService {
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
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
