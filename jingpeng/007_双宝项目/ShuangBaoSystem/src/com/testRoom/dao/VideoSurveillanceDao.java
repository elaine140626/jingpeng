package com.testRoom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.testRoom.model.AutoCollectionEntity;
import com.testRoom.model.BlindInfoEntity;
import com.testRoom.model.VideoSurveillance;



@Repository
public interface VideoSurveillanceDao{
	
	public List<VideoSurveillance> getVideoSurveillance(Map<String, Object> map);
	
	public List<VideoSurveillance> getVideoSurveillanceById(Map<String, Object> map);
	
	// 获取用户权限下试验室的试验总数
	public List<Map<String, Object>> getTestInfoList(Map<String, Object> map);
	
	// 自动采集数量
	public List<Map<String, Object>> getAutoCollectionCount(Map<String, Object> map);
	
	// 自动采集明细
	public List<AutoCollectionEntity> getAutoCollectionList(Map<String, Object> map);
	
	// 盲样数量
	public List<Map<String, Object>> getBlindCount(Map<String, Object> map);
	
	// 盲样明细
	public List<BlindInfoEntity> getBlindList(Map<String, Object> map);
}
