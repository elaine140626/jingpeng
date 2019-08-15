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
import com.curing.projectSchedule.model.SteelGuardrailEntity;
import com.curing.projectSchedule.model.SteelGuardrailSum;
import com.curing.projectSchedule.service.SteelGuardrailService;
@Controller
@RequestMapping("/SteelGuardrail")
public class SteelGuardrailController {
	@Autowired
	private SteelGuardrailService steelGuardrailService;

	// 工程进度（钢护栏设置表）List取得
	@RequestMapping("/getSteelGuardrailList.action")
	@ResponseBody
	public DataTablesResponseInfo getSteelGuardrailList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SteelGuardrailEntity> list = steelGuardrailService.getSteelGuardrailList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（钢护栏设置表）合计
	@RequestMapping("/getSteelGuardrailSum.action")
	@ResponseBody
	public List<SteelGuardrailSum> getSteelGuardrailSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SteelGuardrailSum> list = steelGuardrailService.getSteelGuardrailSum(map);
		return list;
	}
	
	// 工程进度（钢护栏设置表）单条获取
	@RequestMapping("/getSteelGuardrailById.action")
	@ResponseBody
	public List<SteelGuardrailEntity> getSteelGuardrailById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SteelGuardrailEntity> list = steelGuardrailService.getSteelGuardrailList(map);
		return list;
	}
	
	// 新增工程进度（钢护栏设置表）
	@RequestMapping("/insertSteelGuardrail.action")
	@ResponseBody
	public ResponseInfo insertSteelGuardrail(@RequestBody SteelGuardrailEntity steelGuardrailEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = steelGuardrailService.insertSteelGuardrail(steelGuardrailEntity);
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
	
	// 更新工程进度（钢护栏设置表）
	@RequestMapping("/updateSteelGuardrail.action")
	@ResponseBody
	public ResponseInfo updateSteelGuardrail(@RequestBody SteelGuardrailEntity steelGuardrailEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = steelGuardrailService.updateSteelGuardrail(steelGuardrailEntity);
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
	
	// 删除工程进度（钢护栏设置表）
	@RequestMapping("/deleteSteelGuardrail.action")
	@ResponseBody
	public ResponseInfo deleteSteelGuardrail(@RequestBody SteelGuardrailEntity steelGuardrailEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = steelGuardrailService.deleteSteelGuardrail(steelGuardrailEntity);
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
		steelGuardrailService.export(request,response,map);		
	}
}
