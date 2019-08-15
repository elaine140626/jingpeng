package com.oil.service.dispath;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.CustomerOrderEntity;
import com.oil.model.dispath.SalesOrderListEntity;
import com.oil.model.system.CarInfo;

public interface ExportMeasureService {
	// 左侧客户订单信息树
	List<CustomerOrderEntity> getCustomerOrderList(Map<String, Object> map);
	
	// 获取调度单已有车牌号
	List<String> getPlateNumberList(Map<String, Object> map);
	
	// 获取页面list信息
	DataTablesResponseInfo getExportMeasureList(Map<String, Object> map);
	
	// 获取销售订单编号list
	List<SalesOrderListEntity> getSalesOrderList(Map<String, Object> map);
	
	// 空发：查询所有可选出库单号
	List<Map<String, Object>> getAllOutboundList(Map<String, Object> map);
	
	// 获取所有车牌号码
	List<CarInfo> getAllPlateNumbers(Map<String, Object> map);
	
	// 获取所有车牌号码（不包含没有二次称重的车牌号）
	List<CarInfo> getAllPlateNumbersExc(Map<String, Object> map);
	
	// 判断输入车牌号是否没有完成二次称重
	int checkPlateNumber(Map<String, Object> map);
	
	// 出库单作废或者删除
	ResponseInfo updateValidFlag(Map<String, Object> map);	

	// 新增出库单
	ResponseInfo addExportMeasure(Map<String, Object> map);
	
	// 更新出库单信息
	ResponseInfo updateExportMeasure(Map<String, Object> map);
	
	// 调度导出
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);

//	/**
//	 * 关联空发出库单订单
//	 * @param request 
//	 * @return
//	 */
//	int insertEmptyOutBound(HttpServletRequest request, Map<String, Object> map);
//
//	/**
//	 * 修改关联空发出库单订单
//	 * 先删除之前的再新增新的
//	 * @return
//	 */
//	int updateEmptyOutBound(HttpServletRequest request, Map<String, Object> map);

//
//	/**
//	 *   查询所有可选运输单号
//	 * @param map 
//	 * @return
//	 */
//	List<Map<String, Object>> getTransportList(Map<String, Object> map);
//	
	

	/******出库单查询用********/
	// 获取符合条件的调拨销售订单物料Id
	List<Map<String, Object>> getDiaoMaterielId(Map<String, Object> map);
	
	// 获取符合条件的调拨销售订单列表
	List<Map<String, Object>> getDiaoOrderNumber(Map<String, Object> map);
	
	// 获取符合条件的调拨销售订单明细列表
	List<Map<String, Object>> getDiaoOrderDetail(Map<String, Object> map);
	
	// 根据收货地址获取收货人信息
	List<Map<String, Object>> getConsigneeInfo(Map<String, Object> map);
}
