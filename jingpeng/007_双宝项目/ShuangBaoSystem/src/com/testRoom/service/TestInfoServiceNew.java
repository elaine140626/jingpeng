package com.testRoom.service;

import java.util.List;
import java.util.Map;

public interface TestInfoServiceNew {

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
}
