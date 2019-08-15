package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.Production_PlanDao;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Production_Plan;
import com.jingpeng.model.Production_PlanDTO;
import com.jingpeng.service.Production_PlanService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class Production_PlanServiceImpl implements Production_PlanService {
	@Autowired
	private Production_PlanDao production_PlanDao;
	
	public List<Production_PlanDTO> getProductionPlan(Map<String, Object> map) throws BusinessException {
		try {
			return production_PlanDao.getProductionPlan(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public int addProductionPlan(Production_Plan production_Plan) throws BusinessException {
		try {
			return production_PlanDao.addProductionPlan(production_Plan);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	public int updateProductionPlan(Production_Plan production_Plan) throws BusinessException {
		try {
			return production_PlanDao.updateProductionPlan(production_Plan);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public int delProductionPlan(Production_Plan production_Plan) throws BusinessException {
		try {
			return production_PlanDao.delProductionPlan(production_Plan);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List getPlanNo(Production_Plan production_Plan) throws BusinessException {
		try {
			return production_PlanDao.getPlanNo(production_Plan);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}

	public List<Production_PlanDTO> getProductionPlanById(Production_Plan production_Plan)
			throws BusinessException {
		try {
			return production_PlanDao.getProductionPlanById(production_Plan);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jingpeng.service.Production_PlanService#getProductionPlanById(com.jingpeng.model.Production_Plan)
	 * 通过生产计划id查询生产计划信息
	 * tongn
	 * 2018.6.27
	 */
	public List<Production_PlanDTO> getProdID(Map<String, Object> map)throws BusinessException {
		try {
			
			return production_PlanDao.getProdID(map);
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e);
		}
	}
	
	

}
