package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingpeng.dao.Cement_ProductionDataDao;
import com.jingpeng.model.V_Cement_ProductionData;
import com.jingpeng.service.Cement_ProductionDataService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class Cement_ProductionDataServiceImpl implements Cement_ProductionDataService{

	
	@Autowired
	Cement_ProductionDataDao cement_ProductionDataService;
	
	public List<V_Cement_ProductionData> getCement_ProductionDatas(HashMap<String,Object> map) throws BusinessException {
		try {
			return 	cement_ProductionDataService.getcement_ProductionDatas(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
	}
	}

	public List<V_Cement_ProductionData> getcement_ProductionStatistics(HashMap<String,Object> map)throws BusinessException {
		try {
			return 	cement_ProductionDataService.getcement_ProductionStatistics(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
	}
	}
}
