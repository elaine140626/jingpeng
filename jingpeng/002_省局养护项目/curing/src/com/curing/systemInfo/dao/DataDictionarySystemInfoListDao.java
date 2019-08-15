package com.curing.systemInfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.systemInfo.model.DataDictionarySystemInfoListEntity;

@Repository
public interface DataDictionarySystemInfoListDao {
	
	List<DataDictionarySystemInfoListEntity> getDataDictionarySystemInfoList(Map<String,Object> map);
	
	int insertDataDictionarySystemInfoList(DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity);
	
	int updateDataDictionarySystemInfoList(DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity);
	
	int deleteDataDictionarySystemInfoList(DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity);

}
