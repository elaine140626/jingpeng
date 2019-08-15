package com.mix.service.asphalt.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.asphalt.CommonDao;
import com.mix.model.Asph_TargetProportion;
import com.mix.model.Asphalt_Grading;
import com.mix.model.Asphalt_Prod_Proportion;
import com.mix.model.Cement_ConsPropDetailed;
import com.mix.model.Cement_ConstructionProportion;
import com.mix.model.Core_User_Info;
import com.mix.model.Equipment_Info;
import com.mix.model.Organization_Info;
import com.mix.model.User_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.asphalt.CommonService;

@Service
@Transactional
public class CommonServiceImpl implements CommonService{
	@Autowired
	private CommonDao commonDao;
	
	public List<V_MaterialInfo> getMaterialName(V_MaterialInfo v_MaterialInfo){
			return commonDao.getMaterialName(v_MaterialInfo);
	}
	
	public List<V_MaterialInfo> getMaterialModel(V_MaterialInfo v_MaterialInfo) {
			return commonDao.getMaterialModel(v_MaterialInfo);
	}
	
	public List<Asph_TargetProportion> getAsph_TargetProCode(){
			return commonDao.getAsph_TargetProCode();
	}
	
	public List<Asph_TargetProportion> getAsph_TargetProCodeById(V_MaterialInfo v_MaterialInfo){
			return commonDao.getAsph_TargetProCodeById(v_MaterialInfo);
	}

	public List<Cement_ConsPropDetailed> getCement_ConsPropDetailedById(Cement_ConsPropDetailed cement_ConsPropDetailed){
			return commonDao.getCement_ConsPropDetailedById(cement_ConsPropDetailed);
	}

	public List<Cement_ConstructionProportion> getCement_ConstructionProportionById(Cement_ConstructionProportion cement_ConstructionProportion){
			return commonDao.getCement_ConstructionProportionById(cement_ConstructionProportion);
	}

	public List<Equipment_Info> getEquipmentInfo(HashMap<String, Object> map)  {
			return commonDao.getEquipmentInfo(map);
	}

	public List<Asph_TargetProportion> getGradeCodeById(V_MaterialInfo v_MaterialInfo){
			return commonDao.getGradeCodeById(v_MaterialInfo);
	}

	public List<Asphalt_Prod_Proportion> getProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion){
			return commonDao.getProportionCode(asphalt_Prod_Proportion);
	}

	public List<Asphalt_Grading> getGradeCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion){
			return commonDao.getGradeCode(asphalt_Prod_Proportion);
	}

	public List<Organization_Info> getOrgTree(Map<String, String> map){
			return commonDao.getOrgTree(map);
	}
	
	public int[] getUserOrgId(User_Info user){
			List<Map<String, Object>> list = commonDao.getUserOrgId(user);
			int[] orgs = new int[list.size()];
			for(int i = 0; i < list.size(); i++) {
				orgs[i] = Integer.parseInt(list.get(i).get("i_id").toString());
			}
			return orgs;
	}

	public List<V_MaterialInfo> getMaterialNames(HashMap<String, Object> map) {
			return commonDao.getMaterialNames(map);
	}

	public List<V_MaterialInfo> getMaterialModels(HashMap<String, Object> map){
			return commonDao.getMaterialModels(map);
	}

	public String getCUserOrgId(Core_User_Info user){
			List<Map<String, Object>> list = commonDao.getCUserOrgId(user);
			String str_power_Org_Id = list.get(0).get("str_power_Org_Id").toString();
			return str_power_Org_Id;
	}

	public List<V_MaterialInfo> getMaterialModelIdbyNameAndCode(V_MaterialInfo v_MaterialInfo){
			 return commonDao.getMaterialModelIdbyNameAndCode(v_MaterialInfo);	
	}
}
