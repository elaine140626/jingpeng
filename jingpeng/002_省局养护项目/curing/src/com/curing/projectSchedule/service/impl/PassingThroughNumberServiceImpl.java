package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.PassingThroughNumberDao;
import com.curing.projectSchedule.model.PassingThroughNumberEntity;
import com.curing.projectSchedule.model.PassingThroughNumberSum;
import com.curing.projectSchedule.service.PassingThroughNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class PassingThroughNumberServiceImpl implements PassingThroughNumberService{
	@Autowired
	private PassingThroughNumberDao PassingThroughNumberDao;
	
	// 工程进度（过路过户涵工程数量表）List取得
	public List<PassingThroughNumberEntity> getPassingThroughNumberList(Map<String, Object> map) {
		return PassingThroughNumberDao.getPassingThroughNumberList(map);
	}

	// 工程进度（过路过户涵工程数量表）合计
	public List<PassingThroughNumberSum> getPassingThroughNumberSum(Map<String, Object> map) {
		return PassingThroughNumberDao.getPassingThroughNumberSum(map);
	}

	// 新增工程进度（过路过户涵工程数量表）
	public int insertPassingThroughNumber(PassingThroughNumberEntity passingThroughNumberEntity) {
		return PassingThroughNumberDao.insertPassingThroughNumber(passingThroughNumberEntity);
	}

	// 更新工程进度（过路过户涵工程数量表）
	public int updatePassingThroughNumber(PassingThroughNumberEntity passingThroughNumberEntity) {
		return PassingThroughNumberDao.updatePassingThroughNumber(passingThroughNumberEntity);
	}

	// 删除工程进度（过路过户涵工程数量表）
	public int deletePassingThroughNumber(PassingThroughNumberEntity passingThroughNumberEntity) {
		return PassingThroughNumberDao.deletePassingThroughNumber(passingThroughNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "过路过户涵";
		//表头
		String[] head0 = new String[] { "编号", "中心桩号", "孔数-孔径（孔-米）", "位置", "L（m）", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量", "工程数量","备注"};
		String[] head1 = new String[] { "M15浆砌片石端墙基础（m3）", "M15浆砌片石端墙墙身（m3）", "C25砼帽（m3）", "C25硬化面（m3）","涂料（m2）", "C20砼管基（m3）", "管节数（个）", "钢筋（Kg）","C30管节（m3）", "中部砂砾垫层（m3）", 
				"透水性材料（m3）", "挖基土方（m3）" };
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,3,1,1", "2,3,2,2","2,3,3,3", "2,3,4,4", "2,2,5,16" ,"2,3,17,17"};
		String[] headnum1 = new String[] { "3,3,5,5","3,3,6,6", "3,3,7,7", "3,3,8,8","3,3,9,9", "3,3,10,10", "3,3,11,11","3,3,12,12","3,3,13,13","3,3,14,14","3,3,15,15","3,3,16,16"};
		 List<Map<String, Object>> dataList = PassingThroughNumberDao.getPassingThroughNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "CoreNumber", "BridgeName", "SupportingC25Concrete", "SupportingRebar", "AliformC15Concrete", "AliformM10Wall", 
				 "AliformM10Basics", "AliformM10Veneer","RubblePaving", "Rubble", "Gravel", "BridgeBrand", "Mason",
				 "Backfilling", "Earthwork2", "Earthwork", "Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,18,5,17,5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
