package com.jingpeng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

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
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class CommonDao extends KDDaoSupport{
	private final static String NAMESPACE = "common";
	
	public List<V_MaterialInfo> getMaterialName(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialName", v_MaterialInfo);
	}
	
	public List<V_MaterialInfo> getMaterialNames(HashMap<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialNames", map);
	}
	public List<V_MaterialInfo> getMaterialModels(HashMap<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialModels", map);
	}
	
	public List<V_MaterialInfo> getMaterialModel(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialModel", v_MaterialInfo);
	}
	
	public List<Asph_TargetProportion> getAsph_TargetProCode() throws DataAccessException {
		return this.select(NAMESPACE+".getAsph_TargetProCode", "");
	}

	public List<Asph_TargetProportion> getAsph_TargetProCodeById(V_MaterialInfo v_MaterialInfo)  throws DataAccessException {
		return this.select(NAMESPACE+".getAsph_TargetProCodeById", v_MaterialInfo);
	}
	
	public List<Cement_ConsPropDetailed> getCement_ConsPropDetailedById(Cement_ConsPropDetailed cement_ConsPropDetailed)  throws DataAccessException {
		return this.select(NAMESPACE+".getCement_ConsPropDetailedById", cement_ConsPropDetailed);
	}
	
	public List<Cement_ConstructionProportion> getCement_ConstructionProportionById(Cement_ConstructionProportion cement_ConstructionProportion)  throws DataAccessException {
		return this.select(NAMESPACE+".getCement_ConstructionProportionById", cement_ConstructionProportion);
	}

	public List<Equipment_Info> getEquipmentInfo(HashMap<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getEquipmentInfo", map);
	}

	public List<Asph_TargetProportion> getGradeCodeById(V_MaterialInfo v_MaterialInfo) throws DataAccessException {
		return this.select(NAMESPACE+".getGradeCodeById", v_MaterialInfo);
	}

	public List<Asphalt_Prod_Proportion> getProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
		return this.select(NAMESPACE+".getProportionCode", asphalt_Prod_Proportion);
	}

	public List<Asphalt_Grading> getGradeCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
		return this.select(NAMESPACE+".getGradeCode", asphalt_Prod_Proportion);
	}

	public List<Organization_Info> getOrgTree(Map<String, String> map) throws DataAccessException {
		return this.select(NAMESPACE+".getOrgTree", map);
	}

	public List<Map<String, Object>> getUserOrgId(User_Info user) throws DataAccessException {
		return this.select(NAMESPACE+".getUserOrgId", user);
	}

	public List<Map<String, Object>> getCUserOrgId(Core_User_Info user) throws DataAccessException {
		return this.select(NAMESPACE+".getCUserOrgId", user);
	}
	
	public List<V_MaterialInfo> getMaterialModelIdbyNameAndCode(V_MaterialInfo v_MaterialInfo)throws DataAccessException {
		return this.select(NAMESPACE+".getMaterialModelIdbyNameAndCode", v_MaterialInfo); 
	}
}
