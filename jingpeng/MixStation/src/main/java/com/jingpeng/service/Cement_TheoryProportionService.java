package com.jingpeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingpeng.model.Cement_ConsPropDetailed;
import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.BusinessException;

public interface Cement_TheoryProportionService {
	
	public List<Cement_TheoryProportion> getCementProportionCode(Cement_TheoryProportion cement_TheoryProportion) throws BusinessException;

	public List<Cement_TheoryProportion> getCement_TheoryProportionById (Cement_TheoryProportion cement_TheoryProportion)throws BusinessException;

	public List<Cement_TheoryProportion> getCement_TheoryProportion(HashMap<String, Object> map) throws BusinessException;
	
	public int addCement_TheoryProportion(Map<String, Object> map)throws BusinessException;
	
	public int updateCement_TheoryProportion(Map<String, Object> map)throws BusinessException;
	
	public List<Cement_ConsPropDetailed> getCement_TheoryDetailByProporId(Cement_ConsPropDetailed cement_ConsPropDetailed) throws BusinessException;

	List<Map<String, Object>>  getYclList(Cement_ConsPropDetailed cement_ConsPropDetailed) throws BusinessException;
	List<Map<String, Object>>  getAllMaterials_id(Map<String, Object> map) throws BusinessException;
	
	List<Map<String, Object>>  getYclModelList(Map<String, Object> map) throws BusinessException;
	
	public int deletCement_TheoryProportion(Cement_TheoryProportion cement_TheoryProportion)throws BusinessException;

	public List<V_MaterialInfo> getRawMaterial(Map<String, Object> map)throws BusinessException;

	public List<Cement_TheoPropDetailed> select_Asph_TargetPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed) throws BusinessException;
	
}
