package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.SignBoardNumberEntity;
import com.curing.projectSchedule.model.SignBoardNumberSum;

@Repository
public interface SignBoardNumberDao{
	// 工程进度（标志牌工程数量表）List取得
	List<SignBoardNumberEntity> getSignBoardNumberList(Map<String, Object> map);
	
	// 工程进度（标志牌工程数量表）合计
	List<SignBoardNumberSum> getSignBoardNumberSum(Map<String, Object> map);
	
	// 新增工程进度（标志牌工程数量表）
	int insertSignBoardNumber(SignBoardNumberEntity signBoardNumberEntity);
	
	// 更新工程进度（标志牌工程数量表）
	int updateSignBoardNumber(SignBoardNumberEntity signBoardNumberEntity);
	
	// 删除工程进度（标志牌工程数量表）
	int deleteSignBoardNumber(SignBoardNumberEntity signBoardNumberEntity);	
	
	List<Map<String, Object>> getSignBoardNumberListEX(Map<String, Object> map);
}
