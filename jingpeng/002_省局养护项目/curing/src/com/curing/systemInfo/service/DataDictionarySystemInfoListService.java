package com.curing.systemInfo.service;

import java.util.List;
import java.util.Map;

import com.curing.systemInfo.model.DataDictionarySystemInfoListEntity;

public interface DataDictionarySystemInfoListService {

	List<DataDictionarySystemInfoListEntity> getDataDictionarySystemInfoList(Map<String,Object> map);
	
	int insertDataDictionarySystemInfoList(DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity);
	
	int updateDataDictionarySystemInfoList(DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity);
	
	int deleteDataDictionarySystemInfoList(DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity);
}
