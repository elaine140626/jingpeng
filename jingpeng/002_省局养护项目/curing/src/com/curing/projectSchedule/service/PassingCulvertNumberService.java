package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.PassingCulvertNumberEntity;
import com.curing.projectSchedule.model.PassingCulvertNumberSum;

public interface PassingCulvertNumberService {
	// 工程进度（石砌边沟过路涵工程数量表）List取得
	List<PassingCulvertNumberEntity> getPassingCulvertNumberList(Map<String, Object> map);
	
	// 工程进度（石砌边沟过路涵工程数量表）合计
	List<PassingCulvertNumberSum> getPassingCulvertNumberSum(Map<String, Object> map);
	
	// 新增工程进度（石砌边沟过路涵工程数量表）
	int insertPassingCulvertNumber(PassingCulvertNumberEntity passingCulvertNumberEntity);
	
	// 更新工程进度（石砌边沟过路涵工程数量表）
	int updatePassingCulvertNumber(PassingCulvertNumberEntity passingCulvertNumberEntity);
	
	// 删除工程进度（石砌边沟过路涵工程数量表）
	int deletePassingCulvertNumber(PassingCulvertNumberEntity passingCulvertNumberEntity);
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
