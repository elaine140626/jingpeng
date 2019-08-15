package com.mix.service.asphalt;

import java.util.List;
import java.util.Map;

import com.mix.model.AsphaltGrad_DataAnalysis;
import com.mix.model.AsphaltProp_DataAnalysis;
import com.mix.model.Asphalt_ProductionDTO;

public interface Asphalt_ProductionDataService {

	List<Asphalt_ProductionDTO> getAsphaltProductionData(Map<String, Object> map) ;
	
	List<Map<String,String>> getAsphaltProductionDataNew(Map<String, Object> map) ;

	List<AsphaltProp_DataAnalysis> getAsphaltPropDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) ;

	List<AsphaltGrad_DataAnalysis> asphaltGradDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) ;

	List<Asphalt_ProductionDTO> getAsphaltProductionDataByID(Asphalt_ProductionDTO asphalt_ProductionDTO) ;

	List<Map<String, Object>> getMaterialConsumption(Asphalt_ProductionDTO asphalt_ProductionDTO) ;

}
