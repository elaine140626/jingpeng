package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.PlatformCementStableWarningDeviationEntity;

public interface PlatformCementStableWarningDeviationService {
	// 查询水泥稳定土预警偏差
	List<PlatformCementStableWarningDeviationEntity> getPlatformCementStableWarningDeviation(Map<String, Object> map);
		
	// 插入水泥稳定土阀值设定
	int insertPlatformCementStableWarningDeviation(PlatformCementStableWarningDeviationEntity platformCementStableWarningDeviationEntity);
}
