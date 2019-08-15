package com.oil.dao.system;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oil.model.system.Prefix;

//编号管理
public interface SerialNumberDao {
	//添加编号管理前缀
	int adSerialNumber(Prefix prefix);
	//修改编号管理前缀
	int updateSerialNumber(Prefix prefix);
	//查询所有前缀编号
	List<Prefix> getAllPrefix();
	
	Prefix getContractInfoPrefix(@Param(value="types") String types);
}
