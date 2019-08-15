package com.curing.statisticsForms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.statisticsForms.dao.DangerousBridgeDao;
import com.curing.statisticsForms.model.DangerousBridge;
import com.curing.statisticsForms.model.DangerousBridgeEntity;
import com.curing.statisticsForms.model.DangerousBridgeSum;
import com.curing.statisticsForms.service.DangerousBridgeService;


@Service
@Transactional
public class DangerousBridgeServiceImpl implements DangerousBridgeService {
	
	@Autowired
	private DangerousBridgeDao dangerousBridgeDao;

	// 险桥List取得
	public List<DangerousBridgeEntity> getDangerousBridgeList(Map<String, Object> map) {
		return dangerousBridgeDao.getDangerousBridgeList(map);
	}
	
	// 险桥合计
	public List<DangerousBridgeSum> getDangerousBridgeSum(Map<String, Object> map){
		return dangerousBridgeDao.getDangerousBridgeSum(map);
	}
	
	// 险桥 单条取得
	public List<DangerousBridge> getDangerousBridgeByCityId(Map<String, Object> map){
		return dangerousBridgeDao.getDangerousBridgeByCityId(map);
	}

	// 新增险桥
	public int insertDangerousBridge(DangerousBridge dangerousBridge) {
		int res = 0;		
		res += dangerousBridgeDao.deleteDangerousBridge(dangerousBridge);
		res += dangerousBridgeDao.insertDangerousBridge(dangerousBridge);		
		return res;
	}
}
