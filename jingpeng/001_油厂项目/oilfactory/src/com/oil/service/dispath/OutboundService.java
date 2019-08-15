package com.oil.service.dispath;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Purchasecontract;

public interface OutboundService {
	// 根据用户获取对应的销售订单
	List<SalesOrderEntity> getSalesOrderList(Map<String, Object> map);
	
	// 获取调度单已有车牌号
	List<String> getPlateNumberList(Map<String, Object> map);
	
	// 获取页面list信息
	DataTablesResponseInfo getInfoList(Map<String, Object> map);
	
	// 出库单作废或者删除
	ResponseInfo updateValidFlag(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	// 根据id获取出库单信息
	OutboundEntity getOutboundInfo(Map<String, Object> map);
	
	// 获取销售订单编号list
	List<OrderNumberEntity> getOrderNumberList(Map<String, Object> map);
	
	// 获取物料型号list
	List<MaterielinfoEntity> getMaterielinfoList(Map<String, Object> map);
	
	// 获取所有车牌号码
	List<CarInfo> getAllPlateNumbers(Map<String, Object> map);
	
	// 新增出库单
	ResponseInfo addExportMeasure(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	// 更新出库单信息
	ResponseInfo updateExportMeasure(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	// 采购合同
    List<Purchasecontract> getPurchasecontractList(Map<String, Object> map);

	/**
	 * 获取无法选中的车辆信息
	 * @return
	 */
	List<Map<String, Object>> queryCarInUse();

	/**
	 * 销售订单详细信息
	 * @param map 
	 * @return
	 */
	List<Map<String, Object>> getOrderDetail(Map<String, Object> map);
	
	/**
	 * 空发出库单订单
	 * @return
	 */
	List<Map<String, Object>> getEmptyOutboundInfo(Map<String, Object> map);

	/**
	 * 关联空发出库单订单
	 * @param request 
	 * @return
	 */
	int insertEmptyOutBound(HttpServletRequest request, Map<String, Object> map);

	/**
	 * 修改关联空发出库单订单
	 * 先删除之前的再新增新的
	 * @return
	 */
	int updateEmptyOutBound(HttpServletRequest request, Map<String, Object> map);
	
	/**
	 * 先删除出库单封签号
	 * @return
	 */
	int updateFacingSlipNum(HttpServletRequest request, Map<String, Object> map);

	/**
	 *   查询所有可选出库单号
	 * @return
	 */
	List<Map<String, Object>> getAllOutboundList();

	/**
	 *   查询所有可选运输单号
	 * @param map 
	 * @return
	 */
	List<Map<String, Object>> getTransportList(Map<String, Object> map);

	/**
	 *   查询所有可选销售订单明细
	 * @return
	 */
	List<Map<String, Object>> getOrderDetailInfo(Map<String, Object> map);

	/**
	 *   查询该用户运距，止运地
	 * @return
	 */
	List<Map<String, Object>> getCustomerTrans(Map<String, Object> map);


	List<Map<String, Object>> getExportmeasures();

	List<Map<String, Object>> getSalesOrderLists(Map<String, Object> map);

	List<Map<String, Object>> checkTransList(Map<String, Object> map);

	List<Map<String, Object>> getDiaoOrderNumber(Map<String, Object> map);

	List<Map<String, Object>> getDiaoMaterielId(Map<String, Object> map);

	List<Map<String, Object>> getDiaoOrderDetail(Map<String, Object> map);

	List<Map<String, Object>> getConsigneeInfo(Map<String, Object> map);

	/**
	 * 获取当前出库单相关重量
	 * @return
	 */
	List<Map<String, Object>> getOutBoundWeight(Map<String, Object> map);

	/**
	 * 修改当前出库单相关重量
	 * @return
	 */
	ResponseInfo updateOutBoundWeight(Map<String, Object> map);

	int updateExamine(Map<String, Object> map);
	
	void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map);

}
