package com.testRoom.service;

import java.util.List;
import java.util.Map;

import com.testRoom.model.SampleIntelligenceEntity;
import com.testRoom.model.TestCollectionEntity;
import com.testRoom.model.TestInfo;
import com.testRoom.model.TestPageInfos;
import com.testRoom.model.TestRoomInfo;

public interface TestReceviedService {
	// 获取当前用户权限下的树形结构
	List<TestRoomInfo> getTestRoomList(Map<String, Object> map);

	// 根据试验室名称获取相应的测试员
	List<TestInfo> getTestOperatorList(Map<String, Object> map);

	// 获取试验名称
	List<TestPageInfos> getTestNameList(Map<String, Object> map);

	// 获取试验收样明细数据list
	List<TestCollectionEntity> getTestInfoList(Map<String, Object> map);

	// 根据二维码获取相应收样信息
	List<SampleIntelligenceEntity> getSampleIntelligence(Map<String, Object> map);

	// 扫码后保存信息
	int addTestInfo(Map<String, Object> map);

	// 根据二维码判断试验是否已经收样
	int getExistFlag(Map<String, Object> map);
}
