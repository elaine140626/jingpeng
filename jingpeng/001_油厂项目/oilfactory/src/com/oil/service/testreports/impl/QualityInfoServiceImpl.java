package com.oil.service.testreports.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.testreports.QualityInfoDao;
import com.oil.service.testreports.QualityInfoService;

@Service
public class QualityInfoServiceImpl implements QualityInfoService{

	@Autowired
	private QualityInfoDao qualityInfoDao;
	
	@Override
	public List<Map<String, Object>> queryQualityInfo() {
		// TODO Auto-generated method stub
		return qualityInfoDao.queryQualityInfo();
	}
}
