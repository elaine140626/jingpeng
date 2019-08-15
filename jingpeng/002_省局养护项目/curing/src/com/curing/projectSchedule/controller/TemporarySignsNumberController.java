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
import com.curing.projectSchedule.model.TemporarySignsNumberEntity;
import com.curing.projectSchedule.model.TemporarySignsNumberSum;
import com.curing.projectSchedule.service.TemporarySignsNumberService;

@Controller
@RequestMapping("/TemporarySignsNumber")
public class TemporarySignsNumberController {
	@Autowired
	private TemporarySignsNumberService temporarySignsNumberService;

	// 工程进度（临时标志工程数量表）List取得
	@RequestMapping("/getTemporarySignsNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getTemporarySignsNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<TemporarySignsNumberEntity> list = temporarySignsNumberService.getTemporarySignsNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（临时标志工程数量表）合计
	@RequestMapping("/getTemporarySignsNumberSum.action")
	@ResponseBody
	public List<TemporarySignsNumberSum> getTemporarySignsNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<TemporarySignsNumberSum> list = temporarySignsNumberService.getTemporarySignsNumberSum(map);
		return list;
	}
	
	// 工程进度（临时标志工程数量表）单条获取
	@RequestMapping("/getTemporarySignsNumberById.action")
	@ResponseBody
	public List<TemporarySignsNumberEntity> getTemporarySignsNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<TemporarySignsNumberEntity> list = temporarySignsNumberService.getTemporarySignsNumberList(map);
		return list;
	}
	
	// 新增工程进度（临时标志工程数量表）
	@RequestMapping("/insertTemporarySignsNumber.action")
	@ResponseBody
	public ResponseInfo insertTemporarySignsNumber(@RequestBody TemporarySignsNumberEntity temporarySignsNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = temporarySignsNumberService.insertTemporarySignsNumber(temporarySignsNumberEntity);
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
	
	// 更新工程进度（临时标志工程数量表）
	@RequestMapping("/updateTemporarySignsNumber.action")
	@ResponseBody
	public ResponseInfo updateTemporarySignsNumber(@RequestBody TemporarySignsNumberEntity temporarySignsNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = temporarySignsNumberService.updateTemporarySignsNumber(temporarySignsNumberEntity);
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
	
	// 删除工程进度（临时标志工程数量表）
	@RequestMapping("/deleteTemporarySignsNumber.action")
	@ResponseBody
	public ResponseInfo deleteTemporarySignsNumber(@RequestBody TemporarySignsNumberEntity temporarySignsNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = temporarySignsNumberService.deleteTemporarySignsNumber(temporarySignsNumberEntity);
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
		temporarySignsNumberService.export(request,response,map);		
	}

}
