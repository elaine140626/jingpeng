package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.SubgradeKilometreEarthworkNumberEntity;
import com.curing.projectSchedule.model.SubgradeKilometreEarthworkNumberSum;

public interface SubgradeKilometreEarthworkNumberService {
	// 工程进度（路基每公里土石方数量表）List取得
	List<SubgradeKilometreEarthworkNumberEntity> getSubgradeKilometreEarthworkNumberList(Map<String, Object> map);
	
	// 工程进度（路基每公里土石方数量表）合计
	List<SubgradeKilometreEarthworkNumberSum> getSubgradeKilometreEarthworkNumberSum(Map<String, Object> map);
	
	// 新增工程进度（路基每公里土石方数量表）
	int insertSubgradeKilometreEarthworkNumber(SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity);
	
	// 更新工程进度（路基每公里土石方数量表）
	int updateSubgradeKilometreEarthworkNumber(SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity);
	
	// 删除工程进度（路基每公里土石方数量表）
	int deleteSubgradeKilometreEarthworkNumber(SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity);	
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map); 
}
