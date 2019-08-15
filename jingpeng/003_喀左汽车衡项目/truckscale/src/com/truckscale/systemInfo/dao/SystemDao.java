package com.truckscale.systemInfo.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.truckscale.systemInfo.model.SystemEntity;

@Repository
public interface SystemDao {

	// 保存系统设置
	int saveSystem(SystemEntity system);
	// 更新系统设置
	int updateSystem(SystemEntity system);
	// 获取系统设置count数
	int getSystemCount();
	// 系统设置赋值
	List<SystemEntity> getSystem();
}
