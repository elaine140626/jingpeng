package com.oil.service.userinfo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.userInfo.UserInfoDao;
import com.oil.model.Userinfo;
import com.oil.service.userinfo.UserinfoService;
@Service
public class UserinfoServiceImpl implements UserinfoService{

	@Autowired
	UserInfoDao userInfoDao;
	
	@Override
	public List<Userinfo> getAppUser(Map<String, Object> maps) {
		
		return userInfoDao.getAppUser(maps);
	}

}
