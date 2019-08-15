package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.MixStation.model.PlatformAsphaltWarningDeviationEntity;

@Repository
public interface PlatformAsphaltWarningDeviationDao{
	// 查询沥青预警偏差
	List<PlatformAsphaltWarningDeviationEntity> getPlatformAsphaltWarningDeviation(Map<String, Object> map);
	
	// 插入沥青阀值设定
	int insertPlatformAsphaltWarningDeviation(PlatformAsphaltWarningDeviationEntity platformAsphaltWarningDeviationEntity);
}
