package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.RoadbedProtectionNumberEntity;
import com.curing.projectSchedule.model.RoadbedProtectionNumberSum;

public interface RoadbedProtectionNumberService {
	// 工程进度（路基防护工程数量表）List取得
	List<RoadbedProtectionNumberEntity> getRoadbedProtectionNumberList(Map<String, Object> map);
	
	// 工程进度（路基防护工程数量表）合计
	List<RoadbedProtectionNumberSum> getRoadbedProtectionNumberSum(Map<String, Object> map);
	
	// 新增工程进度（路基防护工程数量表）
	int insertRoadbedProtectionNumber(RoadbedProtectionNumberEntity roadbedProtectionNumberEntity);
	
	// 更新工程进度（路基防护工程数量表）
	int updateRoadbedProtectionNumber(RoadbedProtectionNumberEntity roadbedProtectionNumberEntity);
	
	// 删除工程进度（路基防护工程数量表）
	int deleteRoadbedProtectionNumber(RoadbedProtectionNumberEntity roadbedProtectionNumberEntity);	
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
