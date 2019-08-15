package com.mixingStation.service.userInfo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mixingStation.dao.login.MixUserInfoDao;
import com.mixingStation.model.userInfo.IsSameUser;
import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.userInfo.MixUserInfoService;

@Service
@Transactional
public class MixUserInfoServiceImpl implements MixUserInfoService {
	@Autowired
	private MixUserInfoDao userInfoDao;
	
	@Override
	public List<UserInfo> queryUserInfo(UserInfo userinfo) {
		return userInfoDao.queryUserInfo(userinfo);
	}

	@Override
	public List<IsSameUser> getUserSession(UserInfo userinfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userinfo.getId());
		return userInfoDao.getUserSession(map);
	}

	@Override
	public int delUserSession(UserInfo userinfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userinfo.getId());
		return userInfoDao.delUserSession(map);
	}

	@Override
	public int addUserSession(UserInfo userinfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userinfo.getId());
		return userInfoDao.addUserSession(map);
	}

	@Override
	public List<OrganizationInfo> getOrg() {
		return userInfoDao.getOrg();
	}

	@Override
	public int addUser(UserInfo userInfo) {
		return userInfoDao.addUser(userInfo);
	}

	@Override
	public List<OrganizationInfo> getUser(Map<String, Object> map) {
		return userInfoDao.getUser(map);
	}

}
