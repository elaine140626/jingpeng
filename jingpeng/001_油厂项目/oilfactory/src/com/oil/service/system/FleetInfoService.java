package com.oil.service.system;

import java.util.List;
import java.util.Map;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.CarInfo;
import com.oil.model.system.FleetInfo;

public interface FleetInfoService {

	DataTablesResponseInfo getFleetInfo();
	
	List<CarInfo> getCarInfo(FleetInfo fleetInfo);
	
	FleetInfo getFleetInfoById(int id);
	
	int addFleetInfo(FleetInfo fleetInfo);
	
	int addCarInfo(CarInfo carInfo);
	
	int delFleetInfo(FleetInfo fleetInfo);
	
	int delCarInfo(CarInfo carInfo);
	
	int updateFleetInfo(FleetInfo fleetInfo);
	
	int updateCarInfo(CarInfo carInfo);
	
	DataTablesResponseInfo findFleetInfoByName(String fleetName);
	
	ResponseInfo delCarInfoById(int id);
	
	List<Map<String,Object>> getAllCarName(Map<String,Object> map);//车辆删除校验查询
	
	CarInfo getCarInfoById(Map<String,Object> map);//通过车辆id查询车辆
	
	List<CarInfo> getCarInfoByCarNumber(Map<String,Object> map);//通过车辆编号查询车辆
	
	List<Map<String,Object>> getAllCarInfo(Map<String,Object> map);//通过车队id查询车辆信息
}
