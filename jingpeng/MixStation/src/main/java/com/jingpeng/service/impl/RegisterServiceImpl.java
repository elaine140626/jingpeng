package com.jingpeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.RegisterDao;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.RegisterService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService{
	@Autowired
	private RegisterDao registerDao;

	public int addUser(User_Info user) throws BusinessException {
		try {
			return registerDao.addUser(user);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<User_Info> getUserName(User_Info user) throws BusinessException {
		try {
			return registerDao.getUserName(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Organization_Info> getOrg() throws BusinessException {
		try {
			return registerDao.getOrg();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Map<String, Object>> getTopPlatformOrg() throws BusinessException {
		try {
			return registerDao.getTopPlatformOrg();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Core_User_Info> getPlatformUserName(Core_User_Info core_User_Info) throws BusinessException {
		try {
			return registerDao.getPlatformUserName(core_User_Info);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int addPlatformUser(Core_User_Info core_User_Info) {
		try {
			return registerDao.addPlatformUser(core_User_Info);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
