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
import com.curing.projectSchedule.model.PlateCulvertNumberEntity;
import com.curing.projectSchedule.model.PlateCulvertNumberSum;
import com.curing.projectSchedule.service.PlateCulvertNumberService;

@Controller
@RequestMapping("/PlateCulvertNumber")
public class PlateCulvertNumberController {
	@Autowired
	private PlateCulvertNumberService plateCulvertNumberService;

	// 工程进度（板涵工程数量汇总表）List取得
	@RequestMapping("/getPlateCulvertNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getPlateCulvertNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<PlateCulvertNumberEntity> list = plateCulvertNumberService.getPlateCulvertNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（板涵工程数量汇总表）合计
	@RequestMapping("/getPlateCulvertNumberSum.action")
	@ResponseBody
	public List<PlateCulvertNumberSum> getPlateCulvertNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<PlateCulvertNumberSum> list = plateCulvertNumberService.getPlateCulvertNumberSum(map);
		return list;
	}
	
	// 工程进度（板涵工程数量汇总表）单条获取
	@RequestMapping("/getPlateCulvertNumberById.action")
	@ResponseBody
	public List<PlateCulvertNumberEntity> getPlateCulvertNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<PlateCulvertNumberEntity> list = plateCulvertNumberService.getPlateCulvertNumberList(map);
		return list;
	}
	
	// 新增工程进度（板涵工程数量汇总表）
	@RequestMapping("/insertPlateCulvertNumber.action")
	@ResponseBody
	public ResponseInfo insertPlateCulvertNumber(@RequestBody PlateCulvertNumberEntity plateCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = plateCulvertNumberService.insertPlateCulvertNumber(plateCulvertNumberEntity);
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
	
	// 更新工程进度（板涵工程数量汇总表）
	@RequestMapping("/updatePlateCulvertNumber.action")
	@ResponseBody
	public ResponseInfo updatePlateCulvertNumber(@RequestBody PlateCulvertNumberEntity plateCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = plateCulvertNumberService.updatePlateCulvertNumber(plateCulvertNumberEntity);
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
	
	// 删除工程进度（板涵工程数量汇总表）
	@RequestMapping("/deletePlateCulvertNumber.action")
	@ResponseBody
	public ResponseInfo deletePlateCulvertNumber(@RequestBody PlateCulvertNumberEntity plateCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = plateCulvertNumberService.deletePlateCulvertNumber(plateCulvertNumberEntity);
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
		plateCulvertNumberService.export(request,response,map);		
	}

}
