package com.oil.dao.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.sales.Customerinfo;
import com.oil.model.sales.SalesOrdersDetailedEntity;
import com.oil.model.sales.SalesOrdersInfoEntity;

public interface SalesOrdersDao {
	// 客户信息获取
	Customerinfo getCustomerInfo(Map<String,Object> map);
	
	List<Map<String,Object>> getCustomerInfoEX(Map<String,Object> map);
	
	// 销售订单取得
	List<SalesOrdersInfoEntity> getSalesOrdersInfo(Map<String,Object> map);

	// 销售订单明细查询
	List<SalesOrdersDetailedEntity> getSalesOrdersDetailed(Map<String,Object> map);
	
	// 销售订单编号查重
	String checkOrderNumber(@Param("OrderNumber") String ContractNumber,@Param("id") String id);
		
	// 销售订单表插入
	int insertSalesOrdersInfo(SalesOrdersInfoEntity salesOrdersInfoEntity);
	
	// 取得刚插入销售订单表的id
	int getSalesOrderId(@Param("Creater") String Creater);
	
	// 销售订单明细表插入
	int insertSalesOrdersDetailed(SalesOrdersDetailedEntity salesOrdersDetailedEntity);
	
	// 销售订单表更新
	int updateSalesOrdersInfo(SalesOrdersInfoEntity salesOrdersInfoEntity);
	
	// 销售订单明细更新删除
	int deleteSalesOrdersDetailed(@Param("Id") String Id);
	
	// 销售订单作废
	int updateOrderState(SalesOrdersInfoEntity SalesOrdersInfoEntity);
	
	// 销售订单删除
	int updateIsDelSalesOrdersInfo(@Param("Reviser") String Reviser,@Param("Id") String Id);
	
	// 销售订单明细删除(删除所有)
	int updateIsDelSalesOrdersDetailed(@Param("Reviser") String Reviser,@Param("Id") String Id);
	// 销售订单目录tree
	List<ContractTreeEntity> getContractTree(Map<String, Object> map);
	
	List<Map<String, Object>>  getCustomertransportsById(Map<String, Object> map);
	
	int updateSalesOrdersDetailed(SalesOrdersDetailedEntity salesOrdersDetailedEntity);
	
	// 销售订单明细删除 (删除指定)
	int updateIsDelSalesOrdersDetailedByOrderDetailedNumber(Map<String, Object> map);
	
	// 删除订单明细验证
	int isDelOrderDetailedNumber(Map<String, Object> map);

	// 销售订单表插入
	int addSalesOrders(HashMap<String, Object> param);

	// 销售订单明细表插入
	int addSalesOrdersDetailed(HashMap<String, Object> param);
}
