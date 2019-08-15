package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.PlateCulvertNumberEntity;
import com.curing.projectSchedule.model.PlateCulvertNumberSum;

public interface PlateCulvertNumberService {
	// 工程进度（板涵工程数量汇总表）List取得
	List<PlateCulvertNumberEntity> getPlateCulvertNumberList(Map<String, Object> map);
	
	// 工程进度（板涵工程数量汇总表）合计
	List<PlateCulvertNumberSum> getPlateCulvertNumberSum(Map<String, Object> map);
	
	// 新增工程进度（板涵工程数量汇总表）
	int insertPlateCulvertNumber(PlateCulvertNumberEntity plateCulvertNumberEntity);
	
	// 更新工程进度（板涵工程数量汇总表）
	int updatePlateCulvertNumber(PlateCulvertNumberEntity plateCulvertNumberEntity);
	
	// 删除工程进度（板涵工程数量汇总表）
	int deletePlateCulvertNumber(PlateCulvertNumberEntity plateCulvertNumberEntity);	
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
