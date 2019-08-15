package com.oil.service.sales;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.sales.Customerinfo;
import com.oil.model.sales.SalesOrdersDetailedEntity;
import com.oil.model.sales.SalesOrdersInfoEntity;

public interface SalesOrdersService {	
	// 客户信息获取
	Customerinfo getCustomerInfo(Map<String,Object> map);
		
	// 销售订单取得
	List<SalesOrdersInfoEntity> getSalesOrdersInfo(Map<String,Object> map);
	
	// 销售订单明细查询
	List<SalesOrdersDetailedEntity> getSalesOrdersDetailed(Map<String,Object> map);
	
	// 销售订单编号查重
	String checkOrderNumber(String ContractNumber,String id);
	
	// 新增销售订单
	int addSalesOrdersInfo(SalesOrdersInfoEntity salesOrdersInfoEntity,List<SalesOrdersDetailedEntity> SalesOrdersDetailedList);

	// 修改销售订单
	int updateSalesOrdersInfo(SalesOrdersInfoEntity salesOrdersInfoEntity,List<SalesOrdersDetailedEntity> SalesOrdersDetailedList);
	
	// 销售订单作废
	int updateOrderState(SalesOrdersInfoEntity SalesOrdersInfoEntity);
	
	// 销售订单删除
	int deleteSalesOrdersInfo(String Reviser,String Id);
	
	// 销售订单目录tree
    List<ContractTreeEntity> getContractTree(Map<String, Object> map);
    
    DataTablesResponseInfo  getCustomertransportsById(Map<String, Object> map);
    
    ResponseInfo updateIsDelSalesOrdersDetailedByOrderDetailedNumber(Map<String, Object> map);
    
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);
	
}
