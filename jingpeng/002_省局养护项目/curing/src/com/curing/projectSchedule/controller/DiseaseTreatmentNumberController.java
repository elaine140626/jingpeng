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
import com.curing.projectSchedule.model.DiseaseTreatmentNumberEntity;
import com.curing.projectSchedule.model.DiseaseTreatmentNumberSum;
import com.curing.projectSchedule.service.DiseaseTreatmentNumberService;

@Controller
@RequestMapping("/DiseaseTreatmentNumber")
public class DiseaseTreatmentNumberController {
	@Autowired
	private DiseaseTreatmentNumberService diseaseTreatmentNumberService;

	// 工程进度（旧路面病害处治工程数量表）List取得
	@RequestMapping("/getDiseaseTreatmentNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getDiseaseTreatmentNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<DiseaseTreatmentNumberEntity> list = diseaseTreatmentNumberService.getDiseaseTreatmentNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（旧路面病害处治工程数量表）合计
	@RequestMapping("/getDiseaseTreatmentNumberSum.action")
	@ResponseBody
	public List<DiseaseTreatmentNumberSum> getDiseaseTreatmentNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<DiseaseTreatmentNumberSum> list = diseaseTreatmentNumberService.getDiseaseTreatmentNumberSum(map);
		return list;
	}
	
	// 工程进度（旧路面病害处治工程数量表）单条获取
	@RequestMapping("/getDiseaseTreatmentNumberById.action")
	@ResponseBody
	public List<DiseaseTreatmentNumberEntity> getDiseaseTreatmentNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<DiseaseTreatmentNumberEntity> list = diseaseTreatmentNumberService.getDiseaseTreatmentNumberList(map);
		return list;
	}
	
	// 新增工程进度（旧路面病害处治工程数量表）
	@RequestMapping("/insertDiseaseTreatmentNumber.action")
	@ResponseBody
	public ResponseInfo insertDiseaseTreatmentNumber(@RequestBody DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = diseaseTreatmentNumberService.insertDiseaseTreatmentNumber(diseaseTreatmentNumberEntity);
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
	
	// 更新工程进度（旧路面病害处治工程数量表）
	@RequestMapping("/updateDiseaseTreatmentNumber.action")
	@ResponseBody
	public ResponseInfo updateDiseaseTreatmentNumber(@RequestBody DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = diseaseTreatmentNumberService.updateDiseaseTreatmentNumber(diseaseTreatmentNumberEntity);
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
	
	// 删除工程进度（旧路面病害处治工程数量表）
	@RequestMapping("/deleteDiseaseTreatmentNumber.action")
	@ResponseBody
	public ResponseInfo deleteDiseaseTreatmentNumber(@RequestBody DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = diseaseTreatmentNumberService.deleteDiseaseTreatmentNumber(diseaseTreatmentNumberEntity);
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
		diseaseTreatmentNumberService.export(request,response,map);		
	}

}
