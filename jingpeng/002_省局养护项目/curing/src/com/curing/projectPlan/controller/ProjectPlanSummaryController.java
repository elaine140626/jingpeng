package com.curing.projectPlan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.projectPlan.model.ProjectPlanSummaryEntity;
import com.curing.projectPlan.service.ProjectPlanSummaryService;

@Controller
@RequestMapping("/ProjectPlanSummary")
public class ProjectPlanSummaryController {
	@Autowired
	private ProjectPlanSummaryService projectPlanSummaryService;

	// 工程计划List取得
	@RequestMapping("/getProjectPlanSummaryList.action")
	@ResponseBody
	public DataTablesResponseInfo getBridgeBottomList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ProjectPlanSummaryEntity> list = projectPlanSummaryService.getProjectPlanSummaryList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	
	// 工程计划单条获取
	@RequestMapping("/getProjectPlanSummaryById.action")
	@ResponseBody
	public List<ProjectPlanSummaryEntity> getProjectPlanSummaryById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<ProjectPlanSummaryEntity> list = projectPlanSummaryService.getProjectPlanSummaryList(map);
		return list;
	}

	// 更新工程计划
	@RequestMapping("/updateProjectPlanSummary.action")
	@ResponseBody
	public ResponseInfo updateProjectPlanSummary(@RequestBody ProjectPlanSummaryEntity projectPlanSummaryEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = projectPlanSummaryService.updateProjectPlanSummary(projectPlanSummaryEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;		
	}
	
	// 删除工程计划
	@RequestMapping("/deleteProjectPlanSummary.action")
	@ResponseBody
	public ResponseInfo deleteProjectPlanSummary(@RequestBody ProjectPlanSummaryEntity projectPlanSummaryEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = projectPlanSummaryService.deleteProjectPlanSummary(projectPlanSummaryEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;		
	}
}
