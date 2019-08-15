package com.mixingStation.service.cement;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.cement.CementConsPropDetailed;
import com.mixingStation.model.cement.CementTheoPropDetailed;
import com.mixingStation.model.cement.CementTheoryProportion;
import com.mixingStation.model.materialInfo.MaterialInfo;

public interface CementTheoryProportionService {
	
	//查询所有理论配比信息
	DataTablesResponseInfo getAllCementTheoryProportion(Map<String,Object> map);
	
	//获取产品名称
	List<MaterialInfo> getMaterialNameList(Map<String,Object> map);
	
	//获取产品型号
	List<MaterialInfo> getMaterialModelList(Map<String,Object> map);
	
	//新增理论配比信息
	int addCementTheoryProportion(CementTheoryProportion cementTheoryProportion,List<CementTheoPropDetailed> list);
	
	//通过id查询理论配比信息
	List<CementTheoryProportion> getCementTheoryProportionById(Map<String,Object> map);
	
	//通过理论配比信息id查询明细
	List<CementTheoPropDetailed> getCementTheoPropDetailedById(Map<String,Object> map);
	
	//修改理论配比信息
	int updateCementTheoryProportion(CementTheoryProportion cementTheoryProportion,List<CementTheoPropDetailed> list);
	
	//删除修改理论配比信息
	int deleteCementTheoryProportionById(Map<String,Object> map);
	
	//通过施工配比编号获取所有的物料id
	List<Map<String, Object>>  getAllMaterials_id(Map<String, Object> map);

	//材料名称
	List<Map<String, Object>>  getYclList(CementConsPropDetailed cement_ConsPropDetailed);

	//材料类型
	List<Map<String, Object>>  getYclModelList(Map<String, Object> map);

	
}
