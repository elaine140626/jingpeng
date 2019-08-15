package com.mix.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.asphalt.Production_PlanDao;
import com.mix.model.Asph_TargetProportion;
import com.mix.model.Cement_Production_PlanDTO;
import com.mix.model.Production_Plan;
import com.mix.model.Production_PlanDTO;
import com.mix.service.asphalt.Production_PlanService;

@Service
@Transactional
public class Production_PlanServiceImpl implements Production_PlanService {
	@Autowired
	private Production_PlanDao production_PlanDao;
	
	public List<Production_PlanDTO> getProductionPlan(Map<String, Object> map)  {
			return production_PlanDao.getProductionPlan(map);
	
	}
	
	public int addProductionPlan(Production_Plan production_Plan)  {
			return production_PlanDao.addProductionPlan(production_Plan);
	}
	public int zfProductionPlan(Production_Plan production_Plan) {
		return production_PlanDao.zfProductionPlan(production_Plan);
	}
	public int updateProductionPlan(Production_Plan production_Plan)  {
			return production_PlanDao.updateProductionPlan(production_Plan);
	}

	public int delProductionPlan(Production_Plan production_Plan)  {
			return production_PlanDao.delProductionPlan(production_Plan);
	}

	public List getPlanNo(Production_Plan production_Plan)  {
			return production_PlanDao.getPlanNo(production_Plan);
	}

	public List<Production_PlanDTO> getProductionPlanById(Production_Plan production_Plan){
			return production_PlanDao.getProductionPlanById(production_Plan);
	}
	
	public List<Production_PlanDTO> getProdID(Map<String, Object> map) {
			return production_PlanDao.getProdID(map);
	}
	
	

}
