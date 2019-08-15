package com.oil.service.sales.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.dispath.InstroeDao;
import com.oil.dao.dispath.OutboundDao;
import com.oil.dao.sales.OutlistDao;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.model.sales.CustomerInfoEntity;
import com.oil.model.sales.NoweighEntity;
import com.oil.service.sales.OutlistService;
import com.oil.util.PropertyUtil;

@Service
public class OutlistServiceImpl implements OutlistService{

	@Autowired
	OutlistDao outlistDao;
	
	@Autowired
	OutboundDao outboundDao;
	
	@Autowired
	InstroeDao instroeDao;
	
	// 树形显示信息
	@Override
	public List<CustomerInfoEntity> getCustomerInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return outlistDao.getCustomerInfo(map);
	}

	// 销售订单信息
	@Override
	public List<OutboundEntity> getSalesList(Map<String, Object> map) {
		return outlistDao.getSalesList(map);
	}
	
	// 出库单list信息
	@Override
	public List<OutboundEntity> getExportList(Map<String, Object> map) {
		return outlistDao.getExportList(map);
	}

	// 未称重信息
	@Override
	public List<NoweighEntity> getNoweighoutList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return outlistDao.getNoweighoutList(map);
	}

	// 来料加工
	@Override
	public List<InstroeEntity> getProcessList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return outlistDao.getProcessList(map);
	}

	// 退货操作
	@Override
	public ResponseInfo updateRefund(HttpServletRequest request, Map<String, Object> map) throws IOException {
		// 更新出库单的"退货"状态
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		Map<String, Object> params = new HashMap<String, Object>();
		if (userInfo != null) {
			params.put("userId", userInfo.getId());
		}else {
			params.put("userId",map.get("userId").toString());
		}
		params.put("id",Integer.parseInt( map.get("id").toString()));
		params.put("flag", 2);
		params.put("returnWeight", Double.valueOf( map.get("refundAmount").toString()));
		
		int result = outboundDao.updateValidFlag(params);
		if(result > 0) {
			// 新增入库单
			result = this.addImportMeasure(map, userInfo);
			if (result > 0) {
				info.setCode("success");
				// 用户或密码错误!
				info.setMessage(PropertyUtil.getProperties("M0005"));
			} else {
				info.setCode("error");
				// 用户或密码错误!
				info.setMessage(PropertyUtil.getProperties("M0006"));
			}
		}else {
			info.setCode("error");
			// 用户或密码错误!
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}

	//新增入库单
	public int addImportMeasure(Map<String, Object> map, Userinfo userInfo){
		// 入库单信息
		InstroeEntity data = new InstroeEntity();
		// 流水号
		data.setSerialID(map.get("serialID").toString());
		// 销售合同  
		data.setContractId(Integer.parseInt(map.get("contractId").toString()));
		// 销售合同明细Id 
		data.setOrderDetailedId(Integer.parseInt(map.get("orderDetailedId").toString()));
		 // 数量    
//		data.setAmount(Double.valueOf(map.get("refundAmount").toString()));
		//客户名称
		data.setClient(map.get("client").toString());
		//客户别称
		data.setCustomerAlias(map.get("customerAlias").toString());
		 // 优先级   
		data.setPriority(0);
		 // 物料id 
		data.setProductID(Integer.parseInt(map.get("productID").toString()));
		 // 车牌号码  
		data.setPlateNumber(map.get("plateNumber").toString());
		 // 送货人   
		data.setDeliveryMan(map.get("deliveryMan").toString());
		 // 送货人电话 
		data.setDeliveryManPhone(map.get("deliveryManPhone").toString());
		 // 车主    
//		data.setCarOwner(map.get("carOwner").toString());
		 // 车主电话  
//		data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		 // 备注    
		data.setRemarks(map.get("remarks").toString());
		 // 入库单标识 
		data.setEnterTypeMark(3);
		 // 是否大车称重 
//		data.setCartWeighType(map.get("cartWeighType").toString());
		 // 有效标识  
		data.setValidFlag(1);
		// 称重类型
	    data.setCartWeighType(map.get("cartWeighType").toString().equals("null")?"":map.get("cartWeighType").toString());
		 // 出库单id
		data.setOutWarehouseId(Integer.parseInt(map.get("id").toString()));
		 // 创建人 
		if (userInfo != null) {
			data.setCreater(String.valueOf(userInfo.getId()));	
		}else {
			data.setCreater(map.get("userId").toString());
		}
		
		int result = instroeDao.addImportMeasure(data);
		return result;
	}

	// 半车调拨
	@Override
	public ResponseInfo updateHaltCar(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		
		// 更新出库单的状态
		Map<String, Object> params = new HashMap<String, Object>();
		if (userInfo != null) {
			params.put("userId", userInfo.getId());
		}else {
			params.put("userId", map.get("userId"));
		}
		params.put("id",Integer.parseInt( map.get("id").toString()));
		params.put("flag", 3);
		params.put("outType", map.get("outType").toString());
//		params.put("customerAlias", map.get("customerAlias").toString());
//		params.put("consignee", map.get("consignee").toString());
//		params.put("consigneePhone", map.get("consigneePhone").toString());
//		params.put("consigneeAddress", map.get("consigneeAddress").toString());
//		params.put("amount", map.get("amount").toString().replaceAll(" ", ""));		
		int result = 0;
		result += outboundDao.updateValidFlag(params);
		if(result > 0) {
			Map<String,Object> tranSportMap = new HashMap<>();
			tranSportMap.put("dispatchOutWarehouseId", params.get("id"));
			tranSportMap.put("type", 99);
			tranSportMap.put("userName", params.get("userId"));
			result = outboundDao.updateTranSportListOutType(tranSportMap);
		}
		// 整车调拨 添加一张未称重出库单
		result += addNoWeighOut(map, userInfo);
		// 半车调拨 添加第二张未称重出库单
		if (Double.valueOf(map.get("allotWeight").toString())<Double.valueOf(map.get("suttle").toString())) {
			result += addNoWeighOutTwo(map, userInfo);
		}
		if (result > 0) {
			info.setCode("success");
			// 用户或密码错误!
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			info.setCode("error");
			// 用户或密码错误!
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
	

	// 整车调拨 添加一张未称重出库单
	public int addNoWeighOut(Map<String, Object> map, Userinfo userInfo) {
		NoWeighOutWarehouse data = new NoWeighOutWarehouse();
		// 流水号 
		data.setSerialID(map.get("serialID").toString());
		// 销售订单id 
		data.setSalesOrderId(Integer.parseInt(map.get("salesOrderId").toString()));
		// 销售订单详细id 
		data.setOrderDetailedNumber(Integer.parseInt(map.get("orderDetailedId").toString()));
		
		if(map.get("allotWeight") != null && !("").equals(map.get("allotWeight").toString())) {
			// 调拨重量
			data.setAllotWeight(Double.valueOf(map.get("allotWeight").toString()));
		}
		
		if(!("null").equals(map.get("saleNumber").toString()) && !("").equals(map.get("saleNumber").toString())) {
			// 销售数量
			data.setSaleNumber(Double.valueOf(map.get("saleNumber").toString()));
		}
		
		if(!("null").equals(map.get("salePrice").toString()) && !("").equals(map.get("salePrice").toString())) {
			// 销售单价
			data.setSalePrice(Double.valueOf(map.get("salePrice").toString()));
		}
		
		if(!("null").equals(map.get("saleMoney").toString()) && !("").equals(map.get("saleMoney").toString())) {
			// 销售金额 
			data.setSaleMoney(Double.valueOf(map.get("saleMoney").toString()));
		}
		
		// 客户名称
		data.setClient(map.get("allotClient").toString());
		// 客户别称 
		data.setCustomerAlias(map.get("allotCustomerAlias").toString());
		
		if(!("null").equals(map.get("taxRate").toString()) && !("").equals(map.get("taxRate").toString()) ) {
			// 税率 
			data.setTaxRate(map.get("taxRate").toString());
		}
		data.setType("1");
		// 送货地址 
		data.setAddress(map.get("address").toString());
		// 收货人  
		data.setConsignee(map.get("consignee").toString());
		// 收货人电话 
		data.setConsigneePhone(map.get("consigneePhone").toString());
		// 收货人电话 
		data.setConsigneeAddress(map.get("consigneeAddress").toString());
		// 物料id
		data.setMaterielInfoId(Integer.parseInt(map.get("materielInfoId").toString()));
		// 运输结算情况（数据字典） 0:我方承担运费 1:客户自提 2:我方垫付运费 3:其他  
		data.setTransportBalance(map.get("transportBalance").toString());
		// 车牌号码       
		data.setCarName(map.get("plateNumber").toString());
		// 送货人   
		data.setDeliverer(map.get("deliveryMan").toString());
		// 送货人电话         
		data.setDeliveryPhone(map.get("deliveryManPhone").toString());
		// 车主      
//		data.setCarOwner(map.get("carOwner").toString());
		// 车主电话             
//		data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		// 备注         
		data.setRemarks(map.get("remarks").toString());
		// 是否检测       
		//data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		// 出库单id        
		data.setDispatchOutWarehouseId(Integer.parseInt(map.get("id").toString()));
		// 有效标识 
		data.setValidFlag(1);                                       
		// 创建人    
		if (userInfo != null) {
			data.setCreater(String.valueOf(userInfo.getId()));
		}else {
			data.setCreater(map.get("userId").toString());
		}
		int result = outlistDao.addNoWeighOut(data);
		if (result > 0) {
			//自动生成运输单
			Map<String,Object> tranSportMap = new HashMap<>();
			Map<String,Object> materielinfoMap = new HashMap<>();
			tranSportMap.put("dispatchOutWarehouseId", data.getId());
			tranSportMap.put("serialID", data.getSerialID());
			tranSportMap.put("billNumber",map.get("YSserialID").toString());
			tranSportMap.put("isMention", map.get("transportBalance"));
			tranSportMap.put("plateNumber", data.getCarName());
			tranSportMap.put("endAddress", data.getConsigneeAddress());
			tranSportMap.put("carOwner",data.getDeliverer());
			tranSportMap.put("carOwnerTelephone", data.getDeliveryPhone());
			tranSportMap.put("type", data.getType());
			Map<String,Object> mapSad = new HashMap<>();
			mapSad.put("id", data.getSalesOrderId());
			List<OrderNumberEntity> sales = outboundDao.getOrderNumberListAsUserId(mapSad);
			if(sales.size()>0) {
				tranSportMap.put("creater", sales.get(0).getUserInfoId());
			}
			/*tranSportMap.put("creater", data.getCreater());*/
			tranSportMap.put("weigh", data.getAllotWeight());
			materielinfoMap.put("productID", data.getMaterielInfoId());
			List<MaterielinfoEntity> materielinfoEntity =  outboundDao.getMaterielinfoList(materielinfoMap);
			if(materielinfoEntity.size()>0) {
				tranSportMap.put("materielId", materielinfoEntity.get(0).getMaterielName());
				tranSportMap.put("model", materielinfoEntity.get(0).getMaterielModel());
			}
			result = outboundDao.addTranSportList(tranSportMap);
		}
		return result;
	}
	
	// 半车调拨 添加第二张未称重出库单
	public int addNoWeighOutTwo(Map<String, Object> map, Userinfo userInfo) {
		NoWeighOutWarehouse data = new NoWeighOutWarehouse();
		// 流水号 
		data.setSerialID(map.get("serialID1").toString());
		// 销售订单id 
		data.setSalesOrderId(Integer.parseInt(map.get("salesOrderId1").toString()));
		// 销售订单详细id 
		data.setOrderDetailedNumber(Integer.parseInt(map.get("orderDetailedId1").toString()));
		
		if(map.get("allotWeight1") != null && !("").equals(map.get("allotWeight1").toString())) {
			// 调拨重量
			data.setAllotWeight(Double.valueOf(map.get("allotWeight1").toString()));
		}
		
		if(!("null").equals(map.get("saleNumber1").toString()) && !("").equals(map.get("saleNumber1").toString())) {
			// 销售数量
			data.setSaleNumber(Double.valueOf(map.get("saleNumber1").toString()));
		}
		
		if(!("null").equals(map.get("salePrice1").toString()) && !("").equals(map.get("salePrice1").toString())) {
			// 销售单价
			data.setSalePrice(Double.valueOf(map.get("salePrice1").toString()));
		}
		
		if(!("null").equals(map.get("saleMoney1").toString()) && !("").equals(map.get("saleMoney1").toString())) {
			// 销售金额 
			data.setSaleMoney(Double.valueOf(map.get("saleMoney1").toString()));
		}
		
		// 客户名称
		data.setClient(map.get("allotClient1").toString());
		// 客户别称 
		data.setCustomerAlias(map.get("allotCustomerAlias1").toString());
		
		if(!("null").equals(map.get("taxRate1").toString()) && !("").equals(map.get("taxRate1").toString()) ) {
			// 税率 
			data.setTaxRate(map.get("taxRate1").toString());
		}
		data.setType("1");
		// 送货地址 
		data.setAddress(map.get("address1").toString());
		// 收货人  
		data.setConsignee(map.get("consignee1").toString());
		// 收货人电话 
		data.setConsigneePhone(map.get("consigneePhone1").toString());
		// 收货人电话 
		data.setConsigneeAddress(map.get("consigneeAddress1").toString());
		// 物料id
		data.setMaterielInfoId(Integer.parseInt(map.get("materielInfoId").toString()));
		// 运输结算情况（数据字典） 0:我方承担运费 1:客户自提 2:我方垫付运费 3:其他  
		data.setTransportBalance(map.get("transportBalance1").toString());
		// 车牌号码       
		data.setCarName(map.get("plateNumber").toString());
		// 送货人   
		data.setDeliverer(map.get("deliveryMan").toString());
		// 送货人电话         
		data.setDeliveryPhone(map.get("deliveryManPhone").toString());
		// 车主      
//			data.setCarOwner(map.get("carOwner").toString());
		// 车主电话             
//			data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		// 备注         
		data.setRemarks(map.get("remarks").toString());
		// 是否检测       
		//data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		// 出库单id        
		data.setDispatchOutWarehouseId(Integer.parseInt(map.get("id").toString()));
		// 有效标识 
		data.setValidFlag(1);                                       
		// 创建人    
		if (userInfo != null) {
			data.setCreater(String.valueOf(userInfo.getId()));
		}else {
			data.setCreater(map.get("userId").toString());
		}
		int result = outlistDao.addNoWeighOut(data);
		if (result > 0) {
			//自动生成运输单
			Map<String,Object> tranSportMap = new HashMap<>();
			Map<String,Object> materielinfoMap = new HashMap<>();
			tranSportMap.put("dispatchOutWarehouseId", data.getId());
			tranSportMap.put("serialID", data.getSerialID());
			tranSportMap.put("billNumber",map.get("YSserialID1").toString());
			tranSportMap.put("isMention", map.get("transportBalance"));
			tranSportMap.put("plateNumber", data.getCarName());
			tranSportMap.put("endAddress", data.getConsigneeAddress());
			tranSportMap.put("carOwner",data.getDeliverer());
			tranSportMap.put("carOwnerTelephone", data.getDeliveryPhone());
			tranSportMap.put("type", data.getType());
			Map<String,Object> mapSad = new HashMap<>();
			mapSad.put("id", data.getSalesOrderId());
			List<OrderNumberEntity> sales = outboundDao.getOrderNumberListAsUserId(mapSad);
			if(sales.size()>0) {
				tranSportMap.put("creater", sales.get(0).getUserInfoId());
			}
		/*	tranSportMap.put("creater", data.getCreater());*/
			tranSportMap.put("weigh", data.getAllotWeight());
			materielinfoMap.put("productID", data.getMaterielInfoId());
			List<MaterielinfoEntity> materielinfoEntity =  outboundDao.getMaterielinfoList(materielinfoMap);
			if(materielinfoEntity.size()>0) {
				tranSportMap.put("materielId", materielinfoEntity.get(0).getMaterielNameId());
				tranSportMap.put("model", materielinfoEntity.get(0).getMaterielModelId());
			}
			result = outboundDao.addTranSportList(tranSportMap);
		}
		
		return result;
	}

	@Override
	public List<OutboundEntity> getInfoList(Map<String, Object> map) {
		
		return outlistDao.getInfoList(map);
	}

	@Override
	public List<Map<String,Object>> getOutlistExportmeasures() {
		return outlistDao.getOutlistExportmeasures();
	}

	@Override
	public List<Map<String,Object>> getOutlistContractinfo(Map<String, Object> map) {
		return outlistDao.getOutlistContractinfo(map);
	}

	@Override
	public List<Map<String,Object>> getOutlistSalesorder(Map<String, Object> map) {
		return outlistDao.getOutlistSalesorder(map);
	}

	@Override
	public List<String> getPlateNumberList(Map<String, Object> map) {
		List<String> dataList = outlistDao.getPlateNumberList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<String>();
		}
		return dataList;
	}
}