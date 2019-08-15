package com.blindSample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blindSample.dao.TestAutoCollectionDao;
import com.blindSample.model.TestSelectEntity;
import com.blindSample.service.TestAutoCollectionService;
@Service
@Transactional
public class TestAutoCollectionServiceImpl implements TestAutoCollectionService{
	@Autowired
	TestAutoCollectionDao testAutoCollectionDao;
	// 获取页面显示信息
	public List<TestSelectEntity> getInfoList(Map<String, Object> map){
		return testAutoCollectionDao.getInfoList(map);
	};
}
