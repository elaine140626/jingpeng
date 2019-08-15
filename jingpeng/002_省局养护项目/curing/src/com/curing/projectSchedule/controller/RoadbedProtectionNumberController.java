package com.curing.projectSchedule.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.projectSchedule.model.RoadbedProtectionNumberEntity;
import com.curing.projectSchedule.model.RoadbedProtectionNumberSum;
import com.curing.projectSchedule.service.RoadbedProtectionNumberService;

@Controller
@RequestMapping("/RoadbedProtectionNumber")
public class RoadbedProtectionNumberController {
	@Autowired
	private RoadbedProtectionNumberService roadbedProtectionNumberService;

	// 工程进度（路基防护工程数量表）List取得
	@RequestMapping("/getRoadbedProtectionNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getRoadbedProtectionNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<RoadbedProtectionNumberEntity> list = roadbedProtectionNumberService.getRoadbedProtectionNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（路基防护工程数量表）合计
	@RequestMapping("/getRoadbedProtectionNumberSum.action")
	@ResponseBody
	public List<RoadbedProtectionNumberSum> getRoadbedProtectionNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<RoadbedProtectionNumberSum> list = roadbedProtectionNumberService.getRoadbedProtectionNumberSum(map);
		return list;
	}
	
	// 工程进度（路基防护工程数量表）单条获取
	@RequestMapping("/getRoadbedProtectionNumberById.action")
	@ResponseBody
	public List<RoadbedProtectionNumberEntity> getRoadbedProtectionNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<RoadbedProtectionNumberEntity> list = roadbedProtectionNumberService.getRoadbedProtectionNumberList(map);
		return list;
	}
	
	// 新增工程进度（路基防护工程数量表）
	@RequestMapping("/insertRoadbedProtectionNumber.action")
	@ResponseBody
	public ResponseInfo insertRoadbedProtectionNumber(@RequestBody RoadbedProtectionNumberEntity roadbedProtectionNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = roadbedProtectionNumberService.insertRoadbedProtectionNumber(roadbedProtectionNumberEntity);
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
	
	// 更新工程进度（路基防护工程数量表）
	@RequestMapping("/updateRoadbedProtectionNumber.action")
	@ResponseBody
	public ResponseInfo updateRoadbedProtectionNumber(@RequestBody RoadbedProtectionNumberEntity roadbedProtectionNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = roadbedProtectionNumberService.updateRoadbedProtectionNumber(roadbedProtectionNumberEntity);
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
	
	// 删除工程进度（路基防护工程数量表）
	@RequestMapping("/deleteRoadbedProtectionNumber.action")
	@ResponseBody
	public ResponseInfo deleteRoadbedProtectionNumber(@RequestBody RoadbedProtectionNumberEntity roadbedProtectionNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = roadbedProtectionNumberService.deleteRoadbedProtectionNumber(roadbedProtectionNumberEntity);
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
	
	// 导出
	@RequestMapping("/export.action")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> map) throws IOException{
		roadbedProtectionNumberService.export(request,response,map);		
	}

}
