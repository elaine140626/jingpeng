package com.highwayPlatform.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface JpushDao {
	// 获取用户id
	List<Map<String, Object>> getAppUser (Map<String, Object> map);
}
