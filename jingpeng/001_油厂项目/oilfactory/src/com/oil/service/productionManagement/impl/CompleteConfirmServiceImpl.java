package com.oil.service.productionManagement.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.productionManagement.CompleteConfirmDao;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.service.productionManagement.CompleteConfirmService;

@Service
public class CompleteConfirmServiceImpl implements CompleteConfirmService{

	@Autowired
	CompleteConfirmDao completeConfirmDao;
	
	// 生产完成确认
	public List<NextProductionPlanEntity> getCompleteConfirm(Map<String, Object> map) {
		return completeConfirmDao.getCompleteConfirm(map);
	}
	
}
