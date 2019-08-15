package com.oil.dao.pushMessage;

import java.util.List;
import java.util.Map;

import com.oil.model.PushMessage;

public interface PushMessageDao {
	
	List<PushMessage> getPushMessageList(Map<String, Object> map);

	List<Map<String, Object>> getPushMessageNormal(Map<String, Object> map);

	List<Map<String, Object>> getPushMessageEmpty(Map<String, Object> map);

	List<Map<String, Object>> getPushMessagExchange(Map<String, Object> map);

}
