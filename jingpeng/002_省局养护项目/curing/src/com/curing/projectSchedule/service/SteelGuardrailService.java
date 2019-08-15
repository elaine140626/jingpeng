package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.SteelGuardrailEntity;
import com.curing.projectSchedule.model.SteelGuardrailSum;

public interface SteelGuardrailService {
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
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
