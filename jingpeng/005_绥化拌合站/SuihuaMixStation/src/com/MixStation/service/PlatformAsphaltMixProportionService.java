package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.ApshaltMixProportionEntity;

public interface PlatformAsphaltMixProportionService {
	
	List<ApshaltMixProportionEntity> getList(Map<String, Object> map);
	
	int insertPlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity);
	
	int updatePlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity);
	
	int delPlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity);
	
	int update(ApshaltMixProportionEntity apshaltMixProportionEntity);
}
