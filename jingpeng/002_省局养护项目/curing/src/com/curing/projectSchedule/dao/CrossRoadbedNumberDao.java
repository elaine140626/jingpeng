package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.CrossRoadbedNumberEntity;
import com.curing.projectSchedule.model.CrossRoadbedNumberSum;

@Repository
public interface CrossRoadbedNumberDao{
	// 工程进度（平面交叉路基路面工程数量表）List取得
	List<CrossRoadbedNumberEntity> getCrossRoadbedNumberList(Map<String, Object> map);
	
	// 工程进度（平面交叉路基路面工程数量表）合计
	List<CrossRoadbedNumberSum> getCrossRoadbedNumberSum(Map<String, Object> map);
	
	// 新增工程进度（平面交叉路基路面工程数量表）
	int insertCrossRoadbedNumber(CrossRoadbedNumberEntity crossRoadbedNumberEntity);
	
	// 更新工程进度（平面交叉路基路面工程数量表）
	int updateCrossRoadbedNumber(CrossRoadbedNumberEntity crossRoadbedNumberEntity);
	
	// 删除工程进度（平面交叉路基路面工程数量表）
	int deleteCrossRoadbedNumber(CrossRoadbedNumberEntity crossRoadbedNumberEntity);
	
	List<Map<String, Object>> getCrossRoadbedNumberListEX(Map<String, Object> map);
}
