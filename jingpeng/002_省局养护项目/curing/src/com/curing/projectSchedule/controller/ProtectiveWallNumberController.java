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
import com.curing.projectSchedule.model.ProtectiveWallNumberEntity;
import com.curing.projectSchedule.model.ProtectiveWallNumberSum;
import com.curing.projectSchedule.service.ProtectiveWallNumberService;

@Controller
@RequestMapping("/ProtectiveWallNumber")
public class ProtectiveWallNumberController {
	@Autowired
	private ProtectiveWallNumberService protectiveWallNumberService;

	// 工程进度（防护墙工程数量表）List取得
	@RequestMapping("/getProtectiveWallNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getProtectiveWallNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ProtectiveWallNumberEntity> list = protectiveWallNumberService.getProtectiveWallNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（防护墙工程数量表）合计
	@RequestMapping("/getProtectiveWallNumberSum.action")
	@ResponseBody
	public List<ProtectiveWallNumberSum> getProtectiveWallNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<ProtectiveWallNumberSum> list = protectiveWallNumberService.getProtectiveWallNumberSum(map);
		return list;
	}
	
	// 工程进度（防护墙工程数量表）单条获取
	@RequestMapping("/getProtectiveWallNumberById.action")
	@ResponseBody
	public List<ProtectiveWallNumberEntity> getProtectiveWallNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<ProtectiveWallNumberEntity> list = protectiveWallNumberService.getProtectiveWallNumberList(map);
		return list;
	}
	
	// 新增工程进度（防护墙工程数量表）
	@RequestMapping("/insertProtectiveWallNumber.action")
	@ResponseBody
	public ResponseInfo insertProtectiveWallNumber(@RequestBody ProtectiveWallNumberEntity protectiveWallNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = protectiveWallNumberService.insertProtectiveWallNumber(protectiveWallNumberEntity);
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
	
	// 更新工程进度（防护墙工程数量表）
	@RequestMapping("/updateProtectiveWallNumber.action")
	@ResponseBody
	public ResponseInfo updateProtectiveWallNumber(@RequestBody ProtectiveWallNumberEntity protectiveWallNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = protectiveWallNumberService.updateProtectiveWallNumber(protectiveWallNumberEntity);
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
	
	// 删除工程进度（防护墙工程数量表）
	@RequestMapping("/deleteProtectiveWallNumber.action")
	@ResponseBody
	public ResponseInfo deleteProtectiveWallNumber(@RequestBody ProtectiveWallNumberEntity protectiveWallNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = protectiveWallNumberService.deleteProtectiveWallNumber(protectiveWallNumberEntity);
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
		protectiveWallNumberService.export(request,response,map);		
	}

}
