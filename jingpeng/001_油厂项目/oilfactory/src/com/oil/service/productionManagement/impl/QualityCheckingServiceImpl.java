package com.oil.service.productionManagement.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.productionManagement.QualityCheckingDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.service.productionManagement.QualityCheckingService;

@Service
public class QualityCheckingServiceImpl implements QualityCheckingService{

	@Autowired
	QualityCheckingDao qualityCheckingDao;
	
    // 提交质检申请
	public List<NextProductionPlanEntity> getQualityCheck(Map<String, Object> map) {
		return qualityCheckingDao.getQualityCheck(map);
	}
}
