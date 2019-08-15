package com.curing.asphaltSupply.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.asphaltSupply.model.FirstAsphaltSupplyEntity;

@Repository
public interface FirstAsphaltSupplyDao {

	// 沥青供应List获取
	List<FirstAsphaltSupplyEntity> getFirstAsphaltSupplyList(Map<String, Object> map);
	
	// 新增沥青供应
	int insertFirstAsphaltSupply(FirstAsphaltSupplyEntity firstAsphaltSupplyEntity);
	
	// 更新沥青供应
	int updateFirstAsphaltSupply(FirstAsphaltSupplyEntity firstAsphaltSupplyEntity);
}
