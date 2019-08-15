package com.mixingStation.dao.system;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.asphalt.EquipmentInfo;

public interface EquipmentDao {

	//查询所有拌和设备信息
	List<EquipmentInfo> getAllEquipmentInfo(Map<String,Object> map);
	
	//添加拌和设备信息
	int addEquipmentInfo(EquipmentInfo equipmentInfo);
	
	//修改拌和设备信息
	int updateEquipmentInfo(EquipmentInfo equipmentInfo);
	
	//删除拌和设备信息
	int deleteEquipmentInfo(Map<String,Object> map);
}
