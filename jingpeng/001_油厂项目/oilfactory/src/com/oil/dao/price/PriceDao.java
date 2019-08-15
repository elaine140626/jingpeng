package com.oil.dao.price;

import java.util.List;
import java.util.Map;

import com.oil.model.ResponseInfo;
import com.oil.model.dispath.ContractEntity;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Purchasecontract;

public interface PriceDao {
	
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
	List<Map<String, Object>> getMaterielinfoList(Map<String, Object> map);
	
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
	List<Map<String, Object>> getEmptyPriceInfo(Map<String, Object> map);
	int insertEmptyOutBound(Map<String, Object> map);
	List<Map<String, Object>> queryEmptyOutBoundById(int id);
	int deleteEmptyOutBound(Map<String, Object> newMap);
	// 修改价格提交
	int updateRKPriceInfo(Map<String, Object> map);
	// 修改价格提交
	int updateCKPriceInfo(Map<String, Object> map);
	// 修改价格提交
	int updateWCZPriceInfo(Map<String, Object> map);
	//客户止运地
	List<Map<String, Object>> getCustomerTrans(Map<String, Object> map);
	
	//添加财务报价记录
	int addOfferrecord(Map<String, Object> map);
	//查询所有的财务报价记录
	List<Map<String,Object>> getAllOfferrecord(Map<String,Object> map);
	List<SalesOrderEntity> getOfferrecordTree(Map<String, Object> map);
}
