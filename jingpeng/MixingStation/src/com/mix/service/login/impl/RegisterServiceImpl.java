package com.mix.service.login.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.login.RegisterDao;
import com.mix.model.Organization_Info;
import com.mix.model.User_Info;
import com.mix.service.login.RegisterService;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService{
	@Autowired
	private RegisterDao registerDao;

	public int addUser(User_Info user){
			return registerDao.addUser(user);
	}

	public List<User_Info> getUserName(User_Info user){
			return registerDao.getUserName(user);
	}

	public List<Organization_Info> getOrg(){
			return registerDao.getOrg();
	}

}
