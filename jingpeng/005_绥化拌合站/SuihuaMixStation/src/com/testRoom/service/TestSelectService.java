package com.testRoom.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testRoom.model.TestSelectEntity;
@Service
@Transactional
public interface TestSelectService {
	
	// 获取页面显示信息
	List<TestSelectEntity> getInfo(Map<String, Object> map);
	
	// 修改试验状态
	int changeTestState(Map<String, Object> map);
}
