package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.MixStation.model.AsphaltPropDataAnalysisEntity;
import com.MixStation.model.DeviationAsphaltEntity;

@Repository
public interface AsphaltDataDao{
	// 沥青数据结果取得
	Map<String, Object> getAsphaltData (Map<String, Object> map);
	
	// 沥青数据异常列表App
	List<Map<String, Object>> getAppAsphaltData (Map<String, Object> map);
	
	// 预警偏差取得
	DeviationAsphaltEntity getDeviationAsphalt(Map<String, Object> map);
	
	// 沥青目标配比取得
	AsphaltPropDataAnalysisEntity getAsphaltPropDataAnalysis(@Param("id")String id,@Param("data_Type")String data_Type);
}
