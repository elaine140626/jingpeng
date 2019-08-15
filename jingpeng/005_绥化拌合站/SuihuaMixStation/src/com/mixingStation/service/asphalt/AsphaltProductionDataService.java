package com.mixingStation.service.asphalt;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.asphalt.Asph_TargetPropDetailed;
import com.mixingStation.model.asphalt.AsphaltProductionData;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.AsphaltPropDataAnalysis;



public interface AsphaltProductionDataService {
	
	
	//生产数据列表
	DataTablesResponseInfo getProductionList(Map<String, Object> map);

	List<AsphaltProductionData> getAsphaltProductionDataByID(AsphaltProductionPlan asphaltProductionPlan) ;
	
	List<AsphaltPropDataAnalysis> getAsphaltPropDataAnalysis(AsphaltProductionPlan asphaltProductionPlan) ;

	List<Map<String, Object>> asphaltGradDataAnalysis(AsphaltProductionPlan asphaltProductionPlan) ;

	List<Asph_TargetPropDetailed> getMaterialConsumption(AsphaltProductionData asphaltProductionData) ;

}
