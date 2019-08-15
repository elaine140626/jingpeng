package com.mixingStation.dao.asphalt;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.asphalt.AsphaltProdProportion;
import com.mixingStation.model.materialInfo.MaterialInfo;

public interface AsphaltProdProportionDao {

	//查询沥青生产配合比list
	List<AsphaltProdProportion> getAllAsphaltProdProportion(Map<String,Object> map);
	
	//获取产品名称
	List<MaterialInfo> getMaterialNameList(Map<String,Object> map);
	
	//获取产品型号
	List<MaterialInfo> getMaterialModelList(Map<String,Object> map);
	
	//获取目标配合比编号
	List<Map<String,Object>> getAsphTargetProCodeByProductId(Map<String,Object> map);
	
	//获取级配编号
	List<Map<String,Object>> getGradeCodeByIdByProductId(Map<String,Object> map);
	
	//添加沥青生产配合比
	int addAsphaltProdProportion(AsphaltProdProportion asphaltProdProportion);

	//修改沥青生产配合比
	int updateAsphaltProdProportion(AsphaltProdProportion asphaltProdProportion);
	
	//通过沥青生产配合比Id查询 
	List<Map<String,Object>> getAsphaltProdProportionById(Map<String,Object> map);
	
	//删除沥青生产配合比
	int deleteAsphaltProdProportion(Map<String,Object> map);
	
	//查询生产计划是否调用沥青生产配合比
	List<Map<String,Object>> getPlanByProdId(Map<String,Object> map);
}
