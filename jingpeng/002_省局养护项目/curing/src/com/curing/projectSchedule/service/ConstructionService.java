package com.curing.projectSchedule.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.curing.common.model.ResponseInfo;
import com.curing.projectSchedule.model.ConstructionEntity;

public interface ConstructionService {
	// 工程进度（施工组织设计）List取得
	List<ConstructionEntity> getConstructionList(Map<String, Object> map,int count);
	
	// 新增工程进度（施工组织设计）
	int insertConstruction(ConstructionEntity constructionEntity);
	
	// 更新工程进度（施工组织设计）
	int updateConstruction(ConstructionEntity constructionEntity);
	
	// 删除工程进度（施工组织设计）
	int deleteConstruction(ConstructionEntity constructionEntity);	
	
 	//删除组织计划文件
 	ResponseInfo deleteBuildDesignFile(Map<String, Object> map);
 	
 	//删除文件
 	ResponseInfo delUploadfile(Map<String, Object> map);
 	
 	//下载文件
 	void downUploadfile(String imageNamePrams,String imagePrefix,HttpServletResponse response) throws IOException;
}
