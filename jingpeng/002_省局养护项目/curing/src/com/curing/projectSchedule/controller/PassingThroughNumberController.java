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
import com.curing.projectSchedule.model.PassingThroughNumberEntity;
import com.curing.projectSchedule.model.PassingThroughNumberSum;
import com.curing.projectSchedule.service.PassingThroughNumberService;
@Controller
@RequestMapping("/PassingThroughNumber")
public class PassingThroughNumberController {
	@Autowired
	private PassingThroughNumberService PassingThroughNumberService;

	// 工程进度（过路过户涵工程数量表）List取得
	@RequestMapping("/getPassingThroughNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getPassingThroughNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<PassingThroughNumberEntity> list = PassingThroughNumberService.getPassingThroughNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（过路过户涵工程数量表）合计
	@RequestMapping("/getPassingThroughNumberSum.action")
	@ResponseBody
	public List<PassingThroughNumberSum> getPassingThroughNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<PassingThroughNumberSum> list = PassingThroughNumberService.getPassingThroughNumberSum(map);
		return list;
	}
	
	// 工程进度（过路过户涵工程数量表）单条获取
	@RequestMapping("/getPassingThroughNumberById.action")
	@ResponseBody
	public List<PassingThroughNumberEntity> getPassingThroughNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<PassingThroughNumberEntity> list = PassingThroughNumberService.getPassingThroughNumberList(map);
		return list;
	}
	
	// 新增工程进度（过路过户涵工程数量表）
	@RequestMapping("/insertBridgeGuard.action")
	@ResponseBody
	public ResponseInfo insertPassingThroughNumber(@RequestBody PassingThroughNumberEntity passingThroughNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = PassingThroughNumberService.insertPassingThroughNumber(passingThroughNumberEntity);
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
	
	// 更新 工程进度（过路过户涵工程数量表）
	@RequestMapping("/updatePassingThroughNumber.action")
	@ResponseBody
	public ResponseInfo updatePassingThroughNumber(@RequestBody PassingThroughNumberEntity passingThroughNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = PassingThroughNumberService.updatePassingThroughNumber(passingThroughNumberEntity);
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
	
	// 删除 工程进度（过路过户涵工程数量表）
	@RequestMapping("/deletePassingThroughNumber.action")
	@ResponseBody
	public ResponseInfo deletePassingThroughNumber(@RequestBody PassingThroughNumberEntity passingThroughNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = PassingThroughNumberService.deletePassingThroughNumber(passingThroughNumberEntity);
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
		PassingThroughNumberService.export(request,response,map);		
	}
}
