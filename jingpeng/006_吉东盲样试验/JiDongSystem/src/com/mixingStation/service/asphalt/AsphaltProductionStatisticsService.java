package com.mixingStation.service.asphalt;

import java.util.List;
import java.util.Map;

public interface AsphaltProductionStatisticsService {
	//查询所有的生产统计数据
	List<Map<String,Object>> getAsphaltProductionStatistics(Map<String,Object> map);
	
	//树状图
	List<Map<String, Integer>> getBar(Map<String, Object> map);
}
