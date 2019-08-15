package com.oil.service.screenDisplay.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.screenDisplay.Screen2Dao;
import com.oil.model.screenDisplay.Screen2Entity;
import com.oil.service.screenDisplay.Screen2Service;

@Service
public class Screen2ServiceImpl implements Screen2Service {
	@Autowired
	Screen2Dao screen2Dao;
	// 总数统计
	public List<Map<String, Object>> getCountList(Map<String, Object> map){
		return screen2Dao.getCountList(map);
	}
	
	// 出库车辆信息List
	public List<Screen2Entity> getScreen2List(Map<String, Object> map){
		return screen2Dao.getScreen2List(map);
	}
	
	// echar
	public List<Map<String, Object>> getEchar(Map<String, Object> map){
		return screen2Dao.getEchar(map);
	}
}
