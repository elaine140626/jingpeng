package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.kdt.base.exception.BusinessException;

public interface AsphaltProduceStatisticService {
	
	List<Map<String,Object>> getAsphaltProduceStatistic(Map map) throws BusinessException;

	List<Map<String, Integer>> getBar(Map<String, Object> map) throws BusinessException;
	
}
