package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.BridgeTopEntity;
import com.curing.projectSchedule.model.BridgeTopSum;

public interface BridgeTopService {
	// 工程进度（小桥上部工程量汇总表）List取得
	List<BridgeTopEntity> getBridgeTopList(Map<String, Object> map);
	
	// 工程进度（小桥上部工程量汇总表）合计
	List<BridgeTopSum> getBridgeTopSum(Map<String, Object> map);
	
	// 新增工程进度（小桥上部工程量汇总表）
	int insertBridgeTop(BridgeTopEntity bridgeTopEntity);
	
	// 更新工程进度（小桥上部工程量汇总表）
	int updateBridgeTop(BridgeTopEntity bridgeTopEntity);
	
	// 删除工程进度（小桥上部工程量汇总表）
	int deleteBridgeTop(BridgeTopEntity bridgeTopEntity);	
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
