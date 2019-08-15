package com.highwayPlatform.service;

import java.util.List;
import java.util.Map;

public interface JpushService {
	// 获取用户id
	List<Map<String, Object>> getAppUser (Map<String, Object> map);
}
