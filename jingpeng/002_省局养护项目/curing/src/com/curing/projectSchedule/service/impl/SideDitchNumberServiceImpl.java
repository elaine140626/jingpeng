package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.SideDitchNumberDao;
import com.curing.projectSchedule.model.SideDitchNumberEntity;
import com.curing.projectSchedule.model.SideDitchNumberSum;
import com.curing.projectSchedule.service.SideDitchNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class SideDitchNumberServiceImpl implements SideDitchNumberService{
	@Autowired
	private SideDitchNumberDao sideDitchNumberDao;
	
	// 工程进度（边沟工程数量表）List取得
	public List<SideDitchNumberEntity> getSideDitchNumberList(Map<String, Object> map){
		return sideDitchNumberDao.getSideDitchNumberList(map);
	}
	
	// 工程进度（边沟工程数量表）合计
	public List<SideDitchNumberSum> getSideDitchNumberSum(Map<String, Object> map){
		return sideDitchNumberDao.getSideDitchNumberSum(map);
	}
	
	// 新增工程进度（边沟工程数量表）
	public int insertSideDitchNumber(SideDitchNumberEntity sideDitchNumberEntity){
		return sideDitchNumberDao.insertSideDitchNumber(sideDitchNumberEntity);
	}
	
	// 更新工程进度（边沟工程数量表）
	public int updateSideDitchNumber(SideDitchNumberEntity sideDitchNumberEntity){
		return sideDitchNumberDao.updateSideDitchNumber(sideDitchNumberEntity);
	}
	
	// 删除工程进度（边沟工程数量表）
	public int deleteSideDitchNumber(SideDitchNumberEntity sideDitchNumberEntity){
		return sideDitchNumberDao.deleteSideDitchNumber(sideDitchNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "边沟工程";
		//表头
		String[] head0 = new String[] { "编号", "起止桩号", "位置", "形式", "石砌边沟", "石砌边沟","石砌边沟","石砌边沟","石砌边沟","石砌边沟","石砌边沟","石砌边沟","石砌边沟","石砌边沟","备注"};
		String[] head1 = new String[] { "长度（m)）", "挖基土方（m3）", "10号砂浆抹面（m2）", "10cm砂垫层（m3）","7.5号浆砌片石（m3）", "C20混凝土（m3）", "盖板（块）", "8mm钢筋（Kg）","12mm钢筋（Kg）", "C25混凝土（m3）"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,3,1,1", "2,3,2,2","2,3,3,3", "2,2,4,13", "2,3,14,14"};
		String[] headnum1 = new String[] { "3,3,4,4","3,3,5,5","3,3,6,6", "3,3,7,7", "3,3,8,8","3,3,9,9", "3,3,10,10", "3,3,11,11","3,3,12,12","3,3,13,13"};
		 List<Map<String, Object>> dataList = sideDitchNumberDao.getSideDitchNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "Position", "Form", "Length", "Foundation", "Mortar", "SandCushion", "Rubble","C20Concrete", 
				 "CoverPlate", "EightRebar", "TwelveRebar", "C25Concrete","Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,15,4,14,4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
