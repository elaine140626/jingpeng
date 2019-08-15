package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.TemporarySignsNumberEntity;
import com.curing.projectSchedule.model.TemporarySignsNumberSum;

public interface TemporarySignsNumberService {
	// 工程进度（临时标志工程数量表）List取得
	List<TemporarySignsNumberEntity> getTemporarySignsNumberList(Map<String, Object> map);
	
	// 工程进度（临时标志工程数量表）合计
	List<TemporarySignsNumberSum> getTemporarySignsNumberSum(Map<String, Object> map);
	
	// 新增工程进度（临时标志工程数量表）
	int insertTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity);
	
	// 更新工程进度（临时标志工程数量表）
	int updateTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity);
	
	// 删除工程进度（临时标志工程数量表）
	int deleteTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity);	
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
