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
import com.curing.projectSchedule.model.BridgeGuardEntity;
import com.curing.projectSchedule.model.BridgeGuardSum;
import com.curing.projectSchedule.service.BridgeGuardService;

@Controller
@RequestMapping("/BridgeGuard")
public class BridgeGuardController {
	@Autowired
	private BridgeGuardService bridgeGuardService;

	// 工程进度（小桥防护工程及附属设施工程量汇总表）List取得
	@RequestMapping("/getBridgeGuardList.action")
	@ResponseBody
	public DataTablesResponseInfo getBridgeGuardList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<BridgeGuardEntity> list = bridgeGuardService.getBridgeGuardList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（小桥防护工程及附属设施工程量汇总表）合计
	@RequestMapping("/getBridgeGuardSum.action")
	@ResponseBody
	public List<BridgeGuardSum> getBridgeGuardSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<BridgeGuardSum> list = bridgeGuardService.getBridgeGuardSum(map);
		return list;
	}
	
	// 工程进度（小桥防护工程及附属设施工程量汇总表）单条获取
	@RequestMapping("/getBridgeGuardById.action")
	@ResponseBody
	public List<BridgeGuardEntity> getBridgeGuardById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<BridgeGuardEntity> list = bridgeGuardService.getBridgeGuardList(map);
		return list;
	}
	
	// 新增工程进度（小桥防护工程及附属设施工程量汇总表）
	@RequestMapping("/insertBridgeGuard.action")
	@ResponseBody
	public ResponseInfo insertBridgeGuard(@RequestBody BridgeGuardEntity bridgeGuardEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeGuardService.insertBridgeGuard(bridgeGuardEntity);
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
	
	// 更新 工程进度（小桥防护工程及附属设施工程量汇总表）
	@RequestMapping("/updateBridgeGuard.action")
	@ResponseBody
	public ResponseInfo updateBridgeGuard(@RequestBody BridgeGuardEntity bridgeGuardEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeGuardService.updateBridgeGuard(bridgeGuardEntity);
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
	
	// 删除 工程进度（小桥防护工程及附属设施工程量汇总表）
	@RequestMapping("/deleteBridgeGuard.action")
	@ResponseBody
	public ResponseInfo deleteBridgeGuard(@RequestBody BridgeGuardEntity bridgeGuardEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeGuardService.deleteBridgeGuard(bridgeGuardEntity);
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
		bridgeGuardService.export(request,response,map);		
	}

}
