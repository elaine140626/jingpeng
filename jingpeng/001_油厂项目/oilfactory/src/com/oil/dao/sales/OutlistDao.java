package com.oil.dao.sales;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.model.sales.CustomerInfoEntity;
import com.oil.model.sales.NoweighEntity;

public interface OutlistDao {
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
	
	// 新增未称重出库单
	int addNoWeighOut(NoWeighOutWarehouse data);

	int updateFacingSlipNum(Map<String, Object> map);

	List<OutboundEntity> getInfoList(Map<String, Object> map);

	List<Map<String,Object>> getOutlistExportmeasures();

	List<Map<String,Object>> getOutlistContractinfo(Map<String, Object> map);

	List<Map<String,Object>> getOutlistSalesorder(Map<String, Object> map);

	List<String> getPlateNumberList(Map<String, Object> map);
}
