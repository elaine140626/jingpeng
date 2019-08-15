package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.GreenNumberEntity;
import com.curing.projectSchedule.model.GreenNumberSum;

@Repository
public interface GreenNumberDao{
	// 工程进度（绿化工程数量表）List取得
	List<GreenNumberEntity> getGreenNumberList(Map<String, Object> map);
	
	// 工程进度（绿化工程数量表）合计
	List<GreenNumberSum> getGreenNumberSum(Map<String, Object> map);
	
	// 新增工程进度（绿化工程数量表）
	int insertGreenNumber(GreenNumberEntity greenNumberEntity);
	
	// 更新工程进度（绿化工程数量表）
	int updateGreenNumber(GreenNumberEntity greenNumberEntity);
	
	// 删除工程进度（绿化工程数量表）
	int deleteGreenNumber(GreenNumberEntity greenNumberEntity);	
	
	List<Map<String, Object>> getGreenNumberListEX(Map<String, Object> map);
}
