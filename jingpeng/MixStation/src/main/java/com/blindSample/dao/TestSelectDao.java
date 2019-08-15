package com.blindSample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blindSample.model.TestSelectEntity;

@Repository
public interface TestSelectDao{
	
	// 获取页面列表信息
	List<TestSelectEntity> getInfo(Map<String, Object> map);
	
	// 修改试验状态
	int changeTestState(Map<String, Object> map);
}
