package com.MixStation.service;

import java.util.List;
import java.util.Map;

import com.MixStation.model.ApshaltMixProportionEntity;
import com.MixStation.model.AsphaltGradDetailedEntity;
import com.MixStation.model.AsphaltGradingInfoEntity;
import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.model.MeshSizeInfoEntity;

public interface AsphaltGranularCompositionInfoService {

	//查询工区下启用的配合比
	List<ApshaltMixProportionEntity> getApshaltMixProportion(Map<String,Object> map);
	
	//查询工区下启用过的配合比
	List<ApshaltMixProportionEntity> getApshaltMixProportionUsed(Map<String,Object> map);
	
	//查询所有筛孔
	List<MeshSizeInfoEntity> getAllMeshSizeInfo(Map<String,Object> map);
	
	//查询所有级配信息
	DataTablesResponseInfo getAllAsphaltGradingInfo(Map<String,Object> map);
	
	//新增级配
	int addAsphaltGradingInfo(AsphaltGradingInfoEntity param1,List<AsphaltGradDetailedEntity> param2);
	
	//通过级配id查询级配信息
	List<AsphaltGradingInfoEntity> getAsphaltGradingInfoById(Map<String,Object> map);
	
	//通过级配id查询筛孔通过率信息
	List<AsphaltGradDetailedEntity> getAsphaltGradDetailedByGradId(Map<String,Object> map);
	
	//修改级配
	int updateAsphaltGradingInfo(AsphaltGradingInfoEntity param1,List<AsphaltGradDetailedEntity> param2);
	
	//删除级配相关信息
	int deleteAsphaltGradingInfo(Map<String,Object> map);
	
	//启用级配
	int enableAsphaltGradingInfo(Map<String,Object> map);
	
	//级配编号去重
	String selectGradeCode(String gradeCode,String id,String orgId);
	
}
