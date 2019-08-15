package com.curing.asphaltSupply.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.asphaltSupply.dao.FirstAsphaltSupplyDao;
import com.curing.asphaltSupply.model.FirstAsphaltSupplyEntity;
import com.curing.asphaltSupply.service.FirstAsphaltSupplyService;

@Service
@Transactional
public class FirstAsphaltSupplyServiceImpl implements FirstAsphaltSupplyService{

	@Autowired
	private FirstAsphaltSupplyDao firstAsphaltSupplyDao;
	
	// 沥青供应list获取
	public List<FirstAsphaltSupplyEntity> getFirstAsphaltSupplyList(Map<String, Object> map) {
		return firstAsphaltSupplyDao.getFirstAsphaltSupplyList(map);
	}

	// 新增沥青供应
	public int insertFirstAsphaltSupply(FirstAsphaltSupplyEntity firstAsphaltSupplyEntity) {
		return firstAsphaltSupplyDao.insertFirstAsphaltSupply(firstAsphaltSupplyEntity);
	}

	// 更新沥青供应
	public int updateFirstAsphaltSupply(FirstAsphaltSupplyEntity firstAsphaltSupplyEntity) {
		return firstAsphaltSupplyDao.updateFirstAsphaltSupply(firstAsphaltSupplyEntity);
	}

}
