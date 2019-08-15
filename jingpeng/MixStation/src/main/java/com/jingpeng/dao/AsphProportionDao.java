package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class AsphProportionDao extends KDDaoSupport{
	
	private final static String NAMESPACE = "asphProportion";
	
	public List<Asph_TargetProportion> getAsph_TargetProportion (Asph_TargetProportion asph_TargetProportion) throws DataAccessException {
		
		return select(NAMESPACE+".getAsph_TargetProportion", asph_TargetProportion);
	}
	
	public List<Asph_TargetProportion> getAsph_TargetProportionCode (Asph_TargetProportion asph_TargetProportion) throws DataAccessException {
		
		return select(NAMESPACE+".getAsph_TargetProportionCode", asph_TargetProportion);
	}
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionById (Map<String, Object> map) throws DataAccessException {
		
		return select(NAMESPACE+".getAsphalt_Prod_ProportionById", map);
	}
	
	
	/*
	 * 根据Targ_PropID 查找
	 * tongn
	 * 2018.6.27
	 */
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionPropId (Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
		
		return select(NAMESPACE+".getAsphalt_Prod_ProportionPropId", asphalt_Prod_Proportion);
	}
	
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionCode (Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
		
		return select(NAMESPACE+".getAsphalt_Prod_ProportionCode", asphalt_Prod_Proportion);
	}
	
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_Proportion (Map<String, Object> map) throws DataAccessException {
		
		return select(NAMESPACE+".getAsphalt_Prod_Proportion", map);
	}
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_Proportion_Code(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException{
		
		return select(NAMESPACE+".getAsphalt_Prod_Proportion_Code", asphalt_Prod_Proportion);
	}
	
	public int addAsphalt_Prod_Proportion (Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
		
		return insert(NAMESPACE+".addAsphalt_Prod_Proportion", asphalt_Prod_Proportion);
	}

	public int updateAsphalt_Prod_Proportion (Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
	
		return update(NAMESPACE+".updateAsphalt_Prod_Proportion", asphalt_Prod_Proportion);
	}
	
	public int deletAsphalt_Prod_Proportion (Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
		
		return update(NAMESPACE+".deletAsphalt_Prod_Proportion", asphalt_Prod_Proportion);
	}
	
	public int addAsph_TargetProportion (Asph_TargetProportion asph_TargetProportion) throws DataAccessException {
		insert(NAMESPACE+".addAsph_TargetProportion", asph_TargetProportion);
		return asph_TargetProportion.getI_id();
	}

	public List<Map> getProduction_Plan(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
		return select(NAMESPACE+".getProduction_Plan", asphalt_Prod_Proportion);
	}
	
	
	/*
	 * 查找生产配合比信息
	 * tongn
	 * 2018.6.27
	 */
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionByGradId (Map<String, Object> map) throws DataAccessException {
		
		return select(NAMESPACE+".getAsphalt_Prod_ProportionByGradId", map);
	}

	public List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws DataAccessException {
		// TODO Auto-generated method stub
		return select(NAMESPACE+".select_Asph_TargetPropDetailed", asphalt_Prod_Proportion);
	}
	
	

}
