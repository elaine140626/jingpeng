package com.jingpeng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Cement_ConsPropDetailed;
import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.exception.KDException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Cement_TheoryProportionDao extends KDDaoSupport {

	private final static String NAMESPACE = "cement";

	public List<Cement_TheoryProportion> getCement_TheoryProportion(HashMap<String, Object> map)throws DataAccessException {

		return select(NAMESPACE + ".getCement_TheoryProportion", map);
	}
	
	public int addCement_TheoryProportion(HashMap<String, Object> map)throws DataAccessException {

		return update(NAMESPACE + ".addCement_TheoryProportionList", map);
	}
	
	public List<Cement_TheoryProportion> getCement_TheoryProportionById(Cement_TheoryProportion cement_TheoryProportion)throws DataAccessException {
		return select(NAMESPACE + ".getCement_TheoryProportionById", cement_TheoryProportion);
	}
	
	public List<Cement_TheoryProportion> getCementProportionCode(Cement_TheoryProportion cement_TheoryProportion)throws DataAccessException {

		return select(NAMESPACE + ".getCementProportionCode", cement_TheoryProportion);
	}
	
	public List<Cement_ConsPropDetailed> getCement_TheoryDetailByProporId(Cement_ConsPropDetailed cement_ConsPropDetailed)throws DataAccessException {

		return select(NAMESPACE + ".getCement_TheoryDetailByProporId", cement_ConsPropDetailed);
	}
	
	public int addCement_TheoryProportion(Map<String, Object> map)throws DataAccessException {

		insert(NAMESPACE + ".addCement_TheoryProportion", map);
		return (Integer) map.get("i_id");
	}
	
	public int updateCement_TheoryProportion(Map<String, Object> map)throws DataAccessException {

		return update(NAMESPACE + ".updateCement_TheoryProportion", map);
	}

	public int  deletCement_TheoryProportion(Cement_TheoryProportion cement_TheoryProportion)throws DataAccessException {

		return update(NAMESPACE + ".deletCement_TheoryProportion", cement_TheoryProportion);
	}
	
	public int  deletD(Map<String, Object> map)throws DataAccessException {

		return update(NAMESPACE + ".deletD", map);
	}

	public List<V_MaterialInfo> getRawMaterial(Map<String, Object> map)throws DataAccessException {
		return select(NAMESPACE + ".getRawMaterial", map);
	}

	public List<Map<String, Object>> getYclList(Cement_ConsPropDetailed cement_ConsPropDetailed) throws DataAccessException {
		return this.select(NAMESPACE+".getYclList", cement_ConsPropDetailed);
	}

	public List<Map<String, Object>> getAllMaterials_id(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getAllMaterials_id", map);
	}
	public List<Map<String, Object>> getYclModelList(Map<String, Object> map) throws DataAccessException {

		return select(NAMESPACE + ".getYclModelList", map);
	}

	public List<Cement_TheoPropDetailed> select_Asph_TargetPropDetailed(
			Cement_TheoPropDetailed cement_TheoPropDetailed) throws DataAccessException {
		// TODO Auto-generated method stub
		return select(NAMESPACE + ".select_Asph_TargetPropDetailed", cement_TheoPropDetailed);
	}

}
