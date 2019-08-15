package com.jingpeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jingpeng.dao.LoginDao;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.LoginService;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;
	
	public List<User_Info> userLogin(User_Info user) throws BusinessException {
		try {
			return loginDao.userLogin(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	public List<Core_User_Info> userLoginPlatform(User_Info user) throws BusinessException {
		try {
			return loginDao.userLoginPlatform(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
}
