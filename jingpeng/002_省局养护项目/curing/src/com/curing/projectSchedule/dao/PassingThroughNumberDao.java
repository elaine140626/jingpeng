package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.curing.projectSchedule.model.PassingThroughNumberEntity;
import com.curing.projectSchedule.model.PassingThroughNumberSum;
@Repository
public interface PassingThroughNumberDao {
	// 工程进度（过路过户涵工程数量表）List取得
	List<PassingThroughNumberEntity> getPassingThroughNumberList(Map<String, Object> map);
	
	// 工程进度（过路过户涵工程数量表）合计
	List<PassingThroughNumberSum> getPassingThroughNumberSum(Map<String, Object> map);
	
	// 新增工程进度（过路过户涵工程数量表）
	int insertPassingThroughNumber(PassingThroughNumberEntity passingThroughNumberEntity);
	
	// 更新工程进度（过路过户涵工程数量表）
	int updatePassingThroughNumber(PassingThroughNumberEntity passingThroughNumberEntity);
	
	// 删除工程进度（过路过户涵工程数量表）
	int deletePassingThroughNumber(PassingThroughNumberEntity passingThroughNumberEntity);
	
	List<Map<String, Object>> getPassingThroughNumberListEX(Map<String, Object> map);
}
