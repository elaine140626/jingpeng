package com.jingpeng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Cement_Production_Plan;
import com.jingpeng.model.Cement_Production_PlanDTO;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class CementProductionPlanDao extends KDDaoSupport{
	private final static String NAMESPACE = "CementProductionPlan";

	public List<Cement_Production_PlanDTO> getCementProductionPlan(HashMap<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getCementProductionPlan", map);
	}

	public int addCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws DataAccessException {
		return this.insert(NAMESPACE+".addCementProductionPlan", cement_Production_PlanDTO);
	}

	public List getPlanNo(Cement_Production_PlanDTO cement_Production_PlanDTO) throws DataAccessException {
		return this.select(NAMESPACE+".getPlanNo", cement_Production_PlanDTO);
	}

	public List<Cement_Production_PlanDTO> getBunkerCode(Cement_Production_PlanDTO cement_Production_PlanDTO) throws DataAccessException {
		return this.select(NAMESPACE+".getBunkerCode", cement_Production_PlanDTO);
	}
	
	public List<Map<String, Object>> getCementProductionPlanById(Cement_Production_PlanDTO cement_Production_PlanDTO) throws DataAccessException {
		return this.select(NAMESPACE+".getCementProductionPlanById", cement_Production_PlanDTO);
	}
	

	public int updateCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws DataAccessException {
		return this.update(NAMESPACE+".updateCementProductionPlan", cement_Production_PlanDTO);
	}
	
	public int zfProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO)throws DataAccessException {
		return this.update(NAMESPACE+".zfProductionPlan", cement_Production_PlanDTO);
	}

	public int delCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws DataAccessException {
		return this.update(NAMESPACE+".delCementProductionPlan", cement_Production_PlanDTO);
	}

	public List getProductionById(HashMap<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return this.select(NAMESPACE+".getProductionById", map);
	}

	public List getProductionTwoById(HashMap<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return this.select(NAMESPACE+".getProductionTwoById", map);
	}
	
}
