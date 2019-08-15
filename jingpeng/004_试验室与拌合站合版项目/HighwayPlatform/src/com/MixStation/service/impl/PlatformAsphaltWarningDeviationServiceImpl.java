package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformAsphaltWarningDeviationDao;
import com.MixStation.model.PlatformAsphaltWarningDeviationEntity;
import com.MixStation.service.PlatformAsphaltWarningDeviationService;

@Service
@Transactional
public class PlatformAsphaltWarningDeviationServiceImpl implements PlatformAsphaltWarningDeviationService{
	@Autowired
	private PlatformAsphaltWarningDeviationDao platformAsphaltWarningDeviationDao;
	
	// 查询沥青预警偏差
	public List<PlatformAsphaltWarningDeviationEntity> getPlatformAsphaltWarningDeviation(Map<String, Object> map){
		return platformAsphaltWarningDeviationDao.getPlatformAsphaltWarningDeviation(map);
	}
	
	// 插入沥青阀值设定
	public int insertPlatformAsphaltWarningDeviation(PlatformAsphaltWarningDeviationEntity platformAsphaltWarningDeviationEntity) {
		return platformAsphaltWarningDeviationDao.insertPlatformAsphaltWarningDeviation(platformAsphaltWarningDeviationEntity);
	}
	
}
