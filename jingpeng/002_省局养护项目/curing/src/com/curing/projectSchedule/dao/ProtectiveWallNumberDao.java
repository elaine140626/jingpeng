package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.ProtectiveWallNumberEntity;
import com.curing.projectSchedule.model.ProtectiveWallNumberSum;

@Repository
public interface ProtectiveWallNumberDao{
	// 工程进度（防护墙工程数量表）List取得
	List<ProtectiveWallNumberEntity> getProtectiveWallNumberList(Map<String, Object> map);
	
	// 工程进度（防护墙工程数量表）合计
	List<ProtectiveWallNumberSum> getProtectiveWallNumberSum(Map<String, Object> map);
	
	// 新增工程进度（防护墙工程数量表）
	int insertProtectiveWallNumber(ProtectiveWallNumberEntity protectiveWallNumberEntity);
	
	// 更新工程进度（防护墙工程数量表）
	int updateProtectiveWallNumber(ProtectiveWallNumberEntity protectiveWallNumberEntity);
	
	// 删除工程进度（防护墙工程数量表）
	int deleteProtectiveWallNumber(ProtectiveWallNumberEntity protectiveWallNumberEntity);	
	
	List<Map<String, Object>> getProtectiveWallNumberListEX(Map<String, Object> map);
}
