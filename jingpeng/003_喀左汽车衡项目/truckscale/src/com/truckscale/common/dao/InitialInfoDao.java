package com.truckscale.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface InitialInfoDao{	
	// 数据字典取得
	List<Map<String,Object>> getDataDictionary(Map<String, Object> map);
}
