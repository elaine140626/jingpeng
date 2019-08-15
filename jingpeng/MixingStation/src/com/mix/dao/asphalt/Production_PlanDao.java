package com.mix.dao.asphalt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mix.model.Asph_TargetProportion;
import com.mix.model.Cement_Production_PlanDTO;
import com.mix.model.Production_Plan;
import com.mix.model.Production_PlanDTO;

@Repository
public interface Production_PlanDao  {
	
	List<Production_PlanDTO> getProductionPlan(Map<String, Object> map) ;
	
	int addProductionPlan(Production_Plan production_Plan) ;

	int updateProductionPlan(Production_Plan production_Plan) ;

	int delProductionPlan(Production_Plan production_Plan) ;

	List getPlanNo(Production_Plan production_Plan) ;
	
	int zfProductionPlan(Production_Plan production_Plan);

	List<Production_PlanDTO> getProductionPlanById(Production_Plan production_Plan) ;
	
	 List<Production_PlanDTO> getProdID(Map<String, Object> map);
	
}
