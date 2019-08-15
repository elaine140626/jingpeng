package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.CrossingPillarEntity;
import com.curing.projectSchedule.model.CrossingPillarSum;
@Repository
public interface CrossingPillarDao {
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
	
	List<Map<String, Object>> getCrossingPillarListEX(Map<String, Object> map);
}
