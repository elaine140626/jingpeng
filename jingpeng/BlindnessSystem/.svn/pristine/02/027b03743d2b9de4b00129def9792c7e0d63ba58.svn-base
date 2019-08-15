package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.BlindSampDetailed;
import com.jingpeng.model.BlindSampleInfo;
import com.jingpeng.model.QrCodeInfo;
import com.kdt.base.exception.BusinessException;

public interface BlindSampleInfoService {

	void addBlindSampleInfo(BlindSampleInfo blindSampleInfo)throws BusinessException;
	
	List<BlindSampleInfo> getBlindSampleInfo(Map<String, Object> param)throws BusinessException;

	List<Map<String, Object>> getBlindSampleInfo1(Map<String, Object> map)throws BusinessException;

   int deleteBlindSampDetailed(Map<String, Object> param)throws BusinessException;

   int addBlindSampDetailed(List<BlindSampDetailed> blindSampDetailedList)throws BusinessException;

   int updateblindSampleInfo(Map<String, Object> param1)throws BusinessException;

    List<BlindSampDetailed> blindSampDetailed(Map<String, Object> param)throws BusinessException;
    
    List<QrCodeInfo> getQrCodeInfo()throws BusinessException;
    
    List<Map<String, Object>> getypCode(Map<String, Object> param)throws BusinessException;
    
    List<Map<String, Object>> getConstructionUnit(Map<String, Object> map)throws BusinessException;

	List<Map<String, Object>> getEngineeringName(Map<String, Object> map)throws BusinessException;

}
