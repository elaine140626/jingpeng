package com.mixingStation.service.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.EquipmentInfo;
public interface EquipmentService {
	//查询所有拌和设备信息
	DataTablesResponseInfo getAllEquipmentInfo(Map<String,Object> map);
	
	//添加拌和设备信息
	ResponseInfo addEquipmentInfo(HttpServletRequest request,Map<String,Object> map);
	
	//修改拌和设备信息
	ResponseInfo updateEquipmentInfo(HttpServletRequest request,Map<String,Object> map);
	
	//删除拌和设备信息
	ResponseInfo deleteEquipmentInfo(Map<String,Object> map);
	
	//通过id查询拌和设备信息
	List<EquipmentInfo> getEquipmentInfoById(Map<String,Object> map);
}
