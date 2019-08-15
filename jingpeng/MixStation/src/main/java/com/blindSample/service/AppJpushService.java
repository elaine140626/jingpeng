package com.blindSample.service;

import java.util.List;
import java.util.Map;

public interface AppJpushService {
	// 获取页面显示信息
	List<Map<String, Object>> getAppUser(Map<String, Object> UniqueIdentifier);
	
	// 获取将要推送的数据
	List<Map<String, Object>> getPushInfo();
	
	// 修改推送消息状态
	int updatePushState(String id);
}
