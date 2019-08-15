package com.MixStation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformAsphaltMixProportionDao;
import com.MixStation.model.ApshaltMixProportionEntity;
import com.MixStation.service.PlatformAsphaltMixProportionService;
import com.highwayPlatform.util.MessageUtilBlindSample;
import com.mixingStation.model.ResponseInfo;



@Service
@Transactional
public class PlatformAsphaltMixProportionServiceImpl implements PlatformAsphaltMixProportionService{
	@Autowired
	private PlatformAsphaltMixProportionDao platformAsphaltMixProportionDao;
	
	public List<ApshaltMixProportionEntity> getList(Map<String, Object> map){
		return platformAsphaltMixProportionDao.getList(map);
	}

	@Override
	public int insertPlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mix_Number", apshaltMixProportionEntity.getMix_Number());
		map.put("org_Id", apshaltMixProportionEntity.getOrg_Id());
		List<ApshaltMixProportionEntity> list = platformAsphaltMixProportionDao.getList(map);
		if(list.size()>0) {
			return 0;
		}else {
			return platformAsphaltMixProportionDao.insertPlatformAsphaltMixProportion(apshaltMixProportionEntity);
		}
	}

	@Override
	public int updatePlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mix_Number", apshaltMixProportionEntity.getMix_Number());
		map.put("org_Id", apshaltMixProportionEntity.getOrg_Id());
		List<ApshaltMixProportionEntity> list = platformAsphaltMixProportionDao.getList(map);
		if(list.size()>0) {
			return 0;
		}else {
		return platformAsphaltMixProportionDao.updatePlatformAsphaltMixProportion(apshaltMixProportionEntity);
		}
	}
	@Override
	public int delPlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity) {
		return platformAsphaltMixProportionDao.delPlatformAsphaltMixProportion(apshaltMixProportionEntity);
	}
	
	@Override
	public int update(ApshaltMixProportionEntity apshaltMixProportionEntity) {
		int id = apshaltMixProportionEntity.getId();
		apshaltMixProportionEntity.setId(0);
		platformAsphaltMixProportionDao.update(apshaltMixProportionEntity);
		apshaltMixProportionEntity.setId(id);
	    return platformAsphaltMixProportionDao.update(apshaltMixProportionEntity);
		
	}
}
