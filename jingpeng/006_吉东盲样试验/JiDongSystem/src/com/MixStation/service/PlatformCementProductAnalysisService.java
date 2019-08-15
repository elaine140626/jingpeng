package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.ProductAnalysisEntity;

public interface PlatformCementProductAnalysisService {
	// 产能分析 水泥原材料取得
	List<ProductAnalysisEntity> getCementMaterialInfo(Map<String, Object> map);
	
	// 产能分析 echar
	List<ProductAnalysisEntity> getCementProductAnalysisList(Map<String, Object> map);	
}
