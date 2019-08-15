package com.truckscale.weighingManagement.service;

import java.util.List;
import java.util.Map;

import com.truckscale.weighingManagement.model.WeighingQueryOtherOutEntity;

public interface WeighingQueryOtherOutService {

	// 查询其他出库单列表
	List<WeighingQueryOtherOutEntity> getWeighingQueryOtherOut(Map<String, Object> param);
	
	// 获取datalist
	Map<String, Object> getDataList(Map<String, Object> map);
	
    // 删除其他出库单信息
    int deleteWeighingQueryOtherOut(Map<String, Object> param);
    
    // 编辑其他出库单信息
    int updateWeighingQueryOtherOut(WeighingQueryOtherOutEntity weighingQueryOtherOutEntity);

    // 添加其他出库单信息
	int insertWeighingQueryOtherOut(WeighingQueryOtherOutEntity param);
    
	//删除司机电子签名
	int deleteElectronicsName(Map<String, Object> param);
	
	//小票打印
    List<WeighingQueryOtherOutEntity> getWeighingQueryOtherInfo(Map<String, Object> param);
}
