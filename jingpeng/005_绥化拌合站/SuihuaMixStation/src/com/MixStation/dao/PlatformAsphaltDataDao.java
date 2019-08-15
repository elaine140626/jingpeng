package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.MixStation.model.AsphaltGradDataAnalysis;
import com.MixStation.model.AsphaltPropDataAnalysisEntity;
import com.MixStation.model.PlatformAsphaltDataEntity;
import com.MixStation.model.PlatformAsphaltMaterialsConsumptionEntity;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.AsphaltPropDataAnalysis;

@Repository
public interface PlatformAsphaltDataDao{
	// 沥青拌合站树
	List<Map<String,Object>> getOrgId(Map<String, Object> map);
	
	// 查询生产数据列表
	List<PlatformAsphaltDataEntity> getPlatformAsphaltData(Map<String, Object> map);
	
	// 查询生产数据列表总和
	List<Map<String, Object>> getPlatformAsphaltDataSum(Map<String, Object> map);
	
	// 采集数据明细
	List<AsphaltPropDataAnalysisEntity> getAsphaltPropDataAnalysis(Map<String, Object> map);
	
	// 筛分通过率
	List<AsphaltGradDataAnalysis> getAsphaltGradDataAnalysis(Map<String, Object> map);
	
	// 原材料消耗
	List<PlatformAsphaltMaterialsConsumptionEntity> getVMaterialConsumption(Map<String, Object> map);
	
	//生产预警统计(沥青)
	List<Map<String, Object>> getWarningStatistics(Map<String,Object> map);
	
	//生产预警统计(水泥)
	List<Map<String, Object>> getWarningCementStatistics(Map<String,Object> map);
	
	//拌合机汇总展示(沥青)
	List<Map<String, Object>> getAmalgamatorSummary(Map<String,Object> map);
	
	//拌合机汇总展示(水泥)
	List<Map<String, Object>> getCementAmalgamatorSummary(Map<String,Object> map);

	List<Map<String,Object>> getWarnChartData(Map<String, Object> param);

	List<Map<String, Object>> getEquipmentsData(Map<String, Object> param);

	//查询工区id
	List<Map<String, Object>> getAllOrganization(Map<String, Object> param);
	
	//油石比统计数据
	List<Map<String, Object>> getAsphaltChartData(Map<String, Object> param);


	List<Map<String, Object>> getAsphaltInfo(Map<String, Object> map);

	List<Map<String, Object>> getCementInfo(Map<String, Object> map);

	List<Map<String, Object>> getTotalAsphaltInfo(Map<String, Object> map);

	List<Map<String, Object>> getTotalCementInfo(Map<String, Object> map);
	
	//根据生产数据获取的生产编号查询生产计划 
	List<Map<String,Object>> getProductionPlanListByPlanNo(Map<String, Object> map);
}
