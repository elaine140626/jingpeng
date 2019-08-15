package com.truckscale.weighingManagement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.truckscale.weighingManagement.model.WeighingQueryOtherOutEntity;



@Repository
public interface WeighingQueryOtherOutDao {
	
	//查询其它出库单列表
	List<WeighingQueryOtherOutEntity> getWeighingQueryOtherOut(Map<String, Object> param);
	
    // 删除其他出库单信息
    int deleteWeighingQueryOtherOut(Map<String, Object> param);
    
    // 编辑其它出库单信息
    int updateWeighingQueryOtherOut(WeighingQueryOtherOutEntity param);
    
    // 添加其它出库单信息
    int insertWeighingQueryOtherOut(WeighingQueryOtherOutEntity param);
   
    // 删除司机电子签名
    int deleteElectronicsName(Map<String, Object> param);
    
    //小票打印
    List<WeighingQueryOtherOutEntity> getWeighingQueryOtherInfo(Map<String, Object> param);

}