package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.MixStation.model.ApshaltMixProportionEntity;
import com.MixStation.model.AsphaltGradDataAnalysis;
import com.MixStation.model.AsphaltPropDataAnalysisEntity;
import com.MixStation.model.PlatformAsphaltDataEntity;
import com.MixStation.model.PlatformAsphaltMaterialsConsumptionEntity;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.AsphaltPropDataAnalysis;

@Repository
public interface PlatformAsphaltMixProportionDao{
	List<ApshaltMixProportionEntity> getList(Map<String, Object> map);
	
	int insertPlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity);
	
	int updatePlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity);
	
	int delPlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity);
	
	int update(ApshaltMixProportionEntity apshaltMixProportionEntity);
}
