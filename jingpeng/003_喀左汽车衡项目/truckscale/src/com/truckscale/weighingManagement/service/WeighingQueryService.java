package com.truckscale.weighingManagement.service;

import java.util.List;
import java.util.Map;

import com.truckscale.weighingManagement.model.WeighingQueryEntity;

public interface WeighingQueryService {

	// 查询称重列表
	List<WeighingQueryEntity> getWeighingQuery(Map<String, Object> param);
	
	// 查询称重列表Sum
	List<WeighingQueryEntity> getWeighingQuerySum(Map<String, Object> param);
	
	// 获取datalist
	Map<String, Object> getDataList(Map<String, Object> map);
	
	// 获取datalist
	Map<String, Object> getDataList_add(Map<String, Object> map);
	
    // 删除称重信息
    int deleteWeighingQuery(Map<String, Object> param);
    
    // 编辑称重信息
    int updateWeighingQuery(WeighingQueryEntity weighingQueryEntity);
    
    //打印信息
    List<Map<String,Object>> getPrintInfo(Map<String,Object> map);
	
	// 供料单位List
	Map<String, Object> getFeedcompanyList(Map<String, Object> map);
	
	// 材料名称List
	Map<String, Object> getMaterielNameList(Map<String, Object> map);
	
	// 规格型号List
	Map<String, Object> getMaterielModelList(Map<String, Object> map);
	
	//获取供料单位的材料名称和型号（ygt）
	List<Map<String,Object>> getOutFeedCompanyInfo(Map<String,Object> map);
	
	//获取入库供料单位的材料名称和型号（ygt）
    List<Map<String,Object>> getInFeedCompanyInfo(Map<String,Object> map);
	
	//获取收料单位信息(ygt)
    List<Map<String,Object>> getOutReceiveUnitInfo(Map<String,Object> map);
    
    //添加出库(ygt)
    int addExportMeasureOut(WeighingQueryEntity WeighingQueryEntity);
    
    //称重信息出库编号自增(ygt)
    String getWeighingInfoCount(String type);
    
    //称重信息出库编号自增(ygt)
    String getInWeighingInfoCount(String type);
    
    //添加入库(ygt)
    int addExportMeasureIn(WeighingQueryEntity WeighingQueryEntity);
}
