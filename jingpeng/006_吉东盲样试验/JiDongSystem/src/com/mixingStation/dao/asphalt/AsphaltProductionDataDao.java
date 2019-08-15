package com.mixingStation.dao.asphalt;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.asphalt.Asph_TargetPropDetailed;
import com.mixingStation.model.asphalt.AsphaltGradDataAnalysis;
import com.mixingStation.model.asphalt.AsphaltProductionData;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.AsphaltPropDataAnalysis;


public interface AsphaltProductionDataDao {

	List<AsphaltProductionData> getProductionList(Map<String, Object> map);

	List<AsphaltProductionPlan> getProductionPlanListByPlanNo(Map<String, Object> map);
	
	List<AsphaltPropDataAnalysis> getAsphaltPropDataAnalysis(AsphaltProductionPlan asphaltProductionPlan) ;
	
	List<Asph_TargetPropDetailed> getMaterialConsumption(AsphaltProductionData asphaltProductionData) ;
	
	List<AsphaltGradDataAnalysis> asphaltGradDataAnalysis(AsphaltProductionPlan asphaltProductionPlan) ;
}
