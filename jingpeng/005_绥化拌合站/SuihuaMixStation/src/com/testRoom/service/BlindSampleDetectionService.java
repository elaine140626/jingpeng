package com.testRoom.service;

import java.util.List;
import java.util.Map;

import com.testRoom.model.TestSelectEntity;

public interface BlindSampleDetectionService {
	// 获取页面显示信息
	List<TestSelectEntity> getInfoList(Map<String, Object> map);
}
