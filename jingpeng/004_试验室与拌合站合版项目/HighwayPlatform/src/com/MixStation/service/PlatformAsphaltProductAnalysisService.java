package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.ProductAnalysisEntity;

public interface PlatformAsphaltProductAnalysisService {
	// 产能分析 沥青原材料取得
	List<ProductAnalysisEntity> getAsphaltMaterialInfo(Map<String, Object> map);
	
	// 产能分析 echar
	List<ProductAnalysisEntity> getAsphaltProductAnalysisList(Map<String, Object> map);
	
	// 根据组织机构id查name
	String getOrgNameById(Map<String, Object> map);
	
	//油石比下拉类表
	List<Map<String,Object>> getMaterialList(Map<String, Object> map);
	
	//油石比echar
	List<Map<String,Object>> getAggregateEchar(Map<String, Object> map);
}
