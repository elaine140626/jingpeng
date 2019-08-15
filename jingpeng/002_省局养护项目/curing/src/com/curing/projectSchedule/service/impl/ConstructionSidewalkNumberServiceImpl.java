package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.ConstructionSidewalkNumberDao;
import com.curing.projectSchedule.model.ConstructionSidewalkNumberEntity;
import com.curing.projectSchedule.model.ConstructionSidewalkNumberSum;
import com.curing.projectSchedule.service.ConstructionSidewalkNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class ConstructionSidewalkNumberServiceImpl implements ConstructionSidewalkNumberService{
	@Autowired
	private ConstructionSidewalkNumberDao constructionSidewalkNumberDao;
	
	// 工程进度（施工便道路面工程数量汇总表）List取得
	public List<ConstructionSidewalkNumberEntity> getConstructionSidewalkNumberList(Map<String, Object> map){
		return constructionSidewalkNumberDao.getConstructionSidewalkNumberList(map);
	}
	
	// 工程进度（施工便道路面工程数量汇总表）合计
	public List<ConstructionSidewalkNumberSum> getConstructionSidewalkNumberSum(Map<String, Object> map){
		return constructionSidewalkNumberDao.getConstructionSidewalkNumberSum(map);
	}
	
	// 新增工程进度（施工便道路面工程数量汇总表）
	public int insertConstructionSidewalkNumber(ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity){
		return constructionSidewalkNumberDao.insertConstructionSidewalkNumber(constructionSidewalkNumberEntity);
	}
	
	// 更新工程进度（施工便道路面工程数量汇总表）
	public int updateConstructionSidewalkNumber(ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity){
		return constructionSidewalkNumberDao.updateConstructionSidewalkNumber(constructionSidewalkNumberEntity);
	}
	
	// 删除工程进度（施工便道路面工程数量汇总表）
	public int deleteConstructionSidewalkNumber(ConstructionSidewalkNumberEntity constructionSidewalkNumberEntity){
		return constructionSidewalkNumberDao.deleteConstructionSidewalkNumber(constructionSidewalkNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "施工便道路面工程";
		//表头
		String[] head0 = new String[] { "编号", "起止桩号", "长度m", "建设性质", "路面宽度m", "4cm普通沥青混凝土（AC-13）m2", "4cm改性沥青混凝土（AC-13)m2", "5cm改性沥青混凝土（AC-16）m2", "7cm普通沥青混凝土（AC-25）m2","橡胶沥青碎石封层0.6cm",
				"粘油层m2", "透油层m2", "铣刨旧路厚4cm m2", "新建结构", "新建结构", "新建结构", "新建结构", "培路肩厚5cm m2","备注"};
		String[] head1 = new String[] { "挖除旧路m2", "20cm天然砂砾垫层m2", "20cm水泥稳定砂砾上基层m2", "20cm水泥稳定砂砾下基层m2"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,3,1,1", "2,3,2,2","2,3,3,3", "2,3,4,4", "2,3,5,5" ,"2,3,6,6","2,3,7,7","2,3,8,8","2,3,9,9","2,3,10,10","2,3,11,11",
				"2,3,12,12","2,2,13,16","2,3,17,17","2,3,18,18"};
		String[] headnum1 = new String[] { "3,3,13,13","3,3,14,14", "3,3,15,15", "3,3,16,16"};
		 List<Map<String, Object>> dataList = constructionSidewalkNumberDao.getConstructionSidewalkNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "Length", "BuildNature", "RoadWidth", "OrdinaryConcrete4cm", "ModificationConcrete4cm", 
				 "ModificationConcrete5cm", "OrdinaryConcrete7cm","GravelSeal", "StickyLayer", "PermeableLayer", "MillingThick", "DigOut",
				 "GravelCushion", "GravelUpper", "GravelLower", "Shoulder", "Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,19,13,18,5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
