package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.AsphProportionDao;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.jingpeng.service.AsphProportionService;
import com.jingpeng.service.impl.AsphProportionServiceImpl;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class AsphProportionServiceImpl implements AsphProportionService{

	
	@Autowired
	AsphProportionDao  asphProportionDao;
	
	public List<Asph_TargetProportion> getAsph_TargetProportion(Asph_TargetProportion asph_TargetProportion) throws BusinessException {
		
		try {
			return asphProportionDao.getAsph_TargetProportion(asph_TargetProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_Proportion(Map<String, Object> map)throws BusinessException {
		try {
			return asphProportionDao.getAsphalt_Prod_Proportion(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public int addAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws BusinessException {
		
		try {
			return 	 asphProportionDao.addAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public int updateAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws BusinessException {
		 try {
			return asphProportionDao.updateAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public void deletAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion)throws BusinessException {
		 try {
			asphProportionDao.deletAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Asph_TargetProportion> getAsph_TargetProportionCode(Asph_TargetProportion asph_TargetProportion) throws BusinessException {
		try {
			return asphProportionDao.getAsph_TargetProportionCode(asph_TargetProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public int addAsph_TargetProportion(Asph_TargetProportion asph_TargetProportion) throws BusinessException {
		try {
			return asphProportionDao.addAsph_TargetProportion(asph_TargetProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion)
			throws BusinessException {
		try {
			return asphProportionDao.getAsphalt_Prod_ProportionCode(asphalt_Prod_Proportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
		
	}

	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionById(Map<String, Object> map)
			throws BusinessException {
		try {
			return asphProportionDao.getAsphalt_Prod_ProportionById(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}

	}

	public List<Map> getProduction_Plan(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws BusinessException {
		try {
			return asphProportionDao.getProduction_Plan(asphalt_Prod_Proportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	/*
	 * 查找生产配合比信息
	 * tongn
	 * 2018.6.27
	 */
	
	public List<Asphalt_Prod_Proportion> getAsphalt_Prod_ProportionByGradId(Map<String, Object> map)throws BusinessException {
		try {
			return asphProportionDao.getAsphalt_Prod_ProportionByGradId(map);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Asph_TargetProportion> select_Asph_TargetPropDetailed(Asphalt_Prod_Proportion asphalt_Prod_Proportion) throws BusinessException {
		try {
			return asphProportionDao.select_Asph_TargetPropDetailed(asphalt_Prod_Proportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	
}
