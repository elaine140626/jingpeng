package com.truckscale.basicSetting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truckscale.basicSetting.model.CarInfo;
import com.truckscale.basicSetting.model.TransportCompany;
import com.truckscale.basicSetting.service.TransportCompanyService;
import com.truckscale.common.model.DataTablesResponseInfo;
import com.truckscale.common.model.ResponseInfo;
import com.truckscale.common.util.MessageUtil;

@Controller
@RequestMapping("/transportCompany")
public class TransportCompanyController {
	
	@Autowired
	private TransportCompanyService transportCompanyService;
	
	//查询所有的运输单位
	@RequestMapping("/getAllTransportCompany.action")
	@ResponseBody
	public DataTablesResponseInfo getAllTransportCompany(@RequestParam Map<String,Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<TransportCompany> allTransportCompany = transportCompanyService.getAllTransportCompany(map);
		if(allTransportCompany == null || allTransportCompany.size() < 0) {
			allTransportCompany = new ArrayList<TransportCompany>();
		}
		info.setData(allTransportCompany);
		return info;
	}
	
	//添加运输单位
	@RequestMapping("/addTransportCompany.action")
	@ResponseBody
	public ResponseInfo addTransportCompany(@RequestBody TransportCompany transportCompany) {
		ResponseInfo info = new ResponseInfo();
		int result = transportCompanyService.addTransportCompany(transportCompany);
		if(result > 0) {
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
	
	//修改运输单位
	@RequestMapping("/updateTransportCompany.action")
	@ResponseBody
	public ResponseInfo updateTransportCompany(@RequestBody TransportCompany transportCompany) {
		ResponseInfo info = new ResponseInfo();
		int result = transportCompanyService.updateTransportCompany(transportCompany);
		if(result > 0) {
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
	
	//删除运输单位
	@RequestMapping("/delTransportCompany.action")
	@ResponseBody
	public ResponseInfo delTransportCompany(@RequestBody TransportCompany transportCompany) {
		ResponseInfo info = new ResponseInfo();
		int result = transportCompanyService.delTransportCompany(transportCompany);
		if(result > 0) {
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
	
	//查询所有的运输单位去重
	@RequestMapping("/getTransportCompany.action")
	@ResponseBody
	public List<TransportCompany> getTransportCompany(@RequestParam Map<String,Object> map) {
		return transportCompanyService.getTransportCompany(map);
	}
	
	//判断运输单是否被关联
	@RequestMapping("/getCarInfoList")
	@ResponseBody
	public List<CarInfo> getCarInfoList(@RequestParam Map<String,Object> map){
		return transportCompanyService.getCarInfoList(map);
	}
	
	// 运输单位自增
	@RequestMapping("/getGenerateCount.action")
	@ResponseBody
	public Map<String,Object> getGenerateCount(String type) throws IOException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = transportCompanyService.getGenerateCount(type);
		resultMap.put("getGenerateCount", result);
		return resultMap;		
	}
}
