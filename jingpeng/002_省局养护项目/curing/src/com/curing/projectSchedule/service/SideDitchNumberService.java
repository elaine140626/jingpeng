package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.SideDitchNumberEntity;
import com.curing.projectSchedule.model.SideDitchNumberSum;

public interface SideDitchNumberService {
	// 工程进度（边沟工程数量表）List取得
	List<SideDitchNumberEntity> getSideDitchNumberList(Map<String, Object> map);
	
	// 工程进度（边沟工程数量表）合计
	List<SideDitchNumberSum> getSideDitchNumberSum(Map<String, Object> map);
	
	// 新增工程进度（边沟工程数量表）
	int insertSideDitchNumber(SideDitchNumberEntity sideDitchNumberEntity);
	
	// 更新工程进度（边沟工程数量表）
	int updateSideDitchNumber(SideDitchNumberEntity sideDitchNumberEntity);
	
	// 删除工程进度（边沟工程数量表）
	int deleteSideDitchNumber(SideDitchNumberEntity sideDitchNumberEntity);	
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
