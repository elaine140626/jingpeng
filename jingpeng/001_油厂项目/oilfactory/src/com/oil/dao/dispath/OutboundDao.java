package com.oil.dao.dispath;

import java.util.List;
import java.util.Map;

import com.oil.model.dispath.ContractEntity;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Purchasecontract;

public interface OutboundDao {	
	// 根据用户获取对应的销售订单
	List<SalesOrderEntity> getSalesOrderList(Map<String, Object> map);
	
	// 获取调度单已有车牌号
	List<String> getPlateNumberList(Map<String, Object> map);
	
	// 获取页面list信息
	List<OutboundEntity> getInfoList(Map<String, Object> map);
	
	// 更新出库单作废或者删除
	int updateValidFlag(Map<String, Object> map);
	
	// 新增页面获取销售订单编号
	List<OrderNumberEntity> getOrderNumberList(Map<String, Object> map);
	
	// 物料型号
	List<MaterielinfoEntity> getMaterielinfoList(Map<String, Object> map);
	
	// 合同信息
	ContractEntity getContractinfo(Map<String, Object> map);
	
	// 新增出库单
	int addExportMeasure(OutboundEntity param);
	
	// 更新出库单信息
	int updateExportMeasure(OutboundEntity param);
	
	// 获取所有车牌号码
	List<CarInfo> getAllPlateNumbers(Map<String, Object> map);
	
	// 采购合同
	List<Purchasecontract> getPurchasecontractList(Map<String, Object> map);

	List<Map<String, Object>> queryCarInUse();

	List<Map<String, Object>> getOrderDetail(Map<String, Object> map);

	List<Map<String, Object>> getEmptyOutboundInfo(Map<String, Object> map);

	int insertEmptyOutBound(Map<String, Object> map);

	List<Map<String, Object>> queryEmptyOutBoundById(String string);

	int deleteEmptyOutBound(Map<String, Object> newMap);

	List<Map<String, Object>> getAllOutboundList();

	List<Map<String, Object>> getTransportList(Map<String, Object> map);

	List<Map<String, Object>> queryEmptyList(Map<String, Object> map);

	OutboundEntity queryOutBoundInfoById(String string);

	OutboundEntity queryOutBoundInfoBySerialId(String string);

	int queryNoWeight(Map<String, Object> map);

	List<Map<String, Object>> getOrderDetailInfo(Map<String, Object> map);

	List<Map<String, Object>> getCustomerTrans(Map<String, Object> map);

	List<Map<String, Object>> getExportmeasures();

	List<Map<String, Object>> getSalesOrderLists(Map<String, Object> map);

	List<Map<String, Object>> checkTransList(Map<String, Object> map);

	int deleteStoragemeasure(Map<String, Object> param);

	int deleteNoWeight(Map<String, Object> param);

	int deleteEmptyListN(String serialId);
	
	int deleteEmptyListY(String serialId);

	List<Map<String, Object>> getDiaoOrderNumber(Map<String, Object> map);

	List<Map<String, Object>> getDiaoMaterielId(Map<String, Object> map);

	List<Map<String, Object>> getDiaoOrderDetail(Map<String, Object> map);

	List<Map<String, Object>> getConsigneeInfo(Map<String, Object> map);

	List<Map<String, Object>> getOutBoundWeight(Map<String, Object> map);

	int updateOutBoundWeight(Map<String, Object> map);

	String getFactoryTime(String Id);

	int updateExamine(Map<String, Object> map);
	
	int addTranSportList(Map<String, Object> map);
	
	int delTranSportList(Map<String, Object> map);
	
	int updateTranSportList(Map<String, Object> map);
	
	int updateTranSportListOutType(Map<String, Object> map);
	
	List<Map<String, Object>> getInfoListEX(Map<String, Object> map);
	
	List<OrderNumberEntity> getOrderNumberListAsUserId(Map<String, Object> map);
	
}