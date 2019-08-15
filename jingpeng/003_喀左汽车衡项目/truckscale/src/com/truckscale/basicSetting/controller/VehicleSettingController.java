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
import com.truckscale.basicSetting.service.VehicleSettingService;
import com.truckscale.common.model.DataTablesResponseInfo;
import com.truckscale.common.model.ResponseInfo;
import com.truckscale.common.util.MessageUtil;

@Controller
@RequestMapping("/vehicleSetting")
public class VehicleSettingController {

	@Autowired
	private VehicleSettingService vehicleSettingService;
	
	//查询所有车辆信息
	@RequestMapping("/getAllCarInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getAllCarInfo(@RequestParam Map<String,Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<CarInfo> dataList = vehicleSettingService.getAllCarInfo(map);
		if(dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<CarInfo>();
		}
		dtr.setData(dataList);
		return dtr;
	}
	
	//查询运输单位
	@RequestMapping("/getTransportCompanyList.action")
	@ResponseBody
	public List<TransportCompany> getTransportCompanyList(@RequestParam Map<String,Object> map) {
		return vehicleSettingService.getTransportCompanyList(map);
	}
	
	//车辆牌号去重
	@RequestMapping("/getCarInfo.action")
	@ResponseBody
	public List<CarInfo> getCarInfo(@RequestParam Map<String,Object> map) {
		return vehicleSettingService.getCarInfo(map);
	}
	
	//添加车辆信息
	@RequestMapping("/addCarInfo.action")
	@ResponseBody
	public ResponseInfo addCarInfo(@RequestBody CarInfo carInfo) {
		ResponseInfo info = new ResponseInfo();
		int result = vehicleSettingService.addCarInfo(carInfo);
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
	
	//修改车辆信息
	@RequestMapping("/updateCarInfo.action")
	@ResponseBody
	public ResponseInfo updateCarInfo(@RequestBody CarInfo carInfo) {
		ResponseInfo info = new ResponseInfo();
		int result = vehicleSettingService.updateCarInfo(carInfo);
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
	
	//删除车辆信息
	@RequestMapping("/deleteCarInfo.action")
	@ResponseBody
	public ResponseInfo deleteCarInfo(@RequestBody CarInfo carInfo) {
		ResponseInfo info = new ResponseInfo();
		int result = vehicleSettingService.deleteCarInfo(carInfo);
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
	
	// 车辆编号自增
	@RequestMapping("/getCarCount.action")
	@ResponseBody
	public Map<String,Object> getCarCount(String type) throws IOException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = vehicleSettingService.getCarCount(type);
		resultMap.put("carCount", result);
		return resultMap;		
	}
	
	//判断车辆信息是否被关联
	@RequestMapping("/getWeighingQueryEntityInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getWeighingQueryEntityInfo(@RequestParam Map<String,Object> map) {
		return vehicleSettingService.getWeighingQueryEntityInfo(map);		
	}
}
