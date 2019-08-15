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
import com.curing.projectSchedule.model.BridgeBottomEntity;
import com.curing.projectSchedule.model.BridgeBottomSum;
import com.curing.projectSchedule.service.BridgeBottomService;

@Controller
@RequestMapping("/BridgeBottom")
public class BridgeBottomController {
	@Autowired
	private BridgeBottomService bridgeBottomService;

	// 工程进度（小桥下部工程量汇总表）List取得
	@RequestMapping("/getBridgeBottomList.action")
	@ResponseBody
	public DataTablesResponseInfo getBridgeBottomList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<BridgeBottomEntity> list = bridgeBottomService.getBridgeBottomList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（小桥下部工程量汇总表）合计
	@RequestMapping("/getBridgeBottomSum.action")
	@ResponseBody
	public List<BridgeBottomSum> getBridgeBottomSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<BridgeBottomSum> list = bridgeBottomService.getBridgeBottomSum(map);
		return list;
	}
	
	// 工程进度（小桥下部工程量汇总表）单条获取
	@RequestMapping("/getBridgeBottomById.action")
	@ResponseBody
	public List<BridgeBottomEntity> getBridgeTopById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<BridgeBottomEntity> list = bridgeBottomService.getBridgeBottomList(map);
		return list;
	}
	
	// 新增工程进度（小桥下部工程量汇总表）
	@RequestMapping("/insertBridgeBottom.action")
	@ResponseBody
	public ResponseInfo insertBridgeBottom(@RequestBody BridgeBottomEntity bridgeBottomEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeBottomService.insertBridgeBottom(bridgeBottomEntity);
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
	
	// 更新工程进度（小桥下部工程量汇总表）
	@RequestMapping("/updateBridgeBottom.action")
	@ResponseBody
	public ResponseInfo updateBridgeBottom(@RequestBody BridgeBottomEntity bridgeBottomEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeBottomService.updateBridgeBottom(bridgeBottomEntity);
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
	
	// 删除工程进度（小桥下部工程量汇总表）
	@RequestMapping("/deleteBridgeBottom.action")
	@ResponseBody
	public ResponseInfo deleteBridgeBottom(@RequestBody BridgeBottomEntity bridgeBottomEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = bridgeBottomService.deleteBridgeBottom(bridgeBottomEntity);
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
		bridgeBottomService.export(request,response,map);		
	}

}
