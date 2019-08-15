package com.truckscale.systemInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckscale.systemInfo.dao.SystemDao;
import com.truckscale.systemInfo.model.SystemEntity;
import com.truckscale.systemInfo.service.SystemService;

@Service
@Transactional
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDao systemDao;
	
	// 保存系统设置
	@Override
	public int saveSystem(SystemEntity system) {
		int result = 0;
		int count = systemDao.getSystemCount();
		if(count > 0 ) {
	    result = systemDao.updateSystem(system);
		}else {
		result = systemDao.saveSystem(system);
		}
		return result;
	}

	// 系统设置赋值
	@Override
	public List<SystemEntity> getSystem() {
		return systemDao.getSystem();
	}

}
