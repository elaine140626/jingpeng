package com.blindSample.service;

import java.util.List;
import java.util.Map;

import com.blindSample.model.TestSelectEntity;

public interface BlindSampleDetectionService {
	// 获取页面显示信息
	List<TestSelectEntity> getInfoList(Map<String, Object> map);
}
