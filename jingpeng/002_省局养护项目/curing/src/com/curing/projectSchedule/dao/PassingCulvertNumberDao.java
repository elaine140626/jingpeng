package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.PassingCulvertNumberEntity;
import com.curing.projectSchedule.model.PassingCulvertNumberSum;

@Repository
public interface PassingCulvertNumberDao{
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
	
	List<Map<String, Object>> getPassingCulvertNumberListEX(Map<String, Object> map);
}
