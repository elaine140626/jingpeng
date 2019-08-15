package com.mix.service.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mix.model.Cement_Production_PlanDTO;

public interface CementProductionPlanService {
	List<Cement_Production_PlanDTO> getCementProductionPlan(HashMap<String, Object> map);

	List<Map<String, Object>> getCementProductionPlanById(Cement_Production_PlanDTO cement_Production_PlanDTO);
	
	int addCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO);

	List getPlanNo(Cement_Production_PlanDTO cement_Production_PlanDTO);

	List<Cement_Production_PlanDTO> getBunkerCode(Cement_Production_PlanDTO cement_Production_PlanDTO);

	int updateCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO);

	int delCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO);
	
	int zfProductionPlan(Cement_Production_PlanDTO ement_Production_PlanDTO);
	
	List getProductionById(HashMap<String, Object> map);
	
	List getProductionTwoById(HashMap<String, Object> map);
	
	List<Cement_Production_PlanDTO> getCementProductionPlanByPlan_No(HashMap<String, Object> map);
}