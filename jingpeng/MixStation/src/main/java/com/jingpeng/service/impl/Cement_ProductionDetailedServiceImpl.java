package com.jingpeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingpeng.dao.Cement_ProductionDetailedDao;
import com.jingpeng.model.Cement_ProductionDetailed;
import com.jingpeng.service.Cement_ProductionDetailedService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
public class Cement_ProductionDetailedServiceImpl implements Cement_ProductionDetailedService{

	@Autowired
	private Cement_ProductionDetailedDao cement_ProductionDetailedDao;
	
	public List<Cement_ProductionDetailed> getCement_ProductionDetaileds(Cement_ProductionDetailed cement_ProductionDetailed) throws BusinessException {
		try {
			return cement_ProductionDetailedDao.getCement_ProductionDetaileds(cement_ProductionDetailed);
		} catch (DataAccessException e) {
			throw  new BusinessException(e);
		}
	}

}
