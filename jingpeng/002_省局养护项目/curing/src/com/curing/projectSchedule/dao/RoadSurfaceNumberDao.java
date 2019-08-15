package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.RoadSurfaceNumberEntity;
import com.curing.projectSchedule.model.RoadSurfaceNumberSum;

@Repository
public interface RoadSurfaceNumberDao{
	// 工程进度（路面工程数量）List取得
	List<RoadSurfaceNumberEntity> getRoadSurfaceNumberList(Map<String, Object> map);
	
	// 工程进度（路面工程数量）合计
	List<RoadSurfaceNumberSum> getRoadSurfaceNumberSum(Map<String, Object> map);
	
	// 新增工程进度（路面工程数量）
	int insertRoadSurfaceNumber(RoadSurfaceNumberEntity roadSurfaceNumberEntity);
	
	// 更新工程进度（路面工程数量）
	int updateRoadSurfaceNumber(RoadSurfaceNumberEntity roadSurfaceNumberEntity);
	
	// 删除工程进度（路面工程数量）
	int deleteRoadSurfaceNumber(RoadSurfaceNumberEntity roadSurfaceNumberEntity);	
	
	List<Map<String, Object>> getRoadSurfaceNumberListEX(Map<String, Object> map);
}
