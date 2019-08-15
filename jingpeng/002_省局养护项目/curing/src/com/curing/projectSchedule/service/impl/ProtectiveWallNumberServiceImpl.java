package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.ProtectiveWallNumberDao;
import com.curing.projectSchedule.model.ProtectiveWallNumberEntity;
import com.curing.projectSchedule.model.ProtectiveWallNumberSum;
import com.curing.projectSchedule.service.ProtectiveWallNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class ProtectiveWallNumberServiceImpl implements ProtectiveWallNumberService{
	@Autowired
	private ProtectiveWallNumberDao protectiveWallNumberDao;
	
	// 工程进度（防护墙工程数量表）List取得
	public List<ProtectiveWallNumberEntity> getProtectiveWallNumberList(Map<String, Object> map){
		return protectiveWallNumberDao.getProtectiveWallNumberList(map);
	}
	
	// 工程进度（防护墙工程数量表）合计
	public List<ProtectiveWallNumberSum> getProtectiveWallNumberSum(Map<String, Object> map){
		return protectiveWallNumberDao.getProtectiveWallNumberSum(map);
	}
	
	// 新增工程进度（防护墙工程数量表）
	public int insertProtectiveWallNumber(ProtectiveWallNumberEntity protectiveWallNumberEntity){
		return protectiveWallNumberDao.insertProtectiveWallNumber(protectiveWallNumberEntity);
	}
	
	// 更新工程进度（防护墙工程数量表）
	public int updateProtectiveWallNumber(ProtectiveWallNumberEntity protectiveWallNumberEntity){
		return protectiveWallNumberDao.updateProtectiveWallNumber(protectiveWallNumberEntity);
	}
	
	// 删除工程进度（防护墙工程数量表）
	public int deleteProtectiveWallNumber(ProtectiveWallNumberEntity protectiveWallNumberEntity){
		return protectiveWallNumberDao.deleteProtectiveWallNumber(protectiveWallNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "防护墙工程";
		//表头
		String[] head0 = new String[] { "编号", "起讫桩号", "位置", "修建长度（m）", "数量（个）", "C25混凝土（m3）", "涂漆（m2）","备注"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		 List<Map<String, Object>> dataList = protectiveWallNumberDao.getProtectiveWallNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "Position", "Length", "Amount", "C25Concrete", "Lacquer","Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,数值对应列名称,创建时间,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0, colName, date,3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
