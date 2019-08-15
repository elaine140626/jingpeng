package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.SteelGuardrailEntity;
import com.curing.projectSchedule.model.SteelGuardrailSum;
@Repository
public interface SteelGuardrailDao {
	// 工程进度（钢护栏设置表）List取得
	List<SteelGuardrailEntity> getSteelGuardrailList(Map<String, Object> map);
	
	// 工程进度（钢护栏设置表）合计
	List<SteelGuardrailSum> getSteelGuardrailSum(Map<String, Object> map);
	
	// 新增工程进度（钢护栏设置表）
	int insertSteelGuardrail(SteelGuardrailEntity steelGuardrailEntity);
	
	// 更新工程进度（钢护栏设置表）
	int updateSteelGuardrail(SteelGuardrailEntity steelGuardrailEntity);
	
	// 删除工程进度（钢护栏设置表）
	int deleteSteelGuardrail(SteelGuardrailEntity steelGuardrailEntity);	
	
	List<Map<String, Object>> getSteelGuardrailListEX(Map<String, Object> map);
}
