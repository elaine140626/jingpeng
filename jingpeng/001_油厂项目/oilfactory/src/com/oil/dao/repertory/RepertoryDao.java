package com.oil.dao.repertory;

import java.util.List;
import java.util.Map;

import com.oil.model.Datadictionaty;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.repertory.NoWeighOutWarehouse;

public interface RepertoryDao {
	// 根据用户获取对应的销售订单
	List<SalesOrderEntity> getSalesOrderList(Map<String, Object> map);
	
	//app树下拉框销售订单查询
	List<SalesOrderEntity> getAppSalesOrderList();
	
	//app树形结构用户名称下拉框查询
	List<SalesOrderEntity> getAppTreeCustomerName();
	
	//app页面数据
	List<NoWeighOutWarehouse> getAppNoWeighList(Map<String, Object> map);
	
	// 获取页面list信息
	List<NoWeighOutWarehouse> getInfoList(Map<String, Object> map);
	
	// 新增页面获取销售订单编号
	List<OrderNumberEntity> getOrderNumberList(Map<String, Object> map);
	
	// 物料型号
	List<MaterielinfoEntity> getMaterielinfoList(Map<String, Object> map);
	
	// 获取所有车牌号码
	List<Map<String,Object>> getAllPlateNumbers(Map<String, Object> map);
	
	//获取所有的客户名称
	List<Map<String,Object>> getAllCustomerName(Map<String, Object> map);
	
	//获取所有的出库单
	List<Map<String,Object>> getAllOutbounds(Map<String, Object> map);
	
	//添加未称重出库单
	int addNoWeighOutWarehouse(NoWeighOutWarehouse param);
	
	// 更新未称重出库单作删除
	int updateValidFlag(Map<String, Object> map);
	
	// 更新未称重出库单信息
	int updateNoWeighOutWarehouse(NoWeighOutWarehouse param);
	
	//数据字典查询
	List<Datadictionaty> getAllDatadictionaty();
	
	//联动查询
	List<Map<String,Object>> getOutboundEntitys(Map<String,Object> map);
	
	//查询所选的销售订单明细
	List<Map<String, Object>> getOrderDetailInfo(Map<String, Object> map);
	
	//查询车辆信息
	List<Map<String,Object>> getAllCarInfo(Map<String, Object> map);
	
	//查询调拨未称重信息
	List<Map<String,Object>> getDiaoBoNoWeighList(Map<String, Object> map);
	
	//查询可选的车辆信息
	List<Map<String,Object>> getCarNameList(Map<String,Object> map);

	List<Map<String, Object>> getOutBoundInfoList(Map<String, Object> map);

	List<Map<String, Object>> getInstoreInfoList(Map<String, Object> map);

	List<Map<String, Object>> getUnBoundInfoList(Map<String, Object> map);

	int updateExamine(Map<String, Object> map);
	
	//查询未称重中的物料名称
	List<Map<String,Object>> getMaterialNameSearcList(Map<String, Object> map);
}
