package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.PlatformAsphaltWarningDeviationEntity;

public interface PlatformAsphaltWarningDeviationService {
	// 查询沥青预警偏差
	List<PlatformAsphaltWarningDeviationEntity> getPlatformAsphaltWarningDeviation(Map<String, Object> map);
	
	// 插入沥青阀值设定
	int insertPlatformAsphaltWarningDeviation(PlatformAsphaltWarningDeviationEntity platformAsphaltWarningDeviationEntity);
}
