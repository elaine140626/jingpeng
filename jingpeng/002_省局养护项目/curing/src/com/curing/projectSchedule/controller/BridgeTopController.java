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
import com.curing.projectSchedule.model.BridgeTopEntity;
import com.curing.projectSchedule.model.BridgeTopSum;
import com.curing.projectSchedule.service.BridgeTopService;
@Controller
@RequestMapping("/BridgeTopDao")
public class BridgeTopController {
	@Autowired
	private BridgeTopService bridgeTopService;

	// 工程进度（小桥上部工程量汇总表）List取得
	@RequestMapping("/getBridgeTopList.action")
	@ResponseBody
	public DataTablesResponseInfo getBridgeTopList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<BridgeTopEntity> list = bridgeTopService.getBridgeTopList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（小桥上部工程量汇总表）合计
	@RequestMapping("/getBridgeTopSum.action")
	@ResponseBody
	public List<BridgeTopSum> getBridgeTopSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<BridgeTopSum> list = bridgeTopService.getBridgeTopSum(map);
		return list;
	}
	
	// 工程进度（小桥上部工程量汇总表）单条获取
	@RequestMapping("/getBridgeTopById.action")
	@ResponseBody
	public List<BridgeTopEntity> getBridgeTopById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<BridgeTopEntity> list = bridgeTopService.getBridgeTopList(map);
		return list;
	}
	
	// 新增工程进度（小桥上部工程量汇总表）
	@RequestMapping("/insertBridgeTop.action")
	@ResponseBody
	public ResponseInfo insertBridgeTop(@RequestBody BridgeTopEntity bridgeTopEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeTopService.insertBridgeTop(bridgeTopEntity);
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
	
	// 更新工程进度（小桥上部工程量汇总表）
	@RequestMapping("/updateBridgeTop.action")
	@ResponseBody
	public ResponseInfo updateBridgeTop(@RequestBody BridgeTopEntity bridgeTopEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeTopService.updateBridgeTop(bridgeTopEntity);
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
	
	// 删除工程进度（小桥上部工程量汇总表）
	@RequestMapping("/deleteBridgeTop.action")
	@ResponseBody
	public ResponseInfo deleteBridgeTop(@RequestBody BridgeTopEntity bridgeTopEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeTopService.deleteBridgeTop(bridgeTopEntity);
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
		bridgeTopService.export(request,response,map);		
	}
}
