package com.curing.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.common.model.EntryTreeEntity;
import com.curing.common.model.SubheadNumberMoneyEntity;

@Repository
public interface InitialInfoDao{
	// 左侧市县项目树取得
	List<EntryTreeEntity> getEntryTreeList(Map<String, Object> map);
	
	// 数据字典取得
	List<Map<String,Object>> getDataDictionary(Map<String, Object> map);
	
	// 根据项目id 查询项目信息
	List<EntryTreeEntity> getVEntryTree(Map<String, Object> map);
	
	// 根据项目id 查询子目号金额
	List<SubheadNumberMoneyEntity> getSubheadNumberMoney(Map<String, Object> map);
}
