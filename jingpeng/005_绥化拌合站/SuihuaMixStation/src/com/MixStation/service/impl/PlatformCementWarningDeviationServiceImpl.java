package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformCementWarningDeviationDao;
import com.MixStation.model.PlatformCementWarningDeviationEntity;
import com.MixStation.service.PlatformCementWarningDeviationService;

@Service
@Transactional
public class PlatformCementWarningDeviationServiceImpl implements PlatformCementWarningDeviationService{
	@Autowired
	private PlatformCementWarningDeviationDao platformCementWarningDeviationDao;
	
	// 查询沥青预警偏差
	public List<PlatformCementWarningDeviationEntity> getPlatformCementWarningDeviation(Map<String, Object> map){
		return platformCementWarningDeviationDao.getPlatformCementWarningDeviation(map);
	}
	
	// 插入沥青阀值设定
	public int insertPlatformCementWarningDeviation(PlatformCementWarningDeviationEntity platformCementWarningDeviationEntity) {
		return platformCementWarningDeviationDao.insertPlatformCementWarningDeviation(platformCementWarningDeviationEntity);
	}
	
}
