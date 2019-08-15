package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.CrossingPillarEntity;
import com.curing.projectSchedule.model.CrossingPillarSum;

public interface CrossingPillarService {
	// 工程进度（道口标柱设置一览表）List取得
	List<CrossingPillarEntity> getCrossingPillarList(Map<String, Object> map);
	
	// 工程进度（道口标柱设置一览表）合计
	List<CrossingPillarSum> getCrossingPillarSum(Map<String, Object> map);
	
	// 新增工程进度（道口标柱设置一览表）
	int insertCrossingPillar(CrossingPillarEntity crossingPillarEntity);
	
	// 更新工程进度（道口标柱设置一览表）
	int updateCrossingPillar(CrossingPillarEntity crossingPillarEntity);
	
	// 删除工程进度（道口标柱设置一览表）
	int deleteCrossingPillar(CrossingPillarEntity crossingPillarEntity);	
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
