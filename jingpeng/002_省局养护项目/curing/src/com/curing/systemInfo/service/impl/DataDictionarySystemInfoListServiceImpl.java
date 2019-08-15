package com.curing.systemInfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.systemInfo.dao.DataDictionarySystemInfoListDao;
import com.curing.systemInfo.model.DataDictionarySystemInfoListEntity;
import com.curing.systemInfo.service.DataDictionarySystemInfoListService;

@Service
@Transactional
public class DataDictionarySystemInfoListServiceImpl implements DataDictionarySystemInfoListService {

	@Autowired
	private DataDictionarySystemInfoListDao dataDictionarySystemInfoListDao;
	
	@Override
	public List<DataDictionarySystemInfoListEntity> getDataDictionarySystemInfoList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dataDictionarySystemInfoListDao.getDataDictionarySystemInfoList(map);
	}

	@Override
	public int insertDataDictionarySystemInfoList(
			DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("Type", dataDictionarySystemInfoListEntity.getType());
		List<DataDictionarySystemInfoListEntity> dataDictionarySystemInfoListEntityList =  dataDictionarySystemInfoListDao.getDataDictionarySystemInfoList(map);
		int count = dataDictionarySystemInfoListEntityList.size();
		dataDictionarySystemInfoListEntity.setCode(count + 1 + "");
		dataDictionarySystemInfoListEntity.setContentExplain(dataDictionarySystemInfoListEntityList.get(0).getContentExplain());
		return dataDictionarySystemInfoListDao.insertDataDictionarySystemInfoList(dataDictionarySystemInfoListEntity);
	}

	@Override
	public int updateDataDictionarySystemInfoList(
			DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity) {
		// TODO Auto-generated method stub
		return dataDictionarySystemInfoListDao.updateDataDictionarySystemInfoList(dataDictionarySystemInfoListEntity);
	}

	@Override
	public int deleteDataDictionarySystemInfoList(
			DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity) {
		// TODO Auto-generated method stub
		return dataDictionarySystemInfoListDao.deleteDataDictionarySystemInfoList(dataDictionarySystemInfoListEntity);
	}

}
