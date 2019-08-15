package com.jingpeng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.OrganizationInfoDao;
import com.jingpeng.dao.UserInfoDao;
import com.jingpeng.model.UserInfo;
import com.jingpeng.service.OrganizationInfoService;
import com.jingpeng.service.UserInfoService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoDao userInfoDao;

	/*
	 * (non-Javadoc)
	 * @see com.jingpeng.service.UserInfoService#getUserInfo(java.util.Map)
	    * 获取用户信息
	 * tongn
	 * 2018.7.16
	 */
	public UserInfo getUserInfo(Map<String, Object> param) throws BusinessException {
		
		try {
			
			UserInfo userInfo = userInfoDao.getUserInfo(param);
						
			return userInfo;
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	public int addUserInfo(UserInfo user) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			return userInfoDao.addUserInfo(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<UserInfo> userLogin(UserInfo user) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			return userInfoDao.userLogin(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Map<String, Object>> getUserinfo(Map<String, Object> user) throws BusinessException {
		// TODO Auto-generated method stub
		try {
		return userInfoDao.getUserInfo1(user);
		} catch (DataAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new BusinessException(e);
		}
	}
	public int delUserInfo(UserInfo user) throws BusinessException {
		try {
			return userInfoDao.delUserInfo(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	public List<Map<String, Object>> getUserinfoById(Map<String, Object> user) throws BusinessException {
		try {
		return userInfoDao.getUserinfoById(user);
		} catch (DataAccessException e) {
		e.printStackTrace();
		throw new BusinessException(e);
		}
	}

	public int updateUser(UserInfo user) throws BusinessException {
		try {
			return userInfoDao.updateUser(user);
			} catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessException(e);
			}
	}





}
