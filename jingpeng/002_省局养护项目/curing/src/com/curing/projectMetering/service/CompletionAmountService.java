package com.curing.projectMetering.service;

import java.util.List;
import java.util.Map;

import com.curing.projectMetering.model.CompletionAmountEntity;
import com.curing.projectMetering.model.CompletionAmountSum;

public interface CompletionAmountService {
	// 工程计量（2）List取得
	List<CompletionAmountEntity> getCompletionAmountList(Map<String, Object> map);
	
	// 工程计量（2）合计
    List<CompletionAmountSum> getCompletionAmountSum(Map<String, Object> map);
}
