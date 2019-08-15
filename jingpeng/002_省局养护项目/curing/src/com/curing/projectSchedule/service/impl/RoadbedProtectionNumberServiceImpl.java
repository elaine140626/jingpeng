package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.RoadbedProtectionNumberDao;
import com.curing.projectSchedule.model.RoadbedProtectionNumberEntity;
import com.curing.projectSchedule.model.RoadbedProtectionNumberSum;
import com.curing.projectSchedule.service.RoadbedProtectionNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class RoadbedProtectionNumberServiceImpl implements RoadbedProtectionNumberService{
	@Autowired
	private RoadbedProtectionNumberDao roadbedProtectionNumberDao;
	
	// 工程进度（路基防护工程数量表）List取得
	public List<RoadbedProtectionNumberEntity> getRoadbedProtectionNumberList(Map<String, Object> map){
		return roadbedProtectionNumberDao.getRoadbedProtectionNumberList(map);
	}
	
	// 工程进度（路基防护工程数量表）合计
	public List<RoadbedProtectionNumberSum> getRoadbedProtectionNumberSum(Map<String, Object> map){
		return roadbedProtectionNumberDao.getRoadbedProtectionNumberSum(map);
	}
	
	// 新增工程进度（路基防护工程数量表）
	public int insertRoadbedProtectionNumber(RoadbedProtectionNumberEntity roadbedProtectionNumberEntity){
		return roadbedProtectionNumberDao.insertRoadbedProtectionNumber(roadbedProtectionNumberEntity);
	}
	
	// 更新工程进度（路基防护工程数量表）
	public int updateRoadbedProtectionNumber(RoadbedProtectionNumberEntity roadbedProtectionNumberEntity){
		return roadbedProtectionNumberDao.updateRoadbedProtectionNumber(roadbedProtectionNumberEntity);
	}
	
	// 删除工程进度（路基防护工程数量表）
	public int deleteRoadbedProtectionNumber(RoadbedProtectionNumberEntity roadbedProtectionNumberEntity){
		return roadbedProtectionNumberDao.deleteRoadbedProtectionNumber(roadbedProtectionNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "路基防护工程数量";
		//表头
		String[] head0 = new String[] { "编号", "起止桩号", "位置", "建设性质", "挡土墙工程数量", "挡土墙工程数量", "挡土墙工程数量", "挡土墙工程数量", "挡土墙工程数量", "挡土墙工程数量", "挡土墙工程数量", "挡土墙工程数量"
				, "石笼工程数量表", "石笼工程数量表", "石笼工程数量表", "石笼工程数量表","备注"};
		String[] head1 = new String[] { "墙长（m）", "抬高高度（m）", "平均高（h）", "片石墙身（m3）","片石基础（m3）", "抹面（m3）", "砂砾垫层（m3）", "开挖方（m3）","长度（m）", "块石（m3）", "8mm钢筋（Kg）", "铁丝（m）" };
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,3,1,1", "2,3,2,2","2,3,3,3", "2,2,4,11", "2,2,12,15" ,"2,3,16,16"};
		String[] headnum1 = new String[] { "3,3,4,4","3,3,5,5", "3,3,6,6","3,3,7,7", "3,3,8,8","3,3,9,9", "3,3,10,10", "3,3,11,11","3,3,12,12","3,3,13,13","3,3,14,14","3,3,15,15"};
		 List<Map<String, Object>> dataList = roadbedProtectionNumberDao.getRoadbedProtectionNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "startStopNumber", "position", "buildNatureName", "wallLength", "raiseHeight", "averageHigh", 
				 "stoneWallBody", "stoneBasics","plastering", "gravelCushion", "openExcavation", "length", "blockStone",
				 "eightRebar", "wire", "remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,17,4,16,4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
