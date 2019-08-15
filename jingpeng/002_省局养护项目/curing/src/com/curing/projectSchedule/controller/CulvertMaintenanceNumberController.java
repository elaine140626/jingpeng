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
import com.curing.projectSchedule.model.CulvertMaintenanceNumberEntity;
import com.curing.projectSchedule.model.CulvertMaintenanceNumberSum;
import com.curing.projectSchedule.service.CulvertMaintenanceNumberService;

@Controller
@RequestMapping("/CulvertMaintenanceNumber")
public class CulvertMaintenanceNumberController {
	@Autowired
	private CulvertMaintenanceNumberService culvertMaintenanceNumberService;

	// 工程进度（路基防护工程数量表）List取得
	@RequestMapping("/getCulvertMaintenanceNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getCulvertMaintenanceNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<CulvertMaintenanceNumberEntity> list = culvertMaintenanceNumberService.getCulvertMaintenanceNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（路基防护工程数量表）合计
	@RequestMapping("/getCulvertMaintenanceNumberSum.action")
	@ResponseBody
	public List<CulvertMaintenanceNumberSum> getCulvertMaintenanceNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<CulvertMaintenanceNumberSum> list = culvertMaintenanceNumberService.getCulvertMaintenanceNumberSum(map);
		return list;
	}
	
	// 工程进度（路基防护工程数量表）单条获取
	@RequestMapping("/getCulvertMaintenanceNumberById.action")
	@ResponseBody
	public List<CulvertMaintenanceNumberEntity> getCulvertMaintenanceNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<CulvertMaintenanceNumberEntity> list = culvertMaintenanceNumberService.getCulvertMaintenanceNumberList(map);
		return list;
	}
	
	// 新增工程进度（路基防护工程数量表）
	@RequestMapping("/insertCulvertMaintenanceNumber.action")
	@ResponseBody
	public ResponseInfo insertCulvertMaintenanceNumber(@RequestBody CulvertMaintenanceNumberEntity culvertMaintenanceNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = culvertMaintenanceNumberService.insertCulvertMaintenanceNumber(culvertMaintenanceNumberEntity);
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
	@RequestMapping("/updateCulvertMaintenanceNumber.action")
	@ResponseBody
	public ResponseInfo updateCulvertMaintenanceNumber(@RequestBody CulvertMaintenanceNumberEntity culvertMaintenanceNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = culvertMaintenanceNumberService.updateCulvertMaintenanceNumber(culvertMaintenanceNumberEntity);
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
	@RequestMapping("/deleteCulvertMaintenanceNumber.action")
	@ResponseBody
	public ResponseInfo deleteCulvertMaintenanceNumber(@RequestBody CulvertMaintenanceNumberEntity culvertMaintenanceNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = culvertMaintenanceNumberService.deleteCulvertMaintenanceNumber(culvertMaintenanceNumberEntity);
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
		culvertMaintenanceNumberService.export(request,response,map);		
	}
	
}
