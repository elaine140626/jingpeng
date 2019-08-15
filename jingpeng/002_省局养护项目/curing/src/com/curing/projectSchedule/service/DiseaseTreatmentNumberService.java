package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.DiseaseTreatmentNumberEntity;
import com.curing.projectSchedule.model.DiseaseTreatmentNumberSum;

public interface DiseaseTreatmentNumberService {
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
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
