package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.CementProductionPlanDao;
import com.jingpeng.model.Cement_Production_Plan;
import com.jingpeng.model.Cement_Production_PlanDTO;
import com.jingpeng.model.Production_Plan;
import com.jingpeng.service.CementProductionPlanService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class CementProductionPlanServiceImpl implements CementProductionPlanService {
	@Autowired
	private CementProductionPlanDao cementProductionPlanDao;

	public List<Cement_Production_PlanDTO> getCementProductionPlan(HashMap<String, Object> map)
			throws BusinessException {
		try {
			return cementProductionPlanDao.getCementProductionPlan(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Transactional
	public int addCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException {
		try {
			return cementProductionPlanDao.addCementProductionPlan(cement_Production_PlanDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List getPlanNo(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException {
		try {
			return cementProductionPlanDao.getPlanNo(cement_Production_PlanDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Cement_Production_PlanDTO> getBunkerCode(Cement_Production_PlanDTO cement_Production_PlanDTO)
			throws BusinessException {
		try {
			return cementProductionPlanDao.getBunkerCode(cement_Production_PlanDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Transactional
	public int updateCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO)
			throws BusinessException {
		try {
			return cementProductionPlanDao.updateCementProductionPlan(cement_Production_PlanDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Transactional
	public int delCementProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException {
		try {
			return cementProductionPlanDao.delCementProductionPlan(cement_Production_PlanDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Transactional
	public int zfProductionPlan(Cement_Production_PlanDTO cement_Production_PlanDTO) throws BusinessException {
		try {
			return cementProductionPlanDao.zfProductionPlan(cement_Production_PlanDTO);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	public List<Map<String, Object>> getCementProductionPlanById(Cement_Production_PlanDTO cement_Production_PlanDTO)throws BusinessException {
		
		try {
			return cementProductionPlanDao.getCementProductionPlanById(cement_Production_PlanDTO);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List getProductionById(HashMap<String, Object> map)
			throws BusinessException {
		try {
			return cementProductionPlanDao.getProductionById(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	public List getProductionTwoById(HashMap<String, Object> map)
			throws BusinessException {
		try {
			return cementProductionPlanDao.getProductionTwoById(map);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

}
