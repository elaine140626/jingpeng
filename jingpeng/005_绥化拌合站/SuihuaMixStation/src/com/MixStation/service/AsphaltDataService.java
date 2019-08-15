package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.AsphaltPropDataAnalysisEntity;
import com.MixStation.model.DeviationAsphaltEntity;

public interface AsphaltDataService {
	// 沥青数据结果取得
	Map<String, Object> getAsphaltData (Map<String, Object> map);
	
	// 沥青数据异常列表App
	List<Map<String, Object>> getAppAsphaltData (Map<String, Object> map);
	
	// 预警偏差取得
	DeviationAsphaltEntity getDeviationAsphalt(Map<String, Object> map);
	
	// 沥青目标配比取得
	AsphaltPropDataAnalysisEntity getAsphaltPropDataAnalysis(String id,String data_Type);
}
