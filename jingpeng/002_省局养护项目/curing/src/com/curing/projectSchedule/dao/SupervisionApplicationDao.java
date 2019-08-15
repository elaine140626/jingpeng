package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.SupervisionApplicationEntity;

@Repository
public interface SupervisionApplicationDao{
	// 工程进度（监督申请）List取得
	List<SupervisionApplicationEntity> getSupervisionApplicationList(Map<String, Object> map);
	
	// 新增工程进度（监督申请）
	int insertSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity);
	
	// 更新工程进度（监督申请）
	int updateSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity);
	
	// 删除工程进度（监督申请）
	int deleteSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity);	
}
