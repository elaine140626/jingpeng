package com.testRoom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface TestInfoDao{
	List<Map<String, Object>> getTestRoom(Map<String, Object> map);
	
	List<Map<String, Object>> getIndexSummary(Map<String, Object> map);

	List<Map<String, Object>> getTestSummary(Map<String, Object> map);
	
	List<Map<String, Object>> getTestSummaryDetailed(Map<String, Object> map);
	
	public List<Map<String, Object>> getTestSummaryDetailedNumber(Map<String, Object> map);
	
	public List<Map<String, Object>> getChart(Map<String, Object> map);

	List<Map<String, Object>> getConcreteChartData(Map<String, Object> map);

	List<Map<String, Object>> getMixtureChartData(Map<String, Object> map);

	List<Map<String, Object>> getConcreteStrengthChartData(Map<String, Object> params);

	List<Map<String, Object>> queryAsphaltTypeCombobox(HashMap<String, Object> map);

	List<Map<String, Object>> queryAsphaltGradeCombobox(HashMap<String, Object> map);

	List<Map<String, Object>> queryMixtureTypeCombobox(HashMap<String, Object> map);

	List<Map<String, Object>> queryGradationTypeCombobox(HashMap<String, Object> map);

	List<Map<String, Object>> queryAllChart(HashMap<String, Object> map);

	Integer updateScreenDisplay(Map<String, Object> map);

	Integer deleteScreenDisplay();
}
