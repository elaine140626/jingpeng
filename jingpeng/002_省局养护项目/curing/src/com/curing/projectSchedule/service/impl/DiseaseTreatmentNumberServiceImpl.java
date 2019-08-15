package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.DiseaseTreatmentNumberDao;
import com.curing.projectSchedule.model.DiseaseTreatmentNumberEntity;
import com.curing.projectSchedule.model.DiseaseTreatmentNumberSum;
import com.curing.projectSchedule.service.DiseaseTreatmentNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class DiseaseTreatmentNumberServiceImpl implements DiseaseTreatmentNumberService{
	@Autowired
	private DiseaseTreatmentNumberDao diseaseTreatmentNumberDao;
	
	// 工程进度（旧路面病害处治工程数量表）List取得
	public List<DiseaseTreatmentNumberEntity> getDiseaseTreatmentNumberList(Map<String, Object> map){
		return diseaseTreatmentNumberDao.getDiseaseTreatmentNumberList(map);
	}
	
	// 工程进度（旧路面病害处治工程数量表）合计
	public List<DiseaseTreatmentNumberSum> getDiseaseTreatmentNumberSum(Map<String, Object> map){
		return diseaseTreatmentNumberDao.getDiseaseTreatmentNumberSum(map);
	}
	
	// 新增工程进度（旧路面病害处治工程数量表）
	public int insertDiseaseTreatmentNumber(DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity){
		return diseaseTreatmentNumberDao.insertDiseaseTreatmentNumber(diseaseTreatmentNumberEntity);
	}
	
	// 更新工程进度（旧路面病害处治工程数量表）
	public int updateDiseaseTreatmentNumber(DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity){
		return diseaseTreatmentNumberDao.updateDiseaseTreatmentNumber(diseaseTreatmentNumberEntity);
	}
	
	// 删除工程进度（旧路面病害处治工程数量表）
	public int deleteDiseaseTreatmentNumber(DiseaseTreatmentNumberEntity diseaseTreatmentNumberEntity){
		return diseaseTreatmentNumberDao.deleteDiseaseTreatmentNumber(diseaseTreatmentNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = " 旧路面病害处治";
		//表头
		String[] head0 = new String[] { "编号", "病害名称","病害工程数量（m2）", "灌缝（m）", "4cm温拌SBS改性沥青混凝土（AC-13)(平方米）", "20cm4.5%水泥稳定碎石上基层（平方米）", "7cm沥青混凝土（m2）", "透层油（m2）", "粘层油（m2）", "单层沥青表面处治（m2）","铣刨4cm面层（平方米）"
				, "铣刨20cm基层（平方米）", "喷洒沥青（m2）", "碎石（m2）", "石屑（m2）", "挖除旧路面（m2）", "备注"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		 List<Map<String, Object>> dataList = diseaseTreatmentNumberDao.getDiseaseTreatmentNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "DiseaseName", "DiseaseAmount", "Caulking", "SBSConcrete", "GravelUpper", "SevenConcrete",  "PermeableLayer", 
				 "StickyLayer","SingleAsphalt", "MillingFour", "MillingTwenty", "SprayAsphalt", "Gravel", "StoneChip", "DigOut", "Remarks"};
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
