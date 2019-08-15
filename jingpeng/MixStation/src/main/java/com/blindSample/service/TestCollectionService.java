package com.blindSample.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blindSample.model.PlatformUserInfo;
import com.blindSample.model.SampleIntelligenceEntity;
import com.blindSample.model.TestCollectionEntity;
import com.blindSample.model.TestInfo;
import com.blindSample.model.TestPageInfos;
import com.blindSample.model.TestRoomInfo;

@Service
@Transactional
public interface TestCollectionService {
	// 根据用户权限获取试验室名称
	List<TestRoomInfo> getTestRoomList(Map<String, Object> map);

	// 根据试验室名称获取相应的测试员
	List<TestInfo> getTestOperatorList(Map<String, Object> map);
	
	// 获取试验名称
	List<TestPageInfos> getTestNameList(Map<String, Object> map);
	
	// 获取用户权限
	List<PlatformUserInfo> getUserInfo(Map<String, Object> map);

	// 获取试验收样明细数据list
	List<TestCollectionEntity> getTestInfoList(Map<String, Object> map);
	
	// 根据二维码获取相应收样信息
	List<SampleIntelligenceEntity> getSampleIntelligence(Map<String, Object> map);

	// 扫码后保存信息
	int addTestInfo(Map<String, Object> map);
	
	// 根据二维码判断试验是否已经收样
    int getExistFlag(Map<String, Object> map);
}
