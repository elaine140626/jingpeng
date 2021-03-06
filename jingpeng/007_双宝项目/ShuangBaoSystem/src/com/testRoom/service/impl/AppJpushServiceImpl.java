package com.testRoom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testRoom.dao.AppJpushDao;
import com.testRoom.service.AppJpushService;

@Service
@Transactional
public class AppJpushServiceImpl implements AppJpushService{
	@Autowired
	AppJpushDao appJpushDao;
	// 获取页面显示信息
	public List<Map<String, Object>> getAppUser(Map<String, Object> UniqueIdentifier){
		return appJpushDao.getAppUser(UniqueIdentifier);
	};
	
	// 获取将要推送的数据
	public List<Map<String, Object>> getPushInfo(){
		return appJpushDao.getPushInfo();
	};
	
	// 修改推送消息状态
	public int updatePushState(String id){
		return appJpushDao.updatePushState(id);
	};
	
	// 自动采集推送数据
	public List<Map<String, Object>> getAppData(Map<String, Object> map){
		return appJpushDao.getAppData(map);
	}
}
