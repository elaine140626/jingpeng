package com.curing.projectMetering.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectMetering.model.CompletionAmountEntity;
import com.curing.projectMetering.model.CompletionAmountSum;

@Repository
public interface CompletionAmountDao {

	// 工程计量（2）List取得
	List<CompletionAmountEntity> getCompletionAmountList(Map<String, Object> map);
	
	// 工程计量（2）合计
    List<CompletionAmountSum> getCompletionAmountSum(Map<String, Object> map);
}
