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
import com.curing.projectSchedule.model.OverhaulPavementNumberEntity;
import com.curing.projectSchedule.model.OverhaulPavementNumberSum;
import com.curing.projectSchedule.service.OverhaulPavementNumberService;

@Controller
@RequestMapping("/OverhaulPavementNumber")
public class OverhaulPavementNumberController {
	@Autowired
	private OverhaulPavementNumberService overhaulPavementNumberService;

	// 工程进度（大修路面工程数量表）List取得
	@RequestMapping("/getOverhaulPavementNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getOverhaulPavementNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<OverhaulPavementNumberEntity> list = overhaulPavementNumberService.getOverhaulPavementNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（大修路面工程数量表）合计
	@RequestMapping("/getOverhaulPavementNumberSum.action")
	@ResponseBody
	public List<OverhaulPavementNumberSum> getOverhaulPavementNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<OverhaulPavementNumberSum> list = overhaulPavementNumberService.getOverhaulPavementNumberSum(map);
		return list;
	}
	
	// 工程进度（大修路面工程数量表）单条获取
	@RequestMapping("/getOverhaulPavementNumberById.action")
	@ResponseBody
	public List<OverhaulPavementNumberEntity> getOverhaulPavementNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<OverhaulPavementNumberEntity> list = overhaulPavementNumberService.getOverhaulPavementNumberList(map);
		return list;
	}
	
	// 新增工程进度（大修路面工程数量表）
	@RequestMapping("/insertOverhaulPavementNumber.action")
	@ResponseBody
	public ResponseInfo insertOverhaulPavementNumber(@RequestBody OverhaulPavementNumberEntity overhaulPavementNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = overhaulPavementNumberService.insertOverhaulPavementNumber(overhaulPavementNumberEntity);
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
	
	// 更新工程进度（大修路面工程数量表）
	@RequestMapping("/updateOverhaulPavementNumber.action")
	@ResponseBody
	public ResponseInfo updateOverhaulPavementNumber(@RequestBody OverhaulPavementNumberEntity overhaulPavementNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = overhaulPavementNumberService.updateOverhaulPavementNumber(overhaulPavementNumberEntity);
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
	
	// 删除工程进度（大修路面工程数量表）
	@RequestMapping("/deleteOverhaulPavementNumber.action")
	@ResponseBody
	public ResponseInfo deleteOverhaulPavementNumber(@RequestBody OverhaulPavementNumberEntity overhaulPavementNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = overhaulPavementNumberService.deleteOverhaulPavementNumber(overhaulPavementNumberEntity);
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
		overhaulPavementNumberService.export(request,response,map);		
	}


}
