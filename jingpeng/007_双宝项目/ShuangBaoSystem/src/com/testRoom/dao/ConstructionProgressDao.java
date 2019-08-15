package com.testRoom.dao;
import java.util.List;
import java.util.Map;

import com.testRoom.model.ActualCompletionQuantity;
import com.testRoom.model.AppBridgeEntity;
import com.testRoom.model.AppRoadEntity;
import com.testRoom.model.BridgeConstructionDetails;
import com.testRoom.model.ConstructionAuthorization;
import com.testRoom.model.EngineeringDesignContent;

public interface ConstructionProgressDao {
	// 获取该用户权限标段下的桥梁明细信息
	List<ConstructionAuthorization> getConstructionList(Map<String, Object> map);
	
	// 查询工程设计量内容 
	List<EngineeringDesignContent> getEngineeringDesignContent(Map<String, Object> map);
	
	// 新增工程设计量内容
	int addEngineeringDesignContent(EngineeringDesignContent engineeringDesignContent);
	
	// 删除工程设计量内容
	int delEngineeringDesignContent(Map<String, Object> map);
	
	// 查询实际完成量
	List<ActualCompletionQuantity> getActualCompletionQuantity(Map<String, Object> map);
	
	// 新增实际完成量
	int addActualCompletionQuantity(ActualCompletionQuantity actualCompletionQuantity);
	
	// 获取插入的实际完成量id
	int getAddId(ActualCompletionQuantity actualCompletionQuantity);

	// 修改实际完成量
	int updateActualCompletionQuantity(ActualCompletionQuantity actualCompletionQuantity);
	
	// 删除实际完成量
	int delActualCompletionQuantity(Map<String, Object> map);
	
	// 获取桥梁施工明细数据
	List<BridgeConstructionDetails> getBridgeConstructionDetails(Map<String, Object> map);
	
	// 新增桥梁施工明细数据
	int addBridgeConstructionDetails(BridgeConstructionDetails bridgeConstructionDetails);
	
	// 修改桥梁施工明细数据
	int updateBridgeConstructionDetails(BridgeConstructionDetails bridgeConstructionDetails);
	
	// 删除桥梁施工明细数据
	int delBridgeConstructionDetails(Map<String, Object> map);
	
	//上传图片
	int uploadPictures(Map<String, String> map);
	
	// 获取累计值
	Double getSum(Map<String, Object> map);
	
	// App接口 路
	AppRoadEntity getAppRoadByUniqueIdentifier(Map<String, Object> map);
	
	// App接口 桥
	AppBridgeEntity getAppBridgeByRoad(Map<String, Object> map);
}
