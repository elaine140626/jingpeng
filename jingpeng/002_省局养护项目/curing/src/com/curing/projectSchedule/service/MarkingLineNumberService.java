package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;
import com.curing.projectSchedule.model.MarkingLineNumberEntity;
import com.curing.projectSchedule.model.MarkingLineNumberSum;

public interface MarkingLineNumberService {
	// 工程进度（标线工程数量表）List取得
	List<MarkingLineNumberEntity> getMarkingLineNumberList(Map<String, Object> map);
	
	// 工程进度（标线工程数量表）合计
	MarkingLineNumberEntity getMarkingLineNumberSum(Map<String, Object> map);
	
	// 新增工程进度（标线工程数量表）
	int insertMarkingLineNumber(MarkingLineNumberEntity markingLineNumberEntity);
	
	// 更新工程进度（标线工程数量表）
	int updateMarkingLineNumber(MarkingLineNumberEntity markingLineNumberEntity);
	
	// 删除工程进度（标线工程数量表）
	int deleteMarkingLineNumber(MarkingLineNumberEntity markingLineNumberEntity);
}
