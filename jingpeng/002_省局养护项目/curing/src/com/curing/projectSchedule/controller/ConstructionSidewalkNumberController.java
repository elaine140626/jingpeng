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
import com.curing.projectSchedule.model.ConstructionSidewalkNumberEntity;
import com.curing.projectSchedule.model.ConstructionSidewalkNumberSum;
import com.curing.projectSchedule.service.ConstructionSidewalkNumberService;

@Controller
@RequestMapping("/ConstructionSidewalkNumber")
public class ConstructionSidewalkNumberController {
	@Autowired
	private ConstructionSidewalkNumberService constructionSidewalkNumberService;

	// 工程进度（施工便道路面工程数量汇总表）List取得
	@RequestMapping("/getConstructionSidewalkNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getConstructionSidewalkNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ConstructionSidewalkNumberEntity> list = constructionSidewalkNumberService.getConstructionSidewalkNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（施工便道路面工程数量汇总表）合计
	@RequestMapping("/getConstructionSidewalkNumberSum.action")
	@ResponseBody
	public List<ConstructionSidewalkNumberSum> getConstructionSidewalkNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<ConstructionSidewalkNumberSum> list = constructionSidewalkNumberService.getConstructionSidewalkNumberSum(map);
		return list;
	}
	
	// 工程进度（施工便道路面工程数量汇总表）单条获取
	@RequestMapping("/getConstructionSidewalkNumberById.action")
	@ResponseBody
	public List<ConstructionSidewalkNumberEntity> getConstructionSidewalkNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<ConstructionSidewalkNumberEntity> list = constructionSidewalkNumberService.getConstructionSidewalkNumberList(map);
		return list;
	}
	
	// 新增工程进度（施工便道路面工程数量汇总表）
	@RequestMapping("/insertConstructionSidewalkNumber.action")
	@ResponseBody
	public ResponseInfo insertConstructionSidewalkNumber(@RequestBody ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = constructionSidewalkNumberService.insertConstructionSidewalkNumber(constructionSidewalkNumberEntity);
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
	
	// 更新工程进度（施工便道路面工程数量汇总表）
	@RequestMapping("/updateConstructionSidewalkNumber.action")
	@ResponseBody
	public ResponseInfo updateConstructionSidewalkNumber(@RequestBody ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = constructionSidewalkNumberService.updateConstructionSidewalkNumber(constructionSidewalkNumberEntity);
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
	
	// 删除工程进度（施工便道路面工程数量汇总表）
	@RequestMapping("/deleteConstructionSidewalkNumber.action")
	@ResponseBody
	public ResponseInfo deleteConstructionSidewalkNumber(@RequestBody ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = constructionSidewalkNumberService.deleteConstructionSidewalkNumber(constructionSidewalkNumberEntity);
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
		constructionSidewalkNumberService.export(request,response,map);		
	}
}
