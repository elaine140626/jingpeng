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
import com.curing.projectSchedule.model.SignBoardNumberEntity;
import com.curing.projectSchedule.model.SignBoardNumberSum;
import com.curing.projectSchedule.service.SignBoardNumberService;

@Controller
@RequestMapping("/SignBoardNumber")
public class SignBoardNumberController {
	@Autowired
	private SignBoardNumberService signBoardNumberService;

	// 工程进度（标志牌工程数量表）List取得
	@RequestMapping("/getSignBoardNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getSignBoardNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SignBoardNumberEntity> list = signBoardNumberService.getSignBoardNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（标志牌工程数量表）合计
	@RequestMapping("/getSignBoardNumberSum.action")
	@ResponseBody
	public List<SignBoardNumberSum> getSignBoardNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SignBoardNumberSum> list = signBoardNumberService.getSignBoardNumberSum(map);
		return list;
	}
	
	// 工程进度（标志牌工程数量表）单条获取
	@RequestMapping("/getSignBoardNumberById.action")
	@ResponseBody
	public List<SignBoardNumberEntity> getSignBoardNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SignBoardNumberEntity> list = signBoardNumberService.getSignBoardNumberList(map);
		return list;
	}
	
	// 新增工程进度（标志牌工程数量表）
	@RequestMapping("/insertSignBoardNumber.action")
	@ResponseBody
	public ResponseInfo insertSignBoardNumber(@RequestBody SignBoardNumberEntity signBoardNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = signBoardNumberService.insertSignBoardNumber(signBoardNumberEntity);
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
	
	// 更新工程进度（标志牌工程数量表）
	@RequestMapping("/updateSignBoardNumber.action")
	@ResponseBody
	public ResponseInfo updateSignBoardNumber(@RequestBody SignBoardNumberEntity signBoardNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = signBoardNumberService.updateSignBoardNumber(signBoardNumberEntity);
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
	
	// 删除工程进度（标志牌工程数量表）
	@RequestMapping("/deleteSignBoardNumber.action")
	@ResponseBody
	public ResponseInfo deleteSignBoardNumber(@RequestBody SignBoardNumberEntity signBoardNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = signBoardNumberService.deleteSignBoardNumber(signBoardNumberEntity);
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
		signBoardNumberService.export(request,response,map);		
	}

}
