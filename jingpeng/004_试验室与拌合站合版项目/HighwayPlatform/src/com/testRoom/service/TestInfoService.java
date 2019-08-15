package com.testRoom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TestInfoService {

	//查询当前用户权限的实验室
	List<Map<String, Object>> getTestRoomName(Map<String, Object> map);
	//查询index数据
	List<Map<String, Object>> getIndexSummary(Map<String, Object> map);
	//查询试验汇总列表
	List<Map<String, Object>> getTestSummary(Map<String, Object> map);
	//查询试验汇总列表详情
	List<Map<String, Object>> getTestSummaryDetailed(Map<String, Object> map);
	//查询试验汇总列表各个数的详细
	List<Map<String, Object>> getTestSummaryDetailedNumber(Map<String, Object> map);
	//查询折线图
	List<Map<String, Object>> getChart(Map<String, Object> map);
	List<Map<String, Object>> getConcreteChartData(Map<String, Object> params);
	List<Map<String, Object>> getMixtureChartData(Map<String, Object> map);
	List<Map<String, Object>> getConcreteStrengthChartData(Map<String, Object> params);
	List<Map<String, Object>> queryAsphaltTypeCombobox(HashMap<String, Object> map);
	List<Map<String, Object>> queryAsphaltGradeCombobox(HashMap<String, Object> map);
	List<Map<String, Object>> queryMixtureTypeCombobox(HashMap<String, Object> map);
	List<Map<String, Object>> queryGradationTypeCombobox(HashMap<String, Object> map);
	List<Map<String, Object>> queryAllChart(HashMap<String, Object> map);
	Integer updateScreenDisplay(String[] chartArr, String displayType);
}
