package com.curing.projectSchedule.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.PipeCulvertNumberDao;
import com.curing.projectSchedule.model.PipeCulvertNumberEntity;
import com.curing.projectSchedule.model.PipeCulvertNumberSum;
import com.curing.projectSchedule.service.PipeCulvertNumberService;
@Service
@Transactional
public class PipeCulvertNumberServiceImpl implements PipeCulvertNumberService{
	@Autowired
	private PipeCulvertNumberDao pipeCulvertNumberDao;
	
	// 工程进度（圆管涵工程数量汇总表）List取得
	public List<PipeCulvertNumberEntity> getPipeCulvertNumberList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pipeCulvertNumberDao.getPipeCulvertNumberList(map);
	}

	// 工程进度（圆管涵工程数量汇总表）合计
	public List<PipeCulvertNumberSum> getPipeCulvertNumberSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pipeCulvertNumberDao.getPipeCulvertNumberSum(map);
	}

	// 新增工程进度（圆管涵工程数量汇总表）
	public int insertPipeCulvertNumber(PipeCulvertNumberEntity pipeCulvertNumberEntity) {
		// TODO Auto-generated method stub
		return pipeCulvertNumberDao.insertPipeCulvertNumber(pipeCulvertNumberEntity);
	}

	// 更新工程进度（圆管涵工程数量汇总表）
	public int updatePipeCulvertNumber(PipeCulvertNumberEntity pipeCulvertNumberEntity) {
		// TODO Auto-generated method stub
		return pipeCulvertNumberDao.updatePipeCulvertNumber(pipeCulvertNumberEntity);
	}

	// 删除工程进度（圆管涵工程数量汇总表）
	public int deletePipeCulvertNumber(PipeCulvertNumberEntity pipeCulvertNumberEntity) {
		// TODO Auto-generated method stub
		return pipeCulvertNumberDao.deletePipeCulvertNumber(pipeCulvertNumberEntity);
	}

}
