package com.oil.dao.dispath;

import java.util.List;
import java.util.Map;

import com.oil.model.Roadgate;
import com.oil.model.dispath.CustomerOrderEntity;
import com.oil.model.dispath.ExportMeasureEntity;
import com.oil.model.dispath.SalesOrderListEntity;
import com.oil.model.system.CarInfo;

public interface ExportMeasureDao {	
	// 左侧客户订单信息树
	List<CustomerOrderEntity> getCustomerOrderList(Map<String, Object> map);
	
	// 获取调度单已有车牌号
	List<String> getPlateNumberList(Map<String, Object> map);
	
	// 获取页面list信息
	List<ExportMeasureEntity> getExportMeasureList(Map<String, Object> map);
	
	// 新增页面获取销售订单编号
	List<SalesOrderListEntity> getSalesOrderList(Map<String, Object> map);
	
	// 空发：查询所有可选出库单号
	List<Map<String, Object>> getAllOutboundList(Map<String, Object> map);
	
	// 获取所有车牌号码
	List<CarInfo> getAllPlateNumbers(Map<String, Object> map);
	
	// 获取所有车牌号码（不包含没有二次称重的车牌号）
	List<CarInfo> getAllPlateNumbersExc(Map<String, Object> map);
	
	// 判断输入车牌号是否没有完成二次称重
	int checkPlateNumber(Map<String, Object> map);
	
	// 更新出库单作废或者删除
	int updateValidFlag(Map<String, Object> map);
	
	// 删除关联的运输单
	int delTranSportList(Map<String, Object> map);
	
	// 删除关联的未称重出库单
	int deleteNoWeight(Map<String, Object> map);
	
	// 新增出库单
	int addExportMeasure(ExportMeasureEntity exportMeasureEntity);
	
	// 添加运输单
	int addTranSportList(Map<String, Object> map);
	
	// 新增关联空发出库单
	int insertEmptyOutBound(Map<String, Object> map);

	// 更新出库单信息
	int updateExportMeasure(ExportMeasureEntity exportMeasureEntity);
	
	// 更新运输单
	int updateTranSportList(Map<String, Object> map);

	// 删除道闸表
	int delRoadgate(Roadgate roadgate);
	
	// 调度导出
	List<Map<String, Object>> getInfoListEX(Map<String, Object> map);
	
//	List<Map<String, Object>> getTransportList(Map<String, Object> map);

//	OutboundEntity queryOutBoundInfoById(String string);

//	int queryNoWeight(Map<String, Object> map);
//
//	List<Map<String, Object>> getDiaoMaterielId(Map<String, Object> map);


//	
//	int updateTranSportListOutType(Map<String, Object> map);

	
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