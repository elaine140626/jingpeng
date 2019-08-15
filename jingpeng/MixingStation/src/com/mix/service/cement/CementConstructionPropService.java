package com.mix.service.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mix.model.Cement_ConsPropDetailed;
import com.mix.model.Cement_ConstructionProportion;
import com.mix.model.Cement_TheoryProportion;

public interface CementConstructionPropService {

	//理论配合比删除条件
	public List<Map<String, Object>> getCementConstructionProportionByTheoPropID(Map<String, Object> map);
	
	//施工配合比删除条件
	public List<Map<String, Object>> getCementConstructionProportionByID(Map<String, Object> map);
	
	List<Cement_ConstructionProportion> getMainById(Cement_ConstructionProportion cement_ConstructionProportion);
	
	List<Map<String, Object>> getMainByIdGrid(Cement_ConstructionProportion Cement_ConstructionProportion);
	
	List<Cement_ConsPropDetailed> getC_ConstructionDeatlByid(Cement_ConsPropDetailed cement_ConsPropDetailed);
	

	List<Cement_ConstructionProportion> getCementConstructionProp(Map<String, Object> map);

	List<Cement_ConstructionProportion> getPropCode(String str_prop_Code);

	List<Cement_ConstructionProportion> getMaterNameAndModel(Cement_ConstructionProportion cement_ConstructionProportion);

	List<Map<String, Object>> getCementConstructionPropbypid(Map<String, Object> map);
	
	List<Map<String, Object>> getSgpbNo(Map<String, Object> map);
	
	List<Map<String, Object>> getCementConstructionPropbypidList(Map<String, Object> map);
	
	List<Cement_ConstructionProportion> getCementConstructionPropbypids(Map<String, Object> map);
	
	List<Map<String, Object>> getTheory(Map<String, Object> map);
	List<Map<String, Object>> getTheoryList(Map<String, Object> map);
	
	int addSgpbXx(Cement_ConstructionProportion cement_ConstructionProportion);
	
	int addSgpbXxList(List<Cement_ConsPropDetailed> list);
	
	List<Cement_ConstructionProportion> getC_ConstructionByid (Cement_ConstructionProportion cement_ConstructionProportion);
	
	List<Cement_ConstructionProportion> getCement_ConstructionByid (HashMap<String, Object> map);

	int addCementConstructionPro(Map<String, Object> map);

	List<Cement_TheoryProportion> getTheoProp(int i_id);

	int addCementConsPropDetailed(List<Cement_ConsPropDetailed> cement_ConsPropDetailedList);

	int updateCementConstructionPro(Map<String, Object> map);

	

	int delCementConstructionPro(Cement_ConstructionProportion cement_ConstructionProportion);

	List<Map<String, Object>> getgetTheoryProportionCode(Map<String, Object> map);
	
	List<Cement_ConsPropDetailed> select_Asph_TargetPropDetailed(Cement_ConsPropDetailed cement_ConsPropDetailed);
}
