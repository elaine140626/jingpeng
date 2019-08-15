package com.oil.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.oil.dao.purchase.PurchaseDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.service.purchase.PurchaseService;


@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDao purchaseDao;
	
	@Override
	public DataTablesResponseInfo getSupplier() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(purchaseDao.getSupplier());
		return info;
	}

	@Override
	public DataTablesResponseInfo getPurchaserequisition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(purchaseDao.getPurchaserequisition(map));
		return info;
	}

	@Override
	public DataTablesResponseInfo getMaterielInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(purchaseDao.getMaterielInfo(map));
		return info;
	}

	@Override
	public ResponseInfo addPurchasecontract(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		int upResult = 0;
		int staratCount = 0;
		String flag =  map.get("flag").toString();
		String purchaseorderinfoMapString = map.get("purchaseorderinfoMap").toString();
		Map<String,Object> purchaseorder= JSON.parseObject(purchaseorderinfoMapString);
		Map <String,Object> updateApplySignMap = new HashMap<>();
		if(!purchaseorder.get("contractState").toString().equals("3")) {
			purchaseorder.put("cancellationCause", "");
		}
		if(!flag.equals("1")) {
			result = purchaseDao.addPurchasecontract(purchaseorder);
		}else {
			result = purchaseDao.updatePurchasecontractById(purchaseorder);
		}
		if(result>0) {
			if(map.get("fileName")!=null && map.get("fileName") != "") {
				Map<String,Object> upLoadMap = new HashMap<>();
				upLoadMap.put("serialID",map.get("contractNumber"));
				upLoadMap.put("fileName",map.get("fileName"));
				upLoadMap.put("fileRoute",map.get("uploadAddress"));
				upResult = purchaseDao.adduploadfile(upLoadMap);
				if(!(upResult>0)) {
					info.setMessage("操作失败002");
					info.setCode("error");
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return info;
				}
			}
			String purchaseorderinfoListMap = map.get("purchaseorderinfoListMap").toString();
			List<Object> list =JSON.parseArray(purchaseorderinfoListMap);
			for (Object object : list){
				@SuppressWarnings("unchecked")
				Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
				ret.put("purchaseOrderId", purchaseorder.get("Id"));
				updateApplySignMap.put("id", Integer.parseInt(ret.get("purchaseRequisitionId").toString()));
				if(!flag.equals("1")) {
					info = this.addPurchaseorderinfo(ret);
					if(info.getCode().equals("success")) {
						updateApplySignMap.put("applySign",1);
						info = this.updatePurchaserequisitionIsApplySign(updateApplySignMap);
					}
				}else {
					Map<String,Object> orderinfoId = new HashMap<>();
					orderinfoId.put("id", purchaseorder.get("Id"));
					//麻烦死了
					List<Map<String,Object>> PurchaserequisitionByPurchasecontractIdList = purchaseDao.getPurchaserequisitionByPurchasecontractId(orderinfoId);
					if(staratCount == 0) {
						for (int i = 0; i < PurchaserequisitionByPurchasecontractIdList.size(); i++) {
							PurchaserequisitionByPurchasecontractIdList.get(i).put("applySign", 0);
							result = purchaseDao.updatePurchaserequisitionIsApplySign(PurchaserequisitionByPurchasecontractIdList.get(i));
							if(result<0) {
								info.setMessage("操作失败");
								info.setCode("error");
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return info;
							}
						}
					}
					List<Map <String,Object>> PurchaseorderinfoList = purchaseDao.getPurchaseorderinfoById(orderinfoId);
					boolean isHave = false;
					for (int i = 0; i < PurchaseorderinfoList.size(); i++) {
						if(PurchaseorderinfoList.get(i).get("serialID").toString().equals(ret.get("serialID").toString())) {
							isHave = true;
							break;
						}else {
							isHave = false;
						}
					}
					if(isHave) {
						info = this.updatePurchaseorderinfoById(ret);
						if(info.getCode().equals("success")) {
							updateApplySignMap.put("applySign",1);
							info = this.updatePurchaserequisitionIsApplySign(updateApplySignMap);
						}
					}else {
						info = this.addPurchaseorderinfo(ret);
						if(info.getCode().equals("success")) {
							updateApplySignMap.put("applySign",1);
							info = this.updatePurchaserequisitionIsApplySign(updateApplySignMap);
						}
					}
					staratCount += 1;
				}
				if(info.getCode().equals("error")) {
					info.setMessage("操作失败003");
					info.setCode("error");
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return info;
				}
			}
		}else {
			info.setMessage("操作失败001");
			info.setCode("error");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return info;
	}

	@Override
	public ResponseInfo addPurchaseorderinfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = purchaseDao.addPurchaseorderinfo(map);
		System.out.println(map.get("Id"));
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public DataTablesResponseInfo getPurchasecontractList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		if (map.get("contractState")!=null) {
			String ContractState = map.get("contractState").toString();
			String[] param = {};
			if(ContractState != null && !("").equals(ContractState)) {
				param = ContractState.split(",");
				map.put("contractState", param);
			}
		}
		List<Map<String,Object>> contractList = purchaseDao.getPurchasecontractList(map);
		for (int i = 0; i < contractList.size(); i++) {
			contractList.get(i).put("rowCount", i+1);
		}
		info.setData(contractList);
		return info;
	}

	@Override
	public DataTablesResponseInfo getPurchasecontractById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		Map<String,Object> purchasecontract = purchaseDao.getPurchasecontractById(map);
		List<Map<String,Object>> getPurchaseorderinfoByIdList = purchaseDao .getPurchaseorderinfoById(map);
		Map<String,Object> purchasecontractInfo = new HashMap<>();
		purchasecontractInfo.put("purchasecontract", purchasecontract);
		purchasecontractInfo.put("getPurchaseorderinfoByIdList", getPurchaseorderinfoByIdList);
		info.setData(purchasecontractInfo);
		return info;
	}

	@Override
	public ResponseInfo updatePurchaseorderinfoById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = purchaseDao.updatePurchaseorderinfoById(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public ResponseInfo delPurchasecontractById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = purchaseDao.delPurchasecontractById(map);
			if(result > 0) {
				List<Map<String,Object>> PurchaserequisitionList = purchaseDao.getPurchaserequisitionByPurchasecontractId(map);
				for (int i = 0; i < PurchaserequisitionList.size(); i++) {
					PurchaserequisitionList.get(i).put("applySign", 0);
					result = purchaseDao.updatePurchaserequisitionIsApplySign(PurchaserequisitionList.get(i));
					if(result<0) {
						info.setMessage("操作失败");
						info.setCode("error");
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						break;
					}
				}
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public ResponseInfo delPurchaseorderinfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = purchaseDao.delPurchaseorderinfo(map);
			if(result > 0) {
				map.put("applySign", 0);
				result = purchaseDao.updatePurchaserequisitionIsApplySign(map);
					if(result > 0) {
						info.setMessage("操作成功");
						info.setCode("success");
					}else {
						info.setMessage("操作失败");
						info.setCode("error");
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					}
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public ResponseInfo ZuoFeiPurchasecontract(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = purchaseDao.ZuoFeiPurchasecontract(map);
		if(result > 0) {
			List<Map<String,Object>> PurchaserequisitionList = purchaseDao.getPurchaserequisitionByPurchasecontractId(map);
			for (int i = 0; i < PurchaserequisitionList.size(); i++) {
				PurchaserequisitionList.get(i).put("applySign", 0);
				result = purchaseDao.updatePurchaserequisitionIsApplySign(PurchaserequisitionList.get(i));
				if(result<0) {
					info.setMessage("操作失败");
					info.setCode("error");
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					break;
				}
			}
				info.setMessage("操作成功");
				info.setCode("success");
			}else {
				info.setMessage("操作失败");
				info.setCode("error");
		}
		return info;
	}

	@Override
	public ResponseInfo getExAndstor(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		List<Map<String,Object>> ExAndstorList = purchaseDao.getExAndstor(map);
		if(ExAndstorList.size()>0) {
			info.setMessage("已被调用");
			info.setCode("error");
		}else {
			info.setMessage("操作成功");
			info.setCode("success");
		}
		return info;
	}

	@Override
	public ResponseInfo updatePurchaserequisitionIsApplySign(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = purchaseDao.updatePurchaserequisitionIsApplySign(map);
		if(result > 0) {
				info.setMessage("操作成功");
				info.setCode("success");
			}else {
				info.setMessage("操作失败");
				info.setCode("error");
		}
		return info;
	}

}
