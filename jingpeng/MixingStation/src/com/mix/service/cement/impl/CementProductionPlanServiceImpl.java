package com.mix.service.cement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.cement.CementProductionPlanDao;
import com.mix.model.Cement_Production_PlanDTO;
import com.mix.service.cement.CementProductionPlanService;
@Service
@Transactional
public class CementProductionPlanServiceImpl implements CementProductionPlanService {

	@Autowired
	private CementProductionPlanDao cementProductionPlanDao;
	
	@Override
	public List<Cement_Production_PlanDTO> getCementProductionPlan(HashMap<String, Object> map) {
		return cementProductionPlanDao.getCementProductionPlan(map);
	}

	@Override
	public List<Map<String, Object>> getCementProductionPlanById(Cement_Production_PlanDTO cement_Production_PlanDTO) {
		return cementProductionPlanDao.getCementProductionPlanById(cement_Production_PlanDTO);
	}

	@Override
	public int addCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) {
		return cementProductionPlanDao.addCementProductionPlan(cement_Production_PlanDTO);
	}

	@Override
	public List getPlanNo(Cement_Production_PlanDTO cement_Production_PlanDTO) {
		return cementProductionPlanDao.getPlanNo(cement_Production_PlanDTO);
	}

	@Override
	public List<Cement_Production_PlanDTO> getBunkerCode(Cement_Production_PlanDTO cement_Production_PlanDTO) {
		return cementProductionPlanDao.getBunkerCode(cement_Production_PlanDTO);
	}

	@Override
	public int updateCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) {
		return cementProductionPlanDao.updateCementProductionPlan(cement_Production_PlanDTO);
	}

	@Override
	public int delCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) {
		return cementProductionPlanDao.delCementProductionPlan(cement_Production_PlanDTO);
	}

	@Override
	public int zfProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) {
		return cementProductionPlanDao.zfProductionPlan(cement_Production_PlanDTO);
	}

	@Override
	public List getProductionById(HashMap<String, Object> map) {
		return cementProductionPlanDao.getProductionById(map);
	}

	@Override
	public List getProductionTwoById(HashMap<String, Object> map) {
		return cementProductionPlanDao.getProductionTwoById(map);
	}

	@Override
	public List<Cement_Production_PlanDTO> getCementProductionPlanByPlan_No(HashMap<String, Object> map) {
		return cementProductionPlanDao.getCementProductionPlanByPlan_No(map);
	}

}