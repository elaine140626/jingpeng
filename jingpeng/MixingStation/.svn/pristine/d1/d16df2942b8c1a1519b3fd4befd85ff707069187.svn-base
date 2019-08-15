package com.mix.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.asphalt.Asphalt_ProductionDataDao;
import com.mix.model.AsphaltGrad_DataAnalysis;
import com.mix.model.AsphaltProp_DataAnalysis;
import com.mix.model.Asphalt_ProductionDTO;
import com.mix.service.asphalt.Asphalt_ProductionDataService;

@Service
public class Asphalt_ProductionDataServiceImpl implements Asphalt_ProductionDataService{
	@Autowired
	private Asphalt_ProductionDataDao asphalt_ProductionDataDao;

	public List<Asphalt_ProductionDTO> getAsphaltProductionData(Map<String, Object> map)  {
			return asphalt_ProductionDataDao.getAsphaltProductionData(map);
	}

	public List<AsphaltProp_DataAnalysis> getAsphaltPropDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO)  {
			return asphalt_ProductionDataDao.getAsphaltPropDataAnalysis(asphalt_ProductionDTO);
	}

	public List<AsphaltGrad_DataAnalysis> asphaltGradDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO)  {
			return asphalt_ProductionDataDao.asphaltGradDataAnalysis(asphalt_ProductionDTO);
	}

	public List<Asphalt_ProductionDTO> getAsphaltProductionDataByID(Asphalt_ProductionDTO asphalt_ProductionDTO){
			return asphalt_ProductionDataDao.getAsphaltProductionDataByID(asphalt_ProductionDTO);
	}

	public List<Map<String, Object>> getMaterialConsumption(Asphalt_ProductionDTO asphalt_ProductionDTO){
			return asphalt_ProductionDataDao.getMaterialConsumption(asphalt_ProductionDTO);
	}

	public List<Map<String, String>> getAsphaltProductionDataNew(Map<String, Object> map)  {
			return asphalt_ProductionDataDao.getAsphaltProductionDataNew(map);
	}
	
	
	
}
