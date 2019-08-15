package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.AsphaltDataDao;
import com.MixStation.model.AsphaltPropDataAnalysisEntity;
import com.MixStation.model.DeviationAsphaltEntity;
import com.MixStation.service.AsphaltDataService;

@Service
@Transactional
public class AsphaltDataServiceImpl implements AsphaltDataService{
	@Autowired
	private AsphaltDataDao asphaltDataDao;

	// 沥青数据结果取得
	public Map<String, Object> getAsphaltData (Map<String, Object> map){
		return asphaltDataDao.getAsphaltData(map);
	}
		
	// 沥青数据异常列表App
	public List<Map<String, Object>> getAppAsphaltData (Map<String, Object> map){
		return  asphaltDataDao.getAppAsphaltData(map);
	}
	
	// 预警偏差取得
	public DeviationAsphaltEntity getDeviationAsphalt(Map<String, Object> map){
		return asphaltDataDao.getDeviationAsphalt(map);
	}
	
	// 沥青目标配比取得
	public AsphaltPropDataAnalysisEntity getAsphaltPropDataAnalysis(String id,String data_Type){
		return asphaltDataDao.getAsphaltPropDataAnalysis(id,data_Type);
	}
}
