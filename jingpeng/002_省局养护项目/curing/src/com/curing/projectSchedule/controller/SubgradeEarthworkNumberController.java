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
import com.curing.projectSchedule.model.SubgradeEarthworkNumberEntity;
import com.curing.projectSchedule.model.SubgradeEarthworkNumberSum;
import com.curing.projectSchedule.service.SubgradeEarthworkNumberService;

@Controller
@RequestMapping("/SubgradeEarthworkNumber")
public class SubgradeEarthworkNumberController {
	@Autowired
	private SubgradeEarthworkNumberService subgradeEarthworkNumberService;

	// 工程进度（路基土石方数量表）List取得
	@RequestMapping("/getSubgradeEarthworkNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getSubgradeEarthworkNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SubgradeEarthworkNumberEntity> list = subgradeEarthworkNumberService.getSubgradeEarthworkNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（路基土石方数量表）合计
	@RequestMapping("/getSubgradeEarthworkNumberSum.action")
	@ResponseBody
	public List<SubgradeEarthworkNumberSum> getSubgradeEarthworkNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SubgradeEarthworkNumberSum> list = subgradeEarthworkNumberService.getSubgradeEarthworkNumberSum(map);
		return list;
	}
	
	// 工程进度（路基土石方数量表）单条获取
	@RequestMapping("/getSubgradeEarthworkNumberById.action")
	@ResponseBody
	public List<SubgradeEarthworkNumberEntity> getSubgradeEarthworkNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SubgradeEarthworkNumberEntity> list = subgradeEarthworkNumberService.getSubgradeEarthworkNumberList(map);
		return list;
	}
	
	// 新增工程进度（路基土石方数量表）
	@RequestMapping("/insertSubgradeEarthworkNumber.action")
	@ResponseBody
	public ResponseInfo insertSubgradeEarthworkNumber(@RequestBody SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subgradeEarthworkNumberService.insertSubgradeEarthworkNumber(subgradeEarthworkNumberEntity);
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
	
	// 更新工程进度（路基土石方数量表）
	@RequestMapping("/updateSubgradeEarthworkNumber.action")
	@ResponseBody
	public ResponseInfo updateSubgradeEarthworkNumber(@RequestBody SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subgradeEarthworkNumberService.updateSubgradeEarthworkNumber(subgradeEarthworkNumberEntity);
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
	
	// 删除工程进度（路基土石方数量表）
	@RequestMapping("/deleteSubgradeEarthworkNumber.action")
	@ResponseBody
	public ResponseInfo deleteSubgradeEarthworkNumber(@RequestBody SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subgradeEarthworkNumberService.deleteSubgradeEarthworkNumber(subgradeEarthworkNumberEntity);
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
		subgradeEarthworkNumberService.export(request,response,map);		
	}

}
