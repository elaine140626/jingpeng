package com.highwayPlatform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.highwayPlatform.dao.JpushDao;
import com.highwayPlatform.service.JpushService;

@Service
@Transactional
public class JpushServiceImpl implements JpushService{
	
	@Autowired
	private JpushDao jpushDao;

	// 获取用户id
	public List<Map<String, Object>> getAppUser (Map<String, Object> map){
		return jpushDao.getAppUser(map);
	}

}
