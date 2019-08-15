package com.mix.service.login.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mix.dao.login.LoginDao;
import com.mix.model.IsSameUser;
import com.mix.model.User_Info;
import com.mix.service.login.LoginService;
@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;
	
	public List<User_Info> userLogin(User_Info user){
			return loginDao.userLogin(user);
}

	@Override
	public int addUserSession(User_Info userInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getI_id());
		return loginDao.addUserSession(map);
	}

	@Override
	public List<IsSameUser> getUserSession(User_Info userInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getI_id());
		return loginDao.getUserSession(map);
	}

	@Override
	public int delUserSession(User_Info userInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getI_id());
		return loginDao.delUserSession(map);
	}
}
