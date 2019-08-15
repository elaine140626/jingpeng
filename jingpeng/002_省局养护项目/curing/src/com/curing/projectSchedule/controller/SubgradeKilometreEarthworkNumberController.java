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
import com.curing.projectSchedule.model.SubgradeKilometreEarthworkNumberEntity;
import com.curing.projectSchedule.model.SubgradeKilometreEarthworkNumberSum;
import com.curing.projectSchedule.service.SubgradeKilometreEarthworkNumberService;

@Controller
@RequestMapping("/SubgradeKilometreEarthworkNumber")
public class SubgradeKilometreEarthworkNumberController {
	@Autowired
	private SubgradeKilometreEarthworkNumberService subgradeKilometreEarthworkNumberService;

	// 工程进度（路基每公里土石方数量表）List取得
	@RequestMapping("/getSubgradeKilometreEarthworkNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getSubgradeKilometreEarthworkNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SubgradeKilometreEarthworkNumberEntity> list = subgradeKilometreEarthworkNumberService.getSubgradeKilometreEarthworkNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（路基每公里土石方数量表）合计
	@RequestMapping("/getSubgradeKilometreEarthworkNumberSum.action")
	@ResponseBody
	public List<SubgradeKilometreEarthworkNumberSum> getSubgradeKilometreEarthworkNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SubgradeKilometreEarthworkNumberSum> list = subgradeKilometreEarthworkNumberService.getSubgradeKilometreEarthworkNumberSum(map);
		return list;
	}
	
	// 工程进度（路基每公里土石方数量表）单条获取
	@RequestMapping("/getSubgradeKilometreEarthworkNumberById.action")
	@ResponseBody
	public List<SubgradeKilometreEarthworkNumberEntity> getSubgradeKilometreEarthworkNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SubgradeKilometreEarthworkNumberEntity> list = subgradeKilometreEarthworkNumberService.getSubgradeKilometreEarthworkNumberList(map);
		return list;
	}
	
	// 新增工程进度（路基每公里土石方数量表）
	@RequestMapping("/insertSubgradeKilometreEarthworkNumber.action")
	@ResponseBody
	public ResponseInfo insertSubgradeKilometreEarthworkNumber(@RequestBody SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subgradeKilometreEarthworkNumberService.insertSubgradeKilometreEarthworkNumber(subgradeKilometreEarthworkNumberEntity);
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
	
	// 更新工程进度（路基每公里土石方数量表）
	@RequestMapping("/updateSubgradeKilometreEarthworkNumber.action")
	@ResponseBody
	public ResponseInfo updateSubgradeKilometreEarthworkNumber(@RequestBody SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subgradeKilometreEarthworkNumberService.updateSubgradeKilometreEarthworkNumber(subgradeKilometreEarthworkNumberEntity);
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
	
	// 删除工程进度（路基每公里土石方数量表）
	@RequestMapping("/deleteSubgradeKilometreEarthworkNumber.action")
	@ResponseBody
	public ResponseInfo deleteSubgradeKilometreEarthworkNumber(@RequestBody SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subgradeKilometreEarthworkNumberService.deleteSubgradeKilometreEarthworkNumber(subgradeKilometreEarthworkNumberEntity);
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
		subgradeKilometreEarthworkNumberService.export(request,response,map);		
	}

}
