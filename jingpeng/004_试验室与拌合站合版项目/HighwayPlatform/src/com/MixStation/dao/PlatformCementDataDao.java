package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.MixStation.model.CementGradDataAnalysis;
import com.MixStation.model.CementPropDataAnalysisEntity;
import com.MixStation.model.PlatformCementMaterialsConsumptionEntity;
import com.MixStation.model.PlatformCementDataEntity;

@Repository
public interface PlatformCementDataDao{
	// 沥青拌合站树
	List<Map<String,Object>> getOrgId(Map<String, Object> map);
	
	// 查询生产数据列表
	List<PlatformCementDataEntity> getPlatformCementData(Map<String, Object> map);
	
	// 采集数据明细
	List<CementPropDataAnalysisEntity> getCementPropDataAnalysis(Map<String, Object> map);
	
	// 原材料消耗
	List<PlatformCementMaterialsConsumptionEntity> getVMaterialConsumption(Map<String, Object> map);
	
	//查询index沥青拌合站数据
	List<Map<String, Object>> getIndexLq(Map<String, Object> map);
	
	//查询index沥青拌合站数据
	List<Map<String, Object>> getIndexSn(Map<String, Object> map);
		
	//查询index沥青拌合站数据
	List<Map<String, Object>> getIndexSw(Map<String, Object> map);
		
}
