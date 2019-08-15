package com.curing.projectMetering.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectMetering.dao.CompletionAmountDao;
import com.curing.projectMetering.model.CompletionAmountEntity;
import com.curing.projectMetering.model.CompletionAmountSum;
import com.curing.projectMetering.service.CompletionAmountService;
@Service
@Transactional
public class CompletionAmountServiceImpl implements CompletionAmountService{

	@Autowired
	private CompletionAmountDao completionAmountDao;
	
	// 工程计量（2）List取得
	public List<CompletionAmountEntity> getCompletionAmountList(Map<String, Object> map) {
		return completionAmountDao.getCompletionAmountList(map);
	}

	// 工程计量（2）合计
	public List<CompletionAmountSum> getCompletionAmountSum(Map<String, Object> map) {
		return completionAmountDao.getCompletionAmountSum(map);
	}

}
