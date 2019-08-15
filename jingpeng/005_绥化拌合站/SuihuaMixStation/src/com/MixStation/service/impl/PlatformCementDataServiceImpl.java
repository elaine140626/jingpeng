package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformCementDataDao;
import com.MixStation.model.CementGradDataAnalysis;
import com.MixStation.model.CementPropDataAnalysisEntity;
import com.MixStation.model.PlatformCementDataEntity;
import com.MixStation.model.PlatformCementMaterialsConsumptionEntity;
import com.MixStation.service.PlatformCementDataService;

@Service
@Transactional
public class PlatformCementDataServiceImpl implements PlatformCementDataService{
	@Autowired
	private PlatformCementDataDao platformCementDataDao;
	
	// 沥青拌合站树
	public List<Map<String,Object>> getOrgId(Map<String, Object> map){
		return platformCementDataDao.getOrgId(map);
	}
		
	// 查询生产数据列表
	public List<PlatformCementDataEntity> getPlatformCementData(Map<String, Object> map){
		return platformCementDataDao.getPlatformCementData(map);
	}
	
	// 采集数据明细
	public List<CementPropDataAnalysisEntity> getCementPropDataAnalysis(Map<String, Object> map){
		return platformCementDataDao.getCementPropDataAnalysis(map);
	}
	
	// 原材料消耗
	public List<PlatformCementMaterialsConsumptionEntity> getVMaterialConsumption(Map<String, Object> map){
		return platformCementDataDao.getVMaterialConsumption(map);
	}
	//查询index沥青拌合站数据
	public List<Map<String, Object>> getIndexLq(Map<String, Object> map){
		return platformCementDataDao.getIndexLq(map);
	}
	//查询index水泥拌合站数据
	public List<Map<String, Object>> getIndexSn(Map<String, Object> map){
		return platformCementDataDao.getIndexSn(map);
	}
	//查询index水稳拌合站数据
	public List<Map<String, Object>> getIndexSw(Map<String, Object> map){
		return platformCementDataDao.getIndexSw(map);
	}
	
}
