package com.blindSample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface AppJpushDao{
	// 获取用户id
	List<Map<String, Object>> getAppUser(Map<String, Object> UniqueIdentifier);
	
	// 获取将要推送的数据
	List<Map<String, Object>> getPushInfo();
	
	// 修改推送消息状态
	int updatePushState(String id);
}
