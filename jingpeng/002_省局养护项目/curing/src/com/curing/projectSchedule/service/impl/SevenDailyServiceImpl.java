package com.curing.projectSchedule.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.SevenDailyDao;
import com.curing.projectSchedule.model.SevenDailyEntity;
import com.curing.projectSchedule.model.SevenDailySum;
import com.curing.projectSchedule.service.SevenDailyService;

@Service
@Transactional
public class SevenDailyServiceImpl implements SevenDailyService{
	@Autowired
	private SevenDailyDao sevenDailyDao;
	
	// 工程进度（七日报）List取得
	public List<SevenDailyEntity> getSevenDailyList(Map<String, Object> map){
		return sevenDailyDao.getSevenDailyList(map);
	}
	
	// 新增工程进度（七日报）
	public int insertSevenDaily(SevenDailyEntity sevenDailyEntity){
		return sevenDailyDao.insertSevenDaily(sevenDailyEntity);
	}
	
	// 更新工程进度（七日报）
	public int updateSevenDaily(SevenDailyEntity sevenDailyEntity){
		return sevenDailyDao.updateSevenDaily(sevenDailyEntity);
	}
	
	// 删除工程进度（七日报）
	public int deleteSevenDaily(SevenDailyEntity sevenDailyEntity){
		return sevenDailyDao.deleteSevenDaily(sevenDailyEntity);
	}	
	
	// 工程进度（七日报）本期合计
	public List<SevenDailySum> getCumulative(Map<String, Object> map){
		return sevenDailyDao.getCumulative(map);
	}
	
	// 判断新插入的子目号是否存在 
	public int getSevenDailyCount(SevenDailyEntity sevenDailyEntity) {
		return sevenDailyDao.getSevenDailyCount(sevenDailyEntity);	
	}

}
