package com.oil.service.price;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Purchasecontract;

public interface PriceService {
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
	List<Map<String, Object>> getMaterielinfoList(Map<String, Object> map);
	
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
	ResponseInfo insertEmptyOutBound(HttpServletRequest request, Map<String, Object> map);

	/**
	 * 修改关联空发出库单订单
	 * 先删除之前的再新增新的
	 * @return
	 */
	ResponseInfo updateEmptyOutBound(HttpServletRequest request, Map<String, Object> map);
	
	/**
	 * 先删除出库单封签号
	 * @return
	 */
	int updateFacingSlipNum(HttpServletRequest request, Map<String, Object> map);
	/**
	 * 修改价格查询
	 * @return
	 */
	List<Map<String, Object>> getEmptyPriceInfo(Map<String, Object> map);
	
	/**
	 * 修改价格提交
	 * @return
	 */
	ResponseInfo updateEmptyPriceInfo(HttpServletRequest request, Map<String, Object> map);
	
	//客户止运地
	List<Map<String, Object>> getCustomerTrans(Map<String, Object> map);
	
	//添加财务报价记录
	ResponseInfo addOfferrecord (HttpServletRequest request, Map<String, Object> map);
	
	//查询所有的财务报价记录
	DataTablesResponseInfo getAllOfferrecord(Map<String,Object> map);
	List<SalesOrderEntity> getOfferrecordTree(Map<String, Object> map);
}
