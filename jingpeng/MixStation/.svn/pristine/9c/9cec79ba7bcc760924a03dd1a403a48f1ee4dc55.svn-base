package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Production_Plan;
import com.jingpeng.model.Production_PlanDTO;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Production_PlanDao extends KDDaoSupport {
	private final static String NAMESPACE = "productionPlan";
	
	public List<Production_PlanDTO> getProductionPlan(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getProductionPlan", map);
	}

	public int addProductionPlan(Production_Plan production_Plan) throws DataAccessException {
		return this.insert(NAMESPACE+".addProductionPlan", production_Plan);
	}

	public int updateProductionPlan(Production_Plan production_Plan) throws DataAccessException {
		return this.update(NAMESPACE+".updateProductionPlan", production_Plan);
	}

	public int delProductionPlan(Production_Plan production_Plan) throws DataAccessException {
		return this.update(NAMESPACE+".delProductionPlan", production_Plan);
	}

	public List getPlanNo(Production_Plan production_Plan) throws DataAccessException {
		return this.select(NAMESPACE+".getPlanNo", production_Plan);
	}

	public List<Production_PlanDTO> getProductionPlanById(Production_Plan production_Plan) throws DataAccessException {
		return this.select(NAMESPACE+".getProductionPlanById", production_Plan);
	}
	
	/*
	 * 通过生产计划id查询生产计划信息
	 * tongn
	 * 2018.6.27
	 */
	public List<Production_PlanDTO> getProdID(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getProdID", map);
	}
	
}
