package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.RoadSurfaceNumberEntity;
import com.curing.projectSchedule.model.RoadSurfaceNumberSum;

public interface RoadSurfaceNumberService {
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
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
