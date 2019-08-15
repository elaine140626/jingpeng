package com.oil.service.sales;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.oil.model.ResponseInfo;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.sales.CustomerInfoEntity;
import com.oil.model.sales.NoweighEntity;

public interface OutlistService {
	// 树形信息
	List<CustomerInfoEntity> getCustomerInfo(Map<String, Object> map);
	
	// 销售订单list信息
	List<OutboundEntity> getSalesList(Map<String, Object> map);
	
	// 出库单list信息
	List<OutboundEntity> getExportList(Map<String, Object> map);
	
	// 未称重信息list
	List<NoweighEntity> getNoweighoutList(Map<String, Object> map);
	
	// 来料加工信息list
	List<InstroeEntity> getProcessList(Map<String, Object> map);
	
	// 获取调度单已有车牌号
	List<String> getPlateNumberList(Map<String, Object> map);
	
	// 退货
	ResponseInfo updateRefund(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	// 半车调拨
	ResponseInfo updateHaltCar(HttpServletRequest request, Map<String, Object> map) throws IOException;

	List<OutboundEntity> getInfoList(Map<String, Object> map);

	List<Map<String,Object>> getOutlistExportmeasures();

	List<Map<String,Object>> getOutlistContractinfo(Map<String, Object> map);

	List<Map<String,Object>> getOutlistSalesorder(Map<String, Object> map);

}
