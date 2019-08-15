package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformCementProductAnalysisDao;
import com.MixStation.model.ProductAnalysisEntity;
import com.MixStation.service.PlatformCementProductAnalysisService;

@Service
@Transactional
public class PlatformCementProductAnalysisServiceImpl implements PlatformCementProductAnalysisService{
	@Autowired
	private PlatformCementProductAnalysisDao platformCementProductAnalysisDao;
	
	// 产能分析 水泥原材料取得
	public List<ProductAnalysisEntity> getCementMaterialInfo(Map<String, Object> map){
		return platformCementProductAnalysisDao.getCementMaterialInfo(map);
	}
		
	// 产能分析 echar
	public List<ProductAnalysisEntity> getCementProductAnalysisList(Map<String, Object> map){
		return platformCementProductAnalysisDao.getCementProductAnalysisList(map);
	}	
}
