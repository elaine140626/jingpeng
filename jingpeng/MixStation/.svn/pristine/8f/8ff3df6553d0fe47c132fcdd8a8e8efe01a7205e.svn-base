package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.MaterModel_Info;
import com.jingpeng.model.MaterName_Info;
import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

public interface MaterialService {
	List<V_MaterialInfo> getMaterialInfo(V_MaterialInfo v_MaterialInfo) throws BusinessException ;

	List<V_MaterialInfo> getMaterialbyNameOrModel(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<MaterModel_Info> getLastMaterModelId() throws BusinessException;

	List<MaterName_Info> getLastMaterNameId() throws BusinessException;

	List<MaterName_Info> getMaterName(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<MaterName_Info> getMaterModel(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	int addMaterName(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	int addMaterModel(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	int updateMaterial(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	int addMaterial(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	int deletMaterial(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<V_MaterialInfo> getMaterialbyCode(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	void addMater(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<V_MaterialInfo> getMaterialById(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<Map<String, Object>> isInAsphTargetProportion(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<Map<String, Object>> isInCementTheoryProportion(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<Map<String, Object>> isInAsphTargetPropDetailed(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<Map<String, Object>> isInCementTheoPropDetailed(V_MaterialInfo v_MaterialInfo) throws BusinessException;
	
	/* 
	 *沥青 原材料可以删除条件
	 * tongn
	 * 2018.6.28
	 */
	List<Map<String, Object>> isMayMaterialDel(Map<String, Object> map) throws BusinessException;
	

	/* 
	 * 水泥原材料可以删除条件
	 * tongn
	 * 2018.6.28
	 */
	List<Map<String, Object>> iscementMaterialMayMaterialDel(Map<String, Object> map) throws BusinessException;
}
