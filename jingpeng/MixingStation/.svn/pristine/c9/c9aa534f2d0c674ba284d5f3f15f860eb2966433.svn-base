package com.mix.dao.asphalt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mix.model.AsphaltGrad_DataAnalysis;
import com.mix.model.AsphaltProp_DataAnalysis;
import com.mix.model.Asphalt_ProductionDTO;

@Repository
public interface Asphalt_ProductionDataDao{
	
	List<Asphalt_ProductionDTO> getAsphaltProductionData(Map<String, Object> map) ;
	
	List<Map<String,String>> getAsphaltProductionDataNew(Map<String, Object> map) ;

	List<AsphaltProp_DataAnalysis> getAsphaltPropDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) ;

	List<AsphaltGrad_DataAnalysis> asphaltGradDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) ;

	List<Asphalt_ProductionDTO> getAsphaltProductionDataByID(Asphalt_ProductionDTO asphalt_ProductionDTO) ;

	List<Map<String, Object>> getMaterialConsumption(Asphalt_ProductionDTO asphalt_ProductionDTO) ;
	
	
}
