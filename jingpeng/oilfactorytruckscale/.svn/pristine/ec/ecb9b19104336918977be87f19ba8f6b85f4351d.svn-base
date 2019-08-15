package com.truckscale.systemInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckscale.systemInfo.dao.UserManageDao;
import com.truckscale.systemInfo.model.UserManageEntity;
import com.truckscale.systemInfo.service.UserManageService;

@Service
@Transactional
public class UserManageServiceImpl implements UserManageService {

	@Autowired
	private UserManageDao userManageDao;
	
	@Override
	public List<UserManageEntity> getUserInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return userManageDao.getUserInfo(param);
	}

}
