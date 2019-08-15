package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.PlateCulvertNumberEntity;
import com.curing.projectSchedule.model.PlateCulvertNumberSum;

@Repository
public interface PlateCulvertNumberDao {
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
	
	List<Map<String, Object>> getPlateCulvertNumberListEX(Map<String, Object> map);
}
