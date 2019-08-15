package com.oil.service.screenDisplay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.screenDisplay.QualityDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.Exportmeasure;
import com.oil.service.screenDisplay.QualityService;
@Service
public class QualityServiceImpl implements QualityService {
	
	@Autowired
	private QualityDao qualityDao;

	@Override
	public List<Exportmeasure> getAllBeforeQuality() {
		return qualityDao.getAllBeforeQuality();
	}

	@Override
	public List<NextProductionPlanEntity> getAllPlanQuality() {
		return qualityDao.getAllPlanQuality();
	}

}
