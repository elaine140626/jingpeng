package com.testRoom.service;

import java.util.List;
import java.util.Map;

public interface IndexService {
	//查询当前用户权限的实验室
	List<Map<String, Object>> getTestRoomName(Map<String, Object> map);
	//查询index试验室数据
	List<Map<String, Object>> getIndexSummary(Map<String, Object> map);

}
