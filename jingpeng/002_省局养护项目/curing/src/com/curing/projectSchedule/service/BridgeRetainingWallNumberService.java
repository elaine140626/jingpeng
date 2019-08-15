package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.BridgeRetainingWallNumberEntity;
import com.curing.projectSchedule.model.BridgeRetainingWallNumberSum;

public interface BridgeRetainingWallNumberService {
	// 工程进度（桥梁挡墙工程数量表）List取得
	List<BridgeRetainingWallNumberEntity> getBridgeRetainingWallNumberList(Map<String, Object> map);

	// 工程进度（桥梁挡墙工程数量表）合计
	List<BridgeRetainingWallNumberSum> getBridgeRetainingWallNumberSum(Map<String, Object> map);

	// 新增工程进度（桥梁挡墙工程数量表）
	int insertBridgeRetainingWallNumber(BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity);

	// 更新工程进度（桥梁挡墙工程数量表）
	int updateBridgeRetainingWallNumber(BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity);

	// 删除工程进度（桥梁挡墙工程数量表）
	int deleteBridgeRetainingWallNumber(BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity);
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
