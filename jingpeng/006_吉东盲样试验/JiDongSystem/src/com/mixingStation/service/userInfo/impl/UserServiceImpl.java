package com.mixingStation.service.userInfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mixingStation.dao.login.UserDao;
import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.userInfo.UserService;



@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	public List<UserInfo> getUserinfo(UserInfo user){
			return userDao.getUserinfo(user);
	}

	public List<OrganizationInfo> getOrgName(UserInfo user){
			return userDao.getOrgName(user);
	}

	public int updateUser(UserInfo user){
			return userDao.updateUser(user);
	}

	public int del(UserInfo user){
		return userDao.del(user);
	}



}
