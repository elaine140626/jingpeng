package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.SubgradeEarthworkNumberEntity;
import com.curing.projectSchedule.model.SubgradeEarthworkNumberSum;

public interface SubgradeEarthworkNumberService {
	// 工程进度（路基土石方数量表）List取得
	List<SubgradeEarthworkNumberEntity> getSubgradeEarthworkNumberList(Map<String, Object> map);
	
	// 工程进度（路基土石方数量表）合计
	List<SubgradeEarthworkNumberSum> getSubgradeEarthworkNumberSum(Map<String, Object> map);
	
	// 新增工程进度（路基土石方数量表）
	int insertSubgradeEarthworkNumber(SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity);
	
	// 更新工程进度（路基土石方数量表）
	int updateSubgradeEarthworkNumber(SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity);
	
	// 删除工程进度（路基土石方数量表）
	int deleteSubgradeEarthworkNumber(SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity);	
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
