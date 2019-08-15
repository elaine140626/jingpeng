package com.mix.service.cement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mix.dao.cement.Cement_ProductionDetailedDao;
import com.mix.model.Cement_ProductionDetailed;
import com.mix.service.cement.Cement_ProductionDetailedService;
@Service
public class Cement_ProductionDetailedServiceImpl implements Cement_ProductionDetailedService {

	@Autowired
	private Cement_ProductionDetailedDao cement_ProductionDetailedDao;
	
	@Override
	public List<Cement_ProductionDetailed> getCement_ProductionDetaileds(
			Cement_ProductionDetailed cement_ProductionDetailed) {
		return cement_ProductionDetailedDao.getCement_ProductionDetaileds(cement_ProductionDetailed);
	}

}
