package com.oil.service.dispath;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.ContractEntity;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.SalesContractEntity;

public interface InstroeService {
	// 根据用户获取对应的销售合同编号
	List<SalesContractEntity> getSalesContractList(Map<String, Object> map);
		
	// 获取调度单已有车牌号
	List<String> getPlateNumberList(Map<String, Object> map);
	
	// 获取页面显示list
	DataTablesResponseInfo getInfoList(Map<String, Object> map);
	
	// 根据id获取出库单信息
	InstroeEntity getInstroeInfo(Map<String, Object> map);
	
	// 入库单作废或者删除
	ResponseInfo updateValidFlag(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	// 新增入库单
	ResponseInfo addImportMeasure(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	// 更新入库单信息
	ResponseInfo updateImportMeasure(HttpServletRequest request, Map<String, Object> map) throws IOException;
	
	// 销售合同编号(来料加工)
	List<ContractEntity> getContractList(Map<String, Object> map);

	/**
	 * 获取无法选中的车辆信息
	 * @return
	 */
	List<Map<String, Object>> queryCarInUse();

	/**
	 * 根据出库单流水号查询运输单流水号
	 * @return
	 */
	List<Map<String, Object>> queryBillNumber(Map<String, Object> map);

	List<Map<String, Object>> getStoragemeasure();

	List<Map<String, Object>> getSalesContractLists(Map<String, Object> map);

	List<Map<String, Object>> getInBoundWeight(Map<String, Object> map);

	ResponseInfo updateInBoundWeight(Map<String, Object> map);

	int updateExamine(Map<String, Object> map);
}
