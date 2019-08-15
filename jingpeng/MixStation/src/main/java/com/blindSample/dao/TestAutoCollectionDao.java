package com.blindSample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blindSample.model.TestSelectEntity;

@Repository
public interface TestAutoCollectionDao{
	
	// 获取页面显示信息
	List<TestSelectEntity> getInfoList(Map<String, Object> map);
}
