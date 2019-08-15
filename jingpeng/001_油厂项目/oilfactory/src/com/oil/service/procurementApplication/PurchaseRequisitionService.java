package com.oil.service.procurementApplication;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionaty;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.procurementApplication.PurchaseRequisition;
import com.oil.model.repertory.NoWeighOutWarehouse;

public interface PurchaseRequisitionService {
	//tree的查询
	List<PurchaseRequisition> getTreeList(Map<String,Object> map);
	
	//获取页面list信息
	DataTablesResponseInfo getInfoList(Map<String,Object> map);
	
	//采购申请单删除
	ResponseInfo deletePurchaseRequisition(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	//数据字典查询
	List<Datadictionaty> getAllDatadictionaty();
	
	// 获取物料名称
	List<MaterielinfoEntity> getMaterielinfoList(Map<String, Object> map);
	
	// 物料型号
	List<MaterielinfoEntity> getMaterielModel(Map<String, Object> map);
	
	//新增采购申请单
	ResponseInfo addPurchaseRequisition(HttpServletRequest request, Map<String, Object> map) throws IOException;
		
	// 更新采购申请单
	ResponseInfo updatePurchaseRequisition(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	// 根据id获取采购申请单
	PurchaseRequisition getInfoListById(Map<String, Object> map);
	
	//查询采购合同明细
	List<Map<String,Object>> getAllPurchaseorderinfo(Map<String,Object> map);
}
