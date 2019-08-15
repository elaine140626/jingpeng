package com.curing.projectMetering.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectMetering.dao.EngineeringSettlementDao;
import com.curing.projectMetering.model.EngineeringSettlementEntity;
import com.curing.projectMetering.model.EngineeringSettlementSum;
import com.curing.projectMetering.service.EngineeringSettlementService;

@Service
@Transactional
public class EngineeringSettlementServiceImpl implements EngineeringSettlementService{

	@Autowired
	private EngineeringSettlementDao engineeringSettlementDao;
	
	// 工程计量（工程价款结算帐单）List取得
	public List<EngineeringSettlementEntity> getEngineeringSettlementList(Map<String, Object> map) {
		return engineeringSettlementDao.getEngineeringSettlementList(map);
	}

	// 新增计量（工程价款结算帐单）List取得
	public int insertEngineeringSettlement(EngineeringSettlementEntity engineeringSettlementEntity) {
		return engineeringSettlementDao.insertEngineeringSettlement(engineeringSettlementEntity);
	}
	
	// 更新计量（工程价款结算帐单）List取得
	public int updateEngineeringSettlement(EngineeringSettlementEntity engineeringSettlementEntity) {
		return engineeringSettlementDao.updateEngineeringSettlement(engineeringSettlementEntity);
	}

	// 删除计量（工程价款结算帐单）List取得
	public int deleteEngineeringSettlement(EngineeringSettlementEntity engineeringSettlementEntity) {
		return engineeringSettlementDao.deleteEngineeringSettlement(engineeringSettlementEntity);
	}

	// 获取工程计量（工程价款结算帐单）合计值
	public List<EngineeringSettlementSum> getEngineeringSettlementSum(Map<String, Object> map) {
		return engineeringSettlementDao.getEngineeringSettlementSum(map);
	}
}
