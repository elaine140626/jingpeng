package com.testRoom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface IndexDao{
	//查询当前用户权限的实验室
	List<Map<String, Object>> getTestRoom(Map<String, Object> map);
	//查询index试验室数据
	List<Map<String, Object>> getIndexSummary(Map<String, Object> map);
}
