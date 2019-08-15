package com.blindSample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blindSample.dao.TestSelectDao;
import com.blindSample.model.TestSelectEntity;
import com.blindSample.service.TestSelectService;
@Service
@Transactional
public class TestSelectServiceImpl implements TestSelectService{
	@Autowired
	TestSelectDao testSelectDao;
	// 获取页面显示信息
	public List<TestSelectEntity> getInfo(Map<String, Object> map){
		return testSelectDao.getInfo(map);
	};
	
	// 修改试验状态
	public int changeTestState(Map<String, Object> map) {
		return testSelectDao.changeTestState(map);
	}
}