package com.jingpeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;

import com.jingpeng.model.Cement_Production_Plan;
import com.jingpeng.model.Cement_Production_PlanDTO;
import com.jingpeng.model.Production_Plan;
import com.kdt.base.exception.BusinessException;

public interface CementProductionPlanService {

	List<Cement_Production_PlanDTO> getCementProductionPlan(HashMap<String, Object> map) throws BusinessException;

	List<Map<String, Object>> getCementProductionPlanById(Cement_Production_PlanDTO cement_Production_PlanDTO)throws BusinessException;
	
	int addCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException;

	List getPlanNo(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException;

	List<Cement_Production_PlanDTO> getBunkerCode(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException;

	int updateCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException;

	int delCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException;
	
	int zfProductionPlan(Cement_Production_PlanDTO ement_Production_PlanDTO) throws BusinessException;
	
	List getProductionById(HashMap<String, Object> map)throws BusinessException;
	
	List getProductionTwoById(HashMap<String, Object> map)throws BusinessException;
	
}
