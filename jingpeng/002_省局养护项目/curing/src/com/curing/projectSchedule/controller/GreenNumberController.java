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
import com.curing.projectSchedule.model.GreenNumberEntity;
import com.curing.projectSchedule.model.GreenNumberSum;
import com.curing.projectSchedule.service.GreenNumberService;

@Controller
@RequestMapping("/GreenNumber")
public class GreenNumberController {
	@Autowired
	private GreenNumberService greenNumberService;

	// 工程进度（绿化工程数量表）List取得
	@RequestMapping("/getGreenNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getGreenNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<GreenNumberEntity> list = greenNumberService.getGreenNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（绿化工程数量表）合计
	@RequestMapping("/getGreenNumberSum.action")
	@ResponseBody
	public List<GreenNumberSum> getGreenNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<GreenNumberSum> list = greenNumberService.getGreenNumberSum(map);
		return list;
	}
	
	// 工程进度（绿化工程数量表）单条获取
	@RequestMapping("/getGreenNumberById.action")
	@ResponseBody
	public List<GreenNumberEntity> getGreenNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<GreenNumberEntity> list = greenNumberService.getGreenNumberList(map);
		return list;
	}
	
	// 新增工程进度（绿化工程数量表）
	@RequestMapping("/insertGreenNumber.action")
	@ResponseBody
	public ResponseInfo insertGreenNumber(@RequestBody GreenNumberEntity greenNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = greenNumberService.insertGreenNumber(greenNumberEntity);
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
	
	// 更新工程进度（绿化工程数量表）
	@RequestMapping("/updateGreenNumber.action")
	@ResponseBody
	public ResponseInfo updateGreenNumber(@RequestBody GreenNumberEntity greenNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = greenNumberService.updateGreenNumber(greenNumberEntity);
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
	
	// 删除工程进度（绿化工程数量表）
	@RequestMapping("/deleteGreenNumber.action")
	@ResponseBody
	public ResponseInfo deleteGreenNumber(@RequestBody GreenNumberEntity greenNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = greenNumberService.deleteGreenNumber(greenNumberEntity);
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
		greenNumberService.export(request,response,map);		
	}

}
