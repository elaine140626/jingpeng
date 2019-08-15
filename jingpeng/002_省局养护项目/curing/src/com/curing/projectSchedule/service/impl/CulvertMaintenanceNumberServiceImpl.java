package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.CulvertMaintenanceNumberDao;
import com.curing.projectSchedule.model.CulvertMaintenanceNumberEntity;
import com.curing.projectSchedule.model.CulvertMaintenanceNumberSum;
import com.curing.projectSchedule.service.CulvertMaintenanceNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class CulvertMaintenanceNumberServiceImpl implements CulvertMaintenanceNumberService{
	@Autowired
	private CulvertMaintenanceNumberDao culvertMaintenanceNumberDao;
	
	// 工程进度（涵洞维修工程数量表）List取得
	public List<CulvertMaintenanceNumberEntity> getCulvertMaintenanceNumberList(Map<String, Object> map){
		return culvertMaintenanceNumberDao.getCulvertMaintenanceNumberList(map);
	}
	
	// 工程进度（涵洞维修工程数量表）合计
	public List<CulvertMaintenanceNumberSum> getCulvertMaintenanceNumberSum(Map<String, Object> map){
		return culvertMaintenanceNumberDao.getCulvertMaintenanceNumberSum(map);
	}
	
	// 新增工程进度（涵洞维修工程数量表）
	public int insertCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity CulvertMaintenanceNumberEntity){
		return culvertMaintenanceNumberDao.insertCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity);
	}
	
	// 更新工程进度（涵洞维修工程数量表）
	public int updateCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity CulvertMaintenanceNumberEntity){
		return culvertMaintenanceNumberDao.updateCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity);
	}
	
	// 删除工程进度（涵洞维修工程数量表）
	public int deleteCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity CulvertMaintenanceNumberEntity){
		return culvertMaintenanceNumberDao.deleteCulvertMaintenanceNumber(CulvertMaintenanceNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "涵洞维修工程";
		//表头
		String[] head0 = new String[] { "编号", "原构造物情况", "原构造物情况", "原构造物情况", "原构造物情况", "淤泥（m3）", "C30混凝土（m3)", "长35cm间距30cmφ12植筋（根）", "M10浆砌片石（m3）","备注"};
		String[] head1 = new String[] { "桩号", "孔径", "角度", "类型"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,2,1,4", "2,3,5,5","2,3,6,6", "2,3,7,7", "2,3,8,8" ,"2,3,9,9"};
		String[] headnum1 = new String[] { "3,3,1,1","3,3,2,2", "3,3,3,3", "3,3,4,4"};
		 List<Map<String, Object>> dataList = culvertMaintenanceNumberDao.getCulvertMaintenanceNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "Aperture", "Angle", "Type", "Silt", "Concrete", 
				 "BondedRebars", "Rubble","Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,10,1,5,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

}
