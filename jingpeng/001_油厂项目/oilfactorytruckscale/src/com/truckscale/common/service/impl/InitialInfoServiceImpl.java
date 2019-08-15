package com.truckscale.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truckscale.common.dao.InitialInfoDao;
import com.truckscale.common.service.InitialInfoService;

@Service
@Transactional
public class InitialInfoServiceImpl implements InitialInfoService{
	@Autowired
	private InitialInfoDao initialInfoDao;

	// 数据字典取得
	public List<Map<String,Object>> getDataDictionary(Map<String, Object> map){
		return initialInfoDao.getDataDictionary(map);
	}

	@Override
	public int updateExportMeasure(Map<String, Object> map) {
		return initialInfoDao.updateExportMeasure(map);
	}

}
