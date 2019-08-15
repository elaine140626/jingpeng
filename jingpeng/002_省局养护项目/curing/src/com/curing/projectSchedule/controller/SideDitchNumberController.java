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
import com.curing.projectSchedule.model.SideDitchNumberEntity;
import com.curing.projectSchedule.model.SideDitchNumberSum;
import com.curing.projectSchedule.service.SideDitchNumberService;

@Controller
@RequestMapping("/SideDitchNumber")
public class SideDitchNumberController {
	@Autowired
	private SideDitchNumberService sideDitchNumberService;

	// 工程进度（边沟工程数量表）List取得
	@RequestMapping("/getSideDitchNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getSideDitchNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SideDitchNumberEntity> list = sideDitchNumberService.getSideDitchNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（边沟工程数量表）合计
	@RequestMapping("/getSideDitchNumberSum.action")
	@ResponseBody
	public List<SideDitchNumberSum> getSideDitchNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SideDitchNumberSum> list = sideDitchNumberService.getSideDitchNumberSum(map);
		return list;
	}
	
	// 工程进度（边沟工程数量表）单条获取
	@RequestMapping("/getSideDitchNumberById.action")
	@ResponseBody
	public List<SideDitchNumberEntity> getSideDitchNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SideDitchNumberEntity> list = sideDitchNumberService.getSideDitchNumberList(map);
		return list;
	}
	
	// 新增工程进度（边沟工程数量表）
	@RequestMapping("/insertSideDitchNumber.action")
	@ResponseBody
	public ResponseInfo insertSideDitchNumber(@RequestBody SideDitchNumberEntity sideDitchNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = sideDitchNumberService.insertSideDitchNumber(sideDitchNumberEntity);
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
	
	// 更新工程进度（边沟工程数量表）
	@RequestMapping("/updateSideDitchNumber.action")
	@ResponseBody
	public ResponseInfo updateSideDitchNumber(@RequestBody SideDitchNumberEntity sideDitchNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = sideDitchNumberService.updateSideDitchNumber(sideDitchNumberEntity);
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
	
	// 删除工程进度（边沟工程数量表）
	@RequestMapping("/deleteSideDitchNumber.action")
	@ResponseBody
	public ResponseInfo deleteSideDitchNumber(@RequestBody SideDitchNumberEntity sideDitchNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = sideDitchNumberService.deleteSideDitchNumber(sideDitchNumberEntity);
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
		sideDitchNumberService.export(request,response,map);		
	}

}
