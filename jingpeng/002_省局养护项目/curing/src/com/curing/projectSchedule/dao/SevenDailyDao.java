package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.SevenDailyEntity;
import com.curing.projectSchedule.model.SevenDailySum;

@Repository
public interface SevenDailyDao{
	// 工程进度（七日报）List取得
	List<SevenDailyEntity> getSevenDailyList(Map<String, Object> map);
	
	// 新增工程进度（七日报）
	int insertSevenDaily(SevenDailyEntity sevenDailyEntity);
	
	// 更新工程进度（七日报）
	int updateSevenDaily(SevenDailyEntity sevenDailyEntity);
	
	// 删除工程进度（七日报）
	int deleteSevenDaily(SevenDailyEntity sevenDailyEntity);
	
	// 工程进度（七日报）本期合计
	List<SevenDailySum> getCumulative(Map<String, Object> map);
	
	// 判断新插入的子目号是否存在 
	int getSevenDailyCount(SevenDailyEntity sevenDailyEntity);
}
