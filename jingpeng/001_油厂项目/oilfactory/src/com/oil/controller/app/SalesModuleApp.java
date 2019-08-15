package com.oil.controller.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.oil.dao.sales.SalesOrdersDao;
import com.oil.model.contract.ContractDetailedEntity;
import com.oil.model.contract.ContractIncomingDetailedEntity;
import com.oil.model.contract.ContractInfoEntity;
import com.oil.model.contract.ContractTreeEntity;
import com.oil.model.sales.SalesOrdersInfoEntity;
import com.oil.service.contract.SalesContractManagementService;
import com.oil.service.system.CustomerTransportsService;

@Controller
@RequestMapping("/salesModuleApp")
public class SalesModuleApp {

	@Autowired
	SalesOrdersDao salesOrdersDao;
	@Autowired
	SalesContractManagementService salesContractManagementService;
	@Autowired
	CustomerTransportsService customerTransportsService;
	
	// 查询销售订单列表或按id查找
    @RequestMapping(value = { "/getSalesModuleList.json" })
    public ResponseEntity<Map<String, Object>> getSalesModuleList(@RequestParam(value="id")String id,@RequestParam(value="treeIdName") String treeIdName, @RequestParam(value="startTime") String startTime,@RequestParam(value="endTime") String endTime){
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("id", id);
    	param.put("treeIdName", treeIdName);
    	param.put("startTime", startTime);
    	param.put("endTime", endTime);
    	List<SalesOrdersInfoEntity> salesOrdersInfoEntity = salesOrdersDao.getSalesOrdersInfo(param);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("salesOrdersInfoEntity",salesOrdersInfoEntity);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 新增销售订单
 	@RequestMapping("/addSalesOrders.json")
 	public ResponseEntity<Map<String, Object>> addSalesOrders(@RequestParam(value="salesOrdersInfoEntity")String salesOrdersInfoEntity, 
 			@RequestParam(value="userId")String userId) throws IOException {
 		HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(salesOrdersInfoEntity,HashMap.class);
    	param.put("userId",userId);
    	param.put("orderNumber",maplist.get(0).get("orderNumber"));
    	param.put("contractId",maplist.get(1).get("contractId"));
    	param.put("customerId",maplist.get(2).get("customerId"));
    	param.put("customerAlias",maplist.get(3).get("customerAlias"));
    	param.put("orderState",maplist.get(4).get("orderState"));
    	param.put("address",maplist.get(5).get("address"));
    	param.put("contacts",maplist.get(6).get("contacts"));
    	param.put("telephone",maplist.get(7).get("telephone"));
    	param.put("salesCompanyId",maplist.get(8).get("salesCompanyId"));
    	param.put("userInfoId",maplist.get(9).get("userInfoId"));
    	param.put("remarks",maplist.get(10).get("remarks"));
    	salesOrdersDao.addSalesOrders(param);
 		return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
 	}
 	
 	
    // 新增销售订单明细
 	@RequestMapping("/addSalesOrdersDetailed.json")
 	public ResponseEntity<Map<String, Object>> addSalesOrdersDetailed(@RequestParam(value="salesOrdersDetailedList")String salesOrdersDetailedList, 
 			@RequestParam(value="userId")String userId) throws IOException {
 		HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(salesOrdersDetailedList,HashMap.class);
    	param.put("userId",userId);
    	param.put("salesOrderId",maplist.get(0).get("salesOrderId"));
    	param.put("orderDetailedNumber",maplist.get(0).get("orderDetailedNumber"));
    	param.put("materielName",maplist.get(0).get("materielName"));
    	param.put("materielModel",maplist.get(0).get("materielModel"));
    	param.put("unitPrice",maplist.get(0).get("unitPrice"));
    	param.put("listModel",maplist.get(0).get("listModel"));
    	param.put("isActualDelivery",maplist.get(0).get("isActualDelivery"));
    	param.put("transportBalance",maplist.get(0).get("transportBalance"));
    	param.put("expectedDeliveryDate",maplist.get(0).get("expectedDeliveryDate"));
    	param.put("transports",maplist.get(0).get("transports"));
    	param.put("distance",maplist.get(0).get("distance"));
    	param.put("saleNumber",maplist.get(0).get("saleNumber"));
    	param.put("salePrice",maplist.get(0).get("salePrice"));
    	param.put("taxRate",maplist.get(0).get("taxRate"));
    	param.put("saleMoney",maplist.get(0).get("saleMoney"));
    	param.put("isExchange",maplist.get(0).get("isExchange"));
    	param.put("materielNameDh",maplist.get(0).get("materielNameDh"));
    	param.put("materielModelDh",maplist.get(0).get("materielModelDh"));
    	param.put("proportion",maplist.get(0).get("proportion"));
    	param.put("exchangeWeight",maplist.get(0).get("exchangeWeight"));
    	param.put("isCancel",maplist.get(0).get("isCancel"));
    	param.put("cancelPerson",maplist.get(0).get("cancelPerson"));
    	param.put("remarks",maplist.get(0).get("remarks"));
    	salesOrdersDao.addSalesOrdersDetailed(param);
 		return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
 	}
 	 // 获取合同下拉数据
 	@RequestMapping("/getContractInfo.json")
 	public ResponseEntity<Map<String, Object>> getContractInfo() throws IOException {
 		HashMap<String, Object> param = new HashMap<String, Object>();
 		String ContractState = "0,1,4,5";
		String[] contractState = {};
		if(ContractState != null && !("").equals(ContractState)) {
			contractState = ContractState.split(",");
			param.put("ContractState", contractState);
		}
 		List<ContractInfoEntity> contractInfo = salesContractManagementService.getContractInfo(param);
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("contractInfo", contractInfo);
 		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
 	}
 	
 	 // 获取树形结构需要的数据
 	@RequestMapping("/getContractTree.json")
 	public ResponseEntity<Map<String, Object>> getContractTree() throws IOException {
 		HashMap<String, Object> param = new HashMap<String, Object>();
 		List<ContractTreeEntity> contractTree = salesOrdersDao.getContractTree(param);
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("contractTree", contractTree);
 		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
 	}
 	
 	 // 按名称查询
 	@RequestMapping("/getSalesName.json")
 	public ResponseEntity<Map<String, Object>> getSalesName() throws IOException {
 		List<Map<String, Object>> salesName = salesContractManagementService.getSalesName();
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("salesName", salesName);
 		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
 	}
 	
 	 // 按合同id查询
 	@RequestMapping("/getContractInfoById.json")
 	public ResponseEntity<Map<String, Object>> getContractInfoById(@RequestParam(value="id")String id) throws IOException {
 		HashMap<String, Object> param = new HashMap<String, Object>();
 		param.put("id", id);
 		List<ContractInfoEntity> ContractInfoList = salesContractManagementService.getContractInfo(param);
		// 合同明细表获取
		List<ContractDetailedEntity> ContractDetailedList = salesContractManagementService.getContractDetailed(param);
		// 合同来料加工明细
		List<ContractIncomingDetailedEntity> ContractIncomingDetailedList = salesContractManagementService.getContractIncomingDetailed(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ContractInfoList", ContractInfoList);
		map.put("ContractDetailedList", ContractDetailedList);
		map.put("ContractIncomingDetailedList", ContractIncomingDetailedList);
 		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
 	}
 	
 	 // 物料查询
 	@RequestMapping("/getMaterielInfo.json")
 	public ResponseEntity<Map<String, Object>> getMaterielInfo(HttpServletRequest request, @RequestParam Map<String,Object> mapModel) throws IOException {
 		List<Map<String, Object>> materielInfo = salesContractManagementService.getMaterielInfo(mapModel);
 		List<Map<String, Object>> rawMaterielInfo = new ArrayList<Map<String, Object>>(); 
 		List<Map<String, Object>> finishedMaterielInfo = new ArrayList<Map<String, Object>>();
 		for (int i = 0; i < materielInfo.size(); i++) {
 			Map<String, Object> param = new HashMap<String, Object>();
			int MaterielType = Integer.parseInt(materielInfo.get(i).get("MaterielType").toString());
			if(MaterielType == 1) {
				param.put("MaterielNameId", materielInfo.get(i).get("MaterielNameId"));
				param.put("MaterielName", materielInfo.get(i).get("MaterielName"));
				rawMaterielInfo.add(param);
			}else if(MaterielType == 2) {
				param.put("MaterielNameId", materielInfo.get(i).get("MaterielNameId"));
				param.put("MaterielName", materielInfo.get(i).get("MaterielName"));
				finishedMaterielInfo.add(param);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materielInfo",materielInfo);
		map.put("rawMaterielInfo",rawMaterielInfo);
		map.put("finishedMaterielInfo",finishedMaterielInfo);
 		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
 	}
 	
	 // 运输信息取得
	@RequestMapping("/getTrchangeInfo.json")
	public ResponseEntity<Map<String, Object>> getTrchangeInfo(@RequestParam(value="custId")String custId) throws IOException {
		List<Map<String,Object>> trchangeInfoList = customerTransportsService.getTrchangeInfo(Integer.parseInt(custId));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trchangeInfoList",trchangeInfoList);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
}
