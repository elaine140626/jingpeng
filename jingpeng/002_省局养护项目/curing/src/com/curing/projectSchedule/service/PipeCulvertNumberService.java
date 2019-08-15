package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import com.curing.projectSchedule.model.PipeCulvertNumberEntity;
import com.curing.projectSchedule.model.PipeCulvertNumberSum;

public interface PipeCulvertNumberService {
	// 工程进度（圆管涵工程数量汇总表）List取得
	List<PipeCulvertNumberEntity> getPipeCulvertNumberList(Map<String, Object> map);
	
	// 工程进度（圆管涵工程数量汇总表）合计
	List<PipeCulvertNumberSum> getPipeCulvertNumberSum(Map<String, Object> map);
	
	// 新增工程进度（圆管涵工程数量汇总表）
	int insertPipeCulvertNumber(PipeCulvertNumberEntity pipeCulvertNumberEntity);
	
	// 更新工程进度（圆管涵工程数量汇总表）
	int updatePipeCulvertNumber(PipeCulvertNumberEntity pipeCulvertNumberEntity);
	
	// 删除工程进度（圆管涵工程数量汇总表）
	int deletePipeCulvertNumber(PipeCulvertNumberEntity pipeCulvertNumberEntity);
}
