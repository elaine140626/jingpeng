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
import com.curing.projectSchedule.model.BridgeRetainingWallNumberEntity;
import com.curing.projectSchedule.model.BridgeRetainingWallNumberSum;
import com.curing.projectSchedule.service.BridgeRetainingWallNumberService;

@Controller
@RequestMapping("/BridgeRetainingWallNumber")
public class BridgeRetainingWallNumberController {
	@Autowired
	private BridgeRetainingWallNumberService bridgeRetainingWallNumberService;

	// 工程进度（桥梁挡墙工程数量表）List取得
	@RequestMapping("/getBridgeRetainingWallNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getBridgeRetainingWallNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<BridgeRetainingWallNumberEntity> list = bridgeRetainingWallNumberService.getBridgeRetainingWallNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（桥梁挡墙工程数量表）合计
	@RequestMapping("/getBridgeRetainingWallNumberSum.action")
	@ResponseBody
	public List<BridgeRetainingWallNumberSum> getBridgeRetainingWallNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<BridgeRetainingWallNumberSum> list = bridgeRetainingWallNumberService.getBridgeRetainingWallNumberSum(map);
		return list;
	}
	
	// 工程进度（桥梁挡墙工程数量表）单条获取
	@RequestMapping("/getBridgeRetainingWallNumberById.action")
	@ResponseBody
	public List<BridgeRetainingWallNumberEntity> getBridgeRetainingWallNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<BridgeRetainingWallNumberEntity> list = bridgeRetainingWallNumberService.getBridgeRetainingWallNumberList(map);
		return list;
	}
	
	// 新增工程进度（桥梁挡墙工程数量表）
	@RequestMapping("/insertBridgeRetainingWallNumber.action")
	@ResponseBody
	public ResponseInfo insertBridgeRetainingWallNumber(@RequestBody BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeRetainingWallNumberService.insertBridgeRetainingWallNumber(bridgeRetainingWallNumberEntity);
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
	
	// 更新工程进度（桥梁挡墙工程数量表）
	@RequestMapping("/updateBridgeRetainingWallNumber.action")
	@ResponseBody
	public ResponseInfo updateBridgeRetainingWallNumber(@RequestBody BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeRetainingWallNumberService.updateBridgeRetainingWallNumber(bridgeRetainingWallNumberEntity);
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
	
	// 删除工程进度（桥梁挡墙工程数量表）
	@RequestMapping("/deleteBridgeRetainingWallNumber.action")
	@ResponseBody
	public ResponseInfo deleteBridgeRetainingWallNumber(@RequestBody BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeRetainingWallNumberService.deleteBridgeRetainingWallNumber(bridgeRetainingWallNumberEntity);
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
		bridgeRetainingWallNumberService.export(request,response,map);		
	}

}
