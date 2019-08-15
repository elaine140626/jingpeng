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
import com.curing.projectSchedule.model.PassingCulvertNumberEntity;
import com.curing.projectSchedule.model.PassingCulvertNumberSum;
import com.curing.projectSchedule.service.PassingCulvertNumberService;

@Controller
@RequestMapping("/PassingCulvertNumber")
public class PassingCulvertNumberController {
	@Autowired
	private PassingCulvertNumberService passingCulvertNumberService;

	// 工程进度（石砌边沟过路涵工程数量表）List取得
	@RequestMapping("/getPassingCulvertNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getPassingCulvertNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<PassingCulvertNumberEntity> list = passingCulvertNumberService.getPassingCulvertNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（石砌边沟过路涵工程数量表）合计
	@RequestMapping("/getPassingCulvertNumberSum.action")
	@ResponseBody
	public List<PassingCulvertNumberSum> getPassingCulvertNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<PassingCulvertNumberSum> list = passingCulvertNumberService.getPassingCulvertNumberSum(map);
		return list;
	}
	
	// 工程进度（石砌边沟过路涵工程数量表）单条获取
	@RequestMapping("/getPassingCulvertNumberById.action")
	@ResponseBody
	public List<PassingCulvertNumberEntity> getPassingCulvertNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<PassingCulvertNumberEntity> list = passingCulvertNumberService.getPassingCulvertNumberList(map);
		return list;
	}
	
	// 新增工程进度（石砌边沟过路涵工程数量表）
	@RequestMapping("/insertPassingCulvertNumber.action")
	@ResponseBody
	public ResponseInfo insertPassingCulvertNumber(@RequestBody PassingCulvertNumberEntity passingCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = passingCulvertNumberService.insertPassingCulvertNumber(passingCulvertNumberEntity);
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
	
	// 更新工程进度（石砌边沟过路涵工程数量表）
	@RequestMapping("/updatePassingCulvertNumber.action")
	@ResponseBody
	public ResponseInfo updatePassingCulvertNumber(@RequestBody PassingCulvertNumberEntity passingCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = passingCulvertNumberService.updatePassingCulvertNumber(passingCulvertNumberEntity);
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
	
	// 删除工程进度（石砌边沟过路涵工程数量表）
	@RequestMapping("/deletePassingCulvertNumber.action")
	@ResponseBody
	public ResponseInfo deletePassingCulvertNumber(@RequestBody PassingCulvertNumberEntity passingCulvertNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = passingCulvertNumberService.deletePassingCulvertNumber(passingCulvertNumberEntity);
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
		passingCulvertNumberService.export(request,response,map);		
	}

}
