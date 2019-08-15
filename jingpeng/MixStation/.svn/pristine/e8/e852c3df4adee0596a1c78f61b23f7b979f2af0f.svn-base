package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingpeng.dao.AsphaltProduceStatisticDao;
import com.jingpeng.service.AsphaltProduceStatisticService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class AsphaltProduceStatisticServiceImpl implements AsphaltProduceStatisticService {
	@Autowired
	private AsphaltProduceStatisticDao asphaltProduceStatisticDao;

	public List<Map<String, Object>> getAsphaltProduceStatistic(Map map) throws BusinessException {
		try {
			return asphaltProduceStatisticDao.getAsphaltProduceStatistic(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Integer>> getBar(Map<String, Object> map) throws BusinessException {
		try {
			return asphaltProduceStatisticDao.getBar(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
}
