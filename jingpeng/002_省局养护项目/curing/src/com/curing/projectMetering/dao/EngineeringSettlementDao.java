package com.curing.projectMetering.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectMetering.model.EngineeringSettlementEntity;
import com.curing.projectMetering.model.EngineeringSettlementSum;

@Repository
public interface EngineeringSettlementDao {
	// 工程计量（工程价款结算帐单）List取得
	List<EngineeringSettlementEntity> getEngineeringSettlementList(Map<String, Object> map);
	
	// 获取工程计量(工程价款结算帐单)合计
	List<EngineeringSettlementSum> getEngineeringSettlementSum(Map<String, Object> map);
	
	// 添加工程计量（工程价款结算帐单）
	int insertEngineeringSettlement(EngineeringSettlementEntity engineeringSettlementEntity);
	
	// 添加工程计量（工程价款结算帐单）
	int updateEngineeringSettlement(EngineeringSettlementEntity engineeringSettlementEntity);
	
	// 添加工程计量（工程价款结算帐单）
	int deleteEngineeringSettlement(EngineeringSettlementEntity engineeringSettlementEntity);
}
