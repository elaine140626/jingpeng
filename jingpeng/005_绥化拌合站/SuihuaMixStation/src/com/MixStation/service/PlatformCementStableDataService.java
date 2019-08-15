package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.CementStablePropDataAnalysisEntity;
import com.MixStation.model.PlatformCementStableDataEntity;

public interface PlatformCementStableDataService {
	// 水泥稳定土拌合站树
	List<Map<String,Object>> getOrgId(Map<String, Object> map);
		
	// 查询水泥稳定土生产数据列表
	List<PlatformCementStableDataEntity> getPlatformCementStableData(Map<String, Object> map);
	
	// 水泥稳定土采集数据明细
	List<CementStablePropDataAnalysisEntity> getCementStablePropDataAnalysis(Map<String, Object> map);
}
