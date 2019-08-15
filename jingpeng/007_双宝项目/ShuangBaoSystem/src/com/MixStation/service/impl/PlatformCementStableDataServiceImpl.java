package com.MixStation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformCementStableDataDao;
import com.MixStation.model.CementStablePropDataAnalysisEntity;
import com.MixStation.model.PlatformCementStableDataEntity;
import com.MixStation.service.PlatformCementStableDataService;

@Service
@Transactional
public class PlatformCementStableDataServiceImpl implements PlatformCementStableDataService {
	@Autowired
	private PlatformCementStableDataDao platformCementStableDataDao;
	
	// 水泥稳定土拌合站树
	@Override
	public List<Map<String, Object>> getOrgId(Map<String, Object> map) {
		return platformCementStableDataDao.getOrgId(map);
	}

	// 查询水泥稳定土生产数据列表
	@Override
	public List<PlatformCementStableDataEntity> getPlatformCementStableData(Map<String, Object> map) {
		return platformCementStableDataDao.getPlatformCementStableData(map);
	}

	// 水泥稳定土采集数据明细
	@Override
	public List<CementStablePropDataAnalysisEntity> getCementStablePropDataAnalysis(Map<String, Object> map) {
		return platformCementStableDataDao.getCementStablePropDataAnalysis(map);
	}

}
