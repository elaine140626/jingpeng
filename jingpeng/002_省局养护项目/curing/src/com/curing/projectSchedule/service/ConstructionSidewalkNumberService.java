package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.ConstructionSidewalkNumberEntity;
import com.curing.projectSchedule.model.ConstructionSidewalkNumberSum;

public interface ConstructionSidewalkNumberService {
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
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
