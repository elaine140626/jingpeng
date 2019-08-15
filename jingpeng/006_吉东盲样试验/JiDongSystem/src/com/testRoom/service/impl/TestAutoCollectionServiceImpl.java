package com.testRoom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.highwayPlatform.model.DataTablesResponseInfo;
import com.testRoom.dao.TestAutoCollectionDao;
import com.testRoom.model.TestSelectEntity;
import com.testRoom.service.TestAutoCollectionService;
@Service
@Transactional
public class TestAutoCollectionServiceImpl implements TestAutoCollectionService{
	@Autowired
	TestAutoCollectionDao testAutoCollectionDao;
	// 获取页面显示信息
	public List<TestSelectEntity> getInfoList(Map<String, Object> map){
		return testAutoCollectionDao.getInfoList(map);
	}
	@Override
	public List<TestSelectEntity> getSNHNList(Map<String, Object> map) {
		return testAutoCollectionDao.getSNHNList(map);
	}
	@Override
	public List<TestSelectEntity> getBJList(Map<String, Object> map) {
		return testAutoCollectionDao.getBJList(map);
	}
	@Override
	public List<TestSelectEntity> getKQSLList(Map<String, Object> map) {
		return testAutoCollectionDao.getKQSLList(map);
	}
	@Override
	public List<TestSelectEntity> getKLList(Map<String, Object> map) {
		return testAutoCollectionDao.getKLList(map);
	}
	@Override
	public List<TestSelectEntity> getLQZRList(Map<String, Object> map) {
		return testAutoCollectionDao.getLQZRList(map);
	}
	@Override
	public List<TestSelectEntity> getLQRHDList(Map<String, Object> map) {
		return testAutoCollectionDao.getLQRHDList(map);
	}
	@Override
	public List<TestSelectEntity> getLQMXList(Map<String, Object> map) {
		return testAutoCollectionDao.getLQMXList(map);
	};
}
