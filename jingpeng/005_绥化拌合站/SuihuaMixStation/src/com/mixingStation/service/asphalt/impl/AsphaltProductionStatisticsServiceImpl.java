package com.mixingStation.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.asphalt.AsphaltProductionStatisticsDao;
import com.mixingStation.service.asphalt.AsphaltProductionStatisticsService;
@Service
public class AsphaltProductionStatisticsServiceImpl implements AsphaltProductionStatisticsService {

	@Autowired
	private AsphaltProductionStatisticsDao asphaltProductionStatisticsDao;
	
	@Override
	public List<Map<String, Object>> getAsphaltProductionStatistics(Map<String, Object> map) {
		return asphaltProductionStatisticsDao.getAsphaltProductionStatistics(map);
	}

	@Override
	public List<Map<String, Integer>> getBar(Map<String, Object> map) {
		return asphaltProductionStatisticsDao.getBar(map);
	}

}
