package com.mix.dao.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mix.model.Cement_ConsPropDetailed;
import com.mix.model.Cement_ConstructionProportion;
import com.mix.model.Cement_TheoryProportion;

public interface CementConstructionPropDao {
	//理论配合比删除条件
	public List<Map<String, Object>> getCementConstructionProportionByTheoPropID(Map<String, Object> map);
	
	//施工配合比删除条件
	public List<Map<String, Object>> getCementConstructionProportionByID(Map<String, Object> map);
	
	public List<Cement_ConsPropDetailed> getC_ConstructionDeatlByid(Cement_ConsPropDetailed cement_ConsPropDetailed);
	
	public List<Cement_ConstructionProportion> getCementConstructionProp(Map<String, Object> map);
	
	public List<Cement_ConstructionProportion> getCement_ConstructionByid(Map<String, Object> map);
	
	public  List<Map<String, Object>> getCementConstructionPropbyPid(Map<String, Object> map );
	
	public List<Cement_ConstructionProportion> getCementConstructionPropbyPids(Map<String, Object> map );
	
	public List<Cement_ConstructionProportion> getCementConstructionPropbyPid(HashMap<String, Object> map);

	public List<Cement_ConstructionProportion> getPropCode(String str_prop_Code);
	
	
	public List<Cement_ConstructionProportion> getC_ConstructionByid(Cement_ConstructionProportion cement_ConstructionProportion);

	public List<Cement_ConstructionProportion> getMaterNameAndModel(Cement_ConstructionProportion cement_ConstructionProportion);

	public List<Map<String, Object>> getCementConsPropDetailed(Cement_ConstructionProportion cement_ConstructionProportion);

	public int addCementConstructionPro(Map<String, Object> map);

	public List<Cement_TheoryProportion> getTheoProp(int i_id);

	public int addCementConsPropDetailed(List<Cement_ConsPropDetailed> cement_ConsPropDetailedList);

	public int updateCementConstructionPro(Map<String, Object> map);

	public int delCementConsPropDetailed(Map<String, Object> map);

	public int delCementConstructionPro(Cement_ConstructionProportion cement_ConstructionProportion);
	public List<Map<String, Object>> getgetTheoryProportionCode(Map<String, Object> map);

	public int addSgpbXx(Cement_ConstructionProportion cement_ConstructionProportion);

	public int addSgpbXxList(List<Cement_ConsPropDetailed> list);
	
	public List<Cement_ConstructionProportion> getMainById(Cement_ConstructionProportion cement_ConstructionProportion);
	public List<Map<String, Object>> getMainByIdGrid(Cement_ConstructionProportion cement_ConstructionProportion);
	public List<Map<String, Object>> getCementConstructionPropbypidList(Map<String, Object> map);

	public List<Map<String, Object>> getTheory(Map<String, Object> map);

	public List<Map<String, Object>> getTheoryList(Map<String, Object> map);

	public List<Map<String, Object>> getSgpbNo(Map<String, Object> map);
	public List<Cement_ConsPropDetailed> select_Asph_TargetPropDetailed(Cement_ConsPropDetailed cement_ConsPropDetailed);
}
