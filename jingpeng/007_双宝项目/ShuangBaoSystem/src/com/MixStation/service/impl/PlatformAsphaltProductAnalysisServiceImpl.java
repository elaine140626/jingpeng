package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformAsphaltProductAnalysisDao;
import com.MixStation.model.ProductAnalysisEntity;
import com.MixStation.service.PlatformAsphaltProductAnalysisService;

@Service
@Transactional
public class PlatformAsphaltProductAnalysisServiceImpl implements PlatformAsphaltProductAnalysisService{
	@Autowired
	private PlatformAsphaltProductAnalysisDao platformAsphaltProductAnalysisDao;
	
	// 产能分析 沥青原材料取得
	public List<ProductAnalysisEntity> getAsphaltMaterialInfo(Map<String, Object> map){
		return platformAsphaltProductAnalysisDao.getAsphaltMaterialInfo(map);
	}
		
	// 产能分析 echar
	public List<ProductAnalysisEntity> getAsphaltProductAnalysisList(Map<String, Object> map){
		return platformAsphaltProductAnalysisDao.getAsphaltProductAnalysisList(map);
	}
	
	// 根据组织机构id查name
	public String getOrgNameById(Map<String, Object> map) {
		return platformAsphaltProductAnalysisDao.getOrgNameById(map);
	}

	//石油比下拉列表
	@Override
	public List<Map<String,Object>> getMaterialList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return platformAsphaltProductAnalysisDao.getMaterialList(map);
	}

	@Override
	public List<Map<String, Object>> getAggregateEchar(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return platformAsphaltProductAnalysisDao.getAggregateEchar(map);
	}
}
