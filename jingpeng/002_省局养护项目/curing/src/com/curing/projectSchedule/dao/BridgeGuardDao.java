package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.curing.projectSchedule.model.BridgeGuardEntity;
import com.curing.projectSchedule.model.BridgeGuardSum;

@Repository
public interface BridgeGuardDao {
	// 工程进度（小桥防护工程及附属设施工程量汇总表）List取得
	List<BridgeGuardEntity> getBridgeGuardList(Map<String, Object> map);
	
	// 工程进度（小桥防护工程及附属设施工程量汇总表）合计
	List<BridgeGuardSum> getBridgeGuardSum(Map<String, Object> map);
	
	// 新增工程进度（小桥防护工程及附属设施工程量汇总表）
	int insertBridgeGuard(BridgeGuardEntity bridgeGuardEntity);
	
	// 更新工程进度（小桥防护工程及附属设施工程量汇总表）
	int updateBridgeGuard(BridgeGuardEntity bridgeGuardEntity);
	
	// 删除工程进度（小桥防护工程及附属设施工程量汇总表）
	int deleteBridgeGuard(BridgeGuardEntity bridgeGuardEntity);
	
	List<Map<String, Object>> getBridgeGuardListEX(Map<String, Object> map);
}
