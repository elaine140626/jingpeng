package com.blindSample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blindSample.dao.VideoSurveillanceDao;
import com.blindSample.model.AutoCollectionEntity;
import com.blindSample.model.BlindInfoEntity;
import com.blindSample.model.VideoSurveillance;
import com.blindSample.service.VideoSurveillanceService;

@Service
@Transactional
public class VideoSurveillanceServiceImpl implements VideoSurveillanceService {
	
	@Autowired
	VideoSurveillanceDao videoSurveillanceDao;

	public List<VideoSurveillance> getVideoSurveillance(Map<String, Object> map){
		return videoSurveillanceDao.getVideoSurveillance(map);
	}

	public List<VideoSurveillance> getVideoSurveillanceById(Map<String, Object> map){
		return videoSurveillanceDao.getVideoSurveillanceById(map);
	}

	// 获取用户权限下试验室的试验数量
	public List<Map<String, Object>> getTestInfoList(Map<String, Object> map){
		return videoSurveillanceDao.getTestInfoList(map);
	}
	
	// 自动采集数量
	public List<Map<String, Object>> getAutoCollectionCount(Map<String, Object> map){
		return videoSurveillanceDao.getAutoCollectionCount(map);
	}
	
	// 自动采集明细
	public List<AutoCollectionEntity> getAutoCollectionList(Map<String, Object> map){
		return videoSurveillanceDao.getAutoCollectionList(map);
	}
	
	// 盲样数量
	public List<Map<String, Object>> getBlindCount(Map<String, Object> map){
		return videoSurveillanceDao.getBlindCount(map);
	}
	
	// 盲样明细
	public List<BlindInfoEntity> getBlindList(Map<String, Object> map){
		return videoSurveillanceDao.getBlindList(map);
	}

}
