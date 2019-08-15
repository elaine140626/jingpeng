package com.curing.statisticsForms.controller;

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
import com.curing.statisticsForms.model.FirstPlannedProgress;
import com.curing.statisticsForms.model.FirstPlannedProgressEntity;
import com.curing.statisticsForms.model.FirstPlannedProgressSum;
import com.curing.statisticsForms.service.FirstPlannedProgressService;

@Controller
@RequestMapping("/FirstPlannedProgress")
public class FirstPlannedProgressController {

	@Autowired
	private FirstPlannedProgressService firstPlannedProgressService;
	
	// 第一批计划进度List取得
	@RequestMapping("/getFirstPlannedProgressList.action")
	@ResponseBody
	public DataTablesResponseInfo getFirstPlannedProgressList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<FirstPlannedProgressEntity> list = firstPlannedProgressService.getFirstPlannedProgressList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 第一批计划进度合计
	@RequestMapping("/getFirstPlannedProgressSum.action")
	@ResponseBody
	public List<FirstPlannedProgressSum> getFirstPlannedProgressSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<FirstPlannedProgressSum> list = firstPlannedProgressService.getFirstPlannedProgressSum(map);
		return list;
	}
	
	// 第一批计划进度 单条获取
	@RequestMapping("/getFirstPlannedProgressByCityId.action")
	@ResponseBody
	public List<FirstPlannedProgress> getFirstPlannedProgressByCityId(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<FirstPlannedProgress> list = firstPlannedProgressService.getFirstPlannedProgressByCityId(map);
		return list;
	}
	
	// 新增第一批计划进度
	@RequestMapping("/insertFirstPlannedProgress.action")
	@ResponseBody
	public ResponseInfo insertFirstPlannedProgress(@RequestBody FirstPlannedProgress firstPlannedProgress) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = firstPlannedProgressService.insertFirstPlannedProgress(firstPlannedProgress);
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
