package com.oil.service.purchase;

import java.util.Map;


import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;

public interface PurchaseService {

	DataTablesResponseInfo getSupplier();
	//采购单据申请获取
	DataTablesResponseInfo getPurchaserequisition(Map<String,Object> map);
	
	//物料信息获取
	DataTablesResponseInfo getMaterielInfo(Map<String,Object> map);
	
	//添加采购合同
	ResponseInfo addPurchasecontract(Map<String,Object> map);
	
	//添加采购合同明细
	ResponseInfo addPurchaseorderinfo(Map<String,Object> map);
	
	//首页查询集合
	DataTablesResponseInfo getPurchasecontractList(Map<String,Object> map);
	
	//查询合同信息
	DataTablesResponseInfo getPurchasecontractById(Map<String,Object> map);
	
	//修改合同明细
	ResponseInfo updatePurchaseorderinfoById(Map<String,Object> map);
	
	//删除采购合同
	ResponseInfo delPurchasecontractById(Map<String,Object> map);
	
	//删除采购明细
	ResponseInfo delPurchaseorderinfo(Map<String,Object> map);
	
	//作废合同
	ResponseInfo ZuoFeiPurchasecontract(Map<String,Object> map);
	
	//删除验证
	ResponseInfo getExAndstor(Map<String,Object> map);
	
	ResponseInfo updatePurchaserequisitionIsApplySign(Map<String,Object> map);
}
