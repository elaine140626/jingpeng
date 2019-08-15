package com.jingpeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingpeng.dao.Cement_TheoPropDetailedDao;
import com.jingpeng.model.Cement_TheoPropDetailed;
import com.jingpeng.model.Cement_TheoryProportion;
import com.jingpeng.service.Cement_TheoPropDetailedService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class Cement_TheoPropDetailedServiceImpl implements Cement_TheoPropDetailedService{

	
	@Autowired
	private Cement_TheoPropDetailedDao cement_TheoPropDetailedDao;
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed)throws BusinessException {
		
		try {
			return cement_TheoPropDetailedDao.getCement_TheoPropDetailed(cement_TheoPropDetailed);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}
	
	public List<Cement_TheoPropDetailed> getCement_TheoPropDetailedByTheoProp_ID(
			Cement_TheoryProportion cement_TheoryProportion) throws BusinessException {
		try {
			return cement_TheoPropDetailedDao.getCement_TheoPropDetailedByTheoProp_ID(cement_TheoryProportion);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	

	public int updateCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed) throws BusinessException {
		try {
			return cement_TheoPropDetailedDao.updateCement_TheoPropDetailed(cement_TheoPropDetailed);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

	public void deletCement_TheoPropDetailed(Cement_TheoPropDetailed cement_TheoPropDetailed) throws BusinessException {
		try {
			cement_TheoPropDetailedDao.deletCement_TheoPropDetailed(cement_TheoPropDetailed);
		} catch (DataAccessException e) {
			throw new BusinessException(e);
		}
	}

}
