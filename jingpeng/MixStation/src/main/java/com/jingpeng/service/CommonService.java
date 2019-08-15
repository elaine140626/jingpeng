package com.jingpeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_Grading;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.jingpeng.model.Cement_ConsPropDetailed;
import com.jingpeng.model.Cement_ConstructionProportion;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Equipment_Info;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.model.User_Info;
import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.BusinessException;

public interface CommonService {

	List<V_MaterialInfo> getMaterialName(V_MaterialInfo v_MaterialInfo) throws BusinessException;
	
	List<V_MaterialInfo> getMaterialNames(HashMap<String, Object> map) throws BusinessException;
	
	List<V_MaterialInfo> getMaterialModel(V_MaterialInfo v_MaterialInfo) throws BusinessException;
	
	List<V_MaterialInfo> getMaterialModels(HashMap<String, Object> map) throws BusinessException;
	
	List<Asph_TargetProportion> getAsph_TargetProCode() throws BusinessException;

	List<Asph_TargetProportion> getAsph_TargetProCodeById(V_MaterialInfo v_MaterialInfo) throws BusinessException;
	
	List<Cement_ConsPropDetailed> getCement_ConsPropDetailedById(Cement_ConsPropDetailed cement_ConsPropDetailed) throws BusinessException;

	List<Cement_ConstructionProportion> getCement_ConstructionProportionById(Cement_ConstructionProportion cement_ConstructionProportion) throws BusinessException;

	List<Equipment_Info> getEquipmentInfo(HashMap<String, Object> map) throws BusinessException;

	List<Asph_TargetProportion> getGradeCodeById(V_MaterialInfo v_MaterialInfo) throws BusinessException;

	List<Asphalt_Prod_Proportion> getProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws BusinessException;

	List<Asphalt_Grading> getGradeCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws BusinessException;

	List<Organization_Info> getOrgTree(Map<String, String> map) throws BusinessException;
	
	int[] getUserOrgId(User_Info user) throws BusinessException;

	String getCUserOrgId(Core_User_Info user) throws BusinessException;
	
	List<V_MaterialInfo> getMaterialModelIdbyNameAndCode(V_MaterialInfo v_MaterialInfo) throws BusinessException;
}
