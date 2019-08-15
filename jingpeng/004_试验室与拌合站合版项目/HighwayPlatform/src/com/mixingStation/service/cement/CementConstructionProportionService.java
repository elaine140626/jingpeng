package com.mixingStation.service.cement;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.cement.CementConsPropDetailed;
import com.mixingStation.model.cement.CementConstructionProportion;
import com.mixingStation.model.cement.CementTheoPropDetailed;
import com.mixingStation.model.cement.CementTheoryProportion;
import com.mixingStation.model.materialInfo.MaterialInfo;

public interface CementConstructionProportionService {
	
	//查询施工配比信息列表
	List<CementConstructionProportion> getAllCementConstructionProportion(Map<String,Object> map);

	//获取产品名称
	List<MaterialInfo> getMaterialNameList(Map<String,Object> map);
	
	//获取产品型号
	List<MaterialInfo> getMaterialModelList(Map<String,Object> map);
	
	//查询理论配比编号
	List<CementTheoryProportion> getCementTheoryProportion(Map<String,Object> map);
	
	//通过理论配比信息id查询明细
	List<CementTheoPropDetailed> getCementTheoPropDetailedById(Map<String,Object> map);

	//新增施工配比
	int addCementConstructionProportion(CementConstructionProportion cementConstructionProportion,List<CementConsPropDetailed> list);
	
	//通过id查询施工配比信息
	List<CementConstructionProportion> getCementConstructionProportionById(Map<String,Object> map);
	
	//通过id查询施工配比信息明细
	List<CementConsPropDetailed> getCementConsPropDetailedById(Map<String,Object> map);

	//修改施工配比
	int updateCementConstructionProportion(CementConstructionProportion cementConstructionProportion,List<CementConsPropDetailed> list);

	//删除修改施工配比信息
	int deleteCementConstructionProportionById(Map<String,Object> map);
}
