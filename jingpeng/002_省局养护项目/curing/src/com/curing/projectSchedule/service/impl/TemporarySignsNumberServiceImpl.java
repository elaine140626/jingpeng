package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.TemporarySignsNumberDao;
import com.curing.projectSchedule.model.TemporarySignsNumberEntity;
import com.curing.projectSchedule.model.TemporarySignsNumberSum;
import com.curing.projectSchedule.service.TemporarySignsNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class TemporarySignsNumberServiceImpl implements TemporarySignsNumberService{
	@Autowired
	private TemporarySignsNumberDao temporarySignsNumberDao;
	
	// 工程进度（临时标志工程数量表）List取得
	public List<TemporarySignsNumberEntity> getTemporarySignsNumberList(Map<String, Object> map){
		return temporarySignsNumberDao.getTemporarySignsNumberList(map);
	}
	
	// 工程进度（临时标志工程数量表）合计
	public List<TemporarySignsNumberSum> getTemporarySignsNumberSum(Map<String, Object> map){
		return temporarySignsNumberDao.getTemporarySignsNumberSum(map);
	}
	
	// 新增工程进度（临时标志工程数量表）
	public int insertTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity){
		return temporarySignsNumberDao.insertTemporarySignsNumber(temporarySignsNumberEntity);
	}
	
	// 更新工程进度（临时标志工程数量表）
	public int updateTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity){
		return temporarySignsNumberDao.updateTemporarySignsNumber(temporarySignsNumberEntity);
	}
	
	// 删除工程进度（临时标志工程数量表）
	public int deleteTemporarySignsNumber(TemporarySignsNumberEntity temporarySignsNumberEntity){
		return temporarySignsNumberDao.deleteTemporarySignsNumber(temporarySignsNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = " 临时标志";
		//表头
		String[] head0 = new String[] { "编号", "中心桩号", "设置位置", "临时标志名称", "板面尺寸（高度cm×长度cm）", "数量","备注"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		 List<Map<String, Object>> dataList = temporarySignsNumberDao.getTemporarySignsNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "Position", "SignName", "Size", "Amount", "Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,数值对应列名称,创建时间,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0, colName, date,5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
