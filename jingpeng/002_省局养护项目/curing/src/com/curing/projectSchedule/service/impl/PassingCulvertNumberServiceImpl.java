package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.PassingCulvertNumberDao;
import com.curing.projectSchedule.model.PassingCulvertNumberEntity;
import com.curing.projectSchedule.model.PassingCulvertNumberSum;
import com.curing.projectSchedule.service.PassingCulvertNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class PassingCulvertNumberServiceImpl implements PassingCulvertNumberService{
	@Autowired
	private PassingCulvertNumberDao passingCulvertNumberDao;
	
	// 工程进度（石砌边沟过路涵工程数量表）List取得
	public List<PassingCulvertNumberEntity> getPassingCulvertNumberList(Map<String, Object> map){
		return passingCulvertNumberDao.getPassingCulvertNumberList(map);
	}
	
	// 工程进度（石砌边沟过路涵工程数量表）合计
	public List<PassingCulvertNumberSum> getPassingCulvertNumberSum(Map<String, Object> map){
		return passingCulvertNumberDao.getPassingCulvertNumberSum(map);
	}
	
	// 新增工程进度（石砌边沟过路涵工程数量表）
	public int insertPassingCulvertNumber(PassingCulvertNumberEntity passingCulvertNumberEntity){
		return passingCulvertNumberDao.insertPassingCulvertNumber(passingCulvertNumberEntity);
	}
	
	// 更新工程进度（石砌边沟过路涵工程数量表）
	public int updatePassingCulvertNumber(PassingCulvertNumberEntity passingCulvertNumberEntity){
		return passingCulvertNumberDao.updatePassingCulvertNumber(passingCulvertNumberEntity);
	}
	
	// 删除工程进度（石砌边沟过路涵工程数量表）
	public int deletePassingCulvertNumber(PassingCulvertNumberEntity passingCulvertNumberEntity){
		return passingCulvertNumberDao.deletePassingCulvertNumber(passingCulvertNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "石砌边沟过路涵";
		//表头
		String[] head0 = new String[] { "编号", "中心桩号", "孔数-孔径（孔-米）", "位置", "L（m)", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量","备注"};
		String[] head1 = new String[] { "过路板钢筋（Kg）", "过路板混凝土C30（m3）", "涂料（m2）", "涵面铺装钢筋（Kg）","涵面铺装混凝土C30（m3）", "C20混凝土台帽（m3）", "M15浆砌片石涵台身及基础（m2）", "涵身顶面1cm厚油毛毡（m2）","透水性砾料（m3）" };
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,3,1,1", "2,3,2,2","2,3,3,3", "2,3,4,4", "2,2,5,13" ,"2,3,14,14"};
		String[] headnum1 = new String[] { "3,3,5,5","3,3,6,6", "3,3,7,7", "3,3,8,8","3,3,9,9", "3,3,10,10", "3,3,11,11","3,3,12,12","3,3,13,13"};
		 List<Map<String, Object>> dataList = passingCulvertNumberDao.getPassingCulvertNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "HoleCount", "Position", "L", "CrossingRebar", "CrossingConcrete", 
				 "Coating", "SpreadRebar","SpreadConcrete", "ConcreteCap", "RubbleBasics", "ThickFelt", "PermeableGranules","Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,15,5,14,5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
