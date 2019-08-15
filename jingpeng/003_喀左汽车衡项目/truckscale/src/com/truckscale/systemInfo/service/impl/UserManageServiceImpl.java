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

	// 用户名判重复
	@Override
	public int getUserName(Map<String, Object> map) {
		// 获取用户信息
		List<UserManageEntity> dataList = userManageDao.getUserInfo(map);
		
		// 用户名已经存在
		if(dataList != null && dataList.size() > 0) {
			return 1;
		}
		
		return 0;
	}

	// 保存用户信息
	@Override
	public int saveUserInfo(UserManageEntity userInfo) {
		
		int result = 0;
		if(userInfo.getUserName() != null && !("").equals(userInfo.getUserName())) {
			userInfo.setCreater(userInfo.getUserName());
		}

			result = userManageDao.insertUserInfo(userInfo);
		
		return result;
	}

	@Override
	public int updateUserInfo(UserManageEntity info) {
		// TODO Auto-generated method stub
		return userManageDao.updateUserInfo(info);
	}

	@Override
	public int deleteUserInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return userManageDao.deleteUserInfo(param);
	}
	
	@Override
	public int updatePassWord(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return userManageDao.updatePassWord(param);
	}

	@Override
	public int updatePass(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return userManageDao.updatePass(param);
	}
	

}
