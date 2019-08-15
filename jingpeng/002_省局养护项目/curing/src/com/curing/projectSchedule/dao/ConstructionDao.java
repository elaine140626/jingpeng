package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.ConstructionEntity;

@Repository
public interface ConstructionDao{
	// 工程进度（施工组织设计）List取得
	List<ConstructionEntity> getConstructionList(Map<String, Object> map);
	
	// 新增工程进度（施工组织设计）
	int insertConstruction(ConstructionEntity constructionEntity);
	
	// 更新工程进度（施工组织设计）
	int updateConstruction(ConstructionEntity constructionEntity);
	
	// 删除工程进度（施工组织设计）
	int deleteConstruction(ConstructionEntity constructionEntity);	
}
