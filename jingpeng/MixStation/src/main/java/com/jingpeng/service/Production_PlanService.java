package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Production_Plan;
import com.jingpeng.model.Production_PlanDTO;
import com.kdt.base.exception.BusinessException;

public interface Production_PlanService {
	
	List<Production_PlanDTO> getProductionPlan(Map<String, Object> map) throws BusinessException;
	
	int addProductionPlan(Production_Plan production_Plan) throws BusinessException;

	int updateProductionPlan(Production_Plan production_Plan) throws BusinessException;

	int delProductionPlan(Production_Plan production_Plan) throws BusinessException;

	List getPlanNo(Production_Plan production_Plan) throws BusinessException;

	List<Production_PlanDTO> getProductionPlanById(Production_Plan production_Plan) throws BusinessException;
	
	 List<Production_PlanDTO> getProdID(Map<String, Object> map)throws BusinessException;

}
