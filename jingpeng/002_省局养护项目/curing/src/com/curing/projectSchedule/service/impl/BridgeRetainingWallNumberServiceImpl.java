package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.BridgeRetainingWallNumberDao;
import com.curing.projectSchedule.model.BridgeRetainingWallNumberEntity;
import com.curing.projectSchedule.model.BridgeRetainingWallNumberSum;
import com.curing.projectSchedule.service.BridgeRetainingWallNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class BridgeRetainingWallNumberServiceImpl implements BridgeRetainingWallNumberService{
	@Autowired
	private BridgeRetainingWallNumberDao bridgeRetainingWallNumberDao;
	
	// 工程进度（桥梁挡墙工程数量表）List取得
	public List<BridgeRetainingWallNumberEntity> getBridgeRetainingWallNumberList(Map<String, Object> map){
		return bridgeRetainingWallNumberDao.getBridgeRetainingWallNumberList(map);
	}
	
	// 工程进度（桥梁挡墙工程数量表）合计
	public List<BridgeRetainingWallNumberSum> getBridgeRetainingWallNumberSum(Map<String, Object> map){
		return bridgeRetainingWallNumberDao.getBridgeRetainingWallNumberSum(map);
	}
	
	// 新增工程进度（桥梁挡墙工程数量表）
	public int insertBridgeRetainingWallNumber(BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity){
		return bridgeRetainingWallNumberDao.insertBridgeRetainingWallNumber(bridgeRetainingWallNumberEntity);
	}
	
	// 更新工程进度（桥梁挡墙工程数量表）
	public int updateBridgeRetainingWallNumber(BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity){
		return bridgeRetainingWallNumberDao.updateBridgeRetainingWallNumber(bridgeRetainingWallNumberEntity);
	}
	
	// 删除工程进度（桥梁挡墙工程数量表）
	public int deleteBridgeRetainingWallNumber(BridgeRetainingWallNumberEntity bridgeRetainingWallNumberEntity){
		return bridgeRetainingWallNumberDao.deleteBridgeRetainingWallNumber(bridgeRetainingWallNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		String sheetName = "桥梁挡墙工程";
		//表头
		String[] head0 = new String[] { "编号", "桩号", "桥名", "挡墙长（m)", "M10片石基础（m3）", "M10片石墙身（m3）", "M10浆砌块石镶面（m3）", "挖方（m3）", "回填透水性砾料（m3）","备注"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		 List<Map<String, Object>> dataList = bridgeRetainingWallNumberDao.getBridgeRetainingWallNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "BridgeName", "WallLength", "StoneBasics", "StoneWallBody", "BlockStone", 
				 "Excavation", "BackfillGravel","Remarks"};
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
