package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.SubgradeEarthworkNumberDao;
import com.curing.projectSchedule.model.SubgradeEarthworkNumberEntity;
import com.curing.projectSchedule.model.SubgradeEarthworkNumberSum;
import com.curing.projectSchedule.service.SubgradeEarthworkNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class SubgradeEarthworkNumberServiceImpl implements SubgradeEarthworkNumberService{
	@Autowired
	private SubgradeEarthworkNumberDao subgradeEarthworkNumberDao;
	
	// 工程进度（路基土石方数量表）List取得
	public List<SubgradeEarthworkNumberEntity> getSubgradeEarthworkNumberList(Map<String, Object> map){
		return subgradeEarthworkNumberDao.getSubgradeEarthworkNumberList(map);
	}
	
	// 工程进度（路基土石方数量表）合计
	public List<SubgradeEarthworkNumberSum> getSubgradeEarthworkNumberSum(Map<String, Object> map){
		return subgradeEarthworkNumberDao.getSubgradeEarthworkNumberSum(map);
	}
	
	// 新增工程进度（路基土石方数量表）
	public int insertSubgradeEarthworkNumber(SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity){
		return subgradeEarthworkNumberDao.insertSubgradeEarthworkNumber(subgradeEarthworkNumberEntity);
	}
	
	// 更新工程进度（路基土石方数量表）
	public int updateSubgradeEarthworkNumber(SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity){
		return subgradeEarthworkNumberDao.updateSubgradeEarthworkNumber(subgradeEarthworkNumberEntity);
	}
	
	// 删除工程进度（路基土石方数量表）
	public int deleteSubgradeEarthworkNumber(SubgradeEarthworkNumberEntity subgradeEarthworkNumberEntity){
		return subgradeEarthworkNumberDao.deleteSubgradeEarthworkNumber(subgradeEarthworkNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "路基土石方工程";
		//表头
		String[] head0 = new String[] { "编号", "起止桩号", "横断面积（m2）", "横断面积（m2）",  "横断面积（m2）","横断面积（m2）", "距离（m）", "挖方分类及数量（m3）", "挖方分类及数量（m3）", "挖方分类及数量（m3）", "挖方分类及数量（m3）", "填方（m3）", "填方（m3）"
				, "填方（m3）", "备注"};
		String[] head1 = new String[] { "挖土石", "挖旧路", "填山皮石", "填种植土","","总数量", "Ⅰ（挖方）数量", "Ⅲ（挖旧路）数量","Ⅴ（挖石方）数量", "总数量", "填山皮石数量", "填种植土数量" };
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,3,1,1", "2,2,2,5","2,3,6,6", "2,2,7,10", "2,2,11,13", "2,3,14,14"};
		String[] headnum1 = new String[] { "3,3,2,2","3,3,3,3","3,3,4,4","3,3,5,5","3,3,6,6", "3,3,7,7", "3,3,8,8","3,3,9,9", "3,3,10,10", "3,3,11,11","3,3,12,12","3,3,13,13"};
		 List<Map<String, Object>> dataList = subgradeEarthworkNumberDao.getSubgradeEarthworkNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "EarthRock", "OldRoad", "MountainFillStone", "PlantingSoil", "Distance", 
				 "ExcavationAllAmount", "ExcavationAmount","ExcavationOldAmount", "ExcavationRockAmount", "FillAllAmount", "FillStoneAmount", "FillSoilAmount",
				 "Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,15,2,14,2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
