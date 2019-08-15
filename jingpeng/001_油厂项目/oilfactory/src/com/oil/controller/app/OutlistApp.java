package com.oil.controller.app;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.InstroeEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.sales.CustomerInfoEntity;
import com.oil.model.sales.NoweighEntity;
import com.oil.service.sales.OutlistService;



@Controller
@RequestMapping("/outlistApp")
public class OutlistApp {
	
	@Autowired
	OutlistService outlistService;
	
	// 销售管理：出库单查询名称下拉
    @RequestMapping(value = { "/getOutlistExportmeasures.json" })
    public ResponseEntity<Map<String, Object>> getOutlistExportmeasures(){
    	List<Map<String,Object>> customerInfoEntityList=outlistService.getOutlistExportmeasures();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("customerInfoEntityList", customerInfoEntityList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 销售管理：出库单查询树形结构合同下拉
//    @RequestMapping(value = { "/getOutlistContractinfo.json" })
//    public ResponseEntity<Map<String, Object>> getOutlistContractinfo(@RequestParam(value="id")String id){
//    	HashMap<String, Object> map = new HashMap<String, Object>();
//    	map.put("id",id);
//    	List<Map<String,Object>> customerInfoEntityList=outlistService.getOutlistContractinfo(map);
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("customerInfoEntityList", customerInfoEntityList);
//        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
//    }
//    
    // 销售管理：出库单树形结构销售订单下拉
//    @RequestMapping(value = { "/getOutlistSalesorder.json" })
//    public ResponseEntity<Map<String, Object>> getOutlistSalesorder(@RequestParam(value="contractId")String contractId){
//    	
//    	HashMap<String, Object> map = new HashMap<String, Object>();
//    	map.put("contractId",contractId);
//    	List<Map<String,Object>> customerInfoEntityList=outlistService.getOutlistSalesorder(map);
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("customerInfoEntityList", customerInfoEntityList);
//        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
//    }
//    
    // 销售管理：调度出库单车辆检下拉
//    @RequestMapping(value = { "/getPlateNumberList.json" })
//    public ResponseEntity<Map<String, Object>> getPlateNumberList(){
//    	Map<String, Object> param = new HashMap<String, Object>();
//    	List<String> plateNumberList= outlistService.getPlateNumberList(param);
//    	param.put("plateNumberList", plateNumberList);
//        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
//    }
    
    // 销售管理：出库单查询按客户名称和订单编号搜索
    @RequestMapping(value = { "/searchByNameOrderNumber.json" })
    public ResponseEntity<Map<String, Object>> searchByNameOrderNumber(@RequestParam(value="client")String client,@RequestParam(value="plateNumbers")String plateNumbers){
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("client", client);
//    	map.put("orderNumber", orderNumber);
//    	map.put("contractId", contractId);
    	map.put("plateNumber", plateNumbers);
    	List<OutboundEntity> outboundEntityList= outlistService.getSalesList(map);
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("outboundEntityList", outboundEntityList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 销售管理：出库单查询按客户名称和订单编号搜索
//    @RequestMapping(value = { "/searchFuzzyQuery.json" })
//    public ResponseEntity<Map<String, Object>> searchFuzzyQuery(@RequestParam(value="plateNumber")String plateNumber){
//    	HashMap<String, Object> map = new HashMap<String, Object>();
//    	map.put("plateNumber", plateNumber);
//    	List<OutboundEntity> outboundEntityList = outlistService.getSalesList(map);
//    	Map<String, Object> param = new HashMap<String, Object>();
//    	param.put("outboundEntityList", outboundEntityList);
//        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
//    }
    
    // 销售管理：出库单查询列表
    @RequestMapping(value = { "/getSalesList.json" })
    public ResponseEntity<Map<String, Object>> getSalesList(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("type", "true");
    	List<OutboundEntity> outboundEntityList = outlistService.getSalesList(map);
        map.put("outboundEntityList", outboundEntityList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 销售管理：出库单查询根据id查询
    @RequestMapping(value = { "/getSalesListById.json" })
    public ResponseEntity<Map<String, Object>> getSalesListById(@RequestParam(value="id")String id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	map.put("type", "true");
    	List<OutboundEntity> outboundEntityList = outlistService.getSalesList(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("outboundEntityList", outboundEntityList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 销售管理：出库单查询未称重出库单
    @RequestMapping(value = { "/getNoweighoutList.json" })
    public ResponseEntity<Map<String, Object>> getNoweighoutList(@RequestParam(value="id")String id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	map.put("type", "true");
    	List<NoweighEntity> noweighEntityList = outlistService.getNoweighoutList(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("noweighEntityList", noweighEntityList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 销售管理：出库单查询来料加工
    @RequestMapping(value = { "/getProcessList.json" })
    public ResponseEntity<Map<String, Object>> getProcessList(@RequestParam(value="id")String id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	map.put("type", "true");
    	List<InstroeEntity> instroeEntityList = outlistService.getProcessList(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("instroeEntityList", instroeEntityList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调拨
 	@RequestMapping("/haltCar.json")
 	public ResponseEntity<Map<String, Object>> updateHaltCar(HttpServletRequest request, @RequestParam(value="serialID")String serialID,@RequestParam(value="outboundEntity")String outboundEntity, @RequestParam(value="userId")String userId) throws IOException {
 		HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(outboundEntity,HashMap.class);
    	param.put("userId",userId);
    	param.put("serialID",serialID);
    	param.put("id",maplist.get(0).get("id"));
    	param.put("salesOrderId",maplist.get(1).get("orderNumber"));
    	param.put("carName",maplist.get(2).get("carName"));
    	param.put("orderDetailedId",maplist.get(3).get("orderDetailedId"));
    	param.put("contractNumber",maplist.get(4).get("contractNumber"));
    	param.put("materielName",maplist.get(5).get("materielName"));
    	param.put("materielModel",maplist.get(6).get("materielModel"));
    	param.put("customerName",maplist.get(7).get("customerName"));
    	param.put("customerAlias",maplist.get(8).get("customerAlias"));
    	param.put("deliveryMan",maplist.get(9).get("deliveryMan"));
    	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
    	param.put("suttle",maplist.get(11).get("suttle"));
    	param.put("allotWeight",maplist.get(12).get("allotWeight"));
    	param.put("allotClient",maplist.get(13).get("allotClient"));
    	param.put("consignee",maplist.get(14).get("consignee"));
    	param.put("consigneePhone",maplist.get(15).get("consigneePhone"));
    	param.put("consigneeAddress",maplist.get(16).get("consigneeAddress"));
    	param.put("transportBalance",maplist.get(17).get("transportBalance"));
    	param.put("remarks",maplist.get(18).get("remarks"));
    	param.put("saleMoney",maplist.get(19).get("saleMoney"));
    	param.put("salePrice",maplist.get(20).get("salePrice"));
    	param.put("saleNumber",maplist.get(21).get("saleNumber"));
    	param.put("taxRate",maplist.get(22).get("taxRate"));
    	param.put("address",maplist.get(23).get("address"));
    	param.put("plateNumber",maplist.get(2).get("carName"));
    	param.put("materielInfoId",maplist.get(24).get("productID"));
    	param.put("allotCustomerAlias",maplist.get(25).get("customer"));
    	param.put("outType",maplist.get(26).get("outType"));
 		outlistService.updateHaltCar(request, param);
 		return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
 	}
    
    // 退货
  	@RequestMapping("/refund.json")
  	public ResponseEntity<Map<String, Object>> updateRefund(HttpServletRequest request,@RequestParam(value="serialID")String serialID, @RequestParam(value="outboundEntity")String outboundEntity,@RequestParam(value="userId")String userId) throws IOException {
  		HashMap<String, Object> param = new HashMap<String, Object>();
     	List<HashMap> maplist = JSON.parseArray(outboundEntity,HashMap.class);
     	param.put("userId",userId);
     	param.put("serialID",serialID);
     	param.put("cartWeighType","");
     	param.put("id",maplist.get(0).get("id"));
    	param.put("materielName",maplist.get(1).get("materielName"));
    	param.put("materielModel",maplist.get(2).get("materielModel"));
    	param.put("plateNumber",maplist.get(3).get("plateNumber"));
    	param.put("client",maplist.get(4).get("client"));
    	param.put("customerAlias",maplist.get(5).get("customerAlias"));
    	param.put("suttle",maplist.get(6).get("suttle"));
    	param.put("amount",maplist.get(7).get("amount"));
    	param.put("refundAmount",maplist.get(8).get("refundAmount"));
    	param.put("deliveryMan",maplist.get(9).get("deliveryMan"));
    	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
    	param.put("remarks",maplist.get(11).get("remarks"));
    	param.put("productID",maplist.get(12).get("productID"));
    	param.put("contractId",maplist.get(13).get("contractId"));
    	param.put("orderDetailedId",maplist.get(14).get("orderDetailedId"));
  		outlistService.updateRefund(request, param);
  		return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
  	}
  	
    // 销售管理：出库单查询空发数据
    @RequestMapping(value = { "/getInfoList.json" })
    public ResponseEntity<Map<String, Object>> getInfoList(@RequestParam(value="outWarehouseId")String outWarehouseId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("outWarehouseId", outWarehouseId);
    	List<OutboundEntity> outboundEntity = outlistService.getInfoList(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("outboundEntity", outboundEntity);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
}

