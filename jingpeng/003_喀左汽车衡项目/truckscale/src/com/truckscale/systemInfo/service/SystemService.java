package com.truckscale.systemInfo.service;

import java.util.List;

import com.truckscale.systemInfo.model.SystemEntity;

public interface SystemService {

	// 保存系统设置
	int saveSystem(SystemEntity system);
	// 系统设置赋值
	List<SystemEntity> getSystem();
	
}
