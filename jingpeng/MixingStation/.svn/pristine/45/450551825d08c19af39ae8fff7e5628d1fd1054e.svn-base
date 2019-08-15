package com.mix.service.asphalt;

import java.util.List;
import java.util.Map;

import com.mix.model.MaterModel_Info;
import com.mix.model.MaterName_Info;
import com.mix.model.V_MaterialInfo;

public interface MaterialService{
	List<V_MaterialInfo> getMaterialInfo(V_MaterialInfo v_MaterialInfo)  ;

	List<V_MaterialInfo> getMaterialbyNameOrModel(V_MaterialInfo v_MaterialInfo) ;

	List<MaterModel_Info> getLastMaterModelId() ;

	List<MaterName_Info> getLastMaterNameId() ;

	List<MaterName_Info> getMaterName(V_MaterialInfo v_MaterialInfo) ;

	List<MaterName_Info> getMaterModel(V_MaterialInfo v_MaterialInfo) ;

	int addMaterName(V_MaterialInfo v_MaterialInfo) ;

	int addMaterModel(V_MaterialInfo v_MaterialInfo) ;

	int updateMaterial(V_MaterialInfo v_MaterialInfo) ;

	int addMaterial(V_MaterialInfo v_MaterialInfo) ;

	int deletMaterial(V_MaterialInfo v_MaterialInfo) ;

	List<V_MaterialInfo> getMaterialbyCode(V_MaterialInfo v_MaterialInfo) ;

	void addMater(V_MaterialInfo v_MaterialInfo) ;

	List<V_MaterialInfo> getMaterialById(V_MaterialInfo v_MaterialInfo) ;

	List<Map<String, Object>> isInAsphTargetProportion(V_MaterialInfo v_MaterialInfo) ;

	List<Map<String, Object>> isInCementTheoryProportion(V_MaterialInfo v_MaterialInfo) ;

	List<Map<String, Object>> isInAsphTargetPropDetailed(V_MaterialInfo v_MaterialInfo) ;

	List<Map<String, Object>> isInCementTheoPropDetailed(V_MaterialInfo v_MaterialInfo) ;
	
	List<Map<String, Object>> isMayMaterialDel(Map<String, Object> map) ;
	
	List<Map<String, Object>> iscementMaterialMayMaterialDel(Map<String, Object> map) ;
}
