package com.blindSample.dao;

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
	
	public List<Map<String, Object>> getCollection(Map<String, Object> map);
	
	public List<Map<String, Object>> getCollectionSubtable(Map<String, Object> map);
	
	public List<Map<String, Object>> getChart(Map<String, Object> map);
}
