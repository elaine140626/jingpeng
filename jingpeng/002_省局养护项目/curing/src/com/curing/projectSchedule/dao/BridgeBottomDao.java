package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.curing.projectSchedule.model.BridgeBottomEntity;
import com.curing.projectSchedule.model.BridgeBottomSum;

@Repository
public interface BridgeBottomDao {
	// 工程进度（小桥下部工程量汇总表）List取得
	List<BridgeBottomEntity> getBridgeBottomList(Map<String, Object> map);
	
	// 工程进度（小桥下部工程量汇总表）合计
	List<BridgeBottomSum> getBridgeBottomSum(Map<String, Object> map);
	
	// 新增工程进度（小桥下部工程量汇总表）
	int insertBridgeBottom(BridgeBottomEntity bridgeBottomEntity);
	
	// 更新工程进度（小桥下部工程量汇总表）
	int updateBridgeBottom(BridgeBottomEntity bridgeBottomEntity);
	
	// 删除工程进度（小桥下部工程量汇总表）
	int deleteBridgeBottom(BridgeBottomEntity bridgeBottomEntity);	
	
	List<Map<String, Object>> getBridgeBottomListEX(Map<String, Object> map);
}
