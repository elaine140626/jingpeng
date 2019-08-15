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
import com.curing.projectSchedule.model.PipeCulvertNumberEntity;
import com.curing.projectSchedule.model.PipeCulvertNumberSum;
import com.curing.projectSchedule.service.PipeCulvertNumberService;
@Controller
@RequestMapping("/PipeCulvertNumber")
public class PipeCulvertNumberController {
	@Autowired
	private PipeCulvertNumberService pipeCulvertNumberService;

	// 工程进度（板涵工程数量汇总表）List取得
	@RequestMapping("/getPipeCulvertNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getPipeCulvertNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<PipeCulvertNumberEntity> list = pipeCulvertNumberService.getPipeCulvertNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（板涵工程数量汇总表）合计
	@RequestMapping("/getPipeCulvertNumberSum.action")
	@ResponseBody
	public List<PipeCulvertNumberSum> getPipeCulvertNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<PipeCulvertNumberSum> list = pipeCulvertNumberService.getPipeCulvertNumberSum(map);
		return list;
	}
	
	// 工程进度（板涵工程数量汇总表）单条获取
	@RequestMapping("/getPipeCulvertNumberById.action")
	@ResponseBody
	public List<PipeCulvertNumberEntity> getPipeCulvertNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<PipeCulvertNumberEntity> list = pipeCulvertNumberService.getPipeCulvertNumberList(map);
		return list;
	}
	
	// 新增工程进度（板涵工程数量汇总表）
	@RequestMapping("/insertPipeCulvertNumber.action")
	@ResponseBody
	public ResponseInfo insertPipeCulvertNumber(@RequestBody PipeCulvertNumberEntity pipeCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = pipeCulvertNumberService.insertPipeCulvertNumber(pipeCulvertNumberEntity);
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
	
	// 更新 工程进度（板涵工程数量汇总表）
	@RequestMapping("/updatePipeCulvertNumber.action")
	@ResponseBody
	public ResponseInfo updatePipeCulvertNumber(@RequestBody PipeCulvertNumberEntity pipeCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = pipeCulvertNumberService.updatePipeCulvertNumber(pipeCulvertNumberEntity);
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
	
	// 删除 工程进度（板涵工程数量汇总表）
	@RequestMapping("/deletePipeCulvertNumber.action")
	@ResponseBody
	public ResponseInfo deletePipeCulvertNumber(@RequestBody PipeCulvertNumberEntity pipeCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = pipeCulvertNumberService.deletePipeCulvertNumber(pipeCulvertNumberEntity);
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
