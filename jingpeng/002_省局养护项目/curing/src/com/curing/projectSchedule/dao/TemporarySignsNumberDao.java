package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.TemporarySignsNumberEntity;
import com.curing.projectSchedule.model.TemporarySignsNumberSum;

@Repository
public interface TemporarySignsNumberDao{
	// 工程进度（临时标志工程数量表）List取得
	List<TemporarySignsNumberEntity> getTemporarySignsNumberList(Map<String, Object> map);
	
	// 工程进度（临时标志工程数量表）合计
	List<TemporarySignsNumberSum> getTemporarySignsNumberSum(Map<String, Object> map);
	
	// 新增工程进度（临时标志工程数量表）
	int insertTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity);
	
	// 更新工程进度（临时标志工程数量表）
	int updateTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity);
	
	// 删除工程进度（临时标志工程数量表）
	int deleteTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity);	
	
	List<Map<String, Object>> getTemporarySignsNumberListEX(Map<String, Object> map);
}
