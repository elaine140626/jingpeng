package com.oil.service.repertory.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.dispath.OutboundDao;
import com.oil.dao.repertory.RepertoryDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionaty;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.model.sales.Customerinfo;
import com.oil.model.system.CarInfo;
import com.oil.service.repertory.RepertoryService;
import com.oil.util.PropertyUtil;
@Service
@Transactional
public class RepertoryServiceImpl implements RepertoryService {
	
	@Autowired
	private RepertoryDao repertoryDao;

	@Autowired
	OutboundDao outboundDao;
	
	@Override
	public List<SalesOrderEntity> getSalesOrderList(Map<String, Object> map) {
		List<SalesOrderEntity> dataList = repertoryDao.getSalesOrderList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<SalesOrderEntity>();
		}
		return dataList;
	}

	@Override
	public DataTablesResponseInfo getInfoList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<NoWeighOutWarehouse> dataList = repertoryDao.getInfoList(map);

		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<NoWeighOutWarehouse>();
		}
		info.setData(dataList);
		return info;
	}

	@Override
	public List<OrderNumberEntity> getOrderNumberList(Map<String, Object> map) {
		List<OrderNumberEntity> dataList = repertoryDao.getOrderNumberList(map);
		return dataList;
	}

	@Override
	public List<MaterielinfoEntity> getMaterielinfoList(Map<String, Object> map) {
		List<MaterielinfoEntity> dataList = repertoryDao.getMaterielinfoList(map);
		return dataList;
	}

	@Override
	public List<Map<String,Object>> getAllPlateNumbers(Map<String, Object> map) {
		return repertoryDao.getAllPlateNumbers(map);
	}

	@Override
	public List<Map<String,Object>> getAllCustomerName(Map<String, Object> map) {
		return repertoryDao.getAllCustomerName(map);
	}

	@Override
	public List<Map<String,Object>> getAllOutbounds(Map<String, Object> map) {
		return repertoryDao.getAllOutbounds(map);
	}

	@Override
	public ResponseInfo addNoWeighOutWarehouse(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		
		//新增未称重出库单
		NoWeighOutWarehouse data = new NoWeighOutWarehouse();
		// 流水号
		data.setSerialID(map.get("serialID").toString());
		//销售订单编号
		data.setSalesOrderId(Integer.parseInt(map.get("orderNumber").toString()));
		//是否随车带走
		data.setIsCarryOff(Integer.parseInt(map.get("isCarryOff").toString()));
		//选择出库单
		data.setDispatchOutWarehouseId(Integer.parseInt(map.get("dispatchOutWarehouseId").toString()));
		//车主
		//data.setCarOwner(map.get("carOwner").toString());
		//车牌号码
		data.setCarName(map.get("plateNumber").toString());
		//车主电话
		//data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		//是否客户自提
		if(Integer.parseInt(map.get("isCarryOff").toString()) == 1) {
			data.setIsSelfLifting(Integer.parseInt(map.get("isSelfLifting").toString()));
			if(Integer.parseInt(map.get("isSelfLifting").toString()) == 1) {
				//它发货方式
				data.setOtherDelivery(map.get("otherDelivery").toString());
				//车辆信息
				//data.setCustomerCarName(map.get("customerCarName").toString());
			}
		}else {
			data.setIsSelfLifting(1);
			//车辆信息
			//data.setCustomerCarName(map.get("customerCarName").toString());
		}
		//销售数量
		data.setSaleNumber(Double.parseDouble(map.get("saleNumber").toString()));
		//客户名称
		data.setClient(map.get("customername").toString());
		//客户别称
		data.setCustomerAlias(map.get("customerAlias").toString());
		//销售单价
		data.setSalePrice(Double.parseDouble(map.get("salePrice").toString()));
		//销售金额
		data.setSaleMoney(Double.parseDouble(map.get("saleMoney").toString()));
		//收货人
		data.setConsignee(map.get("consignee").toString());
		//联系电话
		data.setConsigneePhone(map.get("consigneePhone").toString());
		//收货地址
		data.setConsigneeAddress(map.get("consigneeAddress").toString());
		//物料名称/型号
		data.setMaterielInfoId(Integer.parseInt(map.get("materielInfoId").toString()));
		//结算情况
		data.setTransportBalance(map.get("transportBalance").toString());
		//税率
		data.setTaxRate(map.get("taxRate").toString());
		//送货人
		data.setDeliverer(map.get("deliverer").toString());
		//送货电话
		data.setDeliveryPhone(map.get("deliveryPhone").toString());
		//是否需要检测
		data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		//备注
		data.setRemarks(map.get("remarks").toString());
		// 创建人
		if (userInfo != null) {
			data.setCreater(userInfo.getUsername());
		}else {
			data.setCreater(map.get("username").toString());
		}
		//邮单编号
		data.setPostNumber(map.get("postNumber").toString());
		//其他备注
		data.setOtherRemarks(map.get("otherRemarks").toString());
		//销售订单明细编号
		data.setOrderDetailedNumber(Integer.parseInt(map.get("orderDetailedId").toString()));
		int result = repertoryDao.addNoWeighOutWarehouse(data);
		if (result > 0) {
			//自动生成运输单
/*			Map<String,Object> tranSportMap = new HashMap<>();
			Map<String,Object> materielinfoMap = new HashMap<>();
			tranSportMap.put("dispatchOutWarehouseId", data.getId());
			tranSportMap.put("serialID", data.getSerialID());
			tranSportMap.put("billNumber",map.get("YSserialID").toString());
			tranSportMap.put("plateNumber", map.get("plateNumber"));
			tranSportMap.put("endAddress", map.get("consigneeAddress"));
			tranSportMap.put("carOwner",map.get("deliverer"));
			tranSportMap.put("carOwnerTelephone", map.get("deliveryPhone"));
			tranSportMap.put("creater", map.get("userId"));
			//tranSportMap.put("weigh", map.get("suttle"));
			materielinfoMap.put("productID", map.get("materielInfoId"));
			List<MaterielinfoEntity> materielinfoEntity =  outboundDao.getMaterielinfoList(materielinfoMap);
			if(materielinfoEntity.size()>0) {
				tranSportMap.put("materielId", materielinfoEntity.get(0).getMaterielNameId());
				tranSportMap.put("model", materielinfoEntity.get(0).getMaterielModelId());
			}
			tranSportMap.put("type", 1);
			int TranSportResult = outboundDao.addTranSportList(tranSportMap);
			if (TranSportResult < 1) {
				info.setCode("error");
				// 保存失败
				info.setMessage(PropertyUtil.getProperties("M0008"));
				return info;
			}*/
			info.setCode("success");
			// 保存成功
			info.setMessage(PropertyUtil.getProperties("M0007"));
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
		}
		return info;
	}

	@Override
	public NoWeighOutWarehouse getOutboundInfo(Map<String, Object> map) {
		// 获取信息
		List<NoWeighOutWarehouse> dataList = repertoryDao.getInfoList(map);
		if (dataList != null && dataList.size() > 0) {
			NoWeighOutWarehouse noWeighOutWarehouse = dataList.get(0);
			return noWeighOutWarehouse;
		} else {
			return null;
		}
	}

	@Override
	public ResponseInfo updateValidFlag(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		if (userInfo != null) {
			map.put("userId", userInfo.getId());	
		}else {
			map.put("userId", map.get("userId"));
		}
		int result = repertoryDao.updateValidFlag(map);
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

	@Override
	public ResponseInfo updateNoWeighOutWarehouse(HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		
		//新增未称重出库单
		NoWeighOutWarehouse data = new NoWeighOutWarehouse();
		// 未称重出库单id
		data.setId(Integer.parseInt(map.get("id").toString()));
		//销售订单编号
		data.setSalesOrderId(Integer.parseInt(map.get("orderNumber").toString()));
		//是否随车带走
		data.setIsCarryOff(Integer.parseInt(map.get("isCarryOff").toString()));
		//选择出库单
		data.setDispatchOutWarehouseId(Integer.parseInt(map.get("dispatchOutWarehouseId").toString()));
		//车主
		//data.setCarOwner(map.get("carOwner").toString());
		//车牌号码
		data.setCarName(map.get("plateNumber").toString());
		//车主电话
		//data.setCarOwnerTelephone(map.get("carOwnerTelephone").toString());
		//是否客户自提
		if(Integer.parseInt(map.get("isCarryOff").toString()) == 1) {
			data.setIsSelfLifting(Integer.parseInt(map.get("isSelfLifting").toString()));
			if(Integer.parseInt(map.get("isSelfLifting").toString()) == 1) {
				//它发货方式
				data.setOtherDelivery(map.get("otherDelivery").toString());
			}else {
				//车辆信息
				//data.setCustomerCarName(map.get("customerCarName").toString());
			}
		}else {
			data.setIsSelfLifting(1);
			//车辆信息
			//data.setCustomerCarName(map.get("customerCarName").toString());
		}
		//销售数量
		data.setSaleNumber(Double.parseDouble(map.get("saleNumber").toString()));
		//客户名称
		data.setClient(map.get("customername").toString());
		//客户别称
		data.setCustomerAlias(map.get("customerAlias").toString());
		//销售单价
		data.setSalePrice(Double.parseDouble(map.get("salePrice").toString()));
		//销售金额
		data.setSaleMoney(Double.parseDouble(map.get("saleMoney").toString()));
		//收货人
		data.setConsignee(map.get("consignee").toString());
		//联系电话
		data.setConsigneePhone(map.get("consigneePhone").toString());
		//收货地址
		data.setConsigneeAddress(map.get("consigneeAddress").toString());
		//物料名称/型号
		data.setMaterielInfoId(Integer.parseInt(map.get("materielInfoId").toString()));
		//结算情况
		data.setTransportBalance(map.get("transportBalance").toString());
		//税率
		data.setTaxRate(map.get("taxRate").toString());
		//送货人
		data.setDeliverer(map.get("deliverer").toString());
		//送货电话
		data.setDeliveryPhone(map.get("deliveryPhone").toString());
		//是否需要检测
		data.setIsTesting(Integer.parseInt(map.get("isTesting").toString()));
		//备注
		data.setRemarks(map.get("remarks").toString());
		// 更新人
		if (userInfo != null) {
			data.setReviser(userInfo.getUsername());
		}else {
			data.setReviser(map.get("username").toString());
		}
		//邮单编号
		data.setPostNumber(map.get("postNumber").toString());
		//其他备注
		data.setOtherRemarks(map.get("otherRemarks").toString());
		//销售订单明细编号
		data.setOrderDetailedNumber(Integer.parseInt(map.get("orderDetailedId").toString()));
		int result = repertoryDao.updateNoWeighOutWarehouse(data);
		System.out.println("++++"+result);
		if (result > 0) {
			info.setCode("success");
			// 操作成功
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			info.setCode("error");
			// 操作失败
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}

	@Override
	public List<Datadictionaty> getAllDatadictionaty() {
		return repertoryDao.getAllDatadictionaty();
	}

	@Override
	public List<Map<String, Object>> getOutboundEntitys(Map<String, Object> map) {
		return repertoryDao.getOutboundEntitys(map);
	}

	@Override
	public List<SalesOrderEntity> getAppSalesOrderList() {
		List<SalesOrderEntity> dataList = repertoryDao.getAppSalesOrderList();
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<SalesOrderEntity>();
		}
		return dataList;
	}

	@Override
	public List<SalesOrderEntity> getAppTreeCustomerName() {
		List<SalesOrderEntity> dataList = repertoryDao.getAppTreeCustomerName();
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<SalesOrderEntity>();
		}
		return dataList;
	}

	@Override
	public List<NoWeighOutWarehouse> getAppNoWeighList(Map<String, Object> map) {
		List<NoWeighOutWarehouse> dataList = repertoryDao.getAppNoWeighList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<NoWeighOutWarehouse>();
		}
		return dataList;
	}

	@Override
	public List<Map<String, Object>> getOrderDetailInfo(Map<String, Object> map) {
		return repertoryDao.getOrderDetailInfo(map);
	}

	@Override
	public List<Map<String, Object>> getAllCarInfo(Map<String, Object> map) {
		return repertoryDao.getAllCarInfo(map);
	}

	@Override
	public List<Map<String, Object>> getDiaoBoNoWeighList(Map<String, Object> map) {
		return repertoryDao.getDiaoBoNoWeighList(map);
	}

	@Override
	public DataTablesResponseInfo getOutBoundInfoList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String, Object>> dataList = repertoryDao.getOutBoundInfoList(map);
		// 查询关联的空发出库单
		for (int i = 0; i < dataList.size(); i++) {
			List<Map<String, Object>> list = outboundDao.queryEmptyOutBoundById(dataList.get(i).get("serialId").toString());
			if (list != null && list.size() > 0) {
				StringBuilder emptyIds = new StringBuilder();
				for (int j = 0; j < list.size(); j++) {
					emptyIds.append(list.get(j).get("EmptyCarId")).append(",");
				}
				dataList.get(i).put("isEmpty","Y");
				dataList.get(i).put("emptyList",emptyIds.toString().substring(0, emptyIds.length()-1));
			} else {
				dataList.get(i).put("isEmpty","N");
			}
			
		}

		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		info.setData(dataList);
		return info;
	}

	@Override
	public DataTablesResponseInfo getInstoreInfoList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String, Object>> dataList = repertoryDao.getInstoreInfoList(map);

		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		info.setData(dataList);
		return info;
	}

	@Override
	public DataTablesResponseInfo getUnBoundInfoList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String, Object>> dataList = repertoryDao.getUnBoundInfoList(map);

		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		info.setData(dataList);
		return info;
	}

	@Override
	public int updateExamine(Map<String, Object> map) {
		
		return repertoryDao.updateExamine(map);
	}

	@Override
	public List<Map<String, Object>> getCarNameList(Map<String, Object> map) {
		return repertoryDao.getCarNameList(map);
	}

	@Override
	public List<Map<String, Object>> getMaterialNameSearcList(Map<String, Object> map) {
		return repertoryDao.getMaterialNameSearcList(map);
	}

}
