package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.ProtectiveWallNumberEntity;
import com.curing.projectSchedule.model.ProtectiveWallNumberSum;

public interface ProtectiveWallNumberService {
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
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
