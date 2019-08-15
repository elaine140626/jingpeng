package com.curing.statisticsForms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.statisticsForms.dao.FirstPlannedProgressDao;
import com.curing.statisticsForms.model.FirstPlannedProgress;
import com.curing.statisticsForms.model.FirstPlannedProgressEntity;
import com.curing.statisticsForms.model.FirstPlannedProgressSum;
import com.curing.statisticsForms.service.FirstPlannedProgressService;


@Service
@Transactional
public class FirstPlannedProgressServiceImpl implements FirstPlannedProgressService {
	
	@Autowired
	private FirstPlannedProgressDao firstPlannedProgressDao;

	// 第一批计划进度List取得
	public List<FirstPlannedProgressEntity> getFirstPlannedProgressList(Map<String, Object> map){
		return firstPlannedProgressDao.getFirstPlannedProgressList(map);
	}

	// 第一批计划进度合计
	public List<FirstPlannedProgressSum> getFirstPlannedProgressSum(Map<String, Object> map){
		return firstPlannedProgressDao.getFirstPlannedProgressSum(map);
	}
	
	// 第一批计划进度 单条取得
	public List<FirstPlannedProgress> getFirstPlannedProgressByCityId(Map<String, Object> map){
		return firstPlannedProgressDao.getFirstPlannedProgressByCityId(map);
	}
	
	// 新增第一批计划进度 
	public int insertFirstPlannedProgress(FirstPlannedProgress firstPlannedProgress) {
		int res = 0;
		// 删除第一批计划进度
		res += firstPlannedProgressDao.deleteFirstPlannedProgress(firstPlannedProgress);
		// 新增第一批计划进度 
		res += firstPlannedProgressDao.insertFirstPlannedProgress(firstPlannedProgress);
		return res;
	}

}
