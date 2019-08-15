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
import com.curing.projectSchedule.model.CrossRoadbedNumberEntity;
import com.curing.projectSchedule.model.CrossRoadbedNumberSum;
import com.curing.projectSchedule.service.CrossRoadbedNumberService;

@Controller
@RequestMapping("/CrossRoadbedNumber")
public class CrossRoadbedNumberController {
	@Autowired
	private CrossRoadbedNumberService crossRoadbedNumberService;

	// 工程进度（平面交叉路基路面工程数量表）List取得
	@RequestMapping("/getCrossRoadbedNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getCrossRoadbedNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<CrossRoadbedNumberEntity> list = crossRoadbedNumberService.getCrossRoadbedNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（平面交叉路基路面工程数量表）合计
	@RequestMapping("/getCrossRoadbedNumberSum.action")
	@ResponseBody
	public List<CrossRoadbedNumberSum> getCrossRoadbedNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<CrossRoadbedNumberSum> list = crossRoadbedNumberService.getCrossRoadbedNumberSum(map);
		return list;
	}
	
	// 工程进度（平面交叉路基路面工程数量表）单条获取
	@RequestMapping("/getCrossRoadbedNumberById.action")
	@ResponseBody
	public List<CrossRoadbedNumberEntity> getCrossRoadbedNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<CrossRoadbedNumberEntity> list = crossRoadbedNumberService.getCrossRoadbedNumberList(map);
		return list;
	}
	
	// 新增工程进度（平面交叉路基路面工程数量表）
	@RequestMapping("/insertCrossRoadbedNumber.action")
	@ResponseBody
	public ResponseInfo insertCrossRoadbedNumber(@RequestBody CrossRoadbedNumberEntity crossRoadbedNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = crossRoadbedNumberService.insertCrossRoadbedNumber(crossRoadbedNumberEntity);
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
	
	// 更新工程进度（平面交叉路基路面工程数量表）
	@RequestMapping("/updateCrossRoadbedNumber.action")
	@ResponseBody
	public ResponseInfo updateCrossRoadbedNumber(@RequestBody CrossRoadbedNumberEntity crossRoadbedNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = crossRoadbedNumberService.updateCrossRoadbedNumber(crossRoadbedNumberEntity);
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
	
	// 删除工程进度（平面交叉路基路面工程数量表）
	@RequestMapping("/deleteCrossRoadbedNumber.action")
	@ResponseBody
	public ResponseInfo deleteCrossRoadbedNumber(@RequestBody CrossRoadbedNumberEntity crossRoadbedNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = crossRoadbedNumberService.deleteCrossRoadbedNumber(crossRoadbedNumberEntity);
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
		crossRoadbedNumberService.export(request,response,map);		
	}
}
