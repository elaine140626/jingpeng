package com.mix.service.asphalt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public interface CommonService {

	List<V_MaterialInfo> getMaterialName(V_MaterialInfo v_MaterialInfo);
	
	List<V_MaterialInfo> getMaterialNames(HashMap<String, Object> map);
	
	List<V_MaterialInfo> getMaterialModel(V_MaterialInfo v_MaterialInfo);
	
	List<V_MaterialInfo> getMaterialModels(HashMap<String, Object> map);
	
	List<Asph_TargetProportion> getAsph_TargetProCode();

	List<Asph_TargetProportion> getAsph_TargetProCodeById(V_MaterialInfo v_MaterialInfo);
	
	List<Cement_ConsPropDetailed> getCement_ConsPropDetailedById(Cement_ConsPropDetailed cement_ConsPropDetailed);

	List<Cement_ConstructionProportion> getCement_ConstructionProportionById(Cement_ConstructionProportion cement_ConstructionProportion);

	List<Equipment_Info> getEquipmentInfo(HashMap<String, Object> map);

	List<Asph_TargetProportion> getGradeCodeById(V_MaterialInfo v_MaterialInfo);

	List<Asphalt_Prod_Proportion> getProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion);

	List<Asphalt_Grading> getGradeCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion);

	List<Organization_Info> getOrgTree(Map<String, String> map);
	
	int[] getUserOrgId(User_Info user);

	String getCUserOrgId(Core_User_Info user);
	
	List<V_MaterialInfo> getMaterialModelIdbyNameAndCode(V_MaterialInfo v_MaterialInfo);
}
