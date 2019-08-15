package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.DiseaseTreatmentNumberEntity;
import com.curing.projectSchedule.model.DiseaseTreatmentNumberSum;

@Repository
public interface DiseaseTreatmentNumberDao{
	// 工程进度（旧路面病害处治工程数量表）List取得
	List<DiseaseTreatmentNumberEntity> getDiseaseTreatmentNumberList(Map<String, Object> map);
	
	// 工程进度（旧路面病害处治工程数量表）合计
	List<DiseaseTreatmentNumberSum> getDiseaseTreatmentNumberSum(Map<String, Object> map);
	
	// 新增工程进度（旧路面病害处治工程数量表）
	int insertDiseaseTreatmentNumber(DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity);
	
	// 更新工程进度（旧路面病害处治工程数量表）
	int updateDiseaseTreatmentNumber(DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity);
	
	// 删除工程进度（旧路面病害处治工程数量表）
	int deleteDiseaseTreatmentNumber(DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity);	
	
	List<Map<String, Object>> getDiseaseTreatmentNumberListEX(Map<String, Object> map);
}
