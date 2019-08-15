package com.curing.projectInfo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.curing.common.model.ResponseInfo;
import com.curing.projectInfo.model.ProjectInfoSummaryEntity;

public interface ProjectInfoSummaryService {
	// 工程信息List取得
	List<ProjectInfoSummaryEntity> getProjectInfoSummaryList(Map<String, Object> map);
	
	// 新增工程信息
    int insertProjectInfoSummary(ProjectInfoSummaryEntity projectInfoSummaryEntity);
    
    // 更新工程信息
    int updateProjectInfoSummary(ProjectInfoSummaryEntity projectInfoSummaryEntity);
    
    // 删除工程信息
 	int deleteProjectInfoSummary(ProjectInfoSummaryEntity projectInfoSummaryEntity);
 	
 	//删除文件
 	ResponseInfo delUploadfile(Map<String, Object> map);
 	
 	//下载文件
 	void downUploadfile(String imageNamePrams,String imagePrefix,HttpServletResponse response) throws IOException;
}
