package com.testRoom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testRoom.dao.IndexDao;
import com.testRoom.service.IndexService;

@Service
@Transactional
public class IndexServiceImpl implements IndexService{
	@Autowired
	private IndexDao indexDao;
	//查询当前用户权限的实验室
	public List<Map<String, Object>> getTestRoomName(Map<String, Object> map){
		return indexDao.getTestRoom(map);

	}
	//查询index试验室数据
	public List<Map<String, Object>> getIndexSummary(Map<String, Object> map){
		return indexDao.getIndexSummary(map);
	}
	
}
