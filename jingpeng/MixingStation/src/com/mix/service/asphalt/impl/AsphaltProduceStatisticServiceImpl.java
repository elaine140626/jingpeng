package com.mix.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mix.dao.asphalt.AsphaltProduceStatisticDao;
import com.mix.service.asphalt.AsphaltProduceStatisticService;

@Service
public class AsphaltProduceStatisticServiceImpl implements AsphaltProduceStatisticService {
	@Autowired
	private AsphaltProduceStatisticDao asphaltProduceStatisticDao;

	public List<Map<String, Object>> getAsphaltProduceStatistic(Map map){
			return asphaltProduceStatisticDao.getAsphaltProduceStatistic(map);
	}

	public List<Map<String, Integer>> getBar(Map<String, Object> map) {
			return asphaltProduceStatisticDao.getBar(map);
	}
	
}
