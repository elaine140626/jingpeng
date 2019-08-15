package com.mix.dao.asphalt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface AsphaltProduceStatisticDao{
	
	List<Map<String,Object>> getAsphaltProduceStatistic(Map map);

	List<Map<String, Integer>> getBar(Map<String, Object> map);
}
