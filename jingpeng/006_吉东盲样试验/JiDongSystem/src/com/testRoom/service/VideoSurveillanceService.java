package com.testRoom.service;

import java.util.List;
import java.util.Map;

import com.testRoom.model.AutoCollectionEntity;
import com.testRoom.model.BlindInfoEntity;
import com.testRoom.model.VideoSurveillance;


public interface VideoSurveillanceService {

	List<VideoSurveillance> getVideoSurveillance(Map<String, Object> map);

	List<VideoSurveillance> getVideoSurveillanceById(Map<String, Object> map);

	// 获取用户权限下试验室的试验数量
	List<Map<String, Object>> getTestInfoList(Map<String, Object> map);

	// 自动采集数量
	List<Map<String, Object>> getAutoCollectionCount(Map<String, Object> map);

	// 自动采集明细
	List<AutoCollectionEntity> getAutoCollectionList(Map<String, Object> map);
	
	// 盲样数量
	List<Map<String, Object>> getBlindCount(Map<String, Object> map);

	// 盲样明细
	List<BlindInfoEntity> getBlindList(Map<String, Object> map);
}
