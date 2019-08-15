package com.testRoom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testRoom.dao.BlindSampleDetectionDao;
import com.testRoom.model.TestSelectEntity;
import com.testRoom.service.BlindSampleDetectionService;
@Service
@Transactional
public class BlindSampleDetectionServiceImpl implements BlindSampleDetectionService{
	@Autowired
	BlindSampleDetectionDao blindSampleDetectionDao;
	// 获取页面显示信息
	public List<TestSelectEntity> getInfoList(Map<String, Object> map){
		return blindSampleDetectionDao.getInfoList(map);
	};
}
