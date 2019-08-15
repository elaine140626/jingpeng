package com.curing.projectSchedule.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curing.projectSchedule.model.DeckTreatmentNumberEntity;
import com.curing.projectSchedule.model.DeckTreatmentNumberSum;

public interface DeckTreatmentNumberService {
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
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
}
