package com.oil.service.dispath.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.dispath.ExportmeasureotherDao;
import com.oil.model.dispath.ExportmeasureotherEntity;
import com.oil.service.dispath.ExportmeasureotherService;

@Service
public class ExportmeasureotherServiceImpl implements ExportmeasureotherService{

	@Autowired
	ExportmeasureotherDao exportmeasureotherDao;
	
	// 查询其它出库单列表
	public List<ExportmeasureotherEntity> getExportmeasureotherList(Map<String, Object> map) {
		return exportmeasureotherDao.getExportmeasureotherList(map);
	}

	// 新增其他出库单
	public int insertExportmeasureother(ExportmeasureotherEntity data) {
		return exportmeasureotherDao.insertExportmeasureother(data);
	}

	// 修改其他出库单
	public int updateExportmeasureother(ExportmeasureotherEntity data) {
		return exportmeasureotherDao.updateExportmeasureother(data);
	}

	// 删除其他出库单
	public int deleteExportmeasureother(Map<String, Object> map) {
		return exportmeasureotherDao.deleteExportmeasureother(map);
	}
	
	// 页面检索list
	public Map<String, Object> getDataList() {
		Map<String, Object> dataList = new HashMap<String, Object>();
		// 客户信息list
		dataList.put("clientList", exportmeasureotherDao.getClientList());
		// 车牌号码list
		dataList.put("plateNumberList", exportmeasureotherDao.getPlateNumberList());
		// 车队信息list
		dataList.put("fleetNameList", exportmeasureotherDao.getFleetNameList());
		// 送货人list
		dataList.put("deliveryManList", exportmeasureotherDao.getDeliveryManList());
		// 规格型号list
		dataList.put("materielModelList", exportmeasureotherDao.getMaterielModelList());
		// 产品名称list
		dataList.put("materielNameList", exportmeasureotherDao.getMaterielNameList());
				
		return dataList;
	}

	// 删除司机电子签名
	public int deleteElectronicsName(Map<String, Object> param) {
		return exportmeasureotherDao.deleteElectronicsName(param);
	}
}
