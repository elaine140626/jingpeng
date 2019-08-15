package com.testRoom.service;

import java.util.List;
import java.util.Map;

import com.highwayPlatform.model.ResponseInfo;
import com.testRoom.model.ActualCompletionQuantity;
import com.testRoom.model.AppBridgeEntity;
import com.testRoom.model.AppRoadEntity;
import com.testRoom.model.ConstructionAuthorization;
import com.testRoom.model.EngineeringDesignContent;

public interface ConstructionProgressService {

	// 获取该用户权限标段下的桥梁明细信息
	List<ConstructionAuthorization> getConstructionList(Map<String, Object> map);

	// 查询实际工程量
	List<ActualCompletionQuantity> getActualCompletionQuantity(Map<String, Object> map);
	
	// 查询工程设计量内容 
	List<EngineeringDesignContent> getEngineeringDesignContent(Map<String, Object> map);
	
	// 获取路和桥梁明细
	Map<String, Object> getInfoById(Map<String, Object> map);
	
	// 新增工程设计量内容 
    ResponseInfo addEngineeringDesignContent(EngineeringDesignContent engineeringDesignContent);
	
	// 新增施工进度内容
	ResponseInfo addInfo(Map<String, Object> map);
	
	// 修改施工进度内容
	ResponseInfo updateInfo(Map<String, Object> map);
	
	// 删除施工进度内容
	ResponseInfo delInfo(Map<String, Object> map);
	
	// 上传图片
	int uploadPictures(Map<String, String> map);
	
	// 获取累计值
	Double getSum(Map<String, Object> map);
	
	// App接口 路
	AppRoadEntity getAppRoadByUniqueIdentifier(Map<String, Object> map);
	
	// App接口 桥
	AppBridgeEntity getAppBridgeByRoad(Map<String, Object> map);
}
