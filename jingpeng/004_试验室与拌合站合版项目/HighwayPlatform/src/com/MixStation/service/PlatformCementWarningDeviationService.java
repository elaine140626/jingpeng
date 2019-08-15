package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.PlatformCementWarningDeviationEntity;

public interface PlatformCementWarningDeviationService {
	// 查询沥青预警偏差
	List<PlatformCementWarningDeviationEntity> getPlatformCementWarningDeviation(Map<String, Object> map);
	
	// 插入沥青阀值设定
	int insertPlatformCementWarningDeviation(PlatformCementWarningDeviationEntity platformCementWarningDeviationEntity);
}
