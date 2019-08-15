package com.oil.service.price.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oil.dao.dispath.InstroeDao;
import com.oil.dao.price.PriceDao;
import com.oil.dao.sales.OutlistDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Purchasecontract;
import com.oil.service.price.PriceService;
import com.oil.util.MessageUtil;
import com.oil.util.PropertyUtil;

@Service
public class PriceServiceImpl implements PriceService {
	@Autowired
	PriceDao priceDao;
	
	@Autowired
	OutlistDao outlistDao;
	
	@Autowired
	InstroeDao instroeDao;

	// 根据用户获取对应的销售订单
	@Override
	public List<SalesOrderEntity> getSalesOrderList(Map<String, Object> map) {
		List<SalesOrderEntity> dataList = priceDao.getSalesOrderList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<SalesOrderEntity>();
		}
		return dataList;
	}

	// 获取调度单已有车牌号
	@Override
	public List<String> getPlateNumberList(Map<String, Object> map) {
		List<String> dataList = priceDao.getPlateNumberList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<String>();
		}
		return dataList;
	}

	// 获取页面list信息
	@Override
	public  DataTablesResponseInfo getInfoList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		if (map.get("OutType")!=null) {
			String ContractState = map.get("OutType").toString();
			String[] param = {};
			if(ContractState != null && !("").equals(ContractState)) {
				param = ContractState.split(",");
				map.put("OutType", param);
			}
		}
 		List<OutboundEntity> dataList = priceDao.getInfoList(map);
		if(map.get("OutType") != null &&  map.get("OutType") != "") {
			List<OutboundEntity> list = new ArrayList<OutboundEntity>();
		String[] ad = (String[]) map.get("OutType");
		for (int i = 0; i < dataList.size(); i++) {
			for (int m = 0; m < ad.length; m++) {
					if  (dataList.get(i).getOutType() == Integer.parseInt(ad[m]))     
					{      
						//不存在
						list.add(dataList.get(i)); //把要移除的统一放在一个集合	
					}
				}
			}
		info.setData(list);
		return info;
		}
		info.setData(dataList);
		return info;
			
	}

	// 出库单作废或者删除
	@Override
	public ResponseInfo updateValidFlag(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		map.put("userId", userInfo.getId());
		int result = 0;
		List<OutboundEntity> dataList = priceDao.getInfoList(map);
		if (dataList != null && dataList.size() > 0) {
			OutboundEntity outboundEntity = dataList.get(0);
			
			// 未出厂出库单
			if(outboundEntity.getOutTypeMark() == 1) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("outWarehouseId", outboundEntity.getId());
				param.put("flag", map.get("flag"));
				param.put("userId", userInfo.getId());
				instroeDao.updateValidFlag(param);
				result = priceDao.updateValidFlag(map);
			}else {
				result = priceDao.updateValidFlag(map);
			}
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

	// 根据出库单id获取信息
	@Override
	public OutboundEntity getOutboundInfo(Map<String, Object> map) {
		// 获取信息
		List<OutboundEntity> dataList = priceDao.getInfoList(map);
		if (dataList != null && dataList.size() > 0) {
			OutboundEntity outboundEntity = dataList.get(0);
			return outboundEntity;
		} else {
			return null;
		}
	}

	// 新增页面获取销售订单编号
	@Override
	public List<OrderNumberEntity> getOrderNumberList(Map<String, Object> map) {
		List<OrderNumberEntity> dataList = priceDao.getOrderNumberList(map);
		return dataList;
	}

	// 物料型号
	@Override
	public List<Map<String, Object>> getMaterielinfoList(Map<String, Object> map) {
		List<Map<String, Object>> dataList = priceDao.getMaterielinfoList(map);
		return dataList;
	}

	// 获取所有车牌号码
	@Override
	public List<CarInfo> getAllPlateNumbers(Map<String, Object> map) {
		return priceDao.getAllPlateNumbers(map);
	}

	// 新增出库单
	@Override
	public ResponseInfo addExportMeasure(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");

		// 新增出库单信息
		OutboundEntity data = new OutboundEntity();
		
		// 流水号
		data.setSerialId(map.get("serialID").toString());
		
		// 销售订单id
		data.setSalesOrderId(Integer.parseInt(map.get("orderNumber").toString()));
		// 根据销售订单id获取结算数量
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", Integer.parseInt(map.get("orderNumber").toString()));
		List<OrderNumberEntity> dataList = priceDao.getOrderNumberList(param);
		if (dataList != null && Integer.valueOf(map.get("outType").toString()) != 3) {
			// 结算数量
			data.setAmount(dataList.get(0).getSaleNumber());

		}
		// 车牌号码
		data.setPlateNumber(map.get("plateNumber").toString());
		// 客户名称
		data.setClient(map.get("client").toString());
		// 客户别称
		data.setCustomerAlias(map.get("customerAlias").toString());
		// 送货地址
		data.setAddress(map.get("address").toString());
		// 收货人
		data.setConsignee(map.get("consignee").toString());
		// 收货人电话
		data.setConsigneePhone(map.get("consigneePhone").toString());
		// 物料id
		data.setProductID(Integer.parseInt(map.get("productID").toString()));
		// 磅单打印物料名称
		data.setLbsMaterialName(map.get("lbsMaterialName").toString());
		
		// 出库单
		if(Integer.parseInt(map.get("outTypeMark").toString()) == 0) {
			// 磅单输出名称
//			data.setLbsOutputName(map.get("lbsOutputName").toString());
		    // 是否大车称重
			data.setCartWeighType(map.get("cartWeighType").toString());
		}
		// 送货人
		data.setDeliveryMan(map.get("deliveryMan").toString());
		// 送货电话
		data.setDeliveryManPhone(map.get("deliveryManPhone").toString());
		// 车主
		data.setCarOwner(map.get("carOwner").toString());
		// 车主电话
		data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		// 备注
		data.setRemarks(map.get("remarks").toString());
		// 优先级 0正常, 1 优先
		data.setPriority(0);
		// 销售公司名称
		data.setSaleCompanyName(map.get("saleCompanyName").toString());
		// 出库单类型标识 0：出库单 1：未入厂出库单
		data.setOutTypeMark(Integer.parseInt(map.get("outTypeMark").toString()));
		
		// 采购合同
		if(Integer.parseInt(map.get("outTypeMark").toString()) == 1) {
			data.setPurchaseContractId(Integer.parseInt(map.get("purchaseContractId").toString()));
			data.setSuttle(Double.valueOf(map.get("suttle").toString()));
		}
		
		// 出库单状态 0：正常 1：整车调拨（作废） 2：半车调拨 3：退货
		data.setOutType(Integer.valueOf(map.get("outType").toString()));
		// 是否未入厂出库
		data.setOutTypeMark(Integer.parseInt(map.get("outTypeMark").toString()));
		// 是否检测
		data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		// 有效标识,0无效,1有效
		data.setValidFlag(1);
		// 创建人
		data.setCreater(String.valueOf(userInfo.getId()));
		// 毛重
		data.setGrossWeight(Double.valueOf(map.get("GrossWeight") == null ? "0" : map.get("GrossWeight").toString()));
		// 皮重
		data.setTareWeight(Double.valueOf(map.get("TareWeight") == null ? "0" : map.get("TareWeight").toString()));
		// 净重
		data.setSuttle(Double.valueOf(map.get("Suttle") == null ? "0" : map.get("Suttle").toString()));
		// 封签号
		data.setFacingSlipNum(map.get("facingSlipNum") == null ? "" : map.get("facingSlipNum").toString());
		// 结算重量
		if (Integer.valueOf(map.get("outType").toString()) == 3) {
			data.setAmount(Double.valueOf(map.get("Amount").toString()));
		}
		// 是否调拨
		if (map.get("isDiaobo") != null && Integer.valueOf(map.get("isDiaobo").toString()) == 0) {
			data.setOutType(1);
			// 调拨重量
			data.setAllotWeight(Double.valueOf(map.get("allotWeight") == null ? "0" : map.get("allotWeight").toString()));
			
		}

/*		// 整车调拨判断
		if(Integer.parseInt(map.get("flag").toString()) == 2) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userInfo.getId());
			params.put("id",Integer.parseInt( map.get("id").toString()));
			params.put("flag", 0);
			if(priceDao.updateValidFlag(params) < 0) {
				info.setCode("error");
				// 保存失败
				info.setMessage(PropertyUtil.getProperties("M0008"));
				return info;
			}
		}*/
		
		int result = priceDao.addExportMeasure(data);
		if (result > 0) {
			if (map.get("isDiaobo") != null && Integer.valueOf(map.get("isDiaobo").toString()) == 0) {
				map.put("id", data.getId());
				if (addNoWeighOut(map, userInfo) > 0) {
					if(Integer.parseInt(map.get("outTypeMark").toString()) == 1) {
						// 新增出库单id
						if(addImportMeasure(data) > 0) {
							info.setCode("success");
							// 保存成功
							info.setMessage(PropertyUtil.getProperties("M0007"));
						}else {
							info.setCode("error");
							// 保存失败
							info.setMessage(PropertyUtil.getProperties("M0008"));
						}
					}else {
						info.setCode("success");
						// 保存成功
						info.setMessage(PropertyUtil.getProperties("M0007"));
					}
					
				}else {
					info.setCode("error");
					// 保存失败
					info.setMessage(PropertyUtil.getProperties("M0008"));
				}
			} else {
				if(Integer.parseInt(map.get("outTypeMark").toString()) == 1) {
					// 新增出库单id
					if(addImportMeasure(data) > 0) {
						info.setCode("success");
						// 保存成功
						info.setMessage(PropertyUtil.getProperties("M0007"));
					}else {
						info.setCode("error");
						// 保存失败
						info.setMessage(PropertyUtil.getProperties("M0008"));
					}
				}else {
					info.setCode("success");
					// 保存成功
					info.setMessage(PropertyUtil.getProperties("M0007"));
				}
			}
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
		}
		return info;
	}

	// 更新出库单
	@Override
	public ResponseInfo updateExportMeasure(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		// 更新出库单信息
		OutboundEntity data = new OutboundEntity();
		// 出库单id
		data.setId(Integer.parseInt(map.get("id").toString()));
		// 销售订单id
		data.setSalesOrderId(Integer.parseInt(map.get("orderNumber").toString()));
		// 根据销售订单id获取结算数量
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", Integer.parseInt(map.get("orderNumber").toString()));
		List<OrderNumberEntity> dataList = priceDao.getOrderNumberList(param);
		if (dataList != null) {
			// 结算数量
			data.setAmount(dataList.get(0).getSaleNumber());

		}
		// 车牌号码
		data.setPlateNumber(map.get("plateNumber").toString());
		// 客户名称
		data.setClient(map.get("client").toString());
		// 客户别称
		data.setCustomerAlias(map.get("customerAlias").toString());
		// 送货地址
		data.setAddress(map.get("address").toString());
		// 收货人
		data.setConsignee(map.get("consignee").toString());
		// 收货人电话
		data.setConsigneePhone(map.get("consigneePhone").toString());
		// 物料id
		data.setProductID(Integer.parseInt(map.get("productID").toString()));
		// 磅单打印物料名称
		data.setLbsMaterialName(map.get("lbsMaterialName").toString());
		
		// 是否大车称重
		if(Integer.parseInt(map.get("outTypeMark").toString()) == 0) {
			data.setCartWeighType(map.get("cartWeighType").toString());
			// 磅单输出名称
//			data.setLbsOutputName(map.get("lbsOutputName").toString());
		}
		// 送货人
		data.setDeliveryMan(map.get("deliveryMan").toString());
		// 送货电话
		data.setDeliveryManPhone(map.get("deliveryManPhone").toString());
		// 车主
		data.setCarOwner(map.get("carOwner").toString());
		// 车主电话
		data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		// 备注
		data.setRemarks(map.get("remarks").toString());
		// 采购合同
		if(Integer.parseInt(map.get("outTypeMark").toString()) == 1) {
			// data.setPurchaseContractId(Integer.parseInt(map.get("purchaseContractId").toString()));
			data.setSuttle(Double.valueOf(map.get("suttle").toString()));
		}
		// 是否检测
		data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		// 销售公司名称
		data.setSaleCompanyName(map.get("saleCompanyName").toString());
		// 封签号
		data.setFacingSlipNum(map.get("facingSlipNum") == null ? "" : map.get("facingSlipNum").toString());
		// 更新人
		data.setReviser(String.valueOf(userInfo.getId()));
		int result = priceDao.updateExportMeasure(data);
		
		if (result > 0) {
			if(Integer.parseInt(map.get("outTypeMark").toString()) == 1) {
				if(updateImportMeasure(data) > 0) {
					info.setCode("success");
					// 保存成功
					info.setMessage(PropertyUtil.getProperties("M0005"));
				}else {
					info.setCode("error");
					// 保存失败
					info.setMessage(PropertyUtil.getProperties("M0006"));
				}
			}else {
				info.setCode("success");
				// 保存成功
				info.setMessage(PropertyUtil.getProperties("M0005"));
			}
			
		} else {
			info.setCode("error");
			// 操作失败
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
	
	// 新增入库单
	public int addImportMeasure(OutboundEntity outboundEntity){
		// 入库单信息
		InstroeEntity data = new InstroeEntity();
		// 流水号
		data.setSerialID(outboundEntity.getSerialId());
		 // 采购合同  
		data.setPurchaseContractId(outboundEntity.getPurchaseContractId());
		
		// 获取供应商id
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", outboundEntity.getPurchaseContractId());
		List<Purchasecontract> list = priceDao.getPurchasecontractList(param);
		if(list != null && list.size() > 0) {
			 data.setSupplierId(list.get(0).getSupplierId());
		}
		 // 数量    
		data.setAmount(outboundEntity.getSuttle());
		 // 优先级   
		data.setPriority(0);
		 // 物料id 
		data.setProductID(outboundEntity.getProductID());
		 // 车牌号码  
		data.setPlateNumber(outboundEntity.getPlateNumber());
		 // 送货人   
		data.setDeliveryMan(outboundEntity.getDeliveryMan());
		 // 送货人电话 
		data.setDeliveryManPhone(outboundEntity.getDeliveryManPhone());
		 // 车主    
		data.setCarOwner(outboundEntity.getCarOwner());
		 // 车主电话  
		data.setCarOwnerTelephone(outboundEntity.getCarOwnerTelephone());
		 // 备注    
		data.setRemarks(outboundEntity.getRemarks());
		 // 入库单标识 
		data.setEnterTypeMark(1);
		 // 是否大车称重
		data.setCartWeighType(outboundEntity.getCartWeighType());
		 // 有效标识  
		data.setValidFlag(1);
		 // 出库单id
		data.setOutWarehouseId(outboundEntity.getId());
		 // 创建人 
		data.setCreater(outboundEntity.getCreater());
		
		int result = instroeDao.addImportMeasure(data);
		return result;
	}

	// 更新入库单
	public int updateImportMeasure(OutboundEntity outboundEntity){
	
		// 入库单信息
		InstroeEntity data = new InstroeEntity();
		
		// 出库单id
		data.setOutWarehouseId(outboundEntity.getId());
		 // 数量    
		data.setAmount(outboundEntity.getSuttle());
		 // 优先级   
		data.setPriority(0);
		 // 物料id 
		data.setProductID(outboundEntity.getProductID());
		 // 车牌号码  
		data.setPlateNumber(outboundEntity.getPlateNumber());
		 // 送货人   
		data.setDeliveryMan(outboundEntity.getDeliveryMan());
		 // 送货人电话 
		data.setDeliveryManPhone(outboundEntity.getDeliveryManPhone());
		 // 车主    
		data.setCarOwner(outboundEntity.getCarOwner());
		 // 车主电话  
		data.setCarOwnerTelephone(outboundEntity.getCarOwnerTelephone());
		 // 备注    
		data.setRemarks(outboundEntity.getRemarks());
		 // 入库单标识 
		data.setEnterTypeMark(0);
		 // 是否大车称重
		data.setCartWeighType(outboundEntity.getCartWeighType());
		 // 有效标识  
		data.setValidFlag(1);
		 // 修改人 
		data.setReviser(outboundEntity.getReviser());
		
		int result = instroeDao.updateImportMeasure(data);

		return result;
	}

	// 获取采购合同
	@Override
	public List<Purchasecontract> getPurchasecontractList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return priceDao.getPurchasecontractList(map);
	}

	/**
	 * 获取无法选中的车辆信息
	 * @param type 出库（out）/入库（in） 
	 * @return
	 */
	@Override
	public List<Map<String,Object>> queryCarInUse() {
		return priceDao.queryCarInUse();
	}

	@Override
	public List<Map<String, Object>> getOrderDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return priceDao.getOrderDetail(map);
	}

	@Override
	public List<Map<String, Object>> getEmptyOutboundInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return priceDao.getEmptyOutboundInfo(map);
	}

	@Override
	public ResponseInfo insertEmptyOutBound(HttpServletRequest request, Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();		
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		String ids = map.get("ids").toString();
		String[] split = ids.split(",");
		int result = 0;
		for (int i = 0; i < split.length; i++) {
			Map<String, Object> newMap = new HashMap<String, Object>();
			newMap.put("outWarehouseId", map.get("id").toString());
			newMap.put("emptyCarId", split[i].toString());
			newMap.put("creater", String.valueOf(userInfo.getId()));
			result += priceDao.insertEmptyOutBound(newMap);
			try {
				if (result <= 0) {
					info.setCode("error");
					info.setMessage(PropertyUtil.getProperties("M0006"));
					return info;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public ResponseInfo updateEmptyOutBound(HttpServletRequest request, Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		// 先删除
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("id", map.get("id").toString());
		newMap.put("reviser", String.valueOf(userInfo.getId()));
		int result = priceDao.deleteEmptyOutBound(newMap);
		if (result <= 0) {
			try {
				info.setCode("error");
				info.setMessage(PropertyUtil.getProperties("M0006"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return info;
		}
		// 再添加
		if (map.get("ids").toString().length() > 0) {
			info = this.insertEmptyOutBound(request, map);
		} else {
			try {
				info.setCode("success");
				info.setMessage(PropertyUtil.getProperties("M0005"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return info;
	}
	
	 // 新增未称重出库单
	public int addNoWeighOut(Map<String, Object> map, Userinfo userInfo) {
		NoWeighOutWarehouse data = new NoWeighOutWarehouse();
		// 流水号 
		data.setSerialID(map.get("wczSerialID").toString());
		// 销售订单id 
		data.setSalesOrderId(Integer.parseInt(map.get("salesOrderId").toString()));
		
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
		//data.setConsignee(map.get("consignee").toString());
		// 收货人电话 
		//data.setConsigneePhone(map.get("consigneePhone").toString());
		// 物料id
		data.setMaterielInfoId(Integer.parseInt(map.get("productID").toString()));
		// 运输结算情况（数据字典） 0:我方承担运费 1:客户自提 2:我方垫付运费 3:其他  
		data.setTransportBalance(map.get("transportBalance").toString());
		// 车牌号码       
		data.setCarName(map.get("plateNumber").toString());
		// 送货人   
		data.setDeliverer(map.get("deliveryMan").toString());
		// 送货人电话         
		data.setDeliveryPhone(map.get("deliveryManPhone").toString());
		// 车主      
		data.setCarOwner(map.get("carOwner").toString());
		// 车主电话             
		data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		// 备注         
		data.setRemarks(map.get("diaoRemarks").toString());
		// 是否检测       
		//data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		// 出库单id        
		data.setDispatchOutWarehouseId(Integer.parseInt(map.get("id").toString()));
		// 有效标识 
		data.setValidFlag(1);                                       
		// 创建人                                          
		data.setCreater(String.valueOf(userInfo.getId()));
		
		return outlistDao.addNoWeighOut(data);
	}

	@Override
	public int updateFacingSlipNum(HttpServletRequest request, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return outlistDao.updateFacingSlipNum(map);
	}
	@Override
	public List<Map<String, Object>> getEmptyPriceInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return priceDao.getEmptyPriceInfo(map);
	}
	//修改价格提交
	@Override
	public ResponseInfo updateEmptyPriceInfo(HttpServletRequest request, Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
			String[] param = {};
			int  result = 0;
			param = map.get("id").toString().split(",");
			for(int i=0;i<param.length;i++) {
				map.put("id", param[i]);
				result += priceDao.updateRKPriceInfo(map);
				result += priceDao.updateCKPriceInfo(map);
				result +=  priceDao.updateWCZPriceInfo(map);
				
			}
			if (result <= 0) {
				info.setCode("error");
				info.setMessage(MessageUtil.OPERATION_FAILED);
			} else {
				info.setCode("success");
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
			return info;
	}

	@Override
	public List<Map<String, Object>> getCustomerTrans(Map<String, Object> map) {
		return priceDao.getCustomerTrans(map);
	}

	@Override
	public ResponseInfo addOfferrecord(HttpServletRequest request, Map<String, Object> map) {
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		map.put("creater", userInfo.getUsername());
		ResponseInfo info = new ResponseInfo();
		int  result = priceDao.addOfferrecord(map);
		if (result <= 0) {
			info.setCode("error");
			info.setMessage(MessageUtil.OPERATION_FAILED);
		} else {
			info.setCode("success");
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		}
		return info;
	}

	@Override
	public DataTablesResponseInfo getAllOfferrecord(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String, Object>> dataList = priceDao.getAllOfferrecord(map);
		info.setData(dataList);
		return info;
	}

	@Override
	public List<SalesOrderEntity> getOfferrecordTree(Map<String, Object> map) {
		List<SalesOrderEntity> dataList = priceDao.getOfferrecordTree(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<SalesOrderEntity>();
		}
		return dataList;
	}
}
