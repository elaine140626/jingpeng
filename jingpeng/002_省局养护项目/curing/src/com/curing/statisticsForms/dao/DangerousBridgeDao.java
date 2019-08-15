package com.curing.statisticsForms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.statisticsForms.model.DangerousBridge;
import com.curing.statisticsForms.model.DangerousBridgeEntity;
import com.curing.statisticsForms.model.DangerousBridgeSum;

@Repository
public interface DangerousBridgeDao {	
	// 险桥List取得
	List<DangerousBridgeEntity> getDangerousBridgeList(Map<String, Object> map);
	
	// 险桥合计
	List<DangerousBridgeSum> getDangerousBridgeSum(Map<String, Object> map);
	
	// 险桥 单条取得
	List<DangerousBridge> getDangerousBridgeByCityId(Map<String, Object> map);
	
	// 新增险桥
	int insertDangerousBridge(DangerousBridge dangerousBridge);
	
	// 删除险桥
	int deleteDangerousBridge(DangerousBridge dangerousBridge);

}
