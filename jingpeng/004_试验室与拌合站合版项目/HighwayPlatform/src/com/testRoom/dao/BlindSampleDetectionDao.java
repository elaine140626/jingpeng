package com.testRoom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.testRoom.model.TestSelectEntity;

@Repository
public interface BlindSampleDetectionDao{
	// 获取页面列表信息
	List<TestSelectEntity> getInfoList(Map<String, Object> map);
}
