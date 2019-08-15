package com.curing.projectSchedule.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.DeckTreatmentNumberDao;
import com.curing.projectSchedule.model.DeckTreatmentNumberEntity;
import com.curing.projectSchedule.model.DeckTreatmentNumberSum;
import com.curing.projectSchedule.service.DeckTreatmentNumberService;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class DeckTreatmentNumberServiceImpl implements DeckTreatmentNumberService {
	@Autowired
	private DeckTreatmentNumberDao deckTreatmentNumberDao;

	// 工程进度（桥面处理工程数量表）List取得
	public List<DeckTreatmentNumberEntity> getDeckTreatmentNumberList(Map<String, Object> map) {
		return deckTreatmentNumberDao.getDeckTreatmentNumberList(map);
	}

	// 工程进度（桥面处理工程数量表）合计
	public List<DeckTreatmentNumberSum> getDeckTreatmentNumberSum(Map<String, Object> map) {
		return deckTreatmentNumberDao.getDeckTreatmentNumberSum(map);
	}

	// 新增工程进度（桥面处理工程数量表）
	public int insertDeckTreatmentNumber(DeckTreatmentNumberEntity deckTreatmentNumberEntity) {
		return deckTreatmentNumberDao.insertDeckTreatmentNumber(deckTreatmentNumberEntity);
	}

	// 更新工程进度（桥面处理工程数量表）
	public int updateDeckTreatmentNumber(DeckTreatmentNumberEntity deckTreatmentNumberEntity) {
		return deckTreatmentNumberDao.updateDeckTreatmentNumber(deckTreatmentNumberEntity);
	}

	// 删除工程进度（桥面处理工程数量表）
	public int deleteDeckTreatmentNumber(DeckTreatmentNumberEntity deckTreatmentNumberEntity) {
		return deckTreatmentNumberDao.deleteDeckTreatmentNumber(deckTreatmentNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = " 桥面处理工程";
		//表头
		String[] head0 = new String[] { "编号", "桥梁名称", "桥梁桩号", "桥梁长度（米）", "桥面净宽（米）", "温拌细粒式SBS改性沥青混凝土（AC-13）", "温拌细粒式SBS改性沥青混凝土（AC-13）",
				"温拌中粒式SBS改性沥青混凝土（AC-20C）", "温拌中粒式SBS改性沥青混凝土（AC-20C）", "橡胶沥青碎石封层（千平方米）", "铣刨桥面", "铣刨桥面","备注"};
		String[] head1 = new String[] { "厚度（厘米）", "面积（千平方米）", "厚度（厘米）", "面积（千平方米）","厚度（厘米）", "面积（千平方米）","厚度（厘米）", "面积（千平方米）"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,3,0,0", "2,3,1,1", "2,3,2,2","2,3,3,3", "2,3,4,4", "2,2,5,6" ,"2,2,7,8","2,3,9,9","2,2,10,11","2,3,12,12"};
		String[] headnum1 = new String[] { "3,3,5,5", "3,3,6,6", "3,3,7,7","3,3,8,8", "3,3,10,10", "3,3,11,11"};
		 List<Map<String, Object>> dataList = deckTreatmentNumberDao.getDeckTreatmentNumberListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "BridgeName", "BridgeNumber", "BridgeLength", "BridgeWidth", "FineThickness", "FineArea", 
				 "NeutralThickness", "NeutralArea","RubberAsphaltGravel", "DeckThickness", "DeckArea", "Remarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, colName, date,head0.length,4,13,3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
