package com.oil.service.dispath.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oil.dao.dispath.InstroeDao;
import com.oil.dao.dispath.OutboundDao;
import com.oil.dao.dispath.RoadgateDao;
import com.oil.dao.repertory.RepertoryDao;
import com.oil.dao.sales.OutlistDao;
import com.oil.dao.system.SerialNumberDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Roadgate;
import com.oil.model.Userinfo;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Prefix;
import com.oil.model.system.Purchasecontract;
import com.oil.service.dispath.OutboundService;
import com.oil.util.PropertyUtil;
import com.oil.util.ReportMergeXls;

@Service
public class OutboundServiceImpl implements OutboundService {
	@Autowired
	OutboundDao outboundDao;
	
	@Autowired
	OutlistDao outlistDao;
	
	@Autowired
	RoadgateDao roadgateDao;
	
	@Autowired
	private RepertoryDao repertoryDao;
	
	@Autowired
	InstroeDao instroeDao;
	
	@Autowired
	SerialNumberDao serialNumberDao;

	// 根据用户获取对应的销售订单
	@Override
	public List<SalesOrderEntity> getSalesOrderList(Map<String, Object> map) {
		List<SalesOrderEntity> dataList = outboundDao.getSalesOrderList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<SalesOrderEntity>();
		}
		return dataList;
	}

	// 获取调度单已有车牌号
	@Override
	public List<String> getPlateNumberList(Map<String, Object> map) {
		List<String> dataList = outboundDao.getPlateNumberList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<String>();
		}
		return dataList;
	}

	// 获取页面list信息
	@Override
	public DataTablesResponseInfo getInfoList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<OutboundEntity> dataList = outboundDao.getInfoList(map);
		// 查询关联的空发出库单
		for (int i = 0; i < dataList.size(); i++) {
			List<Map<String, Object>> list = outboundDao.queryEmptyOutBoundById(dataList.get(i).getSerialId());
			if (list != null && list.size() > 0) {
				StringBuilder emptyIds = new StringBuilder();
				for (int j = 0; j < list.size(); j++) {
					emptyIds.append(list.get(j).get("EmptyCarId")).append(",");
				}
				dataList.get(i).setIsEmpty("Y");
				dataList.get(i).setEmptyList(emptyIds.toString().substring(0, emptyIds.length()-1));
			} else {
				dataList.get(i).setIsEmpty("N");
			}
			
		}

		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<OutboundEntity>();
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
		if (userInfo != null) {
			map.put("userId", userInfo.getId());
		}
		int result = 0;
		OutboundEntity outboundEntity = new OutboundEntity();
		List<OutboundEntity> dataList = outboundDao.getInfoList(map);
		if (dataList != null && dataList.size() > 0) {
			outboundEntity = dataList.get(0);
			
			// 未出厂出库单
			if(outboundEntity.getOutTypeMark() == 1) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("outWarehouseId", outboundEntity.getId());
				param.put("flag", map.get("flag"));
				if (userInfo != null) {
					param.put("userId", userInfo.getId());
				}else {
					param.put("userId", map.get("userId"));
				}
				instroeDao.updateValidFlag(param);
				result += outboundDao.updateValidFlag(map);
			}else {
				result = outboundDao.updateValidFlag(map);
			}
		}
		// 删除运输单
		result += outboundDao.delTranSportList(map);		
		// 删除关联的来料加工或未称重出库单
		result += this.deleteRelationInfo(outboundEntity);		
		// 删除关联的空发出库单
		result += this.deleteEmptyOutList(outboundEntity);
		if (result > 0) {			
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));		
		} else {
			info.setCode("error");
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}

	// 根据出库单id获取信息
	@Override
	public OutboundEntity getOutboundInfo(Map<String, Object> map) {
		// 获取信息
		List<OutboundEntity> dataList = outboundDao.getInfoList(map);
		
		if (dataList != null && dataList.size() > 0) {
			OutboundEntity outboundEntity = dataList.get(0);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("serialID", outboundEntity.getSerialId());
			map2.put("flag", "N");
			List<Map<String, Object>> queryEmptyList = outboundDao.queryEmptyList(map2);
			if (queryEmptyList.size() > 0) {
				// 当本条数据为空发出库单时，查询关联的出库单
				OutboundEntity outboundEntity2 = outboundDao.queryOutBoundInfoBySerialId(queryEmptyList.get(0).get("OutWarehouseId").toString());
				outboundEntity.setEmptyList(outboundEntity2.getSerial_ID());
			}
			return outboundEntity;
		} else {
			return null;
		}
	}

	// 新增页面获取销售订单编号
	@Override
	public List<OrderNumberEntity> getOrderNumberList(Map<String, Object> map) {
		List<OrderNumberEntity> dataList = outboundDao.getOrderNumberList(map);
		return dataList;
	}

	// 物料型号
	@Override
	public List<MaterielinfoEntity> getMaterielinfoList(Map<String, Object> map) {
		List<MaterielinfoEntity> dataList = outboundDao.getMaterielinfoList(map);
		return dataList;
	}

	// 获取所有车牌号码
	@Override
	public List<CarInfo> getAllPlateNumbers(Map<String, Object> map) {
		return outboundDao.getAllPlateNumbers(map);
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
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("orderDetailedId", Integer.parseInt(map.get("orderDetailedId").toString()));
//		param.put("type", "show");
//		List<OrderNumberEntity> dataList = outboundDao.getOrderNumberList(param);
//		if (dataList != null) {
//			// 结算数量
//			data.setAmount(dataList.get(0).getSaleNumber());
//		}
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
//		data.setCarOwner(map.get("carOwner").toString());
		// 车主电话
//		data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		// 备注
		data.setRemarks(map.get("remarks").toString());
		// 优先级 0正常, 1 优先
		data.setPriority(0);
		// 销售公司名称
		data.setSaleCompanyName(map.get("saleCompanyName").toString());
		// 出库单类型标识 0：出库单 1：未入厂出库单
		data.setOutTypeMark(Integer.parseInt(map.get("outTypeMark").toString()));
		// 收货地址
		data.setConsigneeAddress(map.get("consigneeAddress").toString());
		
		// 采购合同
		if(Integer.parseInt(map.get("outTypeMark").toString()) == 1) {
			data.setPurchaseContractId(Integer.parseInt(map.get("purchaseContractId").toString()));
			data.setSuttle(Double.valueOf(map.get("suttle").toString()));
		}
		
		// 出库单状态 0：正常 1：调拨 2：退货 3:空发 4:兑换
		data.setOutType(Integer.valueOf(map.get("outType").toString()));
		// 出库单
		if(Integer.parseInt(map.get("outType").toString()) == 0) {
			// 起运地
			data.setStartAddress(map.get("startAddress").toString());
		}
		// 是否未入厂出库
		data.setOutTypeMark(Integer.parseInt(map.get("outTypeMark").toString()));
		// 是否检测
		data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		// 有效标识,0无效,1有效
		data.setValidFlag(1);
		// 创建人
		if(userInfo != null ){
			data.setCreater(String.valueOf(userInfo.getId()));
		}else {
			data.setCreater(map.get("userId").toString());	
		}
		// 销售订单详情Id
		data.setOrderDetailedId(Integer.valueOf(map.get("orderDetailedId").toString()));
		// 毛重
		if (map.get("GrossWeight") != null) {
			data.setGrossWeight(Double.valueOf(map.get("GrossWeight").toString()));
		}
//		data.setGrossWeight(Double.valueOf(map.get("GrossWeight") == null ? "0" : map.get("GrossWeight").toString()));
		// 皮重
		if (map.get("TareWeight") != null) {
			data.setTareWeight(Double.valueOf(map.get("TareWeight").toString()));
		} 
//		data.setTareWeight(Double.valueOf(map.get("TareWeight") == null ? "null" : map.get("TareWeight").toString()));
		// 净重
		if (map.get("suttle") != null && !"".equals(map.get("suttle").toString())) {
			data.setSuttle(Double.valueOf(map.get("suttle").toString()));
		}

		// 封签号
		data.setFacingSlipNum(map.get("facingSlipNum") == null ? "" : map.get("facingSlipNum").toString());
		// 结算重量
//		if (Integer.valueOf(map.get("outType").toString()) == 3) {
//			data.setAmount(Double.valueOf(map.get("Amount").toString()));
//		}
		// 是否调拨
		if (map.get("isDiaobo") != null && Integer.valueOf(map.get("isDiaobo").toString()) == 0) {
			if (Integer.valueOf(map.get("outType").toString()) == 0) {
				// 调拨
				data.setOutType(1);
			} else if (Integer.valueOf(map.get("outType").toString()) == 4) {
				// 兑换调拨
				data.setOutType(5);
			}
			// 调拨重量
			data.setAllotWeight(Double.valueOf(map.get("allotWeight") == null ? "0" : map.get("allotWeight").toString()));
			
		}

/*		// 整车调拨判断
		if(Integer.parseInt(map.get("flag").toString()) == 2) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userInfo.getId());
			params.put("id",Integer.parseInt( map.get("id").toString()));
			params.put("flag", 0);
			if(outboundDao.updateValidFlag(params) < 0) {
				info.setCode("error");
				// 保存失败
				info.setMessage(PropertyUtil.getProperties("M0008"));
				return info;
			}
		}*/
		
		// 未入厂出库单生成的来料加工流水号
		if (Integer.parseInt(map.get("outTypeMark").toString()) == 1) {
			data.setSerial_ID(map.get("rkSerialID").toString());
			data.setFactoryTime(map.get("factoryTime").toString());
		}
		
		// 关联空发出库单时，将关联的出库单的出厂时间赋给当前空发单
		if (map.get("outboundList") != null && !"".equals(map.get("outboundList").toString())) {
			// 查询出库时间
			String factoryTime = outboundDao.getFactoryTime(map.get("outboundList").toString());
			data.setFactoryTime(factoryTime);
		}
		
		int result = outboundDao.addExportMeasure(data);
		// 新增出库单id
		map.put("id", data.getId());
		//自动生成运输单
		if(data.getOutType() != 3) {
			if(result>0) {
				Map<String,Object> tranSportMap = new HashMap<>();
				Map<String,Object> materielinfoMap = new HashMap<>();
				tranSportMap.put("dispatchOutWarehouseId", data.getId());
				tranSportMap.put("serialID", data.getSerialId());
				tranSportMap.put("billNumber",map.get("YSserialID").toString());
				tranSportMap.put("plateNumber", map.get("plateNumber"));
				tranSportMap.put("endAddress", map.get("consigneeAddress"));
				tranSportMap.put("isMention", map.get("transportBalance"));
				tranSportMap.put("carOwner",map.get("deliveryMan"));
				tranSportMap.put("carOwnerTelephone", map.get("deliveryManPhone"));
				tranSportMap.put("type", Integer.valueOf(map.get("outType").toString()));
				Map<String,Object> mapSad = new HashMap<>();
				mapSad.put("id", data.getSalesOrderId());
				List<OrderNumberEntity> sales = outboundDao.getOrderNumberListAsUserId(mapSad);
				if(sales.size()>0) {
					tranSportMap.put("creater", sales.get(0).getUserInfoId());
				}
				//tranSportMap.put("weigh", map.get("suttle"));
				materielinfoMap.put("productID", map.get("productID"));
				List<MaterielinfoEntity> materielinfoEntity =  outboundDao.getMaterielinfoList(materielinfoMap);
				if(materielinfoEntity.size()>0) {
					tranSportMap.put("materielId", materielinfoEntity.get(0).getMaterielName());
					tranSportMap.put("model", materielinfoEntity.get(0).getMaterielModel());
				}
				int TranSportResult = outboundDao.addTranSportList(tranSportMap);
				if (TranSportResult < 1) {
					info.setCode("error");
					// 保存失败
					info.setMessage(PropertyUtil.getProperties("M0008"));
					return info;
				}
			}
		}
		//新增对应的道闸数据
        Roadgate roadgate = new Roadgate();
        roadgate.setOutWarehouseId(data.getId());
        roadgate.setCarNumber(data.getPlateNumber());
        if(userInfo != null ){
        	roadgate.setCreater(String.valueOf(userInfo.getId()));
		}else {
			roadgate.setCreater(map.get("userId").toString());	
		}
		result = roadgateDao.addRoadgate(roadgate);
		// 关联空发出库单
		if (map.get("outboundList") != null && !"".equals(map.get("outboundList").toString())) {
			map.put("serialID", map.get("serialID").toString());
			int outResult = this.insertEmptyOutBound(request, map);
			if (outResult < 1) {
				info.setCode("error");
				// 保存失败
				info.setMessage(PropertyUtil.getProperties("M0008"));
				return info;
			}
		}
		
		if (result > 0) {
			// 调拨/兑换调拨时，新建一条未称重出库单信息
			if (map.get("isDiaobo") != null && Integer.valueOf(map.get("isDiaobo").toString()) == 0) {
				// 关联空发出库单
				if (map.get("outboundList") != null && !"".equals(map.get("outboundList").toString())) {
					int outResult = this.insertEmptyOutBound(request, map);
					if (outResult < 1) {
						info.setCode("error");
						// 保存失败
						info.setMessage(PropertyUtil.getProperties("M0008"));
						return info;
					}
				}
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
		param.put("orderDetailedId", Integer.parseInt(map.get("orderDetailedId").toString()));
		param.put("type", "show");
		List<OrderNumberEntity> dataList = outboundDao.getOrderNumberList(param);
		int outType = map.get("outType") == null ? 0 : Integer.valueOf(map.get("outType").toString());
//		if (dataList != null && outType != 3) {
		// 结算数量
		data.setAmount(dataList.get(0).getSaleNumber());
//		}
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
//		data.setCarOwner(map.get("carOwner").toString());
		// 车主电话
//		data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		// 备注
		data.setRemarks(map.get("remarks").toString());
		// 采购合同
		if(Integer.parseInt(map.get("outTypeMark").toString()) == 1) {
			// data.setPurchaseContractId(Integer.parseInt(map.get("purchaseContractId").toString()));
			data.setSuttle(Double.valueOf(map.get("suttle").toString()));
		}
		// 收货地址
		data.setConsigneeAddress(map.get("consigneeAddress").toString());
		// 是否检测
		data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		// 销售公司名称
		data.setSaleCompanyName(map.get("saleCompanyName").toString());
			// 毛重
		if (map.get("GrossWeight") != null) {
			data.setGrossWeight(Double.valueOf(map.get("GrossWeight").toString()));
		}
//		data.setGrossWeight(Double.valueOf(map.get("GrossWeight") == null ? "0" : map.get("GrossWeight").toString()));
		// 皮重
		if (map.get("TareWeight") != null) {
			data.setTareWeight(Double.valueOf(map.get("TareWeight").toString()));
		} 
//		data.setTareWeight(Double.valueOf(map.get("TareWeight") == null ? "0" : map.get("TareWeight").toString()));
		// 净重
		if (map.get("suttle") != null && !"".equals(map.get("suttle").toString())) {
			data.setSuttle(Double.valueOf(map.get("suttle").toString()));
		} 
//		if (map.get("suttle") == null || map.get("suttle").toString().isEmpty()) {
//			data.setSuttle(Double.valueOf("0"));
//		}else {
//			data.setSuttle(Double.valueOf(map.get("suttle").toString()));			
//		}
		if (map.get("factoryTime") != null) {
			data.setFactoryTime(map.get("factoryTime").toString().equals("") ? null : map.get("factoryTime").toString());
		}
		
		// 出库单
		if(Integer.parseInt(map.get("outType").toString()) == 0) {
			// 起运地
			data.setStartAddress(map.get("startAddress").toString());
		}
		// 封签号
		data.setFacingSlipNum(map.get("facingSlipNum") == null ? "" : map.get("facingSlipNum").toString());
		// 更新人
		if (userInfo != null) {
			data.setReviser(String.valueOf(userInfo.getId()));
			map.put("userId", String.valueOf(userInfo.getId()));
		}else {
			data.setReviser(map.get("userId").toString());
		}
		// 销售订单详情Id
		data.setOrderDetailedId(Integer.valueOf(map.get("orderDetailedId").toString()));
		
		// 关联空发出库单
		int outResult = this.updateEmptyOutBound(request, map);
		if (outResult < 1) {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
			return info;
		}
		
		// 是否调拨
		if (map.get("isDiaobo") != null && Integer.valueOf(map.get("isDiaobo").toString()) == 0) {
			if (Integer.valueOf(map.get("outType").toString()) == 0) {
				// 调拨
				data.setOutType(1);
			} else if (Integer.valueOf(map.get("outType").toString()) == 4) {
				// 兑换调拨
				data.setOutType(5);
			} else {
				data.setOutType(Integer.valueOf(map.get("outType").toString()));
			}
		} else {
			data.setOutType(Integer.valueOf(map.get("outType").toString()));
		}
		int result = outboundDao.updateExportMeasure(data);
		
		if (result > 0) {
			//自动修改运输单
			Map<String,Object> tranSportMap = new HashMap<>();
			Map<String,Object> materielinfoMap = new HashMap<>();
			tranSportMap.put("dispatchOutWarehouseId", Integer.parseInt(map.get("id").toString()));
			tranSportMap.put("plateNumber", map.get("plateNumber"));
			tranSportMap.put("endAddress", map.get("consigneeAddress"));
			tranSportMap.put("isMention", map.get("transportBalance"));
			tranSportMap.put("carOwner",map.get("deliveryMan"));
			tranSportMap.put("carOwnerTelephone", map.get("deliveryManPhone"));
			tranSportMap.put("type", data.getOutType());
			/*tranSportMap.put("creater", map.get("userId"));*/
			//tranSportMap.put("weigh", map.get("suttle"));
			materielinfoMap.put("productID", map.get("productID"));
			List<MaterielinfoEntity> materielinfoEntity =  outboundDao.getMaterielinfoList(materielinfoMap);
			if(materielinfoEntity.size()>0) {
				tranSportMap.put("materielId", materielinfoEntity.get(0).getMaterielName());
				tranSportMap.put("model", materielinfoEntity.get(0).getMaterielModel());
			}
			int TranSportResult = outboundDao.updateTranSportList(tranSportMap);
			if (TranSportResult < 1) {
				info.setCode("error");
				// 保存失败
				info.setMessage("运输单关联失败");
				return info;
			}
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

			if (map.get("isDiaobo") != null && Integer.valueOf(map.get("isDiaobo").toString()) == 0) {
				map.put("id", data.getId());
				// 查询是否已经有关联的空发单。如果有则修改，没有则新建
				int noweightResult = 0;
				int count = outboundDao.queryNoWeight(map);
				if (count == 0) {
					noweightResult = addNoWeighOut(map, userInfo);
				} else {
					noweightResult = updateNoWeighOutWarehouse(request, map);
				}
				if (noweightResult > 0) {
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
				}else {
					info.setCode("error");
					// 保存失败
					info.setMessage(PropertyUtil.getProperties("M0008"));
				}
			} else {
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
		data.setSerialID(outboundEntity.getSerial_ID());
		 // 采购合同  
		data.setPurchaseContractId(outboundEntity.getPurchaseContractId());
		
		// 获取供应商id
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", outboundEntity.getPurchaseContractId());
		List<Purchasecontract> list = outboundDao.getPurchasecontractList(param);
		if(list != null && list.size() > 0) {
			 data.setSupplierId(list.get(0).getSupplierId());
		}
		// 数量
		data.setAmount(outboundEntity.getSuttle());
		// 净重
		data.setSuttle(outboundEntity.getSuttle());
		// 实际净重
		data.setSupplierSuttle(outboundEntity.getSuttle());
		 // 优先级   
		data.setPriority(0);
		 // 称重
		data.setCartWeighType(outboundEntity.getCartWeighType());
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
		return outboundDao.getPurchasecontractList(map);
	}

	/**
	 * 获取无法选中的车辆信息
	 * @param type 出库（out）/入库（in） 
	 * @return
	 */
	@Override
	public List<Map<String,Object>> queryCarInUse() {
		return outboundDao.queryCarInUse();
	}

	@Override
	public List<Map<String, Object>> getOrderDetail(Map<String, Object> map) {
		return outboundDao.getOrderDetail(map);
	}

	@Override
	public List<Map<String, Object>> getEmptyOutboundInfo(Map<String, Object> map) {
		return outboundDao.getEmptyOutboundInfo(map);
	}

	@Override
	public int insertEmptyOutBound(HttpServletRequest request, Map<String, Object> map) {
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		Map<String, Object> newMap = new HashMap<String, Object>();
		// 空发单id
		newMap.put("emptyCarId", map.get("serialID").toString());
		// 出库单id
		newMap.put("outWarehouseId", map.get("outboundList").toString());
		// 当前用户
		if (userInfo != null) {
			newMap.put("creater", String.valueOf(userInfo.getId()));
		}else {
			newMap.put("creater", map.get("userId").toString());
		}
		return outboundDao.insertEmptyOutBound(newMap);
	}

	/**
	 * 先查询当前空发单是否关联了出库单
	 * 一、关联：
	 * 1.未做修改时，不作处理
	 * 2.修改为其他出库单时，先删除之前的记录，在新增一条记录
	 * 3.修改为空时，删除之前的记录
	 * 二、未关联：
	 * 1.未做修改时，不作处理
	 * 2.修改为其他出库单时，新增一条记录
	 */
	@Override
	public int updateEmptyOutBound(HttpServletRequest request, Map<String, Object> map) {
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		int result = 0;
		if (map.get("outboundList") == null) {
			return 1;
		}
		// 选中的出库单
		String outboundList = map.get("outboundList").toString();
		Map<String, Object> newMap = new HashMap<String, Object>();
		// 空发单id
		newMap.put("emptyCarId", map.get("serialID").toString());
		newMap.put("serialID", map.get("serialID").toString());
		// 出库单id
		newMap.put("outboundList", outboundList);
		newMap.put("userId", map.get("userId").toString());
		// 修改人
		if (userInfo != null) {
			newMap.put("reviser", userInfo.getId());
		}else {
			newMap.put("reviser", map.get("userId").toString());
		}
		
		// 查询当前空发单关联的出库单
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("serialID", map.get("serialID").toString());
		map2.put("flag", "N");
		List<Map<String, Object>> emptyList = outboundDao.queryEmptyList(map2);
		if (emptyList.size() > 0) {
			// 有关联的出库单时
			// 先判断是否更改
			for (int i = 0 ; i < emptyList.size(); i++) {
				if (outboundList.equals(emptyList.get(i).get("OutWarehouseId").toString())) {
					// 有当前出库单记录，说明未做更改，不做操作
					return 1;
				} else {
					newMap.put("outWarehouseId", emptyList.get(i).get("OutWarehouseId").toString());
					if ("".equals(outboundList)) {
						// 当未选择出库单时，删除之前的记录
						result += outboundDao.deleteEmptyOutBound(newMap);
					} else {
						// 更改为其它出库单时，先删除之前的记录，再新增一条记录
						if (outboundDao.deleteEmptyOutBound(newMap) > 0) {
							result +=  this.insertEmptyOutBound(request, newMap);
						} else {
							return 0;
						}
					}
				}
			}
		} else {
			// 没有关联的出库单时
			if ("".equals(outboundList)) {
				// 未选择出库单时则不作处理
				return 1;
			} else {
				// 选择其他出库单时，直接新增一条记录
				result += this.insertEmptyOutBound(request, newMap);
			}
		}
		return result;
	}
	
	 // 新增未称重出库单
	public int addNoWeighOut(Map<String, Object> map, Userinfo userInfo) {
		try {
			NoWeighOutWarehouse data = new NoWeighOutWarehouse();
			// 流水号 
			data.setSerialID(map.get("wczSerialID").toString());
			// 销售订单id 
			data.setSalesOrderId(Integer.parseInt(map.get("diaoOrderNumber").toString()));
			// 销售订单明细id 
			data.setOrderDetailedNumber(Integer.parseInt(map.get("diaoOrderDetailedId").toString()));
			
			if(map.get("allotWeight") != null && !("").equals(map.get("allotWeight").toString())) {
				// 调拨重量
				data.setAllotWeight(Double.valueOf(map.get("allotWeight").toString()));
			}
			if(map.get("saleNumber") != null) {
				if(!("null").equals(map.get("saleNumber").toString()) && !("").equals(map.get("saleNumber").toString())) {
					// 销售数量
					data.setSaleNumber(Double.valueOf(map.get("saleNumber").toString()));
				}	
			}
			if(map.get("salePrice") != null){
				if(!("null").equals(map.get("salePrice").toString()) && !("").equals(map.get("salePrice").toString())) {
					// 销售单价
					data.setSalePrice(Double.valueOf(map.get("salePrice").toString()));
				}	
			}
			if(map.get("saleMoney") != null) {
				if(!("null").equals(map.get("saleMoney").toString()) && !("").equals(map.get("saleMoney").toString())) {
					// 销售金额 
					data.setSaleMoney(Double.valueOf(map.get("saleMoney").toString()));
				}
			}
			
			// 客户名称
			data.setClient(map.get("diaoClient").toString());
			// 客户别称 
			data.setCustomerAlias(map.get("diaoCustomerAlias").toString());
			if(map.get("taxRate") != null) {
				if(!("null").equals(map.get("taxRate").toString()) && !("").equals(map.get("taxRate").toString()) ) {
					// 税率 
					data.setTaxRate(map.get("taxRate").toString());
				}	
			}
			data.setType("1");
			//送货地址
			data.setAddress(map.get("address").toString());
			//收货地址
			data.setConsigneeAddress(map.get("diaoAddress").toString());
			//收货人  
			data.setConsignee(map.get("diaoConsignee").toString());
			//收货人电话 
			data.setConsigneePhone(map.get("diaoConsigneePhone").toString());
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
//			data.setCarOwner(map.get("carOwner").toString());
			// 车主电话             
//			data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
			// 备注         
			data.setRemarks(map.get("diaoRemarks").toString());
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
			if(result>0) {
				//自动生成运输单
				Map<String,Object> tranSportMap = new HashMap<>();
				Map<String,Object> materielinfoMap = new HashMap<>();
				int res = 0;
				Prefix prefix = serialNumberDao.getContractInfoPrefix("YS");
				if (prefix.getFlowNumber()!= null & !"".equals(prefix.getFlowNumber())) {
					int newEquipment = Integer.parseInt(prefix.getFlowNumber()) + 1;
					prefix.setFlowNumber(String.format("%0"+prefix.getFlowNumber().length()+"d",newEquipment));
				}
				res += serialNumberDao.updateSerialNumber(prefix);
				if(res<0) {
					return -1;
				}
				Prefix pix = this.getContractInfoPrefix("YS");
				tranSportMap.put("dispatchOutWarehouseId", data.getId());
				tranSportMap.put("serialID", map.get("wczSerialID"));
				//int rok = serialNumberDao.updateSerialNumber(prefix);
				tranSportMap.put("billNumber",pix.getPrefixs()); //////////////////////////做到这里
				tranSportMap.put("plateNumber", map.get("plateNumber"));
				tranSportMap.put("endAddress", map.get("diaoAddress"));
				tranSportMap.put("carOwner",map.get("deliveryMan"));
				tranSportMap.put("carOwnerTelephone", map.get("deliveryManPhone"));
				tranSportMap.put("Weigh", map.get("allotWeight"));
				/*tranSportMap.put("creater", map.get("userId"));*/
				//tranSportMap.put("weigh", map.get("suttle"));
				materielinfoMap.put("productID", map.get("productID"));
				List<MaterielinfoEntity> materielinfoEntity =  outboundDao.getMaterielinfoList(materielinfoMap);
				if(materielinfoEntity.size()>0) {
					tranSportMap.put("materielId", materielinfoEntity.get(0).getMaterielNameId());
					tranSportMap.put("model", materielinfoEntity.get(0).getMaterielModelId());
				}
				tranSportMap.put("type", 1);
				int TranSportResult = outboundDao.addTranSportList(tranSportMap);
				return TranSportResult;
			}else {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateFacingSlipNum(HttpServletRequest request, Map<String, Object> map) {
		return outlistDao.updateFacingSlipNum(map);
	}
	
	public int updateNoWeighOutWarehouse(HttpServletRequest request, Map<String, Object> map) {
		try {
			ResponseInfo info = new ResponseInfo();
			// 获取当前登录用户的信息
			Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
			
			//新增未称重出库单
			NoWeighOutWarehouse data = new NoWeighOutWarehouse();
			// 未称重出库单id
			data.setId(Integer.parseInt(map.get("noWeightId").toString()));
			//销售订单编号
			data.setSalesOrderId(Integer.parseInt(map.get("diaoOrderNumber").toString()));
			// 销售订单明细id 
			data.setOrderDetailedNumber(Integer.parseInt(map.get("diaoOrderDetailedId").toString()));
			//选择出库单
			data.setDispatchOutWarehouseId(Integer.parseInt(map.get("id").toString()));
			//车主
//			data.setCarOwner(map.get("carOwner").toString());
			//车牌号码
			data.setCarName(map.get("plateNumber").toString());
			//车主电话
//			data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
			// 调拨重量
			data.setAllotWeight(Double.valueOf(map.get("allotWeight") == null ? "0" : map.get("allotWeight").toString()));
			//销售数量
			data.setSaleNumber(map.get("SaleNumber") == "" ? 0 : Double.parseDouble(map.get("SaleNumber").toString()));
			//客户名称
			data.setClient(map.get("diaoClient").toString());
			//客户别称
			data.setCustomerAlias(map.get("diaoCustomerAlias").toString());
			//收货人
			data.setConsignee(map.get("diaoConsignee").toString());
			//联系电话
			data.setConsigneePhone(map.get("diaoConsigneePhone").toString());
			//送货地址
			data.setAddress(map.get("address").toString());
			//收货地址
			data.setConsigneeAddress(map.get("diaoAddress").toString());
			//物料名称/型号
			data.setMaterielInfoId(Integer.parseInt(map.get("productID").toString()));
			//结算情况
			data.setTransportBalance(map.get("transportBalance").toString());
			//送货人
			data.setDeliverer(map.get("deliveryMan").toString());
			//送货电话
			data.setDeliveryPhone(map.get("deliveryManPhone").toString());
			//备注
			data.setRemarks(map.get("diaoRemarks").toString());
			// 更新人
			if (userInfo != null) {
				data.setReviser(String.valueOf(userInfo.getId()));
			}else {
				data.setReviser(map.get("userId").toString());
			}
			return repertoryDao.updateNoWeighOutWarehouse(data);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> getAllOutboundList() {
		return outboundDao.getAllOutboundList();
	}

	@Override
	public List<Map<String, Object>> getTransportList(Map<String, Object> map) {
		return outboundDao.getTransportList(map);
	}

	@Override
	public List<Map<String, Object>> getOrderDetailInfo(Map<String, Object> map) {
		return outboundDao.getOrderDetailInfo(map);
	}

	@Override
	public List<Map<String, Object>> getCustomerTrans(Map<String, Object> map) {
		return outboundDao.getCustomerTrans(map);
	}

	@Override
	public List<Map<String, Object>> getExportmeasures() {
		return  outboundDao.getExportmeasures();
	}

	@Override
	public List<Map<String, Object>> getSalesOrderLists(Map<String, Object> map) {
		return  outboundDao.getSalesOrderLists(map);
	}

	@Override
	public List<Map<String, Object>> checkTransList(Map<String, Object> map) {
		return outboundDao.checkTransList(map);
	}
	
	/**
	 * @since 删除关联的数据
	 * @param outboundEntity
	 * @return
	 */
	public int deleteRelationInfo(OutboundEntity outboundEntity) {
		int result2 = 0;
		if (outboundEntity.getOutType() == 2) {
			// 退货时，删除对应的来料加工
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("outWarehouseId", outboundEntity.getId());
			result2 = outboundDao.deleteStoragemeasure(param);
		} else if (outboundEntity.getOutType() == 1 || outboundEntity.getOutType() == 5) {
			// 调拨时，删除对应的未称重出库单
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("outWarehouseId", outboundEntity.getId());
			result2 = outboundDao.deleteNoWeight(param);
		} else {
			return 1;
		}
		return result2;
	}
	
	/**
	 * @since 删除关联的空发出库单
	 * @param outboundEntity
	 * @return
	 */
	public int deleteEmptyOutList(OutboundEntity outboundEntity) {
		int result = 0;
		// 查询是否有关联的空发数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serialID", outboundEntity.getSerialId());
		map.put("flag", "N");
		// 空发出库单
		List<Map<String, Object>> queryEmptyListN = outboundDao.queryEmptyList(map);
		map.put("flag", "Y");
		// 非空发出库单
		List<Map<String, Object>> queryEmptyListY = outboundDao.queryEmptyList(map);
		if (queryEmptyListN.size() == 0 && queryEmptyListY.size() == 0) {
			return 1;
		} else {
			if (queryEmptyListN.size() > 0) {
				// 删除空发出库单时，删除关联表中的数据
				result = outboundDao.deleteEmptyListN(outboundEntity.getSerialId());
			} else if (queryEmptyListY.size() > 0) {
				// 删除非空发出库单时，删除关联表中的数据
				result = outboundDao.deleteEmptyListY(outboundEntity.getSerialId());
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getDiaoOrderNumber(Map<String, Object> map) {
		return outboundDao.getDiaoOrderNumber(map);
	}

	@Override
	public List<Map<String, Object>> getDiaoMaterielId(Map<String, Object> map) {
		return outboundDao.getDiaoMaterielId(map);
	}
	
	@Override
	public List<Map<String, Object>> getDiaoOrderDetail(Map<String, Object> map) {
		return outboundDao.getDiaoOrderDetail(map);
	}

	@Override
	public List<Map<String, Object>> getConsigneeInfo(Map<String, Object> map) {
		return outboundDao.getConsigneeInfo(map);
	}

	@Override
	public List<Map<String, Object>> getOutBoundWeight(Map<String, Object> map) {
		return outboundDao.getOutBoundWeight(map);
	}

	@Override
	public ResponseInfo updateOutBoundWeight(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		// 若重量被前台清空，则赋null
		if (map.get("grossWeight") == "" || Integer.valueOf(map.get("grossWeight").toString()) == 0) {
			map.put("grossWeight", null);
		}
		if (map.get("sullte") == "" || Integer.valueOf(map.get("sullte").toString()) == 0) {
			map.put("sullte", null);
		}
		if (map.get("tareWeight") == "" || Integer.valueOf(map.get("tareWeight").toString()) == 0) {
			map.put("tareWeight", null);
		}
		if (map.get("amount") == "" || Integer.valueOf(map.get("amount").toString()) == 0) {
			map.put("amount", null);
		}
		if (map.get("factoryTime") == "") {
			map.put("factoryTime", null);
		}
		if (map.get("enterDate") == "") {
			map.put("enterDate", null);
		}
		try {
			if (outboundDao.updateOutBoundWeight(map) > 0) {
				info.setCode("success");
				// 用户或密码错误!
					info.setMessage(PropertyUtil.getProperties("M0005"));
			} else {
				info.setCode("error");
				// 用户或密码错误!
				info.setMessage(PropertyUtil.getProperties("M0006"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public int updateExamine(Map<String, Object> map) {
		
		return outboundDao.updateExamine(map);
	}
	
	public Prefix getContractInfoPrefix(String types) {
		Prefix prefix = serialNumberDao.getContractInfoPrefix(types);
		String prefixs = "";
		Date now = new Date(); 
		if (prefix.getAllPrefix() != null && !"".equals(prefix.getAllPrefix())) {
			prefixs += prefix.getAllPrefix();
		}
		if(!"1".equals(prefix.getYear())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			String dateString = formatter.format(now);
			prefixs += dateString;
		}
		if(!"1".equals(prefix.getMonth())) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM");
			prefixs += formatter.format(now);
		}
		if(!"1".equals(prefix.getDay())) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd");
			prefixs += formatter.format(now);
		}
		if(!"1".equals(prefix.getHour())) {
			SimpleDateFormat formatter = new SimpleDateFormat("HH");
			prefixs += formatter.format(now);
		}
		if(!"1".equals(prefix.getBranch())) {
			SimpleDateFormat formatter = new SimpleDateFormat("mm");
			prefixs += formatter.format(now);
		}
		if(!"1".equals(prefix.getSecond())) {
			SimpleDateFormat formatter = new SimpleDateFormat("ss");
			prefixs += formatter.format(now);
		}
		if(prefix.getFlowNumber() != null && !"".equals(prefix.getFlowNumber())) {
			prefixs += prefix.getFlowNumber();
		}
		prefix.setPrefixs(prefixs);
		return prefix;
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "出库信息";
		//表头
		String[] head0 = new String[] { "编号", "流水号", "日期", "客户名称", "产品名称", "规格型号","车牌号", "皮重/吨", "毛重/吨",
				"净重/吨", "收货重量", "涨亏吨","收货人","电话","备注"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		 List<Map<String, Object>> dataList = outboundDao.getInfoListEX(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
			 if(dataList.get(i).get("outTypeName") != null && dataList.get(i).get("outTypeName").toString().equals("空发")) {
				 dataList.get(i).put("remarks", "系统维护检查");
			 }
		}
		 String[] colName = new String[] { "row", "serialId", "createrDate", "customerName", "materielName", "materielModel", "plateNumber", "tareWeight", "grossWeight",
				 "allotWeight", "collectWeigh", "riseLoss", "consignee", "consigneePhone","sRemarks"};
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,数值对应列名称,创建时间,合计开始列
			ReportMergeXls.reportMergeXls(request, response, dataList, sheetName, head0, colName, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
