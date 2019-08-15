package com.curing.projectMetering.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.curing.common.model.ResponseInfo;
import com.curing.projectMetering.model.ProjectTenderingSummaryEntity;

public interface ProjectTenderingSummaryService {
	// 工程招标List取得
	List<ProjectTenderingSummaryEntity> getProjectTenderingSummaryList(Map<String, Object> map);

	// 修改工程招标
	int updateProjectTenderingSummary(ProjectTenderingSummaryEntity projectTenderingSummaryEntity);
	
	// 删除工程招标
	int deleteProjectTenderingSummary(ProjectTenderingSummaryEntity projectTenderingSummaryEntity);
	
 	//删除文件
 	ResponseInfo delUploadfile(Map<String, Object> map);
 	
 	//下载文件
 	void downUploadfile(String imageNamePrams,String imagePrefix,HttpServletResponse response) throws IOException;
}
