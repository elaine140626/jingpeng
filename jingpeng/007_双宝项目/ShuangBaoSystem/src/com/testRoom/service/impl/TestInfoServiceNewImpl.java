package com.testRoom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testRoom.dao.TestInfoDao;
import com.testRoom.dao.TestInfoDaoNew;
import com.testRoom.service.TestInfoService;
import com.testRoom.service.TestInfoServiceNew;


@Service
@Transactional
public class TestInfoServiceNewImpl implements TestInfoServiceNew{
	@Autowired
	private TestInfoDaoNew testInfoDao;
	public List<Map<String, Object>> getTestRoomName(Map<String, Object> map){
		return testInfoDao.getTestRoom(map);

	}
	
	public List<Map<String, Object>> getIndexSummary(Map<String, Object> map){
		return testInfoDao.getIndexSummary(map);
	}
	
	public List<Map<String, Object>> getTestSummary(Map<String, Object> map){
		return testInfoDao.getTestSummary(map);
	}
	
	public List<Map<String, Object>> getTestSummaryDetailed(Map<String, Object> map){
		return testInfoDao.getTestSummaryDetailed(map);
	}
	
	public List<Map<String, Object>> getTestSummaryDetailedNumber(Map<String, Object> map){
		return testInfoDao.getTestSummaryDetailedNumber(map);
	}
	public List<Map<String, Object>> getChart(Map<String, Object> map){
		return testInfoDao.getChart(map);
	}
}
