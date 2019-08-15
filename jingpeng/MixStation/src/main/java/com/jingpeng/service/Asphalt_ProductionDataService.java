package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.AsphaltGrad_DataAnalysis;
import com.jingpeng.model.AsphaltProp_DataAnalysis;
import com.jingpeng.model.Asphalt_ProductionDTO;
import com.kdt.base.exception.BusinessException;

public interface Asphalt_ProductionDataService {

	List<Asphalt_ProductionDTO> getAsphaltProductionData(Map<String, Object> map) throws BusinessException;
	
	List<Map<String,String>> getAsphaltProductionDataNew(Map<String, Object> map) throws BusinessException;

	List<AsphaltProp_DataAnalysis> getAsphaltPropDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) throws BusinessException;

	List<AsphaltGrad_DataAnalysis> asphaltGradDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) throws BusinessException;

	List<Asphalt_ProductionDTO> getAsphaltProductionDataByID(Asphalt_ProductionDTO asphalt_ProductionDTO) throws BusinessException;

	List<Map<String, Object>> getMaterialConsumption(Asphalt_ProductionDTO asphalt_ProductionDTO) throws BusinessException;

}
