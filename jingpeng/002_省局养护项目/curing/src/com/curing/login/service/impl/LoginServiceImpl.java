package com.curing.login.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.login.dao.LoginDao;
import com.curing.login.model.UserInfo;
import com.curing.login.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;
	
	// 获取用户信息
	@Override
	public List<UserInfo> getUserInfo(Map<String, Object> map) {
		
		// 根据条件获取用户信息
		List<UserInfo> dataList = loginDao.getUserInfo(map);
		
		return dataList;
	}

	// 用户名判重复
	@Override
	public int getUserName(Map<String, Object> map) {
		// 获取用户信息
		List<UserInfo> dataList = loginDao.getUserInfo(map);
		
		// 用户名已经存在
		if(dataList != null && dataList.size() > 0) {
			return 1;
		}
		
		return 0;
	}

	// 保存用户信息
	@Override
	public int saveUserInfo(UserInfo userInfo) {
		
		int result = 0;
		if(userInfo.getUserName() != null && !("").equals(userInfo.getUserName())) {
			userInfo.setCreater(userInfo.getUserName());
		}

			result = loginDao.insertUserInfo(userInfo);
		
		return result;
	}

	// 删除用户信息
	@Override
	public int deleteUserInfo(UserInfo userInfo) {
		return loginDao.deleteUserInfo(userInfo);
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		int result = 0;
		if(userInfo.getUserName() != null && !("").equals(userInfo.getUserName())) {
			userInfo.setReviser(userInfo.getUserName());
		}
			result = loginDao.updateUserInfo(userInfo);
		
		return result;
	}

}
