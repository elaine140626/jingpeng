package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.CrossingPillarDao;
import com.curing.projectSchedule.model.CrossingPillarEntity;
import com.curing.projectSchedule.model.CrossingPillarSum;
import com.curing.projectSchedule.service.CrossingPillarService;
import com.curing.util.ReportMergeXls;
@Service
@Transactional
public class CrossingPillarServiceImpl implements CrossingPillarService{

	@Autowired
	private CrossingPillarDao crossingPillarDao;
	
	// 工程进度（道口标柱设置一览表）List取得
	public List<CrossingPillarEntity> getCrossingPillarList(Map<String, Object> map){
		return crossingPillarDao.getCrossingPillarList(map);
	}
	
	// 工程进度（道口标柱设置一览表）合计
	public List<CrossingPillarSum> getCrossingPillarSum(Map<String, Object> map){
		return crossingPillarDao.getCrossingPillarSum(map);
	}
	
	// 新增工程进度（道口标柱设置一览表）
	public int insertCrossingPillar(CrossingPillarEntity crossingPillarEntity){
		return crossingPillarDao.insertCrossingPillar(crossingPillarEntity);
	}
	
	// 更新工程进度（道口标柱设置一览表）
	public int updateCrossingPillar(CrossingPillarEntity crossingPillarEntity){
		return crossingPillarDao.updateCrossingPillar(crossingPillarEntity);
	}
	
	// 删除工程进度（道口标柱设置一览表）
	public int deleteCrossingPillar(CrossingPillarEntity crossingPillarEntity){
		return crossingPillarDao.deleteCrossingPillar(crossingPillarEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "道口标柱";
		//表头
		String[] head0 = new String[] { "编号", "设置位置", "交叉行形式", "被交叉道路宽度", "被交叉道路宽度", "应设数量(个)","备注"};
		String[] head1 = new String[] { "中心桩号","左", "左", "右"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,2,1,1", "2,3,2,2","2,2,3,4", "2,3,5,5", "2,3,6,6"};
		String[] headnum1 = new String[] { "3,3,1,1","3,3,3,3", "3,3,4,4"};
		 List<Map<String, Object>> dataList = crossingPillarDao.getCrossingPillarListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "CoreNumber", "AcrossForm", "AcrossLeft", "AcrossRight", "Amount", "Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,7,1,6,3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
