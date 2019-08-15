package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.SignBoardNumberDao;
import com.curing.projectSchedule.model.SignBoardNumberEntity;
import com.curing.projectSchedule.model.SignBoardNumberSum;
import com.curing.projectSchedule.service.SignBoardNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class SignBoardNumberServiceImpl implements SignBoardNumberService{
	@Autowired
	private SignBoardNumberDao signBoardNumberDao;
	
	// 工程进度（标志牌工程数量表）List取得
	public List<SignBoardNumberEntity> getSignBoardNumberList(Map<String, Object> map){
		return signBoardNumberDao.getSignBoardNumberList(map);
	}
	
	// 工程进度（标志牌工程数量表）合计
	public List<SignBoardNumberSum> getSignBoardNumberSum(Map<String, Object> map){
		return signBoardNumberDao.getSignBoardNumberSum(map);
	}
	
	// 新增工程进度（标志牌工程数量表）
	public int insertSignBoardNumber(SignBoardNumberEntity signBoardNumberEntity){
		return signBoardNumberDao.insertSignBoardNumber(signBoardNumberEntity);
	}
	
	// 更新工程进度（标志牌工程数量表）
	public int updateSignBoardNumber(SignBoardNumberEntity signBoardNumberEntity){
		return signBoardNumberDao.updateSignBoardNumber(signBoardNumberEntity);
	}
	
	// 删除工程进度（标志牌工程数量表）
	public int deleteSignBoardNumber(SignBoardNumberEntity signBoardNumberEntity){
		return signBoardNumberDao.deleteSignBoardNumber(signBoardNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		String sheetName = "标志牌工程";
		//表头
		String[] head0 = new String[] { "编号", "材料名称及规格", "单位", "单悬标志（块）", "单悬标志（块）", "单柱标志（块)", "单柱标志（块)", "双柱标志（块）", "双柱标志（块）", "附着标志（块）", "附着标志（块）","备注"};
		String[] head1 = new String[] { "数量", "重量（Kg））","数量", "重量（Kg））","数量", "重量（Kg））","数量", "重量（Kg））"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,3,1,1", "2,3,2,2","2,2,3,4", "2,2,5,6", "2,2,7,8" ,"2,2,9,10","2,3,11,11"};
		String[] headnum1 = new String[] {"3,3,3,3","3,3,4,4","3,3,5,5", "3,3,6,6","3,3,7,7", "3,3,8,8","3,3,9,9", "3,3,10,10"};
		List<Map<String, Object>> dataList = signBoardNumberDao.getSignBoardNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "NameSpecifications", "Unit", "SingleHangAmount", "SingleHangWeight", "SingleColumnAmount", "SingleColumnWeight", 
				 "DoubleColumnAmount", "DoubleColumnWeight","AttachmentAmount","AttachmentWeight","Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,12,3,11,3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
