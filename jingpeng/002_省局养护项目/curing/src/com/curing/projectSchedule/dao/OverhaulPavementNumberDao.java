package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.OverhaulPavementNumberEntity;
import com.curing.projectSchedule.model.OverhaulPavementNumberSum;

@Repository
public interface OverhaulPavementNumberDao{
	// 工程进度（大修路面工程数量表）List取得
	List<OverhaulPavementNumberEntity> getOverhaulPavementNumberList(Map<String, Object> map);
	
	// 工程进度（大修路面工程数量表）合计
	List<OverhaulPavementNumberSum> getOverhaulPavementNumberSum(Map<String, Object> map);
	
	// 新增工程进度（大修路面工程数量表）
	int insertOverhaulPavementNumber(OverhaulPavementNumberEntity overhaulPavementNumberEntity);
	
	// 更新工程进度（大修路面工程数量表）
	int updateOverhaulPavementNumber(OverhaulPavementNumberEntity overhaulPavementNumberEntity);
	
	// 删除工程进度（大修路面工程数量表）
	int deleteOverhaulPavementNumber(OverhaulPavementNumberEntity overhaulPavementNumberEntity);	
	
	List<Map<String, Object>> getOverhaulPavementNumberListEX(Map<String, Object> map);
}
