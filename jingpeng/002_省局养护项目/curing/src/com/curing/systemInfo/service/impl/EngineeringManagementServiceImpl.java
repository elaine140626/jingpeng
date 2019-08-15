package com.curing.systemInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.systemInfo.dao.EngineeringManagementDao;
import com.curing.systemInfo.model.EngineeringManagementEntity;
import com.curing.systemInfo.service.EngineeringManagementService;

@Service
@Transactional
public class EngineeringManagementServiceImpl implements EngineeringManagementService {

	@Autowired
	private EngineeringManagementDao engineeringManagementDao;
	
	@Override
	public List<EngineeringManagementEntity> getEngineeringManagementList(
			Map<String,Object> map) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.getEngineeringManagementList(map);
	}

	@Override
	public List<EngineeringManagementEntity> getCityInfoList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.getCityInfoList(map);
	}

	@Override
	public List<EngineeringManagementEntity> getCountyInfo(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.getCountyInfo(map);
	}

	@Override
	public int insertEntryName(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.insertEntryName(engineeringManagementEntity);
	}

	@Override
	public int insertCityInfo(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.insertCityInfo(engineeringManagementEntity);
	}

	@Override
	public int insertCountyInfo(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.insertCountyInfo(engineeringManagementEntity);
	}

	@Override
	public int updateEntryName(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.updateEntryName(engineeringManagementEntity);
	}

	@Override
	public int updateCityInfo(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.updateCityInfo(engineeringManagementEntity);
	}

	@Override
	public int updateCountyInfo(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.updateCountyInfo(engineeringManagementEntity);
	}

	@Override
	public int deleteEntryName(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.deleteEntryName(engineeringManagementEntity);
	}

	@Override
	public int deleteCityInfo(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.deleteCityInfo(engineeringManagementEntity);
	}

	@Override
	public int deleteCountyInfo(EngineeringManagementEntity engineeringManagementEntity) {
		// TODO Auto-generated method stub
		return engineeringManagementDao.deleteCountyInfo(engineeringManagementEntity);
	}

}
