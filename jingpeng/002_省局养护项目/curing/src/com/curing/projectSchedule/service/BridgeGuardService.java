package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.BridgeGuardEntity;
import com.curing.projectSchedule.model.BridgeGuardSum;

public interface BridgeGuardService {
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
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
