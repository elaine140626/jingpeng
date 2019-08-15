package com.curing.projectSchedule.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.curing.common.model.ResponseInfo;
import com.curing.projectSchedule.model.SupervisionApplicationEntity;

public interface SupervisionApplicationService {
	// 工程进度（监督申请）List取得
	List<SupervisionApplicationEntity> getSupervisionApplicationList(Map<String, Object> map,int count);
	
	// 新增工程进度（监督申请）
	int insertSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity);
	
	// 更新工程进度（监督申请）
	int updateSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity);
	
	// 删除工程进度（监督申请）
	int deleteSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity);
	
 	//删除文件
 	ResponseInfo delUploadfile(Map<String, Object> map);
 	
 	//下载文件
 	void downUploadfile(String imageNamePrams,String imagePrefix,HttpServletResponse response) throws IOException;
 	
 	//删除监理计划文件
 	ResponseInfo deleteSupervisorPlanFile(Map<String, Object> map);
 	
 	//删除监理细则文件
 	ResponseInfo deleteSupervisorDetailedFile(Map<String, Object> map);
}
