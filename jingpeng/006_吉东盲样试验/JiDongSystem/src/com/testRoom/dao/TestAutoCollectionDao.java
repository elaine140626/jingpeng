package com.testRoom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.testRoom.model.TestSelectEntity;

@Repository
public interface TestAutoCollectionDao{
	
	// 获取页面显示信息
	List<TestSelectEntity> getInfoList(Map<String, Object> map);
	//查询水泥混凝土抗压强度试验（立方体）
	List<TestSelectEntity> getSNHNList(Map<String, Object> map);
	//查询砂浆抗压强度试验
	List<TestSelectEntity> getBJList(Map<String, Object> map);
	//钢筋拉伸强度、屈服强度、伸长率、冷弯试验
	List<TestSelectEntity> getKQSLList(Map<String, Object> map);
	//钢筋接头拉伸强度、冷弯试验
	List<TestSelectEntity> getKLList(Map<String, Object> map);
	//沥青针入度试验
	List<TestSelectEntity> getLQZRList(Map<String, Object> map);
	//沥青软化点试验
	List<TestSelectEntity> getLQRHDList(Map<String, Object> map);
	//沥青混合料马歇尔试验
	List<TestSelectEntity> getLQMXList(Map<String, Object> map);
}
