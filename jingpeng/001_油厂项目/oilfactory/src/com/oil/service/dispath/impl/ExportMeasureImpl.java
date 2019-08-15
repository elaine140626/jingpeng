package com.oil.service.dispath.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.dispath.ExportMeasureDao;
import com.oil.dao.dispath.RoadgateDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Roadgate;
import com.oil.model.dispath.CustomerOrderEntity;
import com.oil.model.dispath.ExportMeasureEntity;
import com.oil.model.dispath.SalesOrderListEntity;
import com.oil.model.system.CarInfo;
import com.oil.service.dispath.ExportMeasureService;
import com.oil.util.ReportMergeXls;

@Service
public class ExportMeasureImpl implements ExportMeasureService {
	@Autowired
	ExportMeasureDao exportMeasureDao;
//	@Autowired
//	OutlistDao outlistDao;
//	
	@Autowired
	RoadgateDao roadgateDao;
//	
//	@Autowired
//	private RepertoryDao repertoryDao;
//	
//	@Autowired
//	SerialNumberDao serialNumberDao;

	// 左侧客户订单信息树
	public List<CustomerOrderEntity> getCustomerOrderList(Map<String, Object> map) {
		List<CustomerOrderEntity> dataList = exportMeasureDao.getCustomerOrderList(map);
		return dataList;
	}

	// 获取调度单已有车牌号
	public List<String> getPlateNumberList(Map<String, Object> map) {
		List<String> dataList = exportMeasureDao.getPlateNumberList(map);
		return dataList;
	}

