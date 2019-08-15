package com.blindSample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blindSample.dao.TestInfoDao;
import com.blindSample.service.TestInfoService;


@Service
@Transactional
public class TestInfoServiceImpl implements TestInfoService{
	@Autowired
	private TestInfoDao testInfoDao;
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
	
	public List<Map<String, Object>> getCollection(Map<String, Object> map){
		return testInfoDao.getCollection(map);
	}
	
	public List<Map<String, Object>> getCollectionSubtable(Map<String, Object> map){
		return testInfoDao.getCollectionSubtable(map);
	}
	
	public List<Map<String, Object>> getChart(Map<String, Object> map){
		return testInfoDao.getChart(map);
	}
}
