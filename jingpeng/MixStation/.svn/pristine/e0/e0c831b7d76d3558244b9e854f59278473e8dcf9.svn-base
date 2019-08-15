package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.UserDao;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.UseService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class UserServiceImpl implements UseService{
	@Autowired
	private UserDao userDao;

	public List<User_Info> getUserinfo(User_Info user) throws BusinessException {
		try {
			return userDao.getUserinfo(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Map<String, String>> getOrgName(User_Info user) throws BusinessException {
		try {
			return userDao.getOrgName(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updateUser(User_Info user) throws BusinessException {
		try {
			return userDao.updateUser(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int del(User_Info user) throws BusinessException {
		try {
			return userDao.del(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<Core_User_Info> getPlatformUserinfo(Core_User_Info user) throws BusinessException{
		try {
			return userDao.getPlatformUserinfo(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Map<String, String>> getPlatformOrgName(List<Integer> orgids) throws BusinessException {
		try {
			return userDao.getPlatformOrgName(orgids);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Core_User_Info> getPlatformShowInfo(Core_User_Info cuser) throws BusinessException {
		try {
			return userDao.getPlatformUserinfo(cuser);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updatePlatformUser(Core_User_Info user) throws BusinessException {
		try {
			return userDao.updatePlatformUser(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int delPlatform(Core_User_Info user) throws BusinessException {
		try {
			return userDao.delPlatform(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
