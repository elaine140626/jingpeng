package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.ConstructionSidewalkNumberEntity;
import com.curing.projectSchedule.model.ConstructionSidewalkNumberSum;

@Repository
public interface ConstructionSidewalkNumberDao{
	// 工程进度（施工便道路面工程数量汇总表）List取得
	List<ConstructionSidewalkNumberEntity> getConstructionSidewalkNumberList(Map<String, Object> map);
	
	// 工程进度（施工便道路面工程数量汇总表）合计
	List<ConstructionSidewalkNumberSum> getConstructionSidewalkNumberSum(Map<String, Object> map);
	
	// 新增工程进度（施工便道路面工程数量汇总表）
	int insertConstructionSidewalkNumber(ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity);
	
	// 更新工程进度（施工便道路面工程数量汇总表）
	int updateConstructionSidewalkNumber(ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity);
	
	// 删除工程进度（施工便道路面工程数量汇总表）
	int deleteConstructionSidewalkNumber(ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity);
	
	List<Map<String, Object>> getConstructionSidewalkNumberListEX(Map<String, Object> map);
}
