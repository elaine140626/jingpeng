package com.oil.controller.app;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.SalesOrderEntity;
import com.oil.service.dispath.OutboundService;

@Controller
@RequestMapping("/outboundApp")
public class OutboundApp {
	
	@Autowired
	OutboundService outboundService;
	
    // 调度管理：调度出库单树形结构名称下拉
    @RequestMapping(value = { "/getExportmeasures.json" })
    public ResponseEntity<Map<String, Object>> getExportmeasures(){
    	List<Map<String, Object>> salesOrderEntityList=outboundService.getExportmeasures();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salesOrderEntityList", salesOrderEntityList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
	
    // 调度管理：调度出库单树形结构销售订单下拉
//    @RequestMapping(value = { "/getSalesOrderLists.json" })
//    public ResponseEntity<Map<String, Object>> getSalesOrderLists(@RequestParam(value="contractId")String contractId){
//    	Map<String, Object> param = new HashMap<String, Object>();
//    	param.put("contractId", contractId);
//    	List<Map<String, Object>> salesOrderEntityList=outboundService.getSalesOrderLists(param);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("salesOrderEntityList", salesOrderEntityList);
//        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
//    }
    
    // 调度管理：调度出库单车辆检下拉
    @RequestMapping(value = { "/getPlateNumberList.json" })
    public ResponseEntity<Map<String, Object>> getPlateNumberList(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<String> plateNumberList= outboundService.getPlateNumberList(map);
        map.put("plateNumberList", plateNumberList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 调度管理：调度出库单按客户名称和订单编号搜索
//    @RequestMapping(value = { "/searchByNameOrderNumber.json" })
//    public ResponseEntity<Map<String, Object>> searchByNameOrderNumber(@RequestParam(value="client")String client,@RequestParam(value="orderNumber")String orderNumber){
//    	HashMap<String, Object> param = new HashMap<String, Object>();
//    	param.put("client", client);
//    	param.put("orderNumber", orderNumber);
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	DataTablesResponseInfo outboundEntityList= outboundService.getInfoList(param);
//        map.put("outboundEntityList", outboundEntityList);
//        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
//    }
    
    // 调度管理：调度出库单按车牌号和时间搜索
    @RequestMapping(value = { "/searchByPlateNumberTime.json" })
    public ResponseEntity<Map<String, Object>> searchByPlateNumberTime(@RequestParam(value="client")String client,@RequestParam(value="plateNumbers")String plateNumbers,@RequestParam(value="startTime")String startTime,@RequestParam(value="endTime")String endTime){
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("plateNumbers", plateNumbers);
    	param.put("client", client);
    	param.put("startTime", startTime);
    	param.put("endTime", endTime);
    	Map<String, Object> map = new HashMap<String, Object>();
    	DataTablesResponseInfo outboundEntityList= outboundService.getInfoList(param);
        map.put("outboundEntityList", outboundEntityList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 调度管理：调度出库单列表
    @RequestMapping(value = { "/getInfoList.json" })
    public ResponseEntity<Map<String, Object>> getInfoList(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	DataTablesResponseInfo outboundEntityList= outboundService.getInfoList(map);
        map.put("outboundEntityList", outboundEntityList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 调度管理：根据id查询调度出库单
    @RequestMapping(value = { "/getInfoListById.json" })
    public ResponseEntity<Map<String, Object>> getInfoListById(@RequestParam(value="id")String id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	DataTablesResponseInfo outboundEntityList= outboundService.getInfoList(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("outboundEntityList", outboundEntityList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：新增调度出库单，flag=1：兑换新增 ，flag=2：空发新增，flag=3：新增出库单， flag=4：新增未入场出库单
    @RequestMapping(value = { "/addExportMeasure.json" })
    public ResponseEntity<Map<String, Object>> addExportMeasure(HttpServletRequest request,@RequestParam(value="outType")String outType,@RequestParam(value="flag")String flag,@RequestParam(value="productID")String productID,
    		@RequestParam(value="userId")String userId,@RequestParam(value="outboundEntity")String outboundEntity) throws IOException{
//    	exportMeasure = "[{\"id\":\"80\"},{\"parms\":\"123\"}]";
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(outboundEntity,HashMap.class);
    	param.put("userId",userId);
    	param.put("productID",productID);
    	param.put("outType",outType);
//    	param.put("isEnterFactory",1);
    	param.put("isTesting",1);
    	if (flag.equals("1") || flag.equals("3")) {
    		param.put("outTypeMark", 0);
        	param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("orderNumber", maplist.get(1).get("orderNumber"));
        	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
        	param.put("lbsMaterialName",maplist.get(3).get("lbsMaterialName"));
        	param.put("materielName",maplist.get(4).get("materielName"));
        	param.put("materielModel",maplist.get(5).get("materielModel"));
        	param.put("plateNumber",maplist.get(6).get("plateNumber"));
        	param.put("client",maplist.get(7).get("client"));
        	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
        	param.put("consigneeAddress",maplist.get(9).get("transports"));
        	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
        	param.put("saleCompanyName",maplist.get(11).get("saleCompanyName"));
        	param.put("cartWeighType",maplist.get(12).get("cartWeighType"));
        	param.put("distance",maplist.get(13).get("distance"));
        	param.put("customerAlias",maplist.get(14).get("customerAlias"));
        	param.put("transportBalance",maplist.get(15).get("transportBalance"));
        	param.put("consignee",maplist.get(16).get("consignee"));
        	param.put("consigneePhone",maplist.get(17).get("consigneePhone"));
        	param.put("address",maplist.get(18).get("address"));
        	param.put("remarks",maplist.get(19).get("remarks"));
        	param.put("isDiaobo",maplist.get(20).get("isDiaobo"));
        	param.put("diaoOrderNumber",maplist.get(21).get("diaoOrderNumber"));
        	param.put("allotWeight",maplist.get(22).get("allotWeight"));
        	param.put("diaoOrderDetailedId",maplist.get(23).get("diaoOrderDetailedId"));
        	param.put("diaoContractNumber",maplist.get(24).get("diaoContractNumber"));
        	param.put("diaoConsignee",maplist.get(25).get("diaoConsignee"));
        	param.put("diaoClient",maplist.get(26).get("diaoClient"));
        	param.put("diaoConsigneePhone",maplist.get(27).get("diaoConsigneePhone"));
        	param.put("diaoCustomerAlias",maplist.get(28).get("diaoCustomerAlias"));
        	param.put("diaoAddress",maplist.get(29).get("diaoAddress"));
        	param.put("diaoTransportBalance",maplist.get(30).get("diaoTransportBalance"));
        	param.put("diaoRemarks",maplist.get(31).get("diaoRemarks"));
        	param.put("wczSerialID", maplist.get(32).get("wczSerialID"));
        	param.put("facingSlipNum", maplist.get(33).get("facingSlipNum"));
		}else if(flag.equals("2")) {
			param.put("outTypeMark", 0);
			param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("orderNumber", maplist.get(1).get("orderNumber"));
        	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
        	param.put("lbsMaterialName",maplist.get(3).get("lbsMaterialName"));
        	param.put("materielName",maplist.get(4).get("materielName"));
        	param.put("materielModel",maplist.get(5).get("materielModel"));
        	param.put("plateNumber",maplist.get(6).get("plateNumber"));
        	param.put("client",maplist.get(7).get("client"));
        	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
        	param.put("consigneeAddress",maplist.get(9).get("transports"));
        	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
        	param.put("saleCompanyName",maplist.get(11).get("saleCompanyName"));
        	param.put("cartWeighType",maplist.get(12).get("cartWeighType"));
        	param.put("distance",maplist.get(13).get("distance"));
        	param.put("customerAlias",maplist.get(14).get("customerAlias"));
        	param.put("transportBalance",maplist.get(15).get("transportBalance"));
        	param.put("GrossWeight",maplist.get(16).get("GrossWeight"));
        	param.put("TareWeight",maplist.get(17).get("TareWeight"));
        	param.put("consignee",maplist.get(18).get("consignee"));
        	param.put("suttle",maplist.get(19).get("suttle"));
        	param.put("consigneePhone",maplist.get(20).get("consigneePhone"));
        	param.put("address",maplist.get(21).get("address"));
        	param.put("outboundList",maplist.get(22).get("outboundList"));
        	param.put("remarks",maplist.get(23).get("remarks"));
        	param.put("facingSlipNum", maplist.get(24).get("facingSlipNum"));
		}else {
			param.put("outTypeMark", 1);
			param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("orderNumber", maplist.get(1).get("orderNumber"));
        	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
        	param.put("purchaseContractId",maplist.get(3).get("purchaseContractId"));
        	param.put("materielName",maplist.get(4).get("materielName"));
        	param.put("materielModel",maplist.get(5).get("materielModel"));
        	param.put("plateNumber",maplist.get(6).get("plateNumber"));
        	param.put("lbsMaterialName",maplist.get(7).get("lbsMaterialName"));
        	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
        	param.put("client",maplist.get(9).get("client"));
        	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
        	param.put("consigneeAddress",maplist.get(11).get("transports"));
        	param.put("suttle",maplist.get(12).get("suttle"));
        	param.put("saleCompanyName",maplist.get(13).get("saleCompanyName"));
        	param.put("customerAlias",maplist.get(14).get("customerAlias"));
        	param.put("distance",maplist.get(15).get("distance"));
        	param.put("consignee",maplist.get(16).get("consignee"));
        	param.put("transportBalance",maplist.get(17).get("transportBalance"));
        	param.put("consigneePhone",maplist.get(18).get("consigneePhone"));
        	param.put("address",maplist.get(19).get("address"));
        	param.put("isTesting",maplist.get(20).get("isTesting"));
        	param.put("remarks",maplist.get(21).get("remarks"));
        	param.put("rkSerialID", maplist.get(22).get("rkSerialID"));
        	param.put("grossWeight", maplist.get(23).get("grossWeight"));
        	param.put("tareWeight", maplist.get(24).get("tareWeight"));
//        	param.put("isEnterFactory",0);
		}
    	outboundService.addExportMeasure(request,param);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：复制调度出库单，outTypeMark=0：调度出库单，outTypeMark=1：未入场出库单 
    // outType=0：正常 ，outType=1：调拨 ，outType=2：退货，outType=3：空发， outType=4：兑换，outType=4：兑换时调拨
    @RequestMapping(value = { "/copyExportMeasure.json" })
    public ResponseEntity<Map<String, Object>> copyExportMeasure(HttpServletRequest request,@RequestParam(value="outType")String outType,@RequestParam(value="productID")String productID,
    		@RequestParam(value="userId")String userId,@RequestParam(value="outTypeMark")String outTypeMark,@RequestParam(value="outboundEntity")String outboundEntity) throws IOException{
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(outboundEntity,HashMap.class);
    	param.put("userId",userId);
    	param.put("productID",productID);
    	param.put("outType",outType);
    	param.put("outTypeMark",outTypeMark);
//    	param.put("isEnterFactory",1);
    	param.put("isTesting",1);
    	if(outTypeMark.equals("0")) {
    		if (outType.equals("0") || outType.equals("1") || outType.equals("4") || outType.equals("5")) {
    			param.put("serialID", maplist.get(0).get("serialId"));
            	param.put("orderNumber", maplist.get(1).get("orderNumber"));
            	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
            	param.put("lbsMaterialName",maplist.get(3).get("lbsMaterialName"));
            	param.put("materielName",maplist.get(4).get("materielName"));
            	param.put("materielModel",maplist.get(5).get("materielModel"));
            	param.put("plateNumber",maplist.get(6).get("plateNumber"));
            	param.put("client",maplist.get(7).get("client"));
            	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
            	param.put("consigneeAddress",maplist.get(9).get("transports"));
            	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
            	param.put("saleCompanyName",maplist.get(11).get("saleCompanyName"));
            	param.put("cartWeighType",maplist.get(12).get("cartWeighType"));
            	param.put("distance",maplist.get(13).get("distance"));
            	param.put("customerAlias",maplist.get(14).get("customerAlias"));
            	param.put("transportBalance",maplist.get(15).get("transportBalance"));
            	param.put("consignee",maplist.get(16).get("consignee"));
            	param.put("consigneePhone",maplist.get(17).get("consigneePhone"));
            	param.put("address",maplist.get(18).get("address"));
            	param.put("remarks",maplist.get(19).get("remarks"));
            	param.put("isDiaobo",maplist.get(20).get("isDiaobo"));
            	param.put("diaoOrderNumber",maplist.get(21).get("diaoOrderNumber"));
            	param.put("allotWeight",maplist.get(22).get("allotWeight"));
            	param.put("diaoOrderDetailedId",maplist.get(23).get("diaoOrderDetailedId"));
            	param.put("diaoContractNumber",maplist.get(24).get("diaoContractNumber"));
            	param.put("diaoConsignee",maplist.get(25).get("diaoConsignee"));
            	param.put("diaoClient",maplist.get(26).get("diaoClient"));
            	param.put("diaoConsigneePhone",maplist.get(27).get("diaoConsigneePhone"));
            	param.put("diaoCustomerAlias",maplist.get(28).get("diaoCustomerAlias"));
            	param.put("diaoAddress",maplist.get(29).get("diaoAddress"));
            	param.put("diaoTransportBalance",maplist.get(30).get("diaoTransportBalance"));
            	param.put("diaoRemarks",maplist.get(31).get("diaoRemarks"));
            	param.put("wczSerialID", maplist.get(32).get("wczSerialID"));
            	param.put("facingSlipNum", maplist.get(33).get("facingSlipNum"));
			}else if(outType.equals("3")) {
    			param.put("serialID", maplist.get(0).get("serialId"));
            	param.put("orderNumber", maplist.get(1).get("orderNumber"));
            	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
            	param.put("lbsMaterialName",maplist.get(3).get("lbsMaterialName"));
            	param.put("materielName",maplist.get(4).get("materielName"));
            	param.put("materielModel",maplist.get(5).get("materielModel"));
            	param.put("plateNumber",maplist.get(6).get("plateNumber"));
            	param.put("client",maplist.get(7).get("client"));
            	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
            	param.put("consigneeAddress",maplist.get(9).get("transports"));
            	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
            	param.put("saleCompanyName",maplist.get(11).get("saleCompanyName"));
            	param.put("cartWeighType",maplist.get(12).get("cartWeighType"));
            	param.put("distance",maplist.get(13).get("distance"));
            	param.put("customerAlias",maplist.get(14).get("customerAlias"));
            	param.put("transportBalance",maplist.get(15).get("transportBalance"));
            	param.put("GrossWeight",maplist.get(16).get("GrossWeight"));
            	param.put("TareWeight",maplist.get(17).get("TareWeight"));
            	param.put("consignee",maplist.get(18).get("consignee"));
            	param.put("suttle",maplist.get(19).get("suttle"));
            	param.put("consigneePhone",maplist.get(20).get("consigneePhone"));
            	param.put("address",maplist.get(21).get("address"));
            	param.put("outboundList",maplist.get(22).get("outboundList"));
            	param.put("remarks",maplist.get(23).get("remarks"));
            	param.put("facingSlipNum", maplist.get(24).get("facingSlipNum"));
    		}
    	}else {
			param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("orderNumber", maplist.get(1).get("orderNumber"));
        	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
        	param.put("purchaseContractId",maplist.get(3).get("purchaseContractId"));
        	param.put("materielName",maplist.get(4).get("materielName"));
        	param.put("materielModel",maplist.get(5).get("materielModel"));
        	param.put("plateNumber",maplist.get(6).get("plateNumber"));
        	param.put("lbsMaterialName",maplist.get(7).get("lbsMaterialName"));
        	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
        	param.put("client",maplist.get(9).get("client"));
        	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
        	param.put("consigneeAddress",maplist.get(11).get("transports"));
        	param.put("suttle",maplist.get(12).get("suttle"));
        	param.put("saleCompanyName",maplist.get(13).get("saleCompanyName"));
        	param.put("customerAlias",maplist.get(14).get("customerAlias"));
        	param.put("distance",maplist.get(15).get("distance"));
        	param.put("consignee",maplist.get(16).get("consignee"));
        	param.put("transportBalance",maplist.get(17).get("transportBalance"));
        	param.put("consigneePhone",maplist.get(18).get("consigneePhone"));
        	param.put("address",maplist.get(19).get("address"));
        	param.put("isTesting",maplist.get(20).get("isTesting"));
        	param.put("remarks",maplist.get(21).get("remarks"));
        	param.put("rkSerialID", maplist.get(22).get("rkSerialID"));
//        	param.put("isEnterFactory",0);
    	}
    	outboundService.addExportMeasure(request,param);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：修改调度出库单，outTypeMark=0：调度出库单，outTypeMark=1：未入场出库单 
    // outType=0：正常 ，outType=1：调拨 ，outType=2：退货，outType=3：空发， outType=4：兑换，outType=4：兑换时调拨
    @RequestMapping(value = { "/upadteExportMeasure.json" })
    public ResponseEntity<Map<String, Object>> upadteExportMeasure(HttpServletRequest request,@RequestParam(value="id")String id,@RequestParam(value="outType")String outType,
    		@RequestParam(value="userId")String userId,@RequestParam(value="productID")String productID,@RequestParam(value="outTypeMark")String outTypeMark,@RequestParam(value="outboundEntity")String outboundEntity) throws IOException{
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	List<HashMap> maplist = JSON.parseArray(outboundEntity,HashMap.class);
    	param.put("id",id);
    	param.put("userId",userId);
    	param.put("productID",productID);
    	param.put("outType",outType);
    	param.put("outTypeMark",outTypeMark);
//    	param.put("isEnterFactory",1);
    	param.put("isTesting",1);
    	if(outTypeMark.equals("0")) {
    		if (outType.equals("0") || outType.equals("1") || outType.equals("4") || outType.equals("5")) {
    			param.put("serialID", maplist.get(0).get("serialId"));
            	param.put("orderNumber", maplist.get(1).get("orderNumber"));
            	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
            	param.put("lbsMaterialName",maplist.get(3).get("lbsMaterialName"));
            	param.put("materielName",maplist.get(4).get("materielName"));
            	param.put("materielModel",maplist.get(5).get("materielModel"));
            	param.put("plateNumber",maplist.get(6).get("plateNumber"));
            	param.put("client",maplist.get(7).get("client"));
            	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
            	param.put("consigneeAddress",maplist.get(9).get("transports"));
            	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
            	param.put("saleCompanyName",maplist.get(11).get("saleCompanyName"));
            	param.put("cartWeighType",maplist.get(12).get("cartWeighType"));
            	param.put("distance",maplist.get(13).get("distance"));
            	param.put("customerAlias",maplist.get(14).get("customerAlias"));
            	param.put("transportBalance",maplist.get(15).get("transportBalance"));
            	param.put("consignee",maplist.get(16).get("consignee"));
            	param.put("consigneePhone",maplist.get(17).get("consigneePhone"));
            	param.put("address",maplist.get(18).get("address"));
            	param.put("remarks",maplist.get(19).get("remarks"));
            	param.put("isDiaobo",maplist.get(20).get("isDiaobo"));
            	param.put("diaoOrderNumber",maplist.get(21).get("diaoOrderNumber"));
            	param.put("allotWeight",maplist.get(22).get("allotWeight"));
            	param.put("diaoOrderDetailedId",maplist.get(23).get("diaoOrderDetailedId"));
            	param.put("diaoContractNumber",maplist.get(24).get("diaoContractNumber"));
            	param.put("diaoConsignee",maplist.get(25).get("diaoConsignee"));
            	param.put("diaoClient",maplist.get(26).get("diaoClient"));
            	param.put("diaoConsigneePhone",maplist.get(27).get("diaoConsigneePhone"));
            	param.put("diaoCustomerAlias",maplist.get(28).get("diaoCustomerAlias"));
            	param.put("diaoAddress",maplist.get(29).get("diaoAddress"));
            	param.put("diaoTransportBalance",maplist.get(30).get("diaoTransportBalance"));
            	param.put("diaoRemarks",maplist.get(31).get("diaoRemarks"));
            	param.put("wczSerialID", maplist.get(32).get("wczSerialID"));
            	param.put("facingSlipNum", maplist.get(33).get("facingSlipNum"));
			}else if(outType.equals("3")) {
				param.put("serialID", maplist.get(0).get("serialId"));
            	param.put("orderNumber", maplist.get(1).get("orderNumber"));
            	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
            	param.put("lbsMaterialName",maplist.get(3).get("lbsMaterialName"));
            	param.put("materielName",maplist.get(4).get("materielName"));
            	param.put("materielModel",maplist.get(5).get("materielModel"));
            	param.put("plateNumber",maplist.get(6).get("plateNumber"));
            	param.put("client",maplist.get(7).get("client"));
            	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
            	param.put("consigneeAddress",maplist.get(9).get("transports"));
            	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
            	param.put("saleCompanyName",maplist.get(11).get("saleCompanyName"));
            	param.put("cartWeighType",maplist.get(12).get("cartWeighType"));
            	param.put("distance",maplist.get(13).get("distance"));
            	param.put("customerAlias",maplist.get(14).get("customerAlias"));
            	param.put("transportBalance",maplist.get(15).get("transportBalance"));
            	param.put("GrossWeight",maplist.get(16).get("GrossWeight"));
            	param.put("TareWeight",maplist.get(17).get("TareWeight"));
            	param.put("consignee",maplist.get(18).get("consignee"));
            	param.put("suttle",maplist.get(19).get("suttle"));
            	param.put("consigneePhone",maplist.get(20).get("consigneePhone"));
            	param.put("address",maplist.get(21).get("address"));
            	param.put("outboundList",maplist.get(22).get("outboundList"));
            	param.put("remarks",maplist.get(23).get("remarks"));
            	param.put("facingSlipNum", maplist.get(24).get("facingSlipNum"));
    		}
    	}else {
			param.put("serialID", maplist.get(0).get("serialId"));
        	param.put("orderNumber", maplist.get(1).get("orderNumber"));
        	param.put("orderDetailedId",maplist.get(2).get("orderDetailedId"));
        	param.put("purchaseContractId",maplist.get(3).get("purchaseContractId"));
        	param.put("materielName",maplist.get(4).get("materielName"));
        	param.put("materielModel",maplist.get(5).get("materielModel"));
        	param.put("plateNumber",maplist.get(6).get("plateNumber"));
        	param.put("lbsMaterialName",maplist.get(7).get("lbsMaterialName"));
        	param.put("deliveryMan",maplist.get(8).get("deliveryMan"));
        	param.put("client",maplist.get(9).get("client"));
        	param.put("deliveryManPhone",maplist.get(10).get("deliveryManPhone"));
        	param.put("consigneeAddress",maplist.get(11).get("transports"));
        	param.put("suttle",maplist.get(12).get("suttle"));
        	param.put("saleCompanyName",maplist.get(13).get("saleCompanyName"));
        	param.put("customerAlias",maplist.get(14).get("customerAlias"));
        	param.put("distance",maplist.get(15).get("distance"));
        	param.put("consignee",maplist.get(16).get("consignee"));
        	param.put("transportBalance",maplist.get(17).get("transportBalance"));
        	param.put("consigneePhone",maplist.get(18).get("consigneePhone"));
        	param.put("address",maplist.get(19).get("address"));
        	param.put("isTesting",maplist.get(20).get("isTesting"));
        	param.put("remarks",maplist.get(21).get("remarks"));
        	param.put("rkSerialID", maplist.get(22).get("rkSerialID"));
//        	param.put("isEnterFactory",0);
    	}
    	outboundService.updateExportMeasure(request,param);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    // 调度管理：作废或删除 ,flag = 0作废 ,flag = 1删除
    @RequestMapping(value = { "/updateValidFlag.json" })
    public ResponseEntity<Map<String, Object>> updateValidFlag(HttpServletRequest request,@RequestParam(value="id")String id,@RequestParam(value="userId")String userId,
    		@RequestParam(value="flag")String flag,@RequestParam(value="cancellationCause")String cancellationCause) throws IOException{
    	// 参数
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("id",id);
    	param.put("userId",userId);
    	param.put("flag",flag);
    	param.put("cancellationCause",cancellationCause);
    	outboundService.updateValidFlag(request,param);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    // 调度管理：根据订单标号的id查询销售订单明细编号
    @RequestMapping(value = { "/getOrderDetailInfo.json" })
    public ResponseEntity<Map<String, Object>> getOrderDetailInfo(@RequestParam(value="id")String id,@RequestParam(value="type")String type) throws IOException{
    	Map<String, Object> map = new HashMap<String, Object>();
    	// 参数
    	map.put("id",id);
    	if(!type.equals("")) {
    		map.put("type",type);	
    	}
    	List<Map<String, Object>> orderDetailInfo = outboundService.getOrderDetailInfo(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("orderDetailInfo", orderDetailInfo);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：根据销售订单明细id物料/型号id
    @RequestMapping(value = { "/getMaterielinfoList.json" })
    public ResponseEntity<Map<String, Object>> getMaterielinfoList(@RequestParam(value="salesOrderId")String salesOrderId) throws IOException{
    	Map<String, Object> map = new HashMap<String, Object>();
    	// 参数
    	map.put("salesOrderId",salesOrderId);
    	List<MaterielinfoEntity> materielinfoEntity = outboundService.getMaterielinfoList(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("materielinfoEntity", materielinfoEntity);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：根据销售订单明细id获取调拨销售订单编号
    @RequestMapping(value = { "/getDiaoOrderNumberList.json" })
    public ResponseEntity<Map<String, Object>> getDiaoOrderNumberList(@RequestParam(value="salesOrderId")String salesOrderId) throws IOException{
    	Map<String, Object> map = new HashMap<String, Object>();
    	// 参数
    	map.put("orderId",salesOrderId);
    	List<Map<String, Object>> diaoMateriel = outboundService.getDiaoMaterielId(map);
    	if(diaoMateriel.get(0).get("IsExchange").equals("0")) {
    		map.put("materielId", diaoMateriel.get(0).get("ExchangeMaterielId"));
    	}else {
    		map.put("materielId", diaoMateriel.get(0).get("MaterielId"));
    	}
    	map.put("orderDetailId", salesOrderId);
    	List<Map<String, Object>> diaoOrderNumber = outboundService.getDiaoOrderNumber(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("diaoOrderNumber", diaoOrderNumber);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：根据销售订单编号id获取销售订单明细编号
    @RequestMapping(value = { "/getDiaoOrderDetail.json" })
    public ResponseEntity<Map<String, Object>> getDiaoOrderDetail(@RequestParam(value="id")String id,@RequestParam(value="materielId")String materielId,@RequestParam(value="orderDetailId")String orderDetailId) throws IOException{
    	Map<String, Object> map = new HashMap<String, Object>();
    	// 参数
    	map.put("id",id);
    	map.put("materielId",materielId);
    	map.put("orderDetailId",orderDetailId);
    	List<Map<String, Object>> diaoOrderDetail = outboundService.getDiaoOrderDetail(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("diaoOrderDetail", diaoOrderDetail);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 调度管理：根据销售订单明细id获取电话号码
    @RequestMapping(value = { "/getConsigneeInfo.json" })
    public ResponseEntity<Map<String, Object>> getConsigneeInfo(@RequestParam(value="consigneeAddress")String consigneeAddress) throws IOException{
    	Map<String, Object> map = new HashMap<String, Object>();
    	// 参数
    	map.put("consigneeAddress",consigneeAddress);
    	List<Map<String, Object>> consigneeInfo = outboundService.getConsigneeInfo(map);
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("consigneeInfo", consigneeInfo);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    /**
	 * 先删除出库单封签号
	 * @return
	 */
	@RequestMapping("/updateFacingSlipNum.json")
	public ResponseEntity<Map<String, Object>> updateFacingSlipNum(HttpServletRequest request,@RequestParam(value="id")String id,@RequestParam(value="filename")String filename) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		int result = outboundService.updateFacingSlipNum(request, map);
		HashMap<String, Object> param = new HashMap<String, Object>();
		if (result > 0) {
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/"+filename;  
			FileUtils.deleteQuietly(new File(path));
			param.put("type", "0");
		}else {
			param.put("type", "1");
		}
		return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
	}
}
