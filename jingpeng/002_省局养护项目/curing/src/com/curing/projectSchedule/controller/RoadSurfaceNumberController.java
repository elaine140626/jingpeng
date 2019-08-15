package com.curing.projectSchedule.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
import com.curing.projectSchedule.model.RoadSurfaceNumberEntity;
import com.curing.projectSchedule.model.RoadSurfaceNumberSum;
import com.curing.projectSchedule.service.RoadSurfaceNumberService;

@Controller
@RequestMapping("/RoadSurfaceNumber")
public class RoadSurfaceNumberController {
	@Autowired
	private RoadSurfaceNumberService roadSurfaceNumberService;

	// 工程进度（路面工程数量）List取得
	@RequestMapping("/getRoadSurfaceNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getRoadSurfaceNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<RoadSurfaceNumberEntity> list = roadSurfaceNumberService.getRoadSurfaceNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（路面工程数量）合计
	@RequestMapping("/getRoadSurfaceNumberSum.action")
	@ResponseBody
	public List<RoadSurfaceNumberSum> getRoadSurfaceNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<RoadSurfaceNumberSum> list = roadSurfaceNumberService.getRoadSurfaceNumberSum(map);
		return list;
	}
	
	// 工程进度（路面工程数量）单条获取
	@RequestMapping("/getRoadSurfaceNumberById.action")
	@ResponseBody
	public List<RoadSurfaceNumberEntity> getRoadSurfaceNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<RoadSurfaceNumberEntity> list = roadSurfaceNumberService.getRoadSurfaceNumberList(map);
		return list;
	}
	
	// 新增工程进度（路面工程数量）
	@RequestMapping("/insertRoadSurfaceNumber.action")
	@ResponseBody
	public ResponseInfo insertRoadSurfaceNumber(@RequestBody RoadSurfaceNumberEntity roadSurfaceNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = roadSurfaceNumberService.insertRoadSurfaceNumber(roadSurfaceNumberEntity);
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
	
	// 更新工程进度（路面工程数量）
	@RequestMapping("/updateRoadSurfaceNumber.action")
	@ResponseBody
	public ResponseInfo updateRoadSurfaceNumber(@RequestBody RoadSurfaceNumberEntity roadSurfaceNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = roadSurfaceNumberService.updateRoadSurfaceNumber(roadSurfaceNumberEntity);
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
	
	// 删除工程进度（路面工程数量）
	@RequestMapping("/deleteRoadSurfaceNumber.action")
	@ResponseBody
	public ResponseInfo deleteRoadSurfaceNumber(@RequestBody RoadSurfaceNumberEntity roadSurfaceNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = roadSurfaceNumberService.deleteRoadSurfaceNumber(roadSurfaceNumberEntity);
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
		roadSurfaceNumberService.export(request,response,map);		
	}

}
