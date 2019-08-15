package com.curing.projectSchedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.curing.projectSchedule.model.DeckTreatmentNumberEntity;
import com.curing.projectSchedule.model.DeckTreatmentNumberSum;

@Repository
public interface DeckTreatmentNumberDao{
	// 工程进度（桥面处理工程数量表）List取得
	List<DeckTreatmentNumberEntity> getDeckTreatmentNumberList(Map<String, Object> map);
	
	// 工程进度（桥面处理工程数量表）合计
	List<DeckTreatmentNumberSum> getDeckTreatmentNumberSum(Map<String, Object> map);
	
	// 新增工程进度（桥面处理工程数量表）
	int insertDeckTreatmentNumber(DeckTreatmentNumberEntity deckTreatmentNumberEntity);
	
	// 更新工程进度（桥面处理工程数量表）
	int updateDeckTreatmentNumber(DeckTreatmentNumberEntity deckTreatmentNumberEntity);
	
	// 删除工程进度（桥面处理工程数量表）
	int deleteDeckTreatmentNumber(DeckTreatmentNumberEntity deckTreatmentNumberEntity);
	
	List<Map<String, Object>> getDeckTreatmentNumberListEX(Map<String, Object> map);
}
