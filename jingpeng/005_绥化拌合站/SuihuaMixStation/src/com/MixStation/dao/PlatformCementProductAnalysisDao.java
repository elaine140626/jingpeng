package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.MixStation.model.ProductAnalysisEntity;

@Repository
public interface PlatformCementProductAnalysisDao{
	// 产能分析 水泥原材料取得
	List<ProductAnalysisEntity> getCementMaterialInfo(Map<String, Object> map);
	
	// 产能分析 echar
	List<ProductAnalysisEntity> getCementProductAnalysisList(Map<String, Object> map);	
}
