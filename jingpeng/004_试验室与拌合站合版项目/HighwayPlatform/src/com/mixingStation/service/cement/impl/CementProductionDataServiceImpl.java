package com.mixingStation.service.cement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.cement.CementProductionDataDao;
import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.cement.CementProductionDatasDetails;
import com.mixingStation.model.cement.Cement_ProductionData;
import com.mixingStation.service.cement.CementProductionDataService;


@Service
@Transactional
public class CementProductionDataServiceImpl implements CementProductionDataService{

	@Autowired
	private CementProductionDataDao cementProductionDataDao;
	
	@Override
	public DataTablesResponseInfo getCement_ProductionDatas(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Cement_ProductionData> cementList = cementProductionDataDao.getcement_ProductionDatas(map);
		info.setData(cementList);
		return info;
	}

	@Override
	public List<CementProductionDatasDetails> getcement_ProductionDatasDetails(Map<String, Object> map) {
		return cementProductionDataDao.getcement_ProductionDatasDetails(map);
	}

}
