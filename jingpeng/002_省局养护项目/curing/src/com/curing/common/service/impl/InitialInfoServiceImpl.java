package com.curing.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.common.dao.InitialInfoDao;
import com.curing.common.model.EntryTreeEntity;
import com.curing.common.model.SubheadNumberMoneyEntity;
import com.curing.common.service.InitialInfoService;

@Service
@Transactional
public class InitialInfoServiceImpl implements InitialInfoService{
	@Autowired
	private InitialInfoDao initialInfoDao;
	
	// 左侧市县项目树取得
	public List<EntryTreeEntity> getEntryTreeList(Map<String, Object> map){
		return initialInfoDao.getEntryTreeList(map);
	}
	
	// 数据字典取得
	public List<Map<String,Object>> getDataDictionary(Map<String, Object> map){
		return initialInfoDao.getDataDictionary(map);
	}
	
	// 根据项目id 查询项目信息
	public List<EntryTreeEntity> getVEntryTree(Map<String, Object> map){
		return initialInfoDao.getVEntryTree(map);
	}
	
	// 根据项目id 查询子目号金额
	public List<SubheadNumberMoneyEntity> getSubheadNumberMoney(Map<String, Object> map){
		return initialInfoDao.getSubheadNumberMoney(map);
	}

}
