package com.curing.common.service;

import java.util.List;
import java.util.Map;

import com.curing.common.model.EntryTreeEntity;
import com.curing.common.model.SubheadNumberMoneyEntity;

public interface InitialInfoService {
	// 左侧市县项目树取得
	List<EntryTreeEntity> getEntryTreeList(Map<String, Object> map);
	
	// 数据字典取得
	List<Map<String,Object>> getDataDictionary(Map<String, Object> map);
	
	// 根据项目id 查询项目信息
	List<EntryTreeEntity> getVEntryTree(Map<String, Object> map);
	
	// 根据项目id 查询子目号金额
	List<SubheadNumberMoneyEntity> getSubheadNumberMoney(Map<String, Object> map);
}
