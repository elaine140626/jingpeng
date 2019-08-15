package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.MixStation.model.PlatformCementWarningDeviationEntity;

@Repository
public interface PlatformCementWarningDeviationDao{
	// 查询沥青预警偏差
	List<PlatformCementWarningDeviationEntity> getPlatformCementWarningDeviation(Map<String, Object> map);
	
	// 插入沥青阀值设定
	int insertPlatformCementWarningDeviation(PlatformCementWarningDeviationEntity platformCementWarningDeviationEntity);
}
