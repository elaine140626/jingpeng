package com.mix.service.asphalt;

import java.util.List;
import java.util.Map;

import com.mix.model.Asph_TargetProportion;
import com.mix.model.Cement_Production_PlanDTO;
import com.mix.model.Production_Plan;
import com.mix.model.Production_PlanDTO;

public interface Production_PlanService {
	
	List<Production_PlanDTO> getProductionPlan(Map<String, Object> map) ;
	
	int addProductionPlan(Production_Plan production_Plan) ;

	int updateProductionPlan(Production_Plan production_Plan) ;

	int delProductionPlan(Production_Plan production_Plan) ;

	List getPlanNo(Production_Plan production_Plan) ;

	List<Production_PlanDTO> getProductionPlanById(Production_Plan production_Plan) ;
	
	 List<Production_PlanDTO> getProdID(Map<String, Object> map);
	 
	 int zfProductionPlan(Production_Plan production_Plan);

}