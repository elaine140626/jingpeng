package com.mix.service.login.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.login.UserDao;
import com.mix.model.Core_User_Info;
import com.mix.model.User_Info;
import com.mix.service.login.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	public List<User_Info> getUserinfo(User_Info user){
		try {
			return userDao.getUserinfo(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Map<String, String>> getOrgName(User_Info user){
		try {
			return userDao.getOrgName(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updateUser(User_Info user){
		try {
			return userDao.updateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int del(User_Info user){
		try {
			return userDao.del(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<Core_User_Info> getPlatformUserinfo(Core_User_Info user){
		try {
			return userDao.getPlatformUserinfo(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Map<String, String>> getPlatformOrgName(List<Integer> orgids){
		try {
			return userDao.getPlatformOrgName(orgids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Core_User_Info> getPlatformShowInfo(Core_User_Info cuser){
		try {
			return userDao.getPlatformUserinfo(cuser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updatePlatformUser(Core_User_Info user){
		try {
			return userDao.updatePlatformUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int delPlatform(Core_User_Info user){
		try {
			return userDao.delPlatform(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
