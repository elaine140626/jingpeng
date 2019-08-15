package com.truckscale.weighingManagement.dao;

import java.util.Map;

public interface SerialNumberDao {

	// 获取流水号
	String getSerialNumber();
	
	// 插入流水号
	int insertSerialNumber(Map<String, Object> map);
}
