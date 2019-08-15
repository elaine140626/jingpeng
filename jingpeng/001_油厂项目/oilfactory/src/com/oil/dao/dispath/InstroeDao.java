package com.oil.dao.dispath;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.ContractEntity;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.SalesContractEntity;

public interface InstroeDao {
	// 根据用户获取对应的销售订单
	List<SalesContractEntity> getSalesContractList(Map<String, Object> map);
	
	// 获取调度单已有车牌号
	List<String> getPlateNumberList(Map<String, Object> map);
	
	// 获取页面list信息
	List<InstroeEntity> getInfoList(Map<String, Object> map);
	
	// 更新出库单作废或者删除
	int updateValidFlag(Map<String, Object> map);
	
	// 新增入库单
	int addImportMeasure(InstroeEntity param);
	
	// 更新入库单信息
	int updateImportMeasure(InstroeEntity param);
	
	// 销售合同编号(来料加工)
	List<ContractEntity> getContractList(Map<String, Object> map);

	List<Map<String, Object>> queryCarInUse();

	List<Map<String, Object>> queryBillNumber(Map<String, Object> map);

	List<Map<String, Object>> getStoragemeasure();

	List<Map<String, Object>> getSalesContractLists(Map<String, Object> map);

	List<Map<String, Object>> getInBoundWeight(Map<String, Object> map);

	int updateInBoundWeight(Map<String, Object> map);

	int updateExamine(Map<String, Object> map);
}
