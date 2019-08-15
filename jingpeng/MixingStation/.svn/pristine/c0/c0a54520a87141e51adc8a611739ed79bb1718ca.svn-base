package com.mix.service.cement.impl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mix.dao.cement.CementProductionDataDao;
import com.mix.model.V_Cement_ProductionData;
import com.mix.service.cement.CementProductionDataService;
@Service
@Transactional
public class CementProductionDataServiceImpl implements CementProductionDataService{

	@Autowired
	private CementProductionDataDao cementProductionDataDao;
	
	//查询水泥的生产统计数据
	@Override
	public List<V_Cement_ProductionData> getcement_ProductionStatistics(HashMap<String, Object> map) {
		return cementProductionDataDao.getcement_ProductionStatistics(map);
	}

	//查询水泥的生产数据
	@Override
	public List<V_Cement_ProductionData> getCement_ProductionDatas(HashMap<String, Object> map) {
		return cementProductionDataDao.getcement_ProductionDatas(map);
	}

}
