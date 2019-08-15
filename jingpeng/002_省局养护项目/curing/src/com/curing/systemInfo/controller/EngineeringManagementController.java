package com.curing.systemInfo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.curing.systemInfo.model.EngineeringManagementEntity;
import com.curing.systemInfo.service.EngineeringManagementService;

@Controller
@RequestMapping("/EngineeringManagement")
public class EngineeringManagementController {

	@Autowired
	private EngineeringManagementService engineeringManagementService;
	
	// 项目List取得
	@RequestMapping("/getEngineeringManagementList.action")
	@ResponseBody
	public DataTablesResponseInfo getEngineeringManagementList(HttpServletRequest request, @RequestParam Map<String, Object> map)throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		 List<EngineeringManagementEntity> engineeringManagementEntityInfo = engineeringManagementService.getEngineeringManagementList(map);
		 dInfo.setData(engineeringManagementEntityInfo);
		 return dInfo;
	}

	// 市List取得
	@RequestMapping("/getCityInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getCityInfoList(HttpServletRequest request, @RequestParam Map<String, Object> map)throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		 List<EngineeringManagementEntity> engineeringManagementEntityInfo = engineeringManagementService.getCityInfoList(map);
		 dInfo.setData(engineeringManagementEntityInfo);
		 return dInfo;
	}
	
	// 县List取得
	@RequestMapping("/getCountyInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getCountyInfo(HttpServletRequest request, @RequestParam Map<String, Object> map)throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		 List<EngineeringManagementEntity> engineeringManagementEntityInfo = engineeringManagementService.getCountyInfo(map);
		 dInfo.setData(engineeringManagementEntityInfo);
		 return dInfo;
	}
	
	// 添加项目
	@RequestMapping("/insertEntryName.action")
	@ResponseBody
	public ResponseInfo insertEntryName(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringManagementService.insertEntryName(engineeringManagementEntity);
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
	
	// 添加市
	@RequestMapping("/insertCityInfo.action")
	@ResponseBody
	public ResponseInfo insertCityInfo(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringManagementService.insertCityInfo(engineeringManagementEntity);
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
	
	// 添加县
	@RequestMapping("/insertCountyInfo.action")
	@ResponseBody
	public ResponseInfo insertCountyInfo(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringManagementService.insertCountyInfo(engineeringManagementEntity);
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
	
	// 修改项目
	@RequestMapping("/updateEntryName.action")
	@ResponseBody
	public ResponseInfo updateEntryName(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringManagementService.updateEntryName(engineeringManagementEntity);
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
	
	
	// 修改市
	@RequestMapping("/updateCityInfo.action")
	@ResponseBody
	public ResponseInfo updateCityInfo(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringManagementService.updateEntryName(engineeringManagementEntity);
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
	
	
	// 修改县
	@RequestMapping("/updateCountyInfo.action")
	@ResponseBody
	public ResponseInfo updateCountyInfo(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringManagementService.updateCountyInfo(engineeringManagementEntity);
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
	
	// 删除项目
	@RequestMapping("/deleteEntryName.action")
	@ResponseBody
	public ResponseInfo deleteEntryName(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = engineeringManagementService.deleteEntryName(engineeringManagementEntity);
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
	
	
	// 删除市
	@RequestMapping("/deleteCityInfo.action")
	@ResponseBody
	public ResponseInfo deleteCityInfo(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		Map<String, Object> map = new HashMap<>();
		map.put("CityId", engineeringManagementEntity.getId());
		List<EngineeringManagementEntity> engineeringManagementEntityInfo = engineeringManagementService.getCountyInfo(map);
		if(engineeringManagementEntityInfo.size()>0) {
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.SUBORDINATEEXISTENCE);
			return info;
		}
		int res = engineeringManagementService.deleteCityInfo(engineeringManagementEntity);
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
	
	
	// 删除县
	@RequestMapping("/deleteCountyInfo.action")
	@ResponseBody
	public ResponseInfo deleteCountyInfo(@RequestBody EngineeringManagementEntity engineeringManagementEntity)throws IOException{
		ResponseInfo info = new ResponseInfo();
		Map<String, Object> map = new HashMap<>();
		map.put("CityId", engineeringManagementEntity.getId());
		List<EngineeringManagementEntity> engineeringManagementEntityInfo = engineeringManagementService.getEngineeringManagementList(map);
		if(engineeringManagementEntityInfo.size()>0) {
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.SUBORDINATEEXISTENCE);
			return info;
		}
		int res = engineeringManagementService.deleteCountyInfo(engineeringManagementEntity);
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
