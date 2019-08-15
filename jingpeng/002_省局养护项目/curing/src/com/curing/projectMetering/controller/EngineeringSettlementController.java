package com.curing.projectMetering.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.projectMetering.model.EngineeringSettlementEntity;
import com.curing.projectMetering.model.EngineeringSettlementSum;
import com.curing.projectMetering.service.EngineeringSettlementService;

@Controller
@RequestMapping("/EngineeringSettlement")
public class EngineeringSettlementController {

	@Autowired
	private EngineeringSettlementService engineeringSettlementService;
	
	// 工程计量（工程价款结算帐单）取得
	@RequestMapping("/getEngineeringSettlementList.action")
	@ResponseBody
	public DataTablesResponseInfo getEngineeringSettlementList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<EngineeringSettlementEntity> list = engineeringSettlementService.getEngineeringSettlementList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程计量（工程价款结算帐单）取得
	@RequestMapping("/getEngineeringSettlementSum.action")
	@ResponseBody
	public List<EngineeringSettlementSum> getEngineeringSettlementSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<EngineeringSettlementSum> data = engineeringSettlementService.getEngineeringSettlementSum(map);
		return data;
	}
	
	// 工程计量（工程价款结算帐单）单条获取
	@RequestMapping("/getEngineeringSettlementById.action")
	@ResponseBody
	public List<EngineeringSettlementEntity> getProjectTenderingSummaryById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<EngineeringSettlementEntity> list = engineeringSettlementService.getEngineeringSettlementList(map);
		return list;
	}
	
	// 新增工程计量（工程价款结算帐单）
	@RequestMapping("/insertEngineeringSettlement.action")
	@ResponseBody
	public ResponseInfo insertEngineeringSettlement(@RequestBody EngineeringSettlementEntity engineeringSettlementEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringSettlementService.insertEngineeringSettlement(engineeringSettlementEntity);
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
	
	// 更新工程计量（工程价款结算帐单）
	@RequestMapping("/updateEngineeringSettlement.action")
	@ResponseBody
	public ResponseInfo updateEngineeringSettlement(@RequestBody EngineeringSettlementEntity engineeringSettlementEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringSettlementService.updateEngineeringSettlement(engineeringSettlementEntity);
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
	
	// 删除工程计量（工程价款结算帐单）
	@RequestMapping("/deleteEngineeringSettlement.action")
	@ResponseBody
	public ResponseInfo deleteEngineeringSettlement(@RequestBody EngineeringSettlementEntity engineeringSettlementEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringSettlementService.deleteEngineeringSettlement(engineeringSettlementEntity);
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
}
