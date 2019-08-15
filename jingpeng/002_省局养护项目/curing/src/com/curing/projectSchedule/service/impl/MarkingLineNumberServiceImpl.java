package com.curing.projectSchedule.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.MarkingLineNumberDao;
import com.curing.projectSchedule.model.MarkingLineNumberEntity;
import com.curing.projectSchedule.model.MarkingLineNumberSum;
import com.curing.projectSchedule.service.MarkingLineNumberService;
@Service
@Transactional
public class MarkingLineNumberServiceImpl implements MarkingLineNumberService{
	
	@Autowired
	private MarkingLineNumberDao MarkingLineNumberDao;
	
	// 工程进度（标线工程数量表）List取得
	public List<MarkingLineNumberEntity> getMarkingLineNumberList(Map<String, Object> map){
		return MarkingLineNumberDao.getMarkingLineNumberList(map);
	}
	
	// 工程进度（标线工程数量表）合计
	public MarkingLineNumberEntity getMarkingLineNumberSum(Map<String, Object> map){
		List<MarkingLineNumberEntity> markingLineNumberSumInfo = MarkingLineNumberDao.getMarkingLineNumberList(map);
		MarkingLineNumberEntity  MarkingLineNumberInfo = new MarkingLineNumberEntity();
		Double hotmeltmarkingSum = 0.00;
		Double erasemarkingSum = 0.00;
		MarkingLineNumberInfo.setHotmeltmarkingSum(0.00);
		MarkingLineNumberInfo.setErasemarkingSum(0.00);
		for (int i = 0; i < markingLineNumberSumInfo.size(); i++) {
			if(markingLineNumberSumInfo.get(i).getMarkingMaterial().equals("0")) {
				hotmeltmarkingSum += markingLineNumberSumInfo.get(i).getSolidLineArea();
			}else if(markingLineNumberSumInfo.get(i).getMarkingMaterial().equals("1")){
				erasemarkingSum += markingLineNumberSumInfo.get(i).getSolidLineArea();
			}
		}
		MarkingLineNumberInfo.setHotmeltmarkingSum(hotmeltmarkingSum);
		MarkingLineNumberInfo.setErasemarkingSum(erasemarkingSum);
		return MarkingLineNumberInfo;
	}
	
	// 新增工程进度（标线工程数量表）
	public int insertMarkingLineNumber(MarkingLineNumberEntity markingLineNumberEntity){
		return MarkingLineNumberDao.insertMarkingLineNumber(markingLineNumberEntity);
	}
	
	// 更新工程进度（标线工程数量表）
	public int updateMarkingLineNumber(MarkingLineNumberEntity markingLineNumberEntity){
		return MarkingLineNumberDao.updateMarkingLineNumber( markingLineNumberEntity);
	}
	
	// 删除工程进度（标线工程数量表）
	public int deleteMarkingLineNumber(MarkingLineNumberEntity markingLineNumberEntity){
		return MarkingLineNumberDao.deleteMarkingLineNumber( markingLineNumberEntity);
	}	
}
