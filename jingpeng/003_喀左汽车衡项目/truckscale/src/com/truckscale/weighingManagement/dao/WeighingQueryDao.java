package com.truckscale.weighingManagement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.truckscale.basicSetting.model.CarInfo;
import com.truckscale.weighingManagement.model.WeighingQueryEntity;


@Repository
public interface WeighingQueryDao {
	
	//查询称重列表
	List<WeighingQueryEntity> getWeighingQuery(Map<String, Object> param);
	
	//查询称重列表sum
	List<WeighingQueryEntity> getWeighingQuerySum(Map<String, Object> param);
	
	// 供料单位List
	List<WeighingQueryEntity> feedCompanyList(Map<String, Object> map);
	
	// 收料单位List
	List<WeighingQueryEntity> receiveUnitList(Map<String, Object> map);
	
	// 材料名称List
	List<WeighingQueryEntity> materielNameList(Map<String, Object> map);
	
	// 材料型号List
	List<WeighingQueryEntity> materielModelList(Map<String, Object> map);
	
	// 编辑称重信息供料单位List
	List<WeighingQueryEntity> feedCompanyList_add(Map<String, Object> map);
		
	// 编辑称重信息收料单位List
	List<WeighingQueryEntity> receiveUnitList_add(Map<String, Object> map);
		
	// 编辑称重信息材料名称List
	List<WeighingQueryEntity> materielNameList_add(Map<String, Object> map);
		
	// 编辑称重信息材料型号List
	List<WeighingQueryEntity> materielModelList_add(Map<String, Object> map);
		
	// 司机姓名List
	List<WeighingQueryEntity> driverNameList(Map<String, Object> map);
	
	// 车主姓名List
	List<WeighingQueryEntity> carDriverNameList(Map<String, Object> map);
	
	// 车牌号码List
	List<WeighingQueryEntity> carNumberList(Map<String, Object> map);
	
	// 供料单位List
	List<WeighingQueryEntity> getFeedcompanyList(Map<String, Object> map);
	
	// 材料名称List
	List<WeighingQueryEntity> getMaterielNameList(Map<String, Object> map);
	
	// 规格型号List
	List<WeighingQueryEntity> getMaterielModelList(Map<String, Object> map);
	 
    // 删除称重信息
    int deleteWeighingQuery(Map<String, Object> param);
    
    // 编辑称重信息
    int updateWeighingQuery(WeighingQueryEntity param);
    
    //打印信息
    List<Map<String,Object>> getPrintInfo(Map<String,Object> map);
    
    //获取供料单位的材料名称和型号（ygt）
    List<Map<String,Object>> getOutFeedCompanyInfo(Map<String,Object> map);
    
    //获取入库供料单位的材料名称和型号（ygt）
    List<Map<String,Object>> getInFeedCompanyInfo(Map<String,Object> map);
    
    //获取收料单位信息(ygt)
    List<Map<String,Object>> getOutReceiveUnitInfo(Map<String,Object> map);
    
    //添加出库(ygt)
    int addExportMeasureOut(WeighingQueryEntity WeighingQueryEntity);
    
    //称重信息出库编号自增(ygt)
    WeighingQueryEntity getWeighingInfoCount();
    
    //称重信息入库编号自增(ygt)
    WeighingQueryEntity getInWeighingInfoCount();
    
    //查询车牌号码(ygt)
    List<CarInfo> getOnlyCarInfo(Map<String,Object> map);
    
    //添加入库(ygt)
    int addExportMeasureIn(WeighingQueryEntity WeighingQueryEntity);
}
