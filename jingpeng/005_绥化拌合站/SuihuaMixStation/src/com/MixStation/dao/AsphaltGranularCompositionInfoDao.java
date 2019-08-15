package com.MixStation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.MixStation.model.ApshaltMixProportionEntity;
import com.MixStation.model.AsphaltGradDetailedEntity;
import com.MixStation.model.AsphaltGradingInfoEntity;
import com.MixStation.model.MeshSizeInfoEntity;

public interface AsphaltGranularCompositionInfoDao {
	
	//查询工区下启用的配合比
	List<ApshaltMixProportionEntity> getApshaltMixProportion(Map<String,Object> map);
	
	//查询工区下启用过的配合比
	List<ApshaltMixProportionEntity> getApshaltMixProportionUsed(Map<String,Object> map);
	
	//查询所有筛孔
	List<MeshSizeInfoEntity> getAllMeshSizeInfo(Map<String,Object> map);
	
	//查询所有级配信息
	List<AsphaltGradingInfoEntity> getAllAsphaltGradingInfo(Map<String,Object> map);
	
	//新增级配
	int addAsphaltGradingInfo(AsphaltGradingInfoEntity param);
	
	//获取刚增加级配的id
	int getAsphaltGradingInfoId(String operator);
	
	//新增筛孔通过率
	int insertAsphaltGradDetailed(AsphaltGradDetailedEntity param);
	
	//通过级配id查询级配信息
	List<AsphaltGradingInfoEntity> getAsphaltGradingInfoById(Map<String,Object> map);
	
	//通过级配id查询筛孔通过率信息
	List<AsphaltGradDetailedEntity> getAsphaltGradDetailedByGradId(Map<String,Object> map);
	
	//修改级配
	int upateAsphaltGradingInfo(AsphaltGradingInfoEntity param);
	
	//删除筛孔通过率(物理删除)
	int deleteAsphaltGradDetailed(String id);
	
	//删除通过率（逻辑删）
	int deleteAsphaltGradDetailedInfo(Map<String,Object> map);
	
	//删除级配信息
	int deleteAsphaltGradingInfo(Map<String,Object> map);
	
	//启用级配
	int enableAsphaltGradingInfo(Map<String,Object> map);
	
	//同一配比下只能有一个启动的级配
	int updateOtherEnable(Map<String,Object> map);
	
	//级配编号去重
	String selectGradeCode(@Param("gradeCode") String gradeCode,@Param("id") String id,@Param("orgId") String orgId);
}
