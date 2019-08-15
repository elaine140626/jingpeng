package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.SubheadNumberMoneyEntity;

@Repository
public interface SubheadNumberMoneyDao {

	// 子母List取得
	List<SubheadNumberMoneyEntity> getSubheadNumberMoney(Map<String, Object> map);
	
	//添加子母
	int insertSubheadNumberMoney(SubheadNumberMoneyEntity subheadNumberMoneyEntity);
	
	//修改子母
	int updateSubheadNumberMoney(SubheadNumberMoneyEntity subheadNumberMoneyEntity);
	
	//删除子母
	int deleteSubheadNumberMoney(SubheadNumberMoneyEntity subheadNumberMoneyEntity);
	
	//验证去重 查看7日报是否存在子目
	List<Map<String, Object>> getSevenDailyById(Map<String, Object> map);
}
