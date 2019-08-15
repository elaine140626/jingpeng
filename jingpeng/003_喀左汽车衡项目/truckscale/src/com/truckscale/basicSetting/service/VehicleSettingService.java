package com.truckscale.basicSetting.service;

import java.util.List;
import java.util.Map;

import com.truckscale.basicSetting.model.CarInfo;
import com.truckscale.basicSetting.model.TransportCompany;

/**
 * 
 * @Title VehicleSettingService(车辆service)
 * @author Administrator
 * @date 2019年4月10日
 */
public interface VehicleSettingService {
	
	//查询所有车辆信息
	List<CarInfo> getAllCarInfo(Map<String,Object> map);
	
	//查询运输单位
	List<TransportCompany> getTransportCompanyList(Map<String,Object> map);
	
	//车辆牌号去重
	List<CarInfo> getCarInfo(Map<String,Object> map);
	
	//添加车辆信息
	int addCarInfo(CarInfo carInfo);
	
	//修改车辆信息
	int updateCarInfo(CarInfo carInfo);
	
	//删除车辆信息
	int deleteCarInfo(CarInfo carInfo);
	
	//车辆编号自增
	String getCarCount(String type);
	
	//判断车辆信息是否被关联
	List<Map<String,Object>> getWeighingQueryEntityInfo(Map<String,Object> map);
}
