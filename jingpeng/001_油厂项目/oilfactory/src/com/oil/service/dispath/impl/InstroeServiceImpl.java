package com.oil.service.dispath.impl;

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
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.dispath.ContractEntity;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.SalesContractEntity;
import com.oil.model.system.Purchasecontract;
import com.oil.service.dispath.InstroeService;
import com.oil.util.PropertyUtil;

@Service
public class InstroeServiceImpl implements InstroeService{
	@Autowired
	InstroeDao instroeDao;
	
	@Autowired
	OutboundDao outboundDao;

	// 根据用户获取对应的销售合同编号
	@Override
	public List<SalesContractEntity> getSalesContractList(Map<String, Object> map) {
		List<SalesContractEntity> dataList = instroeDao.getSalesContractList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<SalesContractEntity>();
		}
		return dataList;
	}

	// 根据用户获取对应的销售合同编号
	@Override
	public List<Map<String, Object>> getStoragemeasure() {
		return instroeDao.getStoragemeasure();
	}
	
	// 根据用户获取对应的销售合同编号
	@Override
	public List<Map<String, Object>> getSalesContractLists(Map<String, Object> map) {
		return instroeDao.getSalesContractLists(map);
	}
	// 获取调度单已有车牌号
	@Override
	public List<String> getPlateNumberList(Map<String, Object> map) {
		List<String> dataList = instroeDao.getPlateNumberList(map);
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<String>();
		}
		return dataList;
	}

	// 获取画面显示的list信息
	@Override
	public DataTablesResponseInfo getInfoList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<InstroeEntity> dataList = instroeDao.getInfoList(map);

		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<InstroeEntity>();
		}
		info.setData(dataList);
		// TODO Auto-generated method stub
		return info;
	}

	// 入库单作废或者删除
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

		int result = instroeDao.updateValidFlag(map);
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

	// 新增入库单
	@Override
	public ResponseInfo addImportMeasure(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		
		// 入库单信息
		InstroeEntity data = new InstroeEntity();
		// 流水号
		data.setSerialID(map.get("serialID").toString());
		// 入库单
		if(("0").equals(map.get("enterTypeMark").toString())) {
			
			 // 采购合同  
			data.setPurchaseContractId(Integer.parseInt(map.get("purchaseContractId").toString()));
			
			// 获取供应商id
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", Integer.parseInt(map.get("purchaseContractId").toString()));
			List<Purchasecontract> list = outboundDao.getPurchasecontractList(param);
			if(list != null && list.size() > 0) {
				 data.setSupplierId(list.get(0).getSupplierId());
			}
			
			 // 数量    
			data.setAmount(Double.valueOf(map.get("amount").toString()));
			 // 优先级   
			data.setPriority(Integer.parseInt(map.get("priority").toString()));
		}
		
		// 来料加工
		if(("2").equals(map.get("enterTypeMark").toString())) {
			// 销售合同id
			data.setContractId(Integer.parseInt(map.get("contractId").toString()));
			// 客户名称
			data.setClient(map.get("client").toString());
			// 客户别称
			data.setCustomerAlias(map.get("customerAlias").toString());
		}
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
		data.setEnterTypeMark(Integer.parseInt(map.get("enterTypeMark").toString()));
		 // 是否大车称重
		data.setCartWeighType(map.get("cartWeighType").toString());
		 // 有效标识  
		data.setValidFlag(1);
		if (map.get("startAddress") != null && !"".equals(map.get("startAddress").toString())) {
			// 起运地
			data.setStartAddressId(Integer.valueOf(map.get("startAddress").toString()));
			// 允许偏差
			data.setSupplierDeviation(Double.valueOf(map.get("deviation").toString()));
		}
		 // 创建人 
		if (userInfo != null) {
			data.setCreater(String.valueOf(userInfo.getId()));
		}else {
			data.setCreater(map.get("userId").toString());
		}
		
		int result = instroeDao.addImportMeasure(data);
		if (result > 0) {
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

	// 更新出库单
	@Override
	public ResponseInfo updateImportMeasure(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		
		// 入库单信息
		InstroeEntity data = new InstroeEntity();
		
		// 主键id
		data.setId(Integer.parseInt(map.get("id").toString()));
		
		// 来料加工
		if(("2").equals(map.get("enterTypeMark").toString())) {
			// 客户别称
			data.setCustomerAlias(map.get("customerAlias").toString());
		}
		 
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
		// 入库单
		if(("0").equals(map.get("enterTypeMark").toString())) {
			
			data.setPurchaseContractId(Integer.parseInt(map.get("purchaseContractId").toString()));
			// 数量    
			data.setAmount(Double.valueOf(map.get("amount").toString()));
			 // 优先级   
			data.setPriority(Integer.parseInt(map.get("priority").toString()));
		}
		 
		if (map.get("startAddress") != null && !"".equals(map.get("startAddress").toString())) {
			// 起运地
			data.setStartAddressId(Integer.valueOf(map.get("startAddress").toString()));
			// 允许偏差
			data.setSupplierDeviation(Double.valueOf(map.get("deviation").toString()));
		}
		 // 是否大车称重
		data.setCartWeighType(map.get("cartWeighType").toString());
		 // 修改人 
		if (userInfo != null) {
			data.setReviser(String.valueOf(userInfo.getId()));
		}else {
			data.setReviser(map.get("userId").toString());
		}
		
		int result = instroeDao.updateImportMeasure(data);
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

	// 根据id获取入库单信息
	@Override
	public InstroeEntity getInstroeInfo(Map<String, Object> map) {
		// 获取信息
		List<InstroeEntity> dataList = instroeDao.getInfoList(map);
		if (dataList != null && dataList.size() > 0) {
			InstroeEntity instroeEntity = dataList.get(0);
			return instroeEntity;
		} else {
			return null;
		}
	}

	// 获取销售合同编号
	@Override
	public List<ContractEntity> getContractList(Map<String, Object> map) {
		// 获取信息
		List<ContractEntity> dataList = instroeDao.getContractList(map);
		if (dataList != null && dataList.size() > 0) {
			return dataList;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> queryCarInUse() {
		return instroeDao.queryCarInUse();
	}

	@Override
	public List<Map<String, Object>> queryBillNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return instroeDao.queryBillNumber(map);
	}

	@Override
	public List<Map<String, Object>> getInBoundWeight(Map<String, Object> map) {
		return instroeDao.getInBoundWeight(map);
	}

	@Override
	public ResponseInfo updateInBoundWeight(Map<String, Object> map) {
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
		if (map.get("factoryTime") == "") {
			map.put("factoryTime", null);
		}
		try {
			if (instroeDao.updateInBoundWeight(map) > 0) {
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
		
		return instroeDao.updateExamine(map);
	}
}
