package com.oil.service.pushMessage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.pushMessage.PushMessageDao;
import com.oil.model.PushMessage;
import com.oil.service.pushMessage.PushMessageService;
@Service
public class PushMessageServiceImpl implements PushMessageService {
	
	@Autowired
	PushMessageDao pushMessageDao;

	@Override
	public Map<String, Object> getPushMessage(Map<String, Object> map) {
		Map<String, Object> reultMap= new HashMap<String, Object>();
		if(map.get("orgId").toString().indexOf("4") >= 0) {
			//化验员
			map.put("analystOrDirector", 0);
		}else {
			//主任
			map.put("analystOrDirector", 1);
		}
		//调度出库单
		List<Map<String, Object>> getPushMessageNormal = pushMessageDao.getPushMessageNormal(map);
		//调度出库单空
		List<Map<String, Object>> getPushMessageEmpty = pushMessageDao.getPushMessageEmpty(map);
		//调度出库单兑换
		List<Map<String, Object>> getPushMessagExchange = pushMessageDao.getPushMessagExchange(map);
		//其他内容
		List<PushMessage> pushMessageList = pushMessageDao.getPushMessageList(map);
		
		reultMap.put("getPushMessageNormal", getPushMessageNormal);
		reultMap.put("getPushMessageEmpty", getPushMessageEmpty);
		reultMap.put("getPushMessagExchange", getPushMessagExchange);
		reultMap.put("pushMessageList", pushMessageList);
		return reultMap;
	}

}
