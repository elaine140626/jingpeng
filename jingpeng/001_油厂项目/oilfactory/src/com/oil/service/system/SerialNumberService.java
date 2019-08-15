package com.oil.service.system;

import java.util.List;
import java.util.Map;

import com.oil.model.system.Prefix;

public interface SerialNumberService {
	//添加编号管理前缀
	int adSerialNumber(Map<String, Object> param);
	//修改编号管理前缀
	int updateSerialNumber(Map<String, Object> param);
	//查询所有前缀编号
	List<Prefix> getAllPrefix();
	
	Prefix getContractInfoPrefix(String types);
	
	int updateContractInfoPrefix(String types);
}
