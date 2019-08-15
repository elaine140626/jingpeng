package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformCementStableWarningDeviationDao;
import com.MixStation.model.PlatformCementStableWarningDeviationEntity;
import com.MixStation.service.PlatformCementStableWarningDeviationService;
@Service
@Transactional
public class PlatformCementStableWarningDeviationServiceImpl implements PlatformCementStableWarningDeviationService {
	@Autowired
	private PlatformCementStableWarningDeviationDao platformCementStableWarningDeviationDao;
	
	// 查询水泥稳定土预警偏差
	@Override
	public List<PlatformCementStableWarningDeviationEntity> getPlatformCementStableWarningDeviation(
			Map<String, Object> map) {
		return platformCementStableWarningDeviationDao.getPlatformCementStableWarningDeviation(map);
	}

	// 插入水泥稳定土阀值设定
	@Override
	public int insertPlatformCementStableWarningDeviation(
			PlatformCementStableWarningDeviationEntity platformCementStableWarningDeviationEntity) {
		return platformCementStableWarningDeviationDao.insertPlatformCementStableWarningDeviation(platformCementStableWarningDeviationEntity);
	}

}
