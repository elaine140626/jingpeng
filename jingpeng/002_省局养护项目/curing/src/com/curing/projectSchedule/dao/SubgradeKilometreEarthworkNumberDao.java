package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.SubgradeKilometreEarthworkNumberEntity;
import com.curing.projectSchedule.model.SubgradeKilometreEarthworkNumberSum;

@Repository
public interface SubgradeKilometreEarthworkNumberDao{
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
	
	
	List<Map<String, Object>>  getSubgradeKilometreEarthworkNumberListEx(Map<String, Object> map);
}
