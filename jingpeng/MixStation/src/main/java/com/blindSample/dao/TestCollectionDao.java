package com.blindSample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blindSample.model.PlatformUserInfo;
import com.blindSample.model.SampleIntelligenceEntity;
import com.blindSample.model.TestCollectionEntity;
import com.blindSample.model.TestInfo;
import com.blindSample.model.TestPageInfos;
import com.blindSample.model.TestRoomInfo;

@Repository
public interface TestCollectionDao{
	
	// 根据用户权限获取试验室名称
	List<TestRoomInfo> getTestRoomList(Map<String, Object> map);
	
	// 获取相应试验室的试验员
	List<TestInfo> getTestOperatorList(Map<String, Object> map);
	
	// 获取用户权限
	List<PlatformUserInfo> getUserInfo(Map<String, Object> map);
	// 获取试验名称
	List<TestPageInfos> getTestNameList(Map<String, Object> map);
	
	// 获取试验收样明细数据list
	List<TestCollectionEntity> getTestInfoList(Map<String, Object> map);
	
	// 扫码后保存试验基本信息
	int addTestInfo(Map<String, Object> map);
	
	// 根据二维码判断试验是否已经收样
	List<Integer> getExistFlag(Map<String, Object> map);
	
	// 扫码获取取样信息
	List<SampleIntelligenceEntity> getSampleIntelligence(Map<String, Object> map);
	
	// 添加水泥胶砂强度信息
	int addTest04006T0(Map<String, Object> map);
	
	// 添加水泥混凝土抗压强度（立方体）信息
	int addTest0500101T0(Map<String, Object> map);
	
	// 添加砂浆抗压强度试验信息
	int addTest0500102T0(Map<String, Object> map);
	
	// 添加无机结合料稳定材料无侧限抗压强度试验信息
	int addTest07003Test0(Map<String, Object> map);
	
	// 添加沥青三大指标试验信息
	int addTest08001T0(Map<String, Object> map);
	
	// 添加沥青混合料马歇尔稳定度试验信息
	int addTest0900101T0(Map<String, Object> map);
	
	// 添加钢筋抗拉强度、屈服强度、伸长率、冷弯试验信息
	int addTest1000101T0(Map<String, Object> map);
	
	// 添加钢筋接头抗拉强度、冷弯试验信息
	int addTest1000201T0(Map<String, Object> map);
	
	// 粗集料筛分试验 
	int addTest0200101T0(Map<String, Object> map);
	
	// 细集料筛分试验
	int addTest0200102T0(Map<String, Object> map);
	
	// 粗集料含泥量试验
	int addTest02015T0(Map<String, Object> map);
	
	// 细集料含泥量试验
	int addTest02006T0(Map<String, Object> map);
	
	// 粗集料针、片状颗粒含量试验
	int addTest02002T0(Map<String, Object> map);
	
	// 粗集料压碎值试验
	int addTest0200301T0(Map<String, Object> map);
	
	// 水泥凝结时间
	int addTest04003T0(Map<String, Object> map);
	
	// 粗集料试验
	int addTest0201T0(Map<String, Object> map);
	
	// 细集料试验
	int addTest0202T0(Map<String, Object> map);
}