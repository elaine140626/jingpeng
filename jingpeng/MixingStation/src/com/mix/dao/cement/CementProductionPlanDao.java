package com.mix.dao.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.mix.model.Cement_Production_PlanDTO;

public interface CementProductionPlanDao {
	public List<Cement_Production_PlanDTO> getCementProductionPlan(HashMap<String, Object> map);
	
	public int addCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO);

	public List getPlanNo(Cement_Production_PlanDTO cement_Production_PlanDTO);

	public List<Cement_Production_PlanDTO> getBunkerCode(Cement_Production_PlanDTO cement_Production_PlanDTO);
	
	public List<Map<String, Object>> getCementProductionPlanById(Cement_Production_PlanDTO cement_Production_PlanDTO);

	public int updateCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO);
	
	public int zfProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO);

	public int delCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO);

	public List getProductionById(HashMap<String, Object> map);

	public List getProductionTwoById(HashMap<String, Object> map);
	
	public List<Cement_Production_PlanDTO> getCementProductionPlanByPlan_No(HashMap<String, Object> map);
	
}
