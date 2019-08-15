package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.GreenNumberDao;
import com.curing.projectSchedule.model.GreenNumberEntity;
import com.curing.projectSchedule.model.GreenNumberSum;
import com.curing.projectSchedule.service.GreenNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class GreenNumberServiceImpl implements GreenNumberService{
	@Autowired
	private GreenNumberDao greenNumberDao;
	
	// 工程进度（绿化工程数量表）List取得
	public List<GreenNumberEntity> getGreenNumberList(Map<String, Object> map){
		return greenNumberDao.getGreenNumberList(map);
	}
	
	// 工程进度（绿化工程数量表）合计
	public List<GreenNumberSum> getGreenNumberSum(Map<String, Object> map){
		return greenNumberDao.getGreenNumberSum(map);
	}
	
	// 新增工程进度（绿化工程数量表）
	public int insertGreenNumber(GreenNumberEntity greenNumberEntity){
		return greenNumberDao.insertGreenNumber(greenNumberEntity);
	}
	
	// 更新工程进度（绿化工程数量表）
	public int updateGreenNumber(GreenNumberEntity greenNumberEntity){
		return greenNumberDao.updateGreenNumber(greenNumberEntity);
	}
	
	// 删除工程进度（绿化工程数量表）
	public int deleteGreenNumber(GreenNumberEntity greenNumberEntity){
		return greenNumberDao.deleteGreenNumber(greenNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "绿化工程";
		//表头
		String[] head0 = new String[] { "编号", "起讫桩号", "工程名称", "位置", "主要尺寸（规格及说明", "路线长度（m）", "种植长度（m）", "植草（m2）","备注"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		 List<Map<String, Object>> dataList = greenNumberDao.getGreenNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "ProjectName", "Position", "Size", "RouteLength", "PlantLength", 
				 "PlantingGrass", "Remarks"};
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
