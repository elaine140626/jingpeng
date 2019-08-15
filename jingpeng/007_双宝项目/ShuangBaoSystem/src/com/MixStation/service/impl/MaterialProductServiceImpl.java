package com.MixStation.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.MaterialProductDao;
import com.MixStation.dao.PlatformAsphaltDataDao;
import com.MixStation.model.AsphaltGradDataAnalysis;
import com.MixStation.model.AsphaltPropDataAnalysisEntity;
import com.MixStation.model.MaterialProductEntity;
import com.MixStation.model.PlatformAsphaltDataEntity;
import com.MixStation.model.PlatformAsphaltMaterialsConsumptionEntity;
import com.MixStation.service.MaterialProductService;
import com.MixStation.service.PlatformAsphaltDataService;
import com.mixingStation.model.materialInfo.MaterialInfo;

@Service
@Transactional
public class MaterialProductServiceImpl implements MaterialProductService{
	@Autowired
	private MaterialProductDao materialProductDao;
	
	@Override
	public List<MaterialProductEntity> getMaterialProduct(Map<String, Object> map) {
		
		return materialProductDao.getMaterialProduct(map);
	}
	
	
	
}
