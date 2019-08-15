package com.mixingStation.dao.asphalt;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Title AsphaltProductionStatisticsDao(生产统计)
 * @author Administrator
 * @date 2019年2月19日
 */
public interface AsphaltProductionStatisticsDao {
	//查询所有的生产统计数据
	List<Map<String,Object>> getAsphaltProductionStatistics(Map<String,Object> map);
	
	//树状图
	List<Map<String, Integer>> getBar(Map<String, Object> map);
}
