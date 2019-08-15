package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.RoadSurfaceNumberEntity;
import com.curing.projectSchedule.model.RoadbedProtectionNumberEntity;
import com.curing.projectSchedule.model.RoadbedProtectionNumberSum;

@Repository
public interface RoadbedProtectionNumberDao{
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
	
	List<Map<String, Object>> getRoadbedProtectionNumberListEX(Map<String, Object> map);
}
