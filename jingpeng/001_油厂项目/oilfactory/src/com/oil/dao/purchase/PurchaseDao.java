package com.oil.dao.purchase;

import java.util.List;
import java.util.Map;

public interface PurchaseDao {

	//供应商获取
	List<Map<String,Object>> getSupplier();
	
	//采购单据申请获取
	List<Map<String,Object>> getPurchaserequisition(Map<String,Object> map);
	
	//物料信息获取
	List<Map<String,Object>> getMaterielInfo(Map<String,Object> map);
	
	//添加采购合同
	int addPurchasecontract(Map<String,Object> map);
	
	//添加采购合同明细
	int addPurchaseorderinfo(Map<String,Object> map);
	
	//添加长传文件
	int adduploadfile(Map<String,Object> map);
	
	//首页查询全部信息
	List<Map<String,Object>> getPurchasecontractList(Map<String,Object> map);
	
	//根据ID查询合同
	Map<String,Object> getPurchasecontractById(Map<String,Object> map);
	
	//根据合同ID查询合同明细
	List<Map<String,Object>> getPurchaseorderinfoById(Map<String,Object> map);
	
	//修改合同
	int updatePurchasecontractById(Map<String,Object> map);
	
	//修改合同明细
	int updatePurchaseorderinfoById(Map<String,Object> map);
	
	//删除采购合同
	int delPurchasecontractById(Map<String,Object> map);
	
	//删除采购合同明细
	int delPurchaseorderinfo(Map<String,Object> map);
	
	//作废合同
	int ZuoFeiPurchasecontract(Map<String,Object> map);
	
	//删除验证
	List<Map<String,Object>> getExAndstor(Map<String,Object> map);
	
	//采购申请标识修改
	int updatePurchaserequisitionIsApplySign(Map<String,Object> map);
	
	//根据合同Id查询采购明细信息
	List<Map<String,Object>> getPurchaserequisitionByPurchasecontractId(Map<String,Object> map);

}
