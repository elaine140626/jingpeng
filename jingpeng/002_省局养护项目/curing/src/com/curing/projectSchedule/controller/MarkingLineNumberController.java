package com.curing.projectSchedule.controller;

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
import com.curing.projectSchedule.model.MarkingLineNumberEntity;
import com.curing.projectSchedule.model.MarkingLineNumberSum;
import com.curing.projectSchedule.service.MarkingLineNumberService;
@Controller
@RequestMapping("/MarkingLineNumber")
public class MarkingLineNumberController {
	@Autowired
	private MarkingLineNumberService MarkingLineNumberService;

	// 工程进度（标线工程数量表）List取得
	@RequestMapping("/getMarkingLineNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getMarkingLineNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<MarkingLineNumberEntity> list = MarkingLineNumberService.getMarkingLineNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（标线工程数量表）合计
	@RequestMapping("/getMarkingLineNumberSum.action")
	@ResponseBody
	public MarkingLineNumberEntity getMarkingLineNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		MarkingLineNumberEntity list = MarkingLineNumberService.getMarkingLineNumberSum(map);
		return list;
	}
	
	// 工程进度（标线工程数量表）单条获取
	@RequestMapping("/getMarkingLineNumberById.action")
	@ResponseBody
	public List<MarkingLineNumberEntity> getMarkingLineNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<MarkingLineNumberEntity> list = MarkingLineNumberService.getMarkingLineNumberList(map);
		return list;
	}
	
	// 新增工程进度（标线工程数量表）
	@RequestMapping("/insertMarkingLineNumber.action")
	@ResponseBody
	public ResponseInfo insertMarkingLineNumber(@RequestBody MarkingLineNumberEntity markingLineNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = MarkingLineNumberService.insertMarkingLineNumber(markingLineNumberEntity);
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
	
	// 更新 工程进度（标线工程数量表）
	@RequestMapping("/updateMarkingLineNumber.action")
	@ResponseBody
	public ResponseInfo updateMarkingLineNumber(@RequestBody MarkingLineNumberEntity markingLineNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = MarkingLineNumberService.updateMarkingLineNumber(markingLineNumberEntity);
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
	
	// 删除 工程进度（标线工程数量表）
	@RequestMapping("/deleteMarkingLineNumber.action")
	@ResponseBody
	public ResponseInfo deleteMarkingLineNumber(@RequestBody MarkingLineNumberEntity markingLineNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = MarkingLineNumberService.deleteMarkingLineNumber(markingLineNumberEntity);
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
