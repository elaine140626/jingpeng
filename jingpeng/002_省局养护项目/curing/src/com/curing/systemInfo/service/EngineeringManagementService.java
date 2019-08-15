package com.curing.systemInfo.service;

import java.util.List;
import java.util.Map;

import com.curing.systemInfo.model.EngineeringManagementEntity;

public interface EngineeringManagementService {

	//项目集合
	List<EngineeringManagementEntity> getEngineeringManagementList(Map<String,Object> map);
	
	//市集合
	List<EngineeringManagementEntity> getCityInfoList(Map<String,Object> map);
	
	//县集合
	List<EngineeringManagementEntity> getCountyInfo(Map<String,Object> map);
	
	//新增项目
	int insertEntryName(EngineeringManagementEntity engineeringManagementEntity);
	
	//新增市
	int insertCityInfo(EngineeringManagementEntity engineeringManagementEntity);
	
	//新增县
	int insertCountyInfo(EngineeringManagementEntity engineeringManagementEntity);
	
	//修改项目
	int updateEntryName(EngineeringManagementEntity engineeringManagementEntity);
	
	//修改增市
	int updateCityInfo(EngineeringManagementEntity engineeringManagementEntity);
	
	//修改增县
	int updateCountyInfo(EngineeringManagementEntity engineeringManagementEntity);
	
	//删除项目
	int deleteEntryName(EngineeringManagementEntity engineeringManagementEntity);
	
	//删除增市
	int deleteCityInfo(EngineeringManagementEntity engineeringManagementEntity);
	
	//删除增县
	int deleteCountyInfo(EngineeringManagementEntity engineeringManagementEntity);
	
}
