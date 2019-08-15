package com.oil.dao.procurementApplication;

import java.util.List;
import java.util.Map;

import com.oil.model.Datadictionaty;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.procurementApplication.PurchaseRequisition;

public interface PurchaseRequisitionDao {
	//tree的查询
	List<PurchaseRequisition> getTreeList(Map<String,Object> map);
	
	//获取页面list信息
	List<PurchaseRequisition> getInfoList(Map<String,Object> map);
	
	//删除采购申请单
	int deletePurchaseRequisition(Map<String,Object> map);
	
	//数据字典查询
	List<Datadictionaty> getAllDatadictionaty();
	
	// 物料名称
	List<MaterielinfoEntity> getMaterielinfoList(Map<String, Object> map);
	
	// 物料型号
	List<MaterielinfoEntity> getMaterielModel(Map<String, Object> map);
	
	//添加采购申请单
	int addPurchaseRequisition(PurchaseRequisition param);
	
	//修改采购申请单
	int updatePurchaseRequisition(PurchaseRequisition param);
	
	//查询采购合同明细
	List<Map<String,Object>> getAllPurchaseorderinfo(Map<String,Object> map);
}
