package com.jingpeng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Cement_ConsPropDetailed;
import com.jingpeng.model.Cement_ConstructionProportion;
import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class CementConstructionPropDao extends KDDaoSupport{

private final static String NAMESPACE = "cementConstructionProp";


	public List<Cement_ConsPropDetailed> getC_ConstructionDeatlByid(Cement_ConsPropDetailed cement_ConsPropDetailed) throws DataAccessException {
		return this.select(NAMESPACE+".getC_ConstructionDeatlByid", cement_ConsPropDetailed);
	}
	
	public List<Cement_ConstructionProportion> getCementConstructionProp(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getCementConstructionProp", map);
	}
	
	public List<Cement_ConstructionProportion> getCement_ConstructionByid(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getCement_ConstructionByid", map);
	}
	
	public  List<Map<String, Object>> getCementConstructionPropbyPid(Map<String, Object> map ) throws DataAccessException {
		return this.select(NAMESPACE+".getCementConstructionPropbyPid", map);
	}
	
	public List<Cement_ConstructionProportion> getCementConstructionPropbyPids(Map<String, Object> map ) throws DataAccessException {
		return this.select(NAMESPACE+".getCementConstructionPropbyPids", map);
	}
	
	public List<Cement_ConstructionProportion> getCementConstructionPropbyPid(HashMap<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getCementConstructionPropbyPid", map);
	}

	public List<Cement_ConstructionProportion> getPropCode(String str_prop_Code) throws DataAccessException {
		return this.select(NAMESPACE+".getCementConstructionProp", str_prop_Code);
	}
	
	
	public List<Cement_ConstructionProportion> getC_ConstructionByid(Cement_ConstructionProportion cement_ConstructionProportion) throws DataAccessException {
		return this.select(NAMESPACE+".getC_ConstructionByid", cement_ConstructionProportion);
	}

	public List<Cement_ConstructionProportion> getMaterNameAndModel(Cement_ConstructionProportion cement_ConstructionProportion) throws DataAccessException {
		return this.select(NAMESPACE+".getMaterNameAndModel", cement_ConstructionProportion);
	}

	public List<Map<String, Object>> getCementConsPropDetailed(Cement_ConstructionProportion cement_ConstructionProportion) throws DataAccessException {
		return this.select(NAMESPACE+".getCementConsPropDetailed", cement_ConstructionProportion);
	}

	public int addCementConstructionPro(Map<String, Object> map) throws DataAccessException {
		this.insert(NAMESPACE+".addCementConstructionPro", map);
		return (Integer) map.get("i_id");
	}

	public List<Cement_TheoryProportion> getTheoProp(int i_id) throws DataAccessException {
		return this.select(NAMESPACE+".getTheoProp", i_id);
	}

	public int addCementConsPropDetailed(List<Cement_ConsPropDetailed> cement_ConsPropDetailedList) throws DataAccessException {
		return this.insert(NAMESPACE+".addCementConsPropDetailed", cement_ConsPropDetailedList);
	}

	public int updateCementConstructionPro(Map<String, Object> map) throws DataAccessException {
		return this.update(NAMESPACE+".updateCementConstructionPro", map);
	}

	public int delCementConsPropDetailed(Map<String, Object> map) throws DataAccessException {
		return this.update(NAMESPACE+".delCementConsPropDetailed", map);
	}

	public int delCementConstructionPro(Cement_ConstructionProportion cement_ConstructionProportion) throws DataAccessException {
		return this.update(NAMESPACE+".delCementConstructionPro", cement_ConstructionProportion);
	}

	public List<Map<String, Object>> getgetTheoryProportionCode(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getgetTheoryProportionCode", map);
	}

	public int addSgpbXx(Cement_ConstructionProportion cement_ConstructionProportion) throws DataAccessException {
		 this.insert(NAMESPACE+".addSgpbXx", cement_ConstructionProportion);
		 return cement_ConstructionProportion.getI_id();
	}

	public int addSgpbXxList(List<Cement_ConsPropDetailed> list) throws DataAccessException {
		return this.insert(NAMESPACE+".addSgpbXxList", list);
	}

	
	public List<Cement_ConstructionProportion> getMainById(Cement_ConstructionProportion cement_ConstructionProportion) throws DataAccessException {
		return this.select(NAMESPACE+".getMainById", cement_ConstructionProportion);
	}
	public List<Map<String, Object>> getMainByIdGrid(Cement_ConstructionProportion cement_ConstructionProportion) throws DataAccessException {
		return this.select(NAMESPACE+".getMainByIdGrid", cement_ConstructionProportion);
	}

	public List<Map<String, Object>> getCementConstructionPropbypidList(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getCementConstructionPropbypidList", map);
	}
	
	/*
	 * 理论配合比删除条件
	 * tongn
	 * 2018.6.28
	 */
	public List<Map<String, Object>> getCementConstructionProportionByTheoPropID(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getCementConstructionProportionByTheoPropID", map);
	}

	public List<Map<String, Object>> getTheory(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return this.select(NAMESPACE+".getTheory", map);
	}

	public List<Map<String, Object>> getTheoryList(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return this.select(NAMESPACE+".getTheoryList", map);
	}

	public List<Map<String, Object>> getSgpbNo(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return this.select(NAMESPACE+".getSgpbNo", map);
	}
	public List<Cement_ConsPropDetailed> select_Asph_TargetPropDetailed(Cement_ConsPropDetailed cement_ConsPropDetailed) throws DataAccessException {
		return this.select(NAMESPACE+".select_Asph_TargetPropDetailed", cement_ConsPropDetailed);
	}
}