	// 获取页面list信息
	public DataTablesResponseInfo getExportMeasureList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<ExportMeasureEntity> dataList = exportMeasureDao.getExportMeasureList(map);
		info.setData(dataList);
		return info;
	}
	
	// 新增页面获取销售订单编号
	public List<SalesOrderListEntity> getSalesOrderList(Map<String, Object> map) {
		List<SalesOrderListEntity> dataList = exportMeasureDao.getSalesOrderList(map);
		return dataList;
	}
	
	// 空发：查询所有可选出库单号
	public List<Map<String, Object>> getAllOutboundList(Map<String, Object> map) {
		return exportMeasureDao.getAllOutboundList(map);
	}
	
	// 获取所有车牌号码
	public List<CarInfo> getAllPlateNumbers(Map<String, Object> map) {
		return exportMeasureDao.getAllPlateNumbers(map);
	}
	
	// 获取所有车牌号码（不包含没有二次称重的车牌号）
	public List<CarInfo> getAllPlateNumbersExc(Map<String, Object> map){
		return exportMeasureDao.getAllPlateNumbersExc(map);
	}
	
	// 判断输入车牌号是否没有完成二次称重
	public int checkPlateNumber(Map<String, Object> map) {
		return exportMeasureDao.checkPlateNumber(map);
	}

	// 出库单作废或者删除
	public ResponseInfo updateValidFlag(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		// 出库单作废或者删除
		result = exportMeasureDao.updateValidFlag(map);	
		// 删除关联的运输单
		result += exportMeasureDao.delTranSportList(map);		
		// 删除关联的未称重出库单
		result += exportMeasureDao.deleteNoWeight(map);		
		if (result > 0) {			
			info.setCode("success");
			info.setMessage("删除成功");		
		} else {
			info.setCode("error");
			info.setMessage("删除失败");
		}
		return info;
	}

	// 新增出库单
	public ResponseInfo addExportMeasure(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		ExportMeasureEntity data = new ExportMeasureEntity();		
		// 流水号
		data.setSerialId(map.get("serialId").toString());		
		// 销售订单id
		data.setSalesOrderId(Integer.parseInt(map.get("salesOrderId").toString()));
		// 销售订单明细Id
		data.setOrderDetailedId(Integer.valueOf(map.get("orderDetailedId").toString()));
		// 物料id
		data.setProductId(Integer.parseInt(map.get("productId").toString()));
		// 磅单打印物料名称
		data.setLbsMaterialName(map.get("lbsMaterialName").toString());
		// 车牌号码
		data.setPlateNumber(map.get("plateNumber").toString());
		// 客户编号
		data.setCustomerCode(map.get("customerCode").toString());
		// 客户别称
		data.setCustomerAlias(map.get("customerAlias").toString());
		// 送货人
		data.setDeliveryMan(map.get("deliveryMan").toString());
		// 送货电话
		data.setDeliveryManPhone(map.get("deliveryManPhone").toString());
		// 送货地址
		data.setAddress(map.get("address").toString());	
		// 收货人
		data.setConsignee(map.get("consignee").toString());
		// 收货人电话
		data.setConsigneePhone(map.get("consigneePhone").toString());
		// 收货地址
		data.setConsigneeAddress(map.get("transports").toString());
		// 车辆称重类型（数据字典） 0:普通 1:大车 2:超大车
		data.setCartWeighType(map.get("cartWeighType").toString());
		// 销售公司名称
		data.setSaleCompanyName(map.get("saleCompanyName").toString());	
		// 出库单状态0：正常 1：调拨 2：退货 3:空发 4:兑换 5.兑换时调拨
		data.setOutType(map.get("outType").toString());
		// 正常出库单
		if(Integer.parseInt(map.get("outType").toString()) == 0) {
			// 起运地
			data.setStartAddress(map.get("startAddress").toString());
		}
		// 空发出库单的场合
		if(Integer.parseInt(map.get("outType").toString()) == 3) {
			// 皮重
			data.setTareWeight(Double.valueOf(map.get("tareWeight").toString()));
			// 毛重
			data.setGrossWeight(Double.valueOf(map.get("grossWeight").toString()));
			// 净重
			data.setSuttle(Double.valueOf(map.get("suttle").toString()));			
		}
		if (map.get("remarks") != null) {
			// 备注
			data.setRemarks(map.get("remarks").toString());
		}	
		// 创建人
		data.setCreater(map.get("userId").toString());	
		
		int result = exportMeasureDao.addExportMeasure(data);
		// 新增出库单id
		map.put("id", data.getId());
		// 不是空发出库单的场合
		if(Integer.parseInt(map.get("outType").toString()) != 3) {
			// 自动生成运输单
			Map<String,Object> tranSportMap = new HashMap<>();
			// 出库/入库/未入厂出库单id
			tranSportMap.put("dispatchOutWarehouseId", data.getId());
			// 出库/入库/未入厂出库单编号
			tranSportMap.put("serialID", data.getSerialId());
			// 票据编号
			tranSportMap.put("billNumber",map.get("YSserialId").toString());
			// 车牌号
			tranSportMap.put("plateNumber", map.get("plateNumber"));
			// 止运地
			tranSportMap.put("endAddress", map.get("transports"));
			// 是否自提
			tranSportMap.put("isMention", map.get("transportBalancesName").equals("客户自提")?1:0);
			// 司机
			tranSportMap.put("carOwner",map.get("deliveryMan"));
			// 司机电话
			tranSportMap.put("carOwnerTelephone", map.get("deliveryManPhone"));
			// 出库单状态0：正常 1：调拨 2：退货 3:空发 4:兑换 5.兑换时调拨
			tranSportMap.put("type", Integer.valueOf(map.get("outType").toString()));
			// 物料名称
			tranSportMap.put("materielId", map.get("materielName"));
			// 物料型号
			tranSportMap.put("model", map.get("materielModel"));
			// 创建人
			tranSportMap.put("creater", map.get("userId").toString());
			result += exportMeasureDao.addTranSportList(tranSportMap);
			
			// 新增对应的道闸数据
			Roadgate roadgate = new Roadgate();
	        roadgate.setOutWarehouseId(data.getId());
	        roadgate.setCarNumber(data.getPlateNumber());
			roadgate.setCreater(map.get("userId").toString());	
			result = roadgateDao.addRoadgate(roadgate);
		}
	
		// 关联空发出库单
		if (Integer.parseInt(map.get("outType").toString()) == 3) {
			// 新增关联空发出库单
			result += this.insertEmptyOutBound(map);
		}
		
		if (result > 0) {
			info.setCode("success");
			info.setMessage("保存成功");
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage("保存失败");
		}
		return info;
	}
	
	// 新增关联空发出库单
	public int insertEmptyOutBound(Map<String, Object> map) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		// 空发单id
		newMap.put("emptyCarId", map.get("serialID").toString());
		// 出库单id
		newMap.put("outWarehouseId", map.get("outboundList").toString());
		// 当前用户
		newMap.put("creater", map.get("userId").toString());
		
		return exportMeasureDao.insertEmptyOutBound(newMap);
	}

	// 更新出库单
	public ResponseInfo updateExportMeasure(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		// 更新出库单信息
		ExportMeasureEntity data = new ExportMeasureEntity();	
		// 出库单id
		data.setId(Integer.parseInt(map.get("id").toString()));
		// 销售订单id
		data.setSalesOrderId(Integer.parseInt(map.get("salesOrderId").toString()));
		// 销售订单明细Id
		data.setOrderDetailedId(Integer.valueOf(map.get("orderDetailedId").toString()));
		// 物料id
		data.setProductId(Integer.parseInt(map.get("productId").toString()));
		// 磅单打印物料名称
		data.setLbsMaterialName(map.get("lbsMaterialName").toString());
		// 车牌号码
		data.setPlateNumber(map.get("plateNumber").toString());
		// 客户编号
		data.setCustomerCode(map.get("customerCode").toString());
		// 客户别称
		data.setCustomerAlias(map.get("customerAlias").toString());
		// 送货人
		data.setDeliveryMan(map.get("deliveryMan").toString());
		// 送货电话
		data.setDeliveryManPhone(map.get("deliveryManPhone").toString());
		// 送货地址
		data.setAddress(map.get("address").toString());	
		// 收货人
		data.setConsignee(map.get("consignee").toString());
		// 收货人电话
		data.setConsigneePhone(map.get("consigneePhone").toString());
		// 收货地址
		data.setConsigneeAddress(map.get("transports").toString());
		// 车辆称重类型（数据字典） 0:普通 1:大车 2:超大车
		data.setCartWeighType(map.get("cartWeighType").toString());
		// 销售公司名称
		data.setSaleCompanyName(map.get("saleCompanyName").toString());
		// 正常出库单
		if(Integer.parseInt(map.get("outType").toString()) == 0) {
			// 起运地
			data.setStartAddress(map.get("startAddress").toString());
		}
		// 备注
		data.setRemarks(map.get("remarks").toString());	
		// 更新人
		data.setReviser(map.get("userId").toString());
		
		int result = exportMeasureDao.updateExportMeasure(data);
				
		// 不是空发出库单的场合
		if(Integer.parseInt(map.get("outType").toString()) != 3) {
			// 自动生成运输单
			Map<String,Object> tranSportMap = new HashMap<>();
			// 出库/入库/未入厂出库单id
			tranSportMap.put("dispatchOutWarehouseId", data.getId());
			// 车牌号
			tranSportMap.put("plateNumber", map.get("plateNumber"));
			// 止运地
			tranSportMap.put("endAddress", map.get("transports"));
			// 是否自提
			tranSportMap.put("isMention", map.get("transportBalancesName").equals("客户自提")?1:0);
			// 司机
			tranSportMap.put("carOwner",map.get("deliveryMan"));
			// 司机电话
			tranSportMap.put("carOwnerTelephone", map.get("deliveryManPhone"));
			// 物料名称
			tranSportMap.put("materielId", map.get("materielName"));
			// 物料型号
			tranSportMap.put("model", map.get("materielModel"));
			// 更新人
			tranSportMap.put("reviser", map.get("userId").toString());
			result += exportMeasureDao.updateTranSportList(tranSportMap);
			
			// 修改道闸表
			if (map.get("oldPlateNumber").toString() != map.get("plateNumber").toString()) {
				Roadgate roadgate = new Roadgate();
		        roadgate.setOutWarehouseId(data.getId());
		        roadgate.setCarNumber(data.getPlateNumber());
				roadgate.setCreater(map.get("userId").toString());
				// 删除出库单对应的道闸表
				result += exportMeasureDao.delRoadgate(roadgate);
				// 新增对应的道闸数据					
				result += roadgateDao.addRoadgate(roadgate);				
			}
		}
			
		if (result > 0) {
			info.setCode("success");
			info.setMessage("保存成功");
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage("保存失败");
		}
		return info;
	}
	
	// 调度导出
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		String sheetName = "出库信息";
		// 表头
		String[] head0 = new String[] { "编号", "流水号", "关联出库单流水号","日期", "客户名称", "产品名称", "规格型号", "车牌号", "皮重/吨", "毛重/吨", "净重/吨",
				"回执重量", "涨亏吨", "收货人", "电话", "备注" };
		// 对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		// 调度导出。。。todolist
		List<Map<String, Object>> dataList = exportMeasureDao.getInfoListEX(map);
		for (int i = 0; i < dataList.size(); i++) {
			dataList.get(i).put("row", i + 1);
			if (dataList.get(i).get("outTypeName") != null
					&& dataList.get(i).get("outTypeName").toString().equals("空发")) {
				dataList.get(i).put("remarks", "系统维护检查");
			}
		}
		String[] colName = new String[] { "row", "serialId","nowSerialId", "createrDate", "customerName", "materielName",
				"materielModel", "plateNumber", "tareWeight", "grossWeight", "suttle", "receiptWeight", "riseLoss",
				"consignee", "consigneePhone", "remarks" };
		Date now = new Date();
		String date = "创建时间" + now.toString();
		try {
			// request, response, 数值,标题,第一行表头名字,数值对应列名称,创建时间,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0, colName, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	 // 新增未称重出库单
//	public int addNoWeighOut(Map<String, Object> map, Userinfo userInfo) {
//		try {
//			NoWeighOutWarehouse data = new NoWeighOutWarehouse();
//			// 流水号 
//			data.setSerialID(map.get("wczSerialID").toString());
//			// 销售订单id 
//			data.setSalesOrderId(Integer.parseInt(map.get("diaoOrderNumber").toString()));
//			// 销售订单明细id 
//			data.setOrderDetailedNumber(Integer.parseInt(map.get("diaoOrderDetailedId").toString()));
//			
//			if(map.get("allotWeight") != null && !("").equals(map.get("allotWeight").toString())) {
//				// 调拨重量
//				data.setAllotWeight(Double.valueOf(map.get("allotWeight").toString()));
//			}
//			if(map.get("saleNumber") != null) {
//				if(!("null").equals(map.get("saleNumber").toString()) && !("").equals(map.get("saleNumber").toString())) {
//					// 销售数量
//					data.setSaleNumber(Double.valueOf(map.get("saleNumber").toString()));
//				}	
//			}
//			if(map.get("salePrice") != null){
//				if(!("null").equals(map.get("salePrice").toString()) && !("").equals(map.get("salePrice").toString())) {
//					// 销售单价
//					data.setSalePrice(Double.valueOf(map.get("salePrice").toString()));
//				}	
//			}
//			if(map.get("saleMoney") != null) {
//				if(!("null").equals(map.get("saleMoney").toString()) && !("").equals(map.get("saleMoney").toString())) {
//					// 销售金额 
//					data.setSaleMoney(Double.valueOf(map.get("saleMoney").toString()));
//				}
//			}
//			
//			// 客户名称
//			data.setClient(map.get("diaoClient").toString());
//			// 客户别称 
//			data.setCustomerAlias(map.get("diaoCustomerAlias").toString());
//			if(map.get("taxRate") != null) {
//				if(!("null").equals(map.get("taxRate").toString()) && !("").equals(map.get("taxRate").toString()) ) {
//					// 税率 
//					data.setTaxRate(map.get("taxRate").toString());
//				}	
//			}
//			data.setType("1");
//			//送货地址
//			data.setAddress(map.get("address").toString());
//			//收货地址
//			data.setConsigneeAddress(map.get("diaoAddress").toString());
//			//收货人  
//			data.setConsignee(map.get("diaoConsignee").toString());
//			//收货人电话 
//			data.setConsigneePhone(map.get("diaoConsigneePhone").toString());
//			// 物料id
//			data.setMaterielInfoId(Integer.parseInt(map.get("productID").toString()));
//			// 运输结算情况（数据字典） 0:我方承担运费 1:客户自提 2:我方垫付运费 3:其他  
//			data.setTransportBalance(map.get("transportBalance").toString());
//			// 车牌号码       
//			data.setCarName(map.get("plateNumber").toString());
//			// 送货人   
//			data.setDeliverer(map.get("deliveryMan").toString());
//			// 送货人电话         
//			data.setDeliveryPhone(map.get("deliveryManPhone").toString());
//			// 车主      
////			data.setCarOwner(map.get("carOwner").toString());
//			// 车主电话             
////			data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
//			// 备注         
//			data.setRemarks(map.get("diaoRemarks").toString());
//			// 出库单id        
//			data.setDispatchOutWarehouseId(Integer.parseInt(map.get("id").toString()));
//			// 有效标识 
//			data.setValidFlag(1);                                       
//			// 创建人                                          
//			if (userInfo != null) {
//				data.setCreater(String.valueOf(userInfo.getId()));
//			}else {
//				data.setCreater(map.get("userId").toString());	
//			}
//			
//			int result = outlistDao.addNoWeighOut(data);
//			if(result>0) {
//				//自动生成运输单
//				Map<String,Object> tranSportMap = new HashMap<>();
//				Map<String,Object> materielinfoMap = new HashMap<>();
//				int res = 0;
//				Prefix prefix = serialNumberDao.getContractInfoPrefix("YS");
//				if (prefix.getFlowNumber()!= null & !"".equals(prefix.getFlowNumber())) {
//					int newEquipment = Integer.parseInt(prefix.getFlowNumber()) + 1;
//					prefix.setFlowNumber(String.format("%0"+prefix.getFlowNumber().length()+"d",newEquipment));
//				}
//				res += serialNumberDao.updateSerialNumber(prefix);
//				if(res<0) {
//					return -1;
//				}
//				Prefix pix = this.getContractInfoPrefix("YS");
//				tranSportMap.put("dispatchOutWarehouseId", data.getId());
//				tranSportMap.put("serialID", map.get("wczSerialID"));
//				//int rok = serialNumberDao.updateSerialNumber(prefix);
//				tranSportMap.put("billNumber",pix.getPrefixs()); //////////////////////////做到这里
//				tranSportMap.put("plateNumber", map.get("plateNumber"));
//				tranSportMap.put("endAddress", map.get("diaoAddress"));
//				tranSportMap.put("carOwner",map.get("deliveryMan"));
//				tranSportMap.put("carOwnerTelephone", map.get("deliveryManPhone"));
//				tranSportMap.put("Weigh", map.get("allotWeight"));
//				/*tranSportMap.put("creater", map.get("userId"));*/
//				//tranSportMap.put("weigh", map.get("suttle"));
//				materielinfoMap.put("productID", map.get("productID"));
//				List<MaterielinfoEntity> materielinfoEntity =  outboundDao.getMaterielinfoList(materielinfoMap);
//				if(materielinfoEntity.size()>0) {
//					tranSportMap.put("materielId", materielinfoEntity.get(0).getMaterielNameId());
//					tranSportMap.put("model", materielinfoEntity.get(0).getMaterielModelId());
//				}
//				tranSportMap.put("type", 1);
//				int TranSportResult = outboundDao.addTranSportList(tranSportMap);
//				return TranSportResult;
//			}else {
//				return result;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
//
//	public int updateNoWeighOutWarehouse(HttpServletRequest request, Map<String, Object> map) {
//		try {
//			ResponseInfo info = new ResponseInfo();
//			// 获取当前登录用户的信息
//			Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
//			
//			//新增未称重出库单
//			NoWeighOutWarehouse data = new NoWeighOutWarehouse();
//			// 未称重出库单id
//			data.setId(Integer.parseInt(map.get("noWeightId").toString()));
//			//销售订单编号
//			data.setSalesOrderId(Integer.parseInt(map.get("diaoOrderNumber").toString()));
//			// 销售订单明细id 
//			data.setOrderDetailedNumber(Integer.parseInt(map.get("diaoOrderDetailedId").toString()));
//			//选择出库单
//			data.setDispatchOutWarehouseId(Integer.parseInt(map.get("id").toString()));
//			//车主
////			data.setCarOwner(map.get("carOwner").toString());
//			//车牌号码
//			data.setCarName(map.get("plateNumber").toString());
//			//车主电话
////			data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
//			// 调拨重量
//			data.setAllotWeight(Double.valueOf(map.get("allotWeight") == null ? "0" : map.get("allotWeight").toString()));
//			//销售数量
//			data.setSaleNumber(map.get("SaleNumber") == "" ? 0 : Double.parseDouble(map.get("SaleNumber").toString()));
//			//客户名称
//			data.setClient(map.get("diaoClient").toString());
//			//客户别称
//			data.setCustomerAlias(map.get("diaoCustomerAlias").toString());
//			//收货人
//			data.setConsignee(map.get("diaoConsignee").toString());
//			//联系电话
//			data.setConsigneePhone(map.get("diaoConsigneePhone").toString());
//			//送货地址
//			data.setAddress(map.get("address").toString());
//			//收货地址
//			data.setConsigneeAddress(map.get("diaoAddress").toString());
//			//物料名称/型号
//			data.setMaterielInfoId(Integer.parseInt(map.get("productID").toString()));
//			//结算情况
//			data.setTransportBalance(map.get("transportBalance").toString());
//			//送货人
//			data.setDeliverer(map.get("deliveryMan").toString());
//			//送货电话
//			data.setDeliveryPhone(map.get("deliveryManPhone").toString());
//			//备注
//			data.setRemarks(map.get("diaoRemarks").toString());
//			// 更新人
//			if (userInfo != null) {
//				data.setReviser(String.valueOf(userInfo.getId()));
//			}else {
//				data.setReviser(map.get("userId").toString());
//			}
//			return repertoryDao.updateNoWeighOutWarehouse(data);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
//

//
//	@Override
//	public List<Map<String, Object>> getTransportList(Map<String, Object> map) {
//		return outboundDao.getTransportList(map);
//	}

//	
//	public Prefix getContractInfoPrefix(String types) {
//		Prefix prefix = serialNumberDao.getContractInfoPrefix(types);
//		String prefixs = "";
//		Date now = new Date(); 
//		if (prefix.getAllPrefix() != null && !"".equals(prefix.getAllPrefix())) {
//			prefixs += prefix.getAllPrefix();
//		}
//		if(!"1".equals(prefix.getYear())) {
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
//			String dateString = formatter.format(now);
//			prefixs += dateString;
//		}
//		if(!"1".equals(prefix.getMonth())) {
//			SimpleDateFormat formatter = new SimpleDateFormat("MM");
//			prefixs += formatter.format(now);
//		}
//		if(!"1".equals(prefix.getDay())) {
//			SimpleDateFormat formatter = new SimpleDateFormat("dd");
//			prefixs += formatter.format(now);
//		}
//		if(!"1".equals(prefix.getHour())) {
//			SimpleDateFormat formatter = new SimpleDateFormat("HH");
//			prefixs += formatter.format(now);
//		}
//		if(!"1".equals(prefix.getBranch())) {
//			SimpleDateFormat formatter = new SimpleDateFormat("mm");
//			prefixs += formatter.format(now);
//		}
//		if(!"1".equals(prefix.getSecond())) {
//			SimpleDateFormat formatter = new SimpleDateFormat("ss");
//			prefixs += formatter.format(now);
//		}
//		if(prefix.getFlowNumber() != null && !"".equals(prefix.getFlowNumber())) {
//			prefixs += prefix.getFlowNumber();
//		}
//		prefix.setPrefixs(prefixs);
//		return prefix;
//	}
//

	
	/******出库单查询用********/
	// 获取符合条件的调拨销售订单物料Id
	public List<Map<String, Object>> getDiaoMaterielId(Map<String, Object> map) {
		return exportMeasureDao.getDiaoMaterielId(map);
	}
	
	// 获取符合条件的调拨销售订单列表
	public List<Map<String, Object>> getDiaoOrderNumber(Map<String, Object> map) {
		return exportMeasureDao.getDiaoOrderNumber(map);
	}

	// 获取符合条件的调拨销售订单明细列表
	public List<Map<String, Object>> getDiaoOrderDetail(Map<String, Object> map) {
		return exportMeasureDao.getDiaoOrderDetail(map);
	}

	// 根据收货地址获取收货人信息
	public List<Map<String, Object>> getConsigneeInfo(Map<String, Object> map) {
		return exportMeasureDao.getConsigneeInfo(map);
	}
}
